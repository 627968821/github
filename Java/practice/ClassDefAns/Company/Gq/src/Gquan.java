//#### 5.抽签程序
//
//        出现的概率
//
//        |大吉 | 中吉 | 小吉 | 吉 | 末吉 | 凶  | 大凶 |
//        | ----|----|----|----|----|----|---- |
//        | 5% | 10% | 15% | 30% | 20% | 15% | 5%|
//
//        抽签程序，输入任意内容，抽一张，展示内容
//
//        输入0退出

public class Gquan {
    //private String str;
    private static int  num = (int) Math.random() * (100);

    public static void draw() {
        if (num >= 0 & num < 5) {
            System.out.println("大吉");
            return;
        } else if (num >= 5 & num < 15) {
            System.out.println("中吉");
            return;
        } else if (num >= 15 & num < 30) {
            System.out.println("小吉");
            return;
        } else if (num >= 30 & num < 60) {
            System.out.println("吉");
            return;
        } else if (num >= 60 & num < 80) {
            System.out.println("末吉");
            return;
        } else if (num >= 80 & num < 95) {
            System.out.println("凶");
            return;
        } else if (num >= 95 & num <= 99) {
            System.out.println("大凶");
            return;
        }
    }
}
