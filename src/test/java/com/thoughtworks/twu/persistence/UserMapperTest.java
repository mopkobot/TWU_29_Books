package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserMapperTest extends IntegrationTest{

    @Autowired
    private UserMapper userMapper;

    private User user;

    @Before
    public void setUp() {
        user = new User("foo", "foobar");
    }

    @Test
    public void shouldGetUser() {
        userMapper.insertUser(user);
        User expectedUser = userMapper.getUserByCasname("foo");
        assertThat(expectedUser.getName(), is(user.getName()));
    }
}
