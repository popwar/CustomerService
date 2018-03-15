package org.lu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends
		AuthorizationServerConfigurerAdapter {

	@Autowired
	private ServiceProperty appProps;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {
		endpoints.tokenStore(tokenStore()).accessTokenConverter(
				accessTokenConverter());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security)
			throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients)
			throws Exception {
		final InMemoryClientDetailsServiceBuilder clientDetailsBuilder = clients
				.inMemory();

		appProps.getOauth2Clients().forEach(
				oauth2Client -> {
					try {
						clientDetailsBuilder
								.withClient(oauth2Client.getId())
								.resourceIds(oauth2Client.getResourceIds())
								.secret(oauth2Client.getSecret())
								.autoApprove(oauth2Client.isAutoApprove())
								.authorizedGrantTypes(
										oauth2Client.getAuthorizedGrantTypes())
								.scopes(oauth2Client.getScopes());

					} catch (Exception e) {
						throw new IllegalArgumentException(
								"Error building the clients ", e);
					}
				});
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		// TODO improve later with external key
		converter.setSigningKey(appProps.getApiKey());
		return converter;
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}

}
