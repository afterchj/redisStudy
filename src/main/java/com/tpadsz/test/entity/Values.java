package com.tpadsz.test.entity;

import java.io.Serializable;

/**
 * Created by hongjian.chen on 2017/9/28.
 */
public class Values implements Serializable{
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
