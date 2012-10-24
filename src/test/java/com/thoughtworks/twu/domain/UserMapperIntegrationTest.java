package com.thoughtworks.twu.domain;


import com.thoughtworks.twu.persistence.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class UserMapperIntegrationTest extends IntegrationTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public  void shouldChooseUserByUserName() {
        String tom = "Tom";
        addUserToRepository(tom);
        User user = userMapper.getUser(tom);
        assertThat(user.getName(), equalTo("Tom"));
    }

    private void addUserToRepository(String name) {
        userMapper.insertUser(new User(111, name));
    }

    @Test
    public void shouldDeleteUserByName(){
        String bill = "bill";
        userMapper.deleteUserByName(bill);
        User user = userMapper.getUser(bill);
        assertThat(user,equalTo(null));
    }
}
