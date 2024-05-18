package com.openschoolt1.metricsproducer.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Measurement {
    private String statistic;
    private Long value;
}