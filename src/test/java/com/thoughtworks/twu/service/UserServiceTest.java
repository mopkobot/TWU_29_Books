package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.persistence.BookMapper;
import com.thoughtworks.twu.persistence.UserMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserServiceTest {
    private String casname = "foo";
    private String name = "foobar";

    private User user;
    private UserService userService;
    private UserMapper userMapper;

    @Before
    public void setUp() {
        user = new User(casname, name);
        userMapper = createUserMapper();
        userService = new UserService(userMapper);
    }

    @Test
    public void shouldCheckExistingUser() {
        assertThat(userService.isUserExisted(casname), is(true));
    }

    @Test
    public void shouldGetUserByName() {
        User expectedUser = userService.getUserByCasname(casname);

        assertThat(expectedUser.getCasname(), is(casname));
    }

    @Test
    public void shouldCreateUser() {
        String ret = userService.createUser(user);

        assertThat(ret, is("success"));
    }

    @Test
    public void shouldAddBookToWantToReadList() {
        int expectedBookId = 1;
        userService.markBookAsWantToRead(expectedBookId, user.getCasname());
        Book actualBook = userService.getBookFromWantToReadList(expectedBookId);

        assertThat(actualBook.getId(), is(expectedBookId));
    }

    @Test
    public void shouldReturnFalseWhenBookIsNotMarkedAsWantToReadInDB() {
        userMapper.insertUser(user);
        BookMapper bookMapper = mock(BookMapper.class);

        Book book = getBook();
        bookMapper.insertBook(book);

        assertThat(userService.isMarkedAsWantToRead(user.getCasname(), book.getId()), is(false));
    }

    @Test
    public void shouldReturnTrueWhenBookIsMarkedAsWantToReadInDB() {
        int bookId = 1;
        userService.markBookAsWantToRead(bookId, user.getCasname());

        assertThat(userService.isMarkedAsWantToRead(user.getCasname(), bookId), is(true));
    }

    @Test
    public void shouldReturnAListOfWantToReadBooks() {
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(getBook());

        when(userMapper.getBooksInWantToReadList(user.getCasname())).thenReturn(expectedBooks);

        List<Book> actualBooks = userService.getBooksFromWantToReadList(user.getCasname());

        assertThat(actualBooks, is(expectedBooks));
        verify(userMapper).getBooksInWantToReadList(user.getCasname());
    }

    private UserMapper createUserMapper() {
        UserMapper userMapper = mock(UserMapper.class);
        when(userMapper.getUserByCasname(casname)).thenReturn(user);
        when(userMapper.isBookInWantToReadList(user.getCasname(), 1)).thenReturn(1);
        return userMapper;
    }

    private Book getBook() {
        return new Book("author", "title", "image_src", "description", "0156027321", "978-0156027328");
    }
}

