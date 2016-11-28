package app.infrastructure.mail;

import lombok.Builder;
import lombok.Singular;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@Builder
@Slf4j
public class Mail {
    private final static String HOST;
    private final static int PORT;
    private final static boolean AUTHENTICATION;
    private final static String USER;
    private final static String PASSWORD;
    private final static String FROM;
    
    @Singular("to")
    private List<String> toList;
    @Singular("cc")
    private List<String> ccList;
    @Singular("bcc")
    private List<String> bccList;
    private String subject;
    private String body;
    
    static {
        Properties prop = new Properties();
        try (InputStream in = Mail.class.getResourceAsStream("/mail.properties")) {
            prop.load(in);

            FROM = prop.getProperty("from");
            HOST = prop.getProperty("host");
            PORT = Integer.parseInt(prop.getProperty("port"));

            if ("true".equalsIgnoreCase(prop.getProperty("authentication"))) {
                AUTHENTICATION = true;

                String envUserName = prop.getProperty("env.username");
                if (envUserName == null) {
                    throw new IllegalStateException("mail.properties に env.username が設定されていません。");
                }

                USER = System.getenv(envUserName);
                if (USER == null) {
                    throw new IllegalStateException("環境変数 " + envUserName + " に、メールサーバーにログインするためのユーザー名が設定されていません。");
                }

                String envPassword = prop.getProperty("env.password");
                if (envPassword == null) {
                    throw new IllegalStateException("mail.properties に env.password が設定されていません。");
                }

                PASSWORD = System.getenv(envPassword);
                if (PASSWORD == null) {
                    throw new IllegalStateException("環境変数 " + envPassword + " に、メールサーバーにログインするためのパスワードが設定されていません。");
                }
            } else {
                AUTHENTICATION = false;
                USER = null;
                PASSWORD = null;
            }
        } catch (IOException e) {
            throw new MailException("メール設定ファイルの読み込みに失敗しました", e);
        }
    }
    
    public void send() {
        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName(HOST);
            email.setSmtpPort(PORT);
            email.setFrom(FROM);
            
            for (String to : this.toList) {
                email.addTo(to);
            }
            for (String cc : this.ccList) {
                email.addCc(cc);
            }
            for (String bcc : this.bccList) {
                email.addBcc(bcc);
            }
            email.setSubject(this.subject);
            email.setMsg(this.body);
            
            if (AUTHENTICATION) {
                email.setStartTLSEnabled(true);
                email.setAuthentication(USER, PASSWORD);
            }
            
            log.info("send mail... (subject={}, to={})", this.subject, this.toList);
            email.send();
        } catch (EmailException e) {
            throw new MailException("メールの送信に失敗しました", e);
        }
    }
}
