package org.lu.config;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableAuthorizationServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	// TODO enable path for swagger, currently stuck on bypassing oauth2
	// private static final String[] NON_SECURED_URLS = {
	// "/swagger-resources/**",
	// "/v2/api-docs", "/webjars/**", "/swagger-ui.html" };

	//
	// @Override
	// public void configure(HttpSecurity http) throws Exception {
	// http.authorizeRequests().antMatchers(NON_SECURED_URLS).permitAll();
	// }
}
