package org.generation.blogPessoal.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override //
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll() // libera alguns caminho para que o client tenha acesso sem ter uma chave token
		.antMatchers("/usuarios/cadastrar").permitAll() // libera alguns caminho para que o client tenha acesso sem ter uma chave token
		.anyRequest().authenticated() // todas as requisições deveram ser autenticadas
		.and().httpBasic() // utilizando o padrao basic para gerar a chave token
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Indica qual a sessão que sera utilizada e que ela não guardara sessão
		.and().cors()
		.and().csrf().disable(); // desabilita o csrf para utilizar todas as configurações padrões
	}

}
