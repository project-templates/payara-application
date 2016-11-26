package app.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class LoggerProvider {
    
    @Produces
    public Logger getLogger(InjectionPoint ip) {
        Class<?> declaringClass = ip.getMember().getDeclaringClass();
        return LoggerFactory.getLogger(declaringClass);
    }
}
