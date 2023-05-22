package com.smhrd.projectweb.entity.sql;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @TableName device_log
 */
@Data
@EqualsAndHashCode
@ToString
public class DeviceLog implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private Long deviceId;

    /**
     * 
     */
    private Double temperature;

    /**
     * 
     */
    private Double relativeHumidity;

    /**
     * 
     */
    private Double soilHumidity;

    /**
     * 
     */
    private Long imageId;

    /**
     * 
     */
    private LocalDateTime dateCreated;
}