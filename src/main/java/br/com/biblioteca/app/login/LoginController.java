package br.com.biblioteca.app.login;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

@Component
public class LoginController {

	public void login() throws IOException, ServletException {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		ServletRequest request = (ServletRequest) context.getRequest();
		ServletResponse response = (ServletResponse) context.getResponse();
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		FacesContext.getCurrentInstance().responseComplete();
	}

}
