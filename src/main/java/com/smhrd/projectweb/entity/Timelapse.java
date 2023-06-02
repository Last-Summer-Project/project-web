package com.smhrd.projectweb.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
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
    private OffsetDateTime startDate;

    /**
     *
     */
    private OffsetDateTime endDate;

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
    private OffsetDateTime dateCreated;

    /**
     *
     */
    private OffsetDateTime lastEdited;
}