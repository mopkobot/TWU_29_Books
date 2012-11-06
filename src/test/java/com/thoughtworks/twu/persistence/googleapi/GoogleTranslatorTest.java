package com.thoughtworks.twu.persistence.googleapi;

import com.thoughtworks.twu.domain.Book;
import org.junit.Before;
import org.junit.Ignore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GoogleTranslatorTest {

    private GoogleTranslator translator;

    @Before
    public void setUp() {
        translator = new GoogleTranslator();
    }

    //TODO: put test in functional testing
    @Ignore
    public void shouldTurnVolumesToBooks() throws IOException {
        List<Book> expected = new ArrayList<Book>();
        expected.add(new Book("J. K. Rowling", "The Casual Vacancy",
                "http://bks1.books.google.com/books?id=eLzKugAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api",
                "A big novel about a small town... When Barry Fairbrother dies in his early forties, the town of Pagford " +
                        "is left in shock. Pagford is, seemingly, an English idyll, with a cobbled market square and an ancient " +
                        "abbey, but what lies behind the pretty fa\u00E7ade is a town at war. Rich at war with poor, teenagers at war " +
                        "with their parents, wives at war with their husbands, teachers at war with their pupils...Pagford is not " +
                        "what it first seems. And the empty seat left by Barry on the parish council soon becomes the catalyst for " +
                        "the biggest war the town has yet seen. Who will triumph in an election fraught with passion, duplicity " +
                        "and unexpected revelations? A big novel about a small town, The Casual Vacancyis J.K. Rowling's first " +
                        "novel for adults. It is the work of a storyteller like no other.",
                "0316228532", "9780316228534"));
        GoogleSearchClient googleSearchClient = new GoogleSearchClient();
        List<Book> result = translator.translate(googleSearchClient.performSearch("9780316228534", "ISBN"));
        assertThat(result, is(expected));
    }
}
