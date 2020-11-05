package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;

import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		
		String senhaEnconder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEnconder);
		
		return repository.save(usuario);
	}
	
	public Optional<UserLogin> Logar(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario>usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) 
		{
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) // compara as senhas para ver se são iguais, se forem retorna true 
			{
				String auth = user.get().getUsuario() + ":"+ user.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));// determina o formato do byte
				String authHeader = "Basic " + new String(encodeAuth); //  Gera um token para acessar as postangens
				
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				
				return user;
			}
		}
		return null;
	}
}
