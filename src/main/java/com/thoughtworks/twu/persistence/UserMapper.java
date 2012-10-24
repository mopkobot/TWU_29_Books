package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


public interface UserMapper {
    @Select("SELECT id,name FROM user where name = #{name}")
    User getUser(String name);

    @Insert("INSERT INTO user (name) VALUES(#{name})")
    void insertUser(User user);

    @Delete("DELETE FROM user where name = #{name}")
    void deleteUserByName(String name);

}
