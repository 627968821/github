import java.util.Scanner;

public class MenuRentCar {
    public static void main(String[] args) {
        RentCar rentCar = new RentCar();
        rentCar.load();
        while (true) {
            System.out.println("1.添加汽车\n" + "2.出租汽车\n" + "3.归还汽车\n" + "4.汽车保养\n" + "5.结束保养\n" + "6.查询信息\n" + "7.退出系统");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            switch (num) {
                case 1:
                    rentCar.addAutomobile();
                    break;
                case 2:
                    rentCar.rentCar();
                    break;
                case 3:
                    rentCar.returnCar();
                    break;
                case 4:
                    rentCar.startMaintenance();
                    break;
                case 5:
                    rentCar.endMaintenance();
                    break;
                case 6:
                    rentCar.print();
                    break;
                case 7:

                    System.out.println("退出成功");
                    return;
                default:
                    System.out.println("请重新输入");
            }
        }
    }
}
