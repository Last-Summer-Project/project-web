package com.smhrd.projectweb.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
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
    private Long imageId;

    /**
     *
     */
    private OffsetDateTime dateCreated;


    private Detect detect;
    private Image image;


}