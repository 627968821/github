import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入：");
        String str = sc.next();
        int i = Integer.parseInt(str);
        if (i == 0) {
            System.out.println("滚了");
            return;
        } else
            Gquan.draw();
    }
    }
