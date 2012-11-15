package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true)
@TestExecutionListeners({TransactionalTestExecutionListener.class})
public class UserMapperTest extends IntegrationTest{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper bookMapper;

    private User user;
    private Book book;

    @Before
    public void setUp() {
        user = new User("foo", "foobar");
        book = new Book("author", "title", "image_src", "description", "0156027321", "978-0156027328");
    }

    @Test
    public void shouldGetUser() {
        userMapper.insertUser(user);
        User expectedUser = userMapper.getUserByCasname("foo");
        assertThat(expectedUser.getName(), is(user.getName()));
    }

    @Test
    public void shouldAddBooksToUserWantToReadList() {
        userMapper.insertUser(user);
        bookMapper.insertBook(book);

        Book bookFromDB = bookMapper.getBookByTitle("title");
        int bookId = bookFromDB.getId();
        String casname = user.getCasname();

        userMapper.markBookAsWantToRead(casname, bookId);

        int bookInWantToReadList = userMapper.isBookInWantToReadList(casname, bookId);
        assertThat(bookInWantToReadList, is(1));
    }

    @Test
    public void shouldGetBooksInWantToReadList() {
        userMapper.insertUser(user);
        bookMapper.insertBook(book);
        Book secondBook = new Book("author", "second title", "image_src", "description", "0156027321", "978-0156027328");
        bookMapper.insertBook(secondBook);

        String casname = user.getCasname();
        Book bookFromDB = bookMapper.getBookByTitle("title");
        Book secondBookFromDB = bookMapper.getBookByTitle("second title");
        int bookId = bookFromDB.getId();
        int secondBookId = secondBookFromDB.getId();

        userMapper.markBookAsWantToRead(casname, bookId);
        userMapper.markBookAsWantToRead(casname, secondBookId);

        List<Book> expectedBooks = new ArrayList<Book>();
        expectedBooks.add(bookFromDB);
        expectedBooks.add(secondBookFromDB);

        List<Book> actualBooks = userMapper.getBooksInWantToReadList(casname);

        assertThat(actualBooks, is(expectedBooks));
    }
}
