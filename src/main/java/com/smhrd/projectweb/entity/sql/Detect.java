package com.smhrd.projectweb.entity.sql;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
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