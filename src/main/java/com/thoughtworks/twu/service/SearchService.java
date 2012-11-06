package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.persistence.GoogleBookSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SearchService {
    private GoogleBookSource googleBookSource;

    @Autowired
    public SearchService(GoogleBookSource googleBookSource) {
        this.googleBookSource = googleBookSource;
    }

    public List<Book> findBooks(String searchValue, String searchType) throws IOException {
        return googleBookSource.search(searchValue, searchType);
    }
}
