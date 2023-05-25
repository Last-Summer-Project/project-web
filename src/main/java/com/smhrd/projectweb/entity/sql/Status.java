package com.smhrd.projectweb.entity.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;

@AllArgsConstructor
@ToString
public enum Status implements Serializable {
    NOT_STARTED, IN_PROGRESS, DONE;

}
