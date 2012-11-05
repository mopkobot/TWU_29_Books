package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


public interface UserMapper {

    @Insert("insert into users (casname, name) values (#{casname}, #{name})")
    void insertUser(User user);


    @Select("select * from users where casname=#{casname}")
    User getUserByCasname(String casname);

}
