package com.smhrd.projectweb.entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@ToString
public enum Status implements Serializable {
    NOT_STARTED, IN_PROGRESS, DONE

}
