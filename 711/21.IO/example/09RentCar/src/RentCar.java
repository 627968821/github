import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RentCar {
    public static List<Automobile> carList = new LinkedList<>();
    private String PATH = "C:\\Users\\86187\\github\\711\\21.IO\\example\\09RentCar\\src\\carStore";
    void store() {//汽车流
        File file = new File(PATH);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(carList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH));) {
            Object o = objectInputStream.readObject();
            carList = (List<Automobile>) o;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("空仓");
        }
    }
    public void addAutomobile() {//增加汽车
        Automobile automobile = new Automobile().addAntomobile();
        carList.add(automobile);
        System.out.println("添加成功！车辆信息如下！");
        System.out.println(automobile);
        store();
    }

    public void rentCar() {//租车
        String vehicleNumber = null;
        String num = null;
        String startTime = null;
        int time = 0;
        try {
            System.out.println("输入汽车编号");
            Scanner scanner = new Scanner(System.in);
            vehicleNumber = scanner.nextLine();
            if (equalStr(vehicleNumber)) {
                equalVehicleNumber(vehicleNumber).setRent(true);//z\找到编号设置为已出租
            } else if (equalVehicleNumber(vehicleNumber).isMaintenance()) {
                System.out.println("汽车保养,无法出租");
                return;
            } else {
                System.out.println("未找到该车，请重新输入");
                rentCar();
            }
            System.out.println("输入顾客编号");
            Scanner scannerNum = new Scanner(System.in);
            num = scannerNum.nextLine();
            equalVehicleNumber(vehicleNumber).setCustomer(num);
            System.out.println("出租起始时间(年-月-日)：");
            Scanner scannerStartTime = new Scanner(System.in);
            startTime = scannerStartTime.nextLine();
            equalVehicleNumber(vehicleNumber).setStartTime(startTime);//设置起租时间
            System.out.println("出租时长(天): ");
            Scanner scannerTime = new Scanner(System.in);
            time = scannerStartTime.nextInt();
            equalVehicleNumber(vehicleNumber).setTime(time);//设置租时常
//        equalVehicleNumber(vehicleNumber).setRentNum(vehicleNumber);//设置顾客所租汽车的编号
        } catch (Exception e) {
            System.out.println("输入错误");
            rentCar();
        }
        System.out.println(vehicleNumber + " 被顾客" + num + " 借出，从" + startTime + "开始，借出" + time + "天");
        store();
    }

    public void returnCar() {
        String vehicleNumber = null;
        try {
            System.out.println("输入汽车编号");
            Scanner scannerNum = new Scanner(System.in);
            vehicleNumber = scannerNum.nextLine();
            if (vehicleNumber == null || !equalStr(vehicleNumber) || !equalVehicleNumber(vehicleNumber).isRent()) {
                System.out.println("未找到该车");
                return;
            }
            System.out.println("归还日期(年-月-日)：");
            Scanner scannerReturn = new Scanner(System.in);
            String returnTime = scannerReturn.nextLine();
            equalVehicleNumber(vehicleNumber).setEndTime(returnTime);//设置归还时间
        } catch (Exception e) {
            System.out.println("输入有误");
            rentCar();
        }
        System.out.println("顾客" + equalVehicleNumber(vehicleNumber).getCustomer() + "归还" + vehicleNumber + "成功！");
        store();
    }

    public void startMaintenance() {
        System.out.println("输入汽车编号");
        Scanner scannerNum = new Scanner(System.in);
        String vehicleNumber = scannerNum.nextLine();
        if (vehicleNumber != null && equalStr(vehicleNumber) && !equalVehicleNumber(vehicleNumber).isRent()) {
            equalVehicleNumber(vehicleNumber).setMaintenance(true);
        } else {
            System.out.println("未找到该车");
        }
        System.out.println(vehicleNumber + "开始保养");
        store();
    }

    public void endMaintenance() {//结束保养
        System.out.println("输入汽车编号");
        Scanner scannerNum = new Scanner(System.in);
        String vehicleNumber = scannerNum.nextLine();
        if (vehicleNumber != null && equalStr(vehicleNumber) && equalVehicleNumber(vehicleNumber).isRent()) {
            equalVehicleNumber(vehicleNumber).setMaintenance(false);
        } else {
            System.out.println("未找到该车");
            return;
        }
        System.out.println("保养结束时间(年-月-日):");
        Scanner scannerTime = new Scanner(System.in);
        String time = scannerTime.nextLine();
        equalVehicleNumber(vehicleNumber).setEndMaintenance(time);
        System.out.println(vehicleNumber + "结束保养,时间:" + time);
        store();
    }

    public Automobile equalVehicleNumber(String string) {//有相同返回对象
        for (Automobile automobile : carList) {
            if (string.equals(automobile.getVehicleNumber())) {
                return automobile;
            }
        }
        return null;
    }

    public boolean equalStr(String string) {//有相同返回true
        for (Automobile automobile : carList) {
            if (string.equals(automobile.getVehicleNumber())) {
                return true;
            }
        }
        return false;

    }
    public void print() {
        for (Automobile automobile : carList) {
            automobile.message();
        }
    }
}
