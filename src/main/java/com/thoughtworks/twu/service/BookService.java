package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.persistence.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Job: Understands the interface of ORM
@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    public BookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public Book getBookByTitle(String title) {
        return bookMapper.getBookByTitle(title);
    }

    public void insertBook(Book book) {
        bookMapper.insertBook(book);
    }
}
