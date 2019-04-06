package com.thoughtbend.securitysysapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${app.ldap.endpointUrl}")
	private String ldapEndpointUrl;
	
	@Value("${app.ldap.baseDn}")
	private String ldapBaseDn;
	
	@Value("${app.ldap.managerUser}")
	private String managerUser;
	
	@Value("${app.ldap.managerPassword}")
	private String managerPassword;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/api/login").fullyAuthenticated()
				.and()
					.httpBasic();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		/* TODO - for now it is caching the LDAP authenticaiton.  Would be nice 
		 * to figure out how to get this into a distributed session manager */ 
		
		final String url = String.format("%1$s/%2$s", this.ldapEndpointUrl, this.ldapBaseDn);
		
		auth
			.ldapAuthentication()
				.userDnPatterns("cn={0}")
				/*.groupSearchBase("ou=groups")*/
				.contextSource()
					.url(url)
					.and()
						.contextSource()
						.managerDn(this.managerUser)
						.managerPassword(this.managerPassword)
				.and()
				.passwordCompare()
					//.passwordEncoder(new LdapShaPasswordEncoder())
					//.passwordEncoder(new SCryptPasswordEncoder())
					.passwordAttribute("userPassword");
	}

}
