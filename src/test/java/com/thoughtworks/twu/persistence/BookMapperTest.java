package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


//Ensure that BookMapper could interact with DB as expected
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class BookMapperTest extends IntegrationTest{
    @Autowired
    private BookMapper bookMapper;
    private Book book;

    @Before
    public void setUp() {
        book = new Book("author", "title", "image_src", "description", "0156027321", "978-0156027328");
    }

    @Test
    public void shouldGetBookByTitle() {
        bookMapper.insertBook(book);
        Book result = bookMapper.getBookByTitle("title");
        assertThat(result, equalTo(book));
    }
}
