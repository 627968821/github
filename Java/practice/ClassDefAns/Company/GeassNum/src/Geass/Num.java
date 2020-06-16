package Geass;
//#### 4. 猜数字
//        ```
//        猜一猜4个不重复的数字，请输入4个数字：
//        1234
//        包含了1个正确的数字
//        但是这些数字位置错误
//
//        请输入4个数字：
//        2314
//        包含了1个正确的数字
//        1个数字的位置正确
//
//        请输入4个数字：
//        5367
//        包含了3个正确的数字
//        2个数字位置正确
//
//        请输入4个数字：
//        5396
//        回答正确
//        ```
//private static  int[]num1=new int[4];
//private static int count=0;

public class Num<i> {
    private static int[] num1 = new int[4];
    private static int[] num2 = {5, 3, 9, 6};
    //new int[]
    private static int count = 0;

    public static void toInt(int aNum) {
        num1[count] = aNum;
        count++;
    }

    public static void geass() {
        int same1 = 0;//位置正确
        int same2 = 0;//数字正确
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                if (num1[i] == num2[j] & (i == j)) {
                    //int same1=0;
                    same1++;
//              int same2=0;
                    same2++;
                } else {
                    if (num1[i] == num2[j] & (i != j)) {
                        same1++;//
                    }
                }
            }
        }
        if(same2==count){
            System.out.println("猜对啦");
            return;
        }
         System.out.println("数字正确的个数为"+same1);
        System.out.println("位置正确的个数为"+same2);
    }
}
