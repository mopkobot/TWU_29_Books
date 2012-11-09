package com.thoughtworks.twu.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class User implements Serializable {
    private String name;
    private String casname;

    private List<Book> wantToReadBooks;

    public User(){

    }

    public User(String casname, String name) {
        this.name = name;
        this.casname = casname;

        this.wantToReadBooks = new ArrayList<Book>();
    }

    public String getName() {
        return name;
    }

    public String getCasname() {
        return casname;
    }

    public List<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }
}
