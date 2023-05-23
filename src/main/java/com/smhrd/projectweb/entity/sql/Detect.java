package com.smhrd.projectweb.entity.sql;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @TableName detect
 */
@Data
@EqualsAndHashCode
@ToString
public class Detect implements Serializable {
    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Status status;

    /**
     *
     */
    private String result;
}