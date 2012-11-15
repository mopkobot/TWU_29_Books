package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Book;
import com.thoughtworks.twu.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper {

    int WANT_TO_READ_STATUS = 1;

    @Insert("insert into users (casname, name) values (#{casname}, #{name})")
    void insertUser(User user);


    @Select("select * from users where casname=#{casname}")
    User getUserByCasname(String casname);


    @Insert("insert into readings (user_casname, book_id, reading_status) values (#{casname}, #{bookId}, " + WANT_TO_READ_STATUS + ")")
    void markBookAsWantToRead(@Param("casname") String casname, @Param("bookId")int bookId);

    @Select("select count(*) from readings, books where user_casname=#{casname} AND books.id = #{bookId} AND book_id = books.id AND reading_status=" + WANT_TO_READ_STATUS)
    int isBookInWantToReadList(@Param("casname") String casname, @Param("bookId") int bookId);

    @Select("select books.* from books, readings where readings.user_casname = #{casname} and books.id = readings.book_id and reading_status = "+ WANT_TO_READ_STATUS)
    List<Book> getBooksInWantToReadList(String casname);
}
