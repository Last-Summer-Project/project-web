package com.smhrd.projectweb.entity.sql;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@ToString
public enum Status implements Serializable {
    NOT_STARTED, IN_PROGRESS, DONE

}
