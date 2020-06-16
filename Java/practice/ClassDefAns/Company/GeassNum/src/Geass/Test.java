package Geass;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("请输入四个不同的数字:");
        System.out.println("第一个：");
        //Num.toInt();
        Scanner sc = new Scanner(System.in);
        Num.toInt(sc.nextInt());
        System.out.println("第二个：");
        Num.toInt(sc.nextInt());
       // Scanner sc2 = new Scanner(System.in);
        System.out.println("第三个：");
        Num.toInt(sc.nextInt());
        //Scanner sc3= new Scanner(System.in);
        System.out.println("第四个：");
        Num.toInt(sc.nextInt());
        //Scanner sc4 = new Scanner(System.in);
        Num.geass();
    }
}
