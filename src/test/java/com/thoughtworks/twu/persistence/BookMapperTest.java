package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

//Ensure that BookMapper could interact with DB as expected
public class BookMapperTest extends IntegrationTest {
    @Autowired
    private BookMapper bookMapper;

    private final String title = "title";
    private final String author = "author";
    private final String image = "http://ecx.images-amazon.com/images/I/51MU5VilKpL._BO2,204,203,200_PIsitb-sticker-arrow-click,TopRight,35,-76_AA300_SH20_OU01_.jpg";
    private final String description = "this is a book about magic, I love it very much!!";
    private final String ISBN10 = "0156027321";
    private final String ISBN13 = "978-0156027328";
    private Book book = new Book(author, title, image, description, ISBN10, ISBN13);

    @Test
    public void shouldGetBookByTitle() {
        bookMapper.insertBook(book);
        Book result = bookMapper.getBookByTitle(title);
        assertThat(result, equalTo(book));
    }
}
