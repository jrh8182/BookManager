package book.manage;



import book.manage.OtherUtils.Utility;
import book.manage.server.ManageService;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;

@Log
public class Main {
    public static void main(String[] args)  {
        System.out.println("==============图书管理系统===============");
         try {
             LogManager manager = LogManager.getLogManager();
             manager.readConfiguration(Resources.getResourceAsStream("logging.properties"));
             while (true) {
                 System.out.println("===========================");
                 System.out.println("1.录入学生信息");
                 System.out.println("2.录入书籍信息");
                 System.out.println("3.添加借阅信息");
                 System.out.println("4.查询借阅信息");
                 System.out.println("5.查询学生信息");
                 System.out.println("6.查询书籍信息");
                 System.out.println("7.删除学生信息(注意)");
                 System.out.printf("输入您需要执行的操作(按其他任意数字退出):");

                 int input = Utility.readInt();
                 switch (input){
                     case 1:
                         ManageService.addStudent();
                         break;
                     case 2:
                         ManageService.addBook();
                         break;
                     case 3:
                         ManageService.addBorrow();
                         break;
                     case 4:
                         ManageService.showBorrow();
                         break;
                     case 5:
                         ManageService.showStudentList();
                         break;
                     case 6:
                         ManageService.showBookList();
                         break;
                     case 7:
                         ManageService.deleteStudentBySid();
                         break;
                     default:
                         System.out.printf("！请再次确认是否退出！ ");
                         if (( Utility.readConfirmSelection()) == 'Y'){
                             System.out.println("===============已退出系统================");
                             return;
                          }else {
                             System.out.println("取消退出，可以继续进行操作");
                            }

                 }
             }
         }catch (Exception e){
             e.printStackTrace();

         }
    }
}
