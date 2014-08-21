package com.elasticpath.rest.sdk;

import static com.elasticpath.rest.sdk.Debug.prettyTrace;
import static javax.ws.rs.client.ClientBuilder.newClient;
import static javax.ws.rs.client.Entity.form;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.UriBuilder.fromPath;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.google.common.base.Joiner;

import com.elasticpath.rest.sdk.model.Auth;
import com.elasticpath.rest.sdk.model.AuthToken;
import com.elasticpath.rest.sdk.model.Link;
import com.elasticpath.rest.sdk.model.Linkable;

public class ClientSdk {

	public static final MediaType TYPE = APPLICATION_JSON_TYPE;

	public void zoom(Linkable root,
					 Iterable<String> zooms,
					 AuthToken authToken) {

		UriBuilder target = fromPath(root.self.href)
				.queryParam("zoom", Joiner.on(":")
						.join(zooms));

		String zoom = get(target, authToken)
				.readEntity(String.class);

		prettyTrace(zoom);
	}

	public Linkable getLinkable(UriBuilder target,
								AuthToken authToken) {
		return get(target, authToken)
				.readEntity(Linkable.class);
	}

	public Linkable getLinkable(Link link,
								AuthToken authToken) {

		UriBuilder target = fromPath(link.href);

		return getLinkable(target, authToken);
	}

	private Response get(UriBuilder target,
						 AuthToken authToken) {
		return newClient()
				.register(JacksonProvider.class)
				.target(target)
				.request(TYPE)
				.header(authToken.getHeaderName(), authToken.getHeaderValue())
				.get();
	}

	public AuthToken auth(UriBuilder target,
						  Form auth) {

		Response response = newClient()
				.register(JacksonProvider.class)
				.target(target)
				.request(TYPE)
				.post(form(auth));

		String accessToken = response.readEntity(Auth.class)
				.getAccessToken();

		return new AuthToken(accessToken);
	}
}