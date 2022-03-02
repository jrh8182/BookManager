package book.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Student {
    int sid ;
    String name;
    String sex;
    int grade;
//    变量设定成final会影响映射,还是要自己写构造器
    public Student(String name, String sex, int grade) {
        this.name = name;
        this.sex = sex;
        this.grade = grade;
    }


}
