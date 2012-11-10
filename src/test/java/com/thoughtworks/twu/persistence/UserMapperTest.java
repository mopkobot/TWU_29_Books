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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true )
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
    public void shouldAddBooksToUserWantToReadList() throws Exception {
        userMapper.insertUser(user);
        bookMapper.insertBook(book);

        Book bookFromDB = bookMapper.getBookByTitle("title");
        int bookId = bookFromDB.getId();
        userMapper.markBookAsWantToRead("foo", bookId);

        int bookInWantToReadList = userMapper.isBookInWantToReadList(user.getCasname(), bookId);;
        assertThat(bookInWantToReadList, is(1));
    }
}
