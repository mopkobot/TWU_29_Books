package main.com.thoughtworks.twu.persistence;


import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.persistence.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserMapperIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

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
