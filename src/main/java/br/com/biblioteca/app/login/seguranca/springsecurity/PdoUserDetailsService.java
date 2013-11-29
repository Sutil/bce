package br.com.biblioteca.app.login.seguranca.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.biblioteca.app.login.seguranca.QUsuario;
import br.com.biblioteca.app.login.seguranca.Usuario;
import br.com.biblioteca.app.login.seguranca.repository.UsuarioRepository;

@Service
public class PdoUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findOne(QUsuario.usuario.login.equalsIgnoreCase(username));
		return user;
	}
}
