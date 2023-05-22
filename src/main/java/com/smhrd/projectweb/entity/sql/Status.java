package com.smhrd.projectweb.entity.sql;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
public enum Status implements Serializable {
    NOT_STARTED("not_started"),
    IN_PROGRESS("in_progress"),
    DONE("done");

    @Getter private final String code;
}
