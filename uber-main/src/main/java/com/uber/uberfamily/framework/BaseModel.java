package com.uber.uberfamily.framework;

import java.io.Serializable;

/**
 * @Project uber
 * @Package com.uber.uberfamily.model
 * @Description //基础MODEL
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class BaseModel implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
