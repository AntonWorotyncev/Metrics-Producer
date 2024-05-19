package com.openschoolt1.metricsproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class MetricsProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricsProducerApplication.class, args);
	}

}
