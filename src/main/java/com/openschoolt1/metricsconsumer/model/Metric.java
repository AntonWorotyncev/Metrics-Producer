package com.openschoolt1.metricsconsumer.model;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@NoArgsConstructor
public class Metric {
    /*
    Идентификатор метрики
    */
    private Long id;
    /*
    Временная метка
    */
    private Long timestamp;
    /*
    Использование памяти JVM
    */
    private Long jvmMemoryUsed;
    /*
     Максимальная память JVM
     */
    private Long jvmMemoryMax;
}
