import java.util.Scanner;

//请选择难度等级
//        1. 0~9
//        2. 0~99
//        3. 0~999
//        0. 退出
//
//        你当前选择的难度等级0~99
//        请输入想要猜的次数，0返回上一级
//        你的输入：
//
//        现在开始猜数
//        剩余次数10，请输入数字（0~999）：5
//        输入的是5，没有猜中，猜小了
//
//        剩余次数9，请输入数字：50
//        输入的是50，没有猜中，猜大了
//
//        剩余次数8，请输入数字：30
//        输入的是30，恭喜你，猜中了
//
//        是否继续，1.继续, 0. 退出
//
//        猜题记录
//
//        第1次 50 => -30
//        第2次 70 => -10
//        第3次 90 => +10
//        第4次 80 => 正确
public class Gnum {
    private static int i;
    private static int j=80;
    private static int count;

    public static void setI(int i) {
        Gnum.i = i;
    }
public static void choice(){
    System.out.println("请选择难度：");
    Scanner sc =new Scanner(System.in);
    int i=sc.nextInt();
}
public int option(){
    System.out.printf("剩余次数%d，请输入数字:",count);
//    int num=sc1.nextInt();
    Scanner sc1=new Scanner(System.in);
    int num=sc1.nextInt();
    choose(num);
   // return num;
}
public void choose(int num){
    if(num==0){
        this.choice();
    }else if(num==j){
        System.out.println("恭喜你，猜中了");
    }else if(num>j){
        System.out.printf("输入的是%d，没有猜中，猜大了\n",num);
        count--;
        option();
    }else if(num<j){
        System.out.printf("输入的是%d，没有猜中，猜小了\n",num);
        count--;
        option();
    }
}
    public void gease() {
        switch (i) {
            case 1:Scanner sc1=new Scanner(System.in);
            int count=sc1.nextInt();
                System.out.printf("你当前选择的难度等级0~99\n请输入想要猜的次数，0返回上一级\n你的输入：%d",count);
                if(count==0){
                    choice();
                }

        }
    }
}