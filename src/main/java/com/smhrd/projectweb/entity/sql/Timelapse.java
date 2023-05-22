package com.smhrd.projectweb.entity.sql;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @TableName timelapse
 */
@Data
@EqualsAndHashCode
@ToString
public class Timelapse implements Serializable {
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
    private LocalDateTime startDate;

    /**
     * 
     */
    private LocalDateTime endDate;

    /**
     * 
     */
    private Status status;

    /**
     * 
     */
    private String result;

    /**
     * 
     */
    private LocalDateTime dateCreated;

    /**
     * 
     */
    private LocalDateTime lastEdited;
}