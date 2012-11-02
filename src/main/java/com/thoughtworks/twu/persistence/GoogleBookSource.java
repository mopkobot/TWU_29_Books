package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.persistence.googleapi.GoogleSearchClient;
import com.thoughtworks.twu.persistence.googleapi.GoogleTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class GoogleBookSource {
    private final GoogleSearchClient googleSearchClient;
    private final GoogleTranslator googleTranslator;

    @Autowired
    public GoogleBookSource(GoogleSearchClient googleSearchClient, GoogleTranslator googleTranslator) {
        this.googleSearchClient = googleSearchClient;
        this.googleTranslator = googleTranslator;
    }

    public List<Book> search(String value, String typeOfSearch) throws IOException {
        return googleTranslator.translate(googleSearchClient.performSearch(value, typeOfSearch) );
    }
}
