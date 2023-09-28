package cl.awakelab.bootcamp.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DataBaseWebSecurity {
	
	@Bean
	UserDetailsManager users(DataSource dataSource){
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		//Consulta para obtener usuarios desde la bbdd personalizada
		users.setUsersByUsernameQuery("select username, password, estatus from Usuarios u where username=?");
		//Crear autorizaciones... es necesario agregar el espacio al final al ordenar la consulta como parrafo
		users.setAuthoritiesByUsernameQuery("select u.username, p.perfil from UsuarioPerfil up "+
				 "inner join Usuarios u on u.id = up.idUsuario "+
				"inner join Perfiles p on p.id = up.idPerfil "+
				 "where u.username=?");
		
		return users;
	}
	
	@Bean
	 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		  http
		  	.authorizeRequests()
		  		.antMatchers("/css/**", "/images/**", "/js/**").permitAll()
		  		.antMatchers("/").permitAll()
		  		.anyRequest().authenticated()
		  		.and()
	      .formLogin()
	      		.loginPage("/login")
	      		.defaultSuccessUrl("/students")
	      		.permitAll()		
	      		.and()
	      	.logout()
	      		.logoutSuccessUrl("/home")
	      		.permitAll()
	      		.and()
	      	.csrf().disable();	
		return http.build();
	}	
	

  
}