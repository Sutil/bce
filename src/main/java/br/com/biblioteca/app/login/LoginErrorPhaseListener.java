package br.com.biblioteca.app.login;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

public class LoginErrorPhaseListener implements PhaseListener {
	private static final long serialVersionUID = 1L;

	@Override
	public void beforePhase(final PhaseEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Exception e = (Exception) facesContext.getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);
		if (e instanceof BadCredentialsException) {
			facesContext.getExternalContext().getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de autenticação","Usuário/senha inválidos"));
		}
	}

	@Override
	public void afterPhase(final PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
}
