package com.smhrd.projectweb.entity.sql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@ToString
public enum Status implements Serializable {
    NOT_STARTED("not_started"), IN_PROGRESS("in_progress"), DONE("done");

    @Getter
    private final String code;
}
