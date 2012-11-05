package com.thoughtworks.twu.persistence.googleapi;

import com.google.api.services.books.model.Volumes;
import com.thoughtworks.twu.domain.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoogleTranslator {
    public List<Book> translate(Volumes volumes) {
        return null;
    }
}
