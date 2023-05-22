package com.smhrd.projectweb.entity.sql;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * @TableName device
 */
@Data
@EqualsAndHashCode
@ToString
public class Device implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String loginId;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private LocalDateTime dateCreated;

    /**
     * 
     */
    private LocalDateTime lastEdited;
}