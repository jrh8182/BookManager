package book.manage.server;

import book.manage.OtherUtils.Utility;
import book.manage.entity.Book;
import book.manage.entity.Student;
import book.manage.sql.SqlUtil;
import lombok.extern.java.Log;

import java.util.List;

@Log
@SuppressWarnings("all")
public class ManageService {
    //    录入学生信息
    public static void addStudent() {
        System.out.printf("请输入学生姓名:");
        String name = Utility.readString(10); //长度限制为10
        System.out.printf("请输入学生性别（男/女）:");
        String sex = Utility.readConfirmSelectionForSex();
        System.out.printf("请输入学生年级:");
        int grade = Utility.readInt();

        Student student = new Student(name, sex, grade);
        SqlUtil.doSqlWork(mapper -> {
            int i = mapper.addStudent(student);
            if (i > 0) {
                System.out.println("学生信息录入成功!");
                log.info("新添加了一条学生信息:" + student);
            } else System.out.println("学生信息录入失败，请重试");
        });
    }

    public static void addBook() {
        System.out.printf("请输入书名:");
        String title = Utility.readString(20); //长度限制为20
        System.out.printf("请输入书籍描述:");
        String desc = Utility.readString(255);
        System.out.printf("请输入书籍价格:");
        double price = Utility.readDoubleForPositive(); //读取一个大于0的浮点数

        Book book = new Book(title, desc, price);
        SqlUtil.doSqlWork(mapper -> {
            int i = mapper.addBook(book);
            if (i > 0) {
                System.out.println("书籍信息录入成功!");
                log.info("新添加了一条书籍信息:" + book);
            } else System.out.println("书籍信息录入失败，请重试");
        });
    }
    //若存入的sid或bid不存在会报错
    public static void addBorrow() {
        System.out.printf("请输入学生编号(sid):");
        int sid = Utility.readInt();
        System.out.printf("请输入书籍编号(bid):");
        int bid = Utility.readInt();
        SqlUtil.doSqlWork(mapper -> {
            int i = mapper.addBorrow(sid, bid);
            if (i > 0) {
                System.out.println("借阅信息录入成功!");
                log.info("新添加了一条借阅信息:" + "学生id = " + sid + "  " + "书籍id = " + bid);
            } else System.out.println("借阅信息录入失败，请检查 学号 ， 书号 是否存在 或 已有记录");
        });
    }

    public static void showBorrow() {
        System.out.println("==========书籍借阅信息===========");
        SqlUtil.doSqlWork(mapper -> {
            mapper.showBorrow().forEach(borrow -> {
                System.out.println(borrow.getStudent().getName()+ " 借阅 " +"《"+borrow.getBook().getTitle()+"》");
            });
        });
    }

    public static void showStudentList(){
        System.out.println("当前学生借阅信息如下:");
        SqlUtil.doSqlWork(mapper -> {
           List<Student> studentlist = mapper.showStudentList();
            for (Student student :studentlist) {
                System.out.println("学号:" + student.getSid() +"\t"+ "姓名:" + student.getName() +"\t"+ "性别:" +
                                   student.getSex()+ "\t" + "年级:" + student.getGrade() );
            }
        });
    }

    public static void showBookList(){
        System.out.println("书籍信息如下:");
        SqlUtil.doSqlWork(mapper -> {
            List<Book> books = mapper.showBookList();
            for (Book book : books) {
                System.out.println("书号:" + book.getBid() +"\t"+ "书名:" + "《"+book.getTitle()+"》" +"\t"+ "描述:" +
                        book.getDesc()+ "\t" + "价格:" + book.getPrice() );
            }
        });
    }

    public static void deleteStudentBySid(){
        System.out.println("删除学生信息会将该生借阅信息一并删除，请确认！");
        if (Utility.readConfirmSelection() == 'Y'){
            SqlUtil.doSqlWork(mapper -> {
                System.out.printf("需要删除的学生id:");
                int i =mapper.deleteStudentByBid(Utility.readInt());
                if (i > 0){System.out.println("删除成功");}
                else {System.out.println("删除失败，查无此人，请核查id信息");}
            });
        }else {
            System.out.println("操作已取消");
            return;
        }
    }
}