package com.openschoolt1.metricsproducer.controllers;

import com.openschoolt1.metricsproducer.service.MetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/metrics-producer")
@RequiredArgsConstructor
@Tag(name = "Metrics Producer", description = "Микросервис отслеживает" +
        "и собирает метрики работы данного приложения " +
        "")
public class MetricsProducerController {

    private final MetricsService metricsService;

    @Operation(summary = "Отправка метрик работы приложения",
            description = "Отправка метрик работы приложения в формате JSON." +
                    " метрики включают различную информацию о производительности, а также " +
                    "сведения об используемых ресурсах\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешный запрос"),
    })
    @PostMapping("/metrics")
    public void sendMetrics() {
        metricsService.publishingTopicMetrics();
    }
}
