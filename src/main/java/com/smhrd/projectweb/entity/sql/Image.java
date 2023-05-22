package com.smhrd.projectweb.entity.sql;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @TableName image
 */
@Data
@EqualsAndHashCode
@ToString
public class Image implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private LocalDate dateCreated;
}