package com.openschoolt1.metricsconsumer.model;

import lombok.*;

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
     максимальная память JVM
     */
    private Long jvmMemoryMax;
}
