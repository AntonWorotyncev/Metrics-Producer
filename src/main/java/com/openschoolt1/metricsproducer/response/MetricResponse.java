package com.openschoolt1.metricsproducer.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MetricResponse {
    private String name;
    private String description;
    private String baseUnit;
    private List<Measurement> measurements;
}