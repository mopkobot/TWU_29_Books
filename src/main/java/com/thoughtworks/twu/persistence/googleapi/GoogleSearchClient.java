package com.thoughtworks.twu.persistence.googleapi;

import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volumes;
import org.springframework.stereotype.Component;

import java.io.IOException;

//Understands how to connect with GoogleAPI
@Component
public class GoogleSearchClient {

    private Books books;

    public GoogleSearchClient(Books books) {
        this.books = books;
    }

    public GoogleSearchClient() {
        this.books = BooksFactory.newBooks();
    }

    public String buildQuery(String searchValue, String searchType) {
        if (searchType.toLowerCase().equals("isbn")) {
            return "isbn:" + searchValue;
        }
        return "in" + searchType.toLowerCase() + ":" + searchValue;
    }

    //TODO: move MaxResults to a config file, easily accessible by client
    public Volumes performSearch(String searchTerm, String searchType) throws IOException {
        return books.volumes().list(buildQuery(searchTerm,
                searchType)).setMaxResults((long) 20).setOrderBy("RELEVANCE")
                .execute();
    }
}