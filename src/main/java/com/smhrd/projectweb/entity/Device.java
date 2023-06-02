package com.smhrd.projectweb.entity;

import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * @TableName device
 */
@Data
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
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
    private OffsetDateTime dateCreated;

    /**
     *
     */
    private OffsetDateTime lastEdited;
}