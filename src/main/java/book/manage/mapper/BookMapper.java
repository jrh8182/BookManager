package book.manage.mapper;

import book.manage.entity.Book;
import book.manage.entity.Borrow;
import book.manage.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

import static org.apache.ibatis.type.JdbcType.VARCHAR;

public interface BookMapper {
    @Insert("insert into student(name,sex,grade) values(#{name}, #{sex}, #{grade})")
    int addStudent(Student student);

    @Insert("insert into book(title,`desc`,price) values(#{title}, #{desc}, #{price})")
    int addBook(Book book);

    @Insert("insert into borrow(sid, bid) values(#{sid}, #{bid})")
    int addBorrow(@Param("sid") int sid, @Param("bid") int bid);

    @Results({
        @Result(column =  "id" ,property = "id", id = true),
        @Result(column = "sid" ,property = "student", one = @One(select = "selectStudentBySid")),
        @Result(column = "bid" ,property = "book" , one = @One(select = "selectBookByBid"))
    })
    @Select("select * from borrow")
    List<Borrow>  showBorrow();

    @Results({
            @Result(column = "bid" ,property = "bid" ,id = true),
            @Result(column = "title" ,property = "title"),
            @Result(column = "desc" ,property = "desc"),
            @Result(column = "price" , property = "price")
    })
    @Select("select * from book where bid = #{bid}")
    Book selectBookByBid(int bid);

//    @Results({
//            @Result(column = "sid" ,property = "sid" ,id = true),
//            @Result(column = "name" ,property = "name"),
//            @Result(column = "sex" ,property = "sex"   ),
//            @Result(column = "grade" , property = "grade")
//    })
    @Select("select * from student where sid = #{sid}")
    Student selectStudentBySid(int sid);

    @Results({
            @Result(column = "sid" ,property = "sid" ,id = true),
            @Result(column = "name" ,property = "name"),
            @Result(column = "sex" ,property = "sex"   ),
            @Result(column = "grade" , property = "grade")
    })
    @Select("select * from student")
    List<Student> showStudentList();

    @Select("select * from book")
    List<Book> showBookList();

    @Delete("delete  from student where sid = #{sid}")
    int deleteStudentByBid(int sid);
}
