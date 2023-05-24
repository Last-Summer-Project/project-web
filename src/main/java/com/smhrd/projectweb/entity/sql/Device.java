package com.smhrd.projectweb.entity.sql;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName device
 */
@Data
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
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