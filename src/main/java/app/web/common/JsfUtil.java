package app.web.common;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JsfUtil {
    
    public static void addMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
        context.getExternalContext().getFlash().setKeepMessages(true);
    }
    
    private JsfUtil() {}
}
