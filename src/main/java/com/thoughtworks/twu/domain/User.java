package com.thoughtworks.twu.domain;

import java.io.Serializable;


public class User implements Serializable {
    private String name;
    private String casname;

    public User(){

    }

    public User(String casname, String name) {
        this.name = name;
        this.casname = casname;
    }

    public String getName() {
        return name;
    }

    public String getCasname() {
        return casname;
    }
}
