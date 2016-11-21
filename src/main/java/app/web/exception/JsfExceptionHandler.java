package app.web.exception;

import app.common.exception.InvalidException;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;

@Slf4j
public class JsfExceptionHandler extends ExceptionHandlerWrapper {
    private ExceptionHandler handler;

    public JsfExceptionHandler(ExceptionHandler handler) {
        this.handler = handler;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.handler;
    }

    @Override
    public void handle() {
        Iterator<ExceptionQueuedEvent> iterator = this.getUnhandledExceptionQueuedEvents().iterator();

        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = iterator.next();
            ExceptionQueuedEventContext eventContext = event.getContext();
            Throwable exception = eventContext.getException();
            Throwable rootCause = this.getRootCause(exception);

            if (rootCause instanceof InvalidException) {
                String message = rootCause.getMessage();
                log.warn(message);
                FacesContext facesContext = eventContext.getContext();
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
                facesContext.getExternalContext().getFlash().setKeepMessages(true);
                iterator.remove();
            }
        }

        this.handler.handle();
    }

    @Override
    public Throwable getRootCause(Throwable th) {
        Throwable cause = th.getCause();
        if (cause == null) {
            return th;
        } else {
            return this.getRootCause(cause);
        }
    }
}
