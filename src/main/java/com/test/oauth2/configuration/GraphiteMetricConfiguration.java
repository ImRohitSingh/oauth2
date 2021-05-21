package com.test.oauth2.configuration;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class GraphiteMetricConfiguration {
	
	@Bean
	public MeterRegistryCustomizer<MeterRegistry> commonTags() {
	   return r -> r.config().commonTags("application", "oauth2");
	}

}
