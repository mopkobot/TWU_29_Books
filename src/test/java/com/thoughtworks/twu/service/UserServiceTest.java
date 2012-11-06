package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.persistence.UserMapper;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private String casname = "foo";
    private String name = "foobar";

    private User user;
    private UserService userService;

    @Before
    public void setUp() {
        user = new User(casname, name);
    }

    @Test
    public void shouldCheckExistingUser() {

        UserMapper userMapper = createUserMapper();
        userService = new UserService(userMapper);

        assertThat(userService.isUserExisted(casname), is(true));
    }

    @Test
    public void shouldGetUserByName() {
        UserMapper userMapper = createUserMapper();
        userService = new UserService(userMapper);

        User expectedUser = userService.getUserByCasname(casname);

        assertThat(expectedUser.getCasname(), is(casname));
    }

    @Test
    public void shouldCreateUser() {
        UserMapper userMapper = createUserMapper();
        UserService service = new UserService(userMapper);

        String ret = service.createUser(user);

        assertThat(ret, is("success"));
    }

    private UserMapper createUserMapper() {
        UserMapper userMapper = mock(UserMapper.class);
        when(userMapper.getUserByCasname(casname)).thenReturn(user);
        return userMapper;
    }
}
