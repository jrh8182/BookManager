package book.manage.OtherUtils;

import java.util.Scanner;
/*
简化输入判断输入的工具类
 */
public class Utility {
    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public Utility() {

    }

    public static char readMenuSelection() {
        while (true) {
            String str = readKeyBoard(1, false);
            char c = str.charAt(0);
            if (c == 1 || c == 2 || c == 3 || c == 4 || c == 5) {
                return c;
            }

            System.out.print("选择错误，请重新输入：");
        }
    }

    public static char readChar() {
        String str = readKeyBoard(1, false);
        return str.charAt(0);
    }

    public static char readChar(char defaultValue){
        String str=readKeyBoard(1, true);
        return str.length()==0?defaultValue:str.charAt(0);
    }

    public static int readInt(){
        while (true){
            String str=readKeyBoard(10, false);
            try {
                int n=Integer.parseInt(str);
                return n;
            }catch (NumberFormatException var3){
                System.out.println("数字输入错误，请重新输入：");
            }
        }
    }
    //自己加的,只是输入一个小数
    public static double readDouble(){
        while (true){
            String str=readKeyBoard(10, false);
            try {
                double n=Double.parseDouble(str);
                return n;
            }catch (NumberFormatException var3){
                System.out.println("数字输入错误，请重新输入：");
            }
        }
    }

//    输入＞=0的小数
    public static double readDoubleForPositive(){
        while (true){
            String str=readKeyBoard(10, false);
            try {
                double n=Double.parseDouble(str);
                if(n>=0){ return n;}
                else System.out.println("请输入大于0的有效数字");
            }catch (NumberFormatException var3){
                System.out.println("数字输入错误，请重新输入：");
            }
        }
    }




    public static int readInt(int defaultValue) {
        while(true) {
            String str = readKeyBoard(10, true);
            if (str.equals("")) {
                return defaultValue;
            }

            try {
                int n = Integer.parseInt(str);
                return n;
            } catch (NumberFormatException var4) {
                System.out.print("数字输入错误，请重新输入：");
            }
        }
    }

    public static String readString(int limit) {
        return readKeyBoard(limit, false);
    }

    public static String readString(int limit, String defaultValue) {
        String str = readKeyBoard(limit, true);
        return str.equals("") ? defaultValue : str;
    }

    public static char readConfirmSelection(){
        System.out.println("请输入你的选择(Y/N): 请小心选择");
        while (true){
            String str=readKeyBoard(1,false).toUpperCase();
            char c=str.charAt(0);
            if(c=='Y'||c=='N'){
                return c;
            }
            System.out.print("选择错误，请重新输入：");
        }
    }

    //输入性别 只能为 男/女 否则循环
    public static String readConfirmSelectionForSex(){
//        System.out.println("请输入学生的性别(男/女):");
        while (true){
            String str=readKeyBoard(1,false);
            char c=str.charAt(0);
            if(c=='男'||c=='女'){
                return str ;
            }
            System.out.print("输入错误，请重新输入(男/女)：");
        }
    }

    private static String readKeyBoard(int limit, boolean blankReturn){
        String line="";

        while (scanner.hasNextLine()){
            line=scanner.nextLine();
            if(line.length()==0){
                if(blankReturn){
                    return line;
                }
            }else {
                if(line.length()>=1 && line.length()<=limit){
                    break;
                }
                System.out.println("输入长度（不大于" + limit + "）错误，请重新输入：");
            }
        }
        return line;
    }
}
