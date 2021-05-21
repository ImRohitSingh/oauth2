package com.test.oauth2.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class GraphiteMetricConfiguration {

	@Value("${management.metrics.export.graphite.tags-as-prefix}")
	private String commonTag;

	@Value("${oauth2.graphitedb.metric-name}")
	private String graphiteDbMetricName;

	@Bean
	public MeterRegistryCustomizer<MeterRegistry> commonTags() {
		return r -> r.config().commonTags(commonTag, graphiteDbMetricName);
	}

}
