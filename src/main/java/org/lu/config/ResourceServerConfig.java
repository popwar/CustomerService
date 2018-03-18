package org.lu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String[] NON_SECURED_URLS = { "/swagger-resources/**",
			"/v2/api-docs", "/webjars/**", "/swagger-ui.html" };

	private static final String[] SECURED_URLS = { "/customerProfiles/**" };

	@Autowired
	private ServiceProperty appProps;

	@Override
	public void configure(final ResourceServerSecurityConfigurer config) {
		config.resourceId(appProps.getResourceId());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
				.requestMatchers().antMatchers(SECURED_URLS).requestMatchers()
				.antMatchers(NON_SECURED_URLS).and().authorizeRequests()
				.antMatchers(SECURED_URLS)
				.access("#oauth2.hasScope('authenticate')").and()
				.authorizeRequests().antMatchers(NON_SECURED_URLS).permitAll();
	}
}
