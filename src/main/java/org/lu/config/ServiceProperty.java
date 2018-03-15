package org.lu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "app")
public class ServiceProperty {

	private String apiKey;

	private List<Oauth2Clients> oauth2Clients = new ArrayList<>();

	public static class Oauth2Clients {

		private String id;

		private String[] resourceIds;

		private String secret;

		private boolean autoApprove;

		private String[] authorizedGrantTypes;

		private String[] scopes;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String[] getResourceIds() {
			return resourceIds;
		}

		public void setResourceIds(String[] resourceIds) {
			this.resourceIds = resourceIds;
		}

		public String getSecret() {
			return secret;
		}

		public void setSecret(String secret) {
			this.secret = secret;
		}

		public boolean isAutoApprove() {
			return autoApprove;
		}

		public void setAutoApprove(boolean autoApprove) {
			this.autoApprove = autoApprove;
		}

		public String[] getAuthorizedGrantTypes() {
			return authorizedGrantTypes;
		}

		public void setAuthorizedGrantTypes(String[] authorizedGrantTypes) {
			this.authorizedGrantTypes = authorizedGrantTypes;
		}

		public String[] getScopes() {
			return scopes;
		}

		public void setScopes(String[] scopes) {
			this.scopes = scopes;
		}

	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public List<Oauth2Clients> getOauth2Clients() {
		return oauth2Clients;
	}

	public void setOauth2Clients(List<Oauth2Clients> oauth2Clients) {
		this.oauth2Clients = oauth2Clients;
	}
}
