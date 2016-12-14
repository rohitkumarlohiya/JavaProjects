package com.hascode.tutorial;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@RestController
@EnableResourceServer
public class Oauth2AuthorizationServerApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthorizationServerApplication.class, args);
	}

	@Configuration
	@EnableAuthorizationServer
	protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {
		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authenticationManager);
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory().withClient("foo").secret("foosecret")
					.authorizedGrantTypes("authorization_code", "refresh_token", "password").scopes("read", "trust");//.scopes("openid");
		}
	}

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
	protected static class OAuth2Config2 extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			
			http
	           .requestMatchers().antMatchers("/hascode/user","/hascode/**").and()
	           .authorizeRequests()
	           .anyRequest().permitAll();
			
			//http.authorizeRequests().antMatchers("/hascode/**", "/signup", "/hascode").permitAll();
			
			// TODO Auto-generated method stub
			//http.requestMatchers().antMatchers("/hascode/**").and().authorizeRequests().anyRequest().permitAll();
			//http.requestMatchers().antMatchers("/hascode/**").and().authorizeRequests().anyRequest().permitAll();
			//.access("#oauth2.hasScope('read')");
			//super.configure(http);
		}

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			// TODO Auto-generated method stub
			//resources.
			super.configure(resources);
		}
		
	}

}
