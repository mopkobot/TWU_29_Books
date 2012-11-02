package com.thoughtworks.twu.persistence.googleapi;

import com.google.api.client.googleapis.services.GoogleKeyInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
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
        JsonHttpRequestInitializer credential = new GoogleKeyInitializer("AIzaSyAMR4F-UvVtgGByBiSa6vwHRpYitMy2jLY");
        this.books = new Books.Builder(new NetHttpTransport(), new JacksonFactory(), null)
                .setApplicationName("Google-BooksSample/1.0")
                .setJsonHttpRequestInitializer(credential)
                .build();
    }

    public String buildQuery(String searchTerm, String searchType) {
        if (searchType.equals("isbn")) {
            return "isbn:" + searchTerm;
        }
        return "in" + searchType + ":" + searchTerm;
    }


    public Volumes performSearch(String searchTerm, String searchType) throws IOException {
        return books.volumes().list(buildQuery(searchTerm, searchType)).setMaxResults((long) 20).execute();
    }
}