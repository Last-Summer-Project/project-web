package com.smhrd.projectweb.entity.sql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonIgnore
    private String loginId;

    /**
     *
     */
    @JsonIgnore
    private String password;

    /**
     *
     */
    private LocalDateTime dateCreated;

    /**
     *
     */
    private LocalDateTime lastEdited;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(final String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getLoginId() {
        return loginId;
    }

    @JsonProperty
    public void setLoginId(final String loginId) {
        this.loginId = loginId;
    }
}