package com.elasticpath.rest.client.config;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import com.elasticpath.rest.client.CortexClient;
import com.elasticpath.rest.client.DefaultCortexClient;
import com.elasticpath.rest.client.zoom.ZoomUrlFactory;
import com.elasticpath.rest.client.zoom.ZoomUrlFactoryImpl;

/**
 * Guice configuration class.
 */
public class GuiceConfig extends AbstractModule {

	@Override
	protected void configure() {
		bind(CortexClient.class).to(DefaultCortexClient.class);
		bind(ZoomUrlFactory.class).to(ZoomUrlFactoryImpl.class);
	}

	@Provides
	public ObjectMapper provideObjectMapper() {
		return new ObjectMapper()
				.disable(FAIL_ON_UNKNOWN_PROPERTIES);
	}
}
