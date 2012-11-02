package com.thoughtworks.twu.persistence.googleapi;

import com.google.api.client.googleapis.services.GoogleKeyInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;

public class BooksFactory {

    public static Books newBooks(String googleApiKey) {
        return new Books.Builder(new NetHttpTransport(), new JacksonFactory(), null)
                .setApplicationName("Google-BooksSample/1.0").setJsonHttpRequestInitializer(new GoogleKeyInitializer(googleApiKey))
                .build();
    }
}
