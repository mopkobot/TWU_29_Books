package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private List<Book> foundBooks;
    public List<Book> findBooks(){
        return foundBooks;
    }
}
