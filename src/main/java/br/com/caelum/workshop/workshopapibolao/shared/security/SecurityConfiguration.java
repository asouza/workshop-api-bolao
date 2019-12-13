package br.com.caelum.workshop.workshopapibolao.shared.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private TokenAuthenticationService tokenAuthenticationService;
	private UserDetailsService userDetailsService;
	

	public SecurityConfiguration(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
		this.tokenAuthenticationService = new TokenAuthenticationService(userDetailsService);
	}

	/*
	 * login alberto => 
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		
		StatelessLoginFilter statelessLoginFilter = new StatelessLoginFilter("/secret/api/login", 
				tokenAuthenticationService, authenticationManager());
		
		StatelessAuthenticationFilter statelessAuthenticationFilter = 
				new StatelessAuthenticationFilter(tokenAuthenticationService);
		
		http.authorizeRequests()
		.antMatchers("/api/public/**").permitAll()
		.antMatchers("/magic/**").permitAll()
		.antMatchers(HttpMethod.POST, "/api/login").permitAll()
		.antMatchers("/swagger-ui.html").permitAll()
			.and()
			.cors()
			.and()
			.csrf().disable()
		
			.addFilterBefore(statelessLoginFilter, UsernamePasswordAuthenticationFilter.class)		
			.addFilterBefore(statelessAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests().anyRequest().authenticated();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public TokenAuthenticationService tokenAuthenticationService() {
        return tokenAuthenticationService;
    }    
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html",  "/v2/api-docs", 
                "/webjars/**", "/configuration/**", 
                "/swagger-resources/**","/assets/**");
    }
    
    public static void main(String[] args) {
    	//apenas para gerar um quando for necessário
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}
    
}
