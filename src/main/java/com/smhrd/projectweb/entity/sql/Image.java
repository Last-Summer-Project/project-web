package com.smhrd.projectweb.entity.sql;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/**
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