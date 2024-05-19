package com.openschoolt1.metricsproducer.service.impl;

import com.openschoolt1.metricsconsumer.model.Metric;
import com.openschoolt1.metricsproducer.response.*;
import com.openschoolt1.metricsproducer.service.MetricsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {

    private final WebClient webClient;
    private final KafkaTemplate<String, Metric> kafkaTemplate;

    @Override
    public void publishingTopicMetrics() {
        Mono<Long> memoryMax = webClient.get().uri("metrics/jvm.memory.max")
                .retrieve()
                .bodyToMono(MetricResponse.class)
                .map(response -> response.getMeasurements().stream()
                        .filter(measurement -> "VALUE".equals(measurement.getStatistic())).findFirst()
                        .map(Measurement::getValue)
                        .orElseThrow(() -> new RuntimeException("system.cpu.usage value not found")));

        Mono<Long> memoryUsed = webClient.get().uri("metrics/jvm.memory.used")
                .retrieve()
                .bodyToMono(MetricResponse.class)
                .map(response -> response.getMeasurements().stream()
                        .filter(measurement -> "VALUE".equals(measurement.getStatistic()))
                        .findFirst()
                        .map(Measurement::getValue)
                        .orElseThrow(() -> new RuntimeException("jvm.memory.used value not found")));

        Mono.zip(memoryMax, memoryUsed, (memMax, memUsed) -> {
            Metric metrics = new Metric();
            metrics.setTimestamp(System.currentTimeMillis());
            metrics.setJvmMemoryMax(memMax);
            metrics.setJvmMemoryUsed(memUsed);
            return metrics;

        }).subscribe(metrics -> kafkaTemplate.send("metrics-topic", metrics));
    }
}
