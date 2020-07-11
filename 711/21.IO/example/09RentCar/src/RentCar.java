import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RentCar {
    public static List<Automobile> carList = new LinkedList<>();
    public static List<Customer> customerList = new LinkedList<>();
    private String PATH = "/Users/edz/project/JAVA/711/21.IO/example/09RentCar/src/车库";
    private String PATHCustomer = "/Users/edz/project/JAVA/711/21.IO/example/09RentCar/src/顾客";

    void store() {//汽车流
        File file = new File(PATH);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file)))
        {
            objectOutputStream.writeObject(carList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void storeCustomer() {//顾客流
        File file = new File(PATHCustomer);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file)))
        {
            objectOutputStream.writeObject(customerList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void load() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH));) {
            Object o = objectInputStream.readObject();
            carList = (List<Automobile>) o;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("空仓");
        }
    }
    void loadCustomer() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(PATH));) {
            Object o = objectInputStream.readObject();
            customerList = (List<Customer>) o;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("木有客源");
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
        Customer customer = new Customer();//创建顾客类
        System.out.println("输入汽车编号");
        Scanner scanner = new Scanner(System.in);
        String vehicleNumber = scanner.nextLine();
        if (equalStr(vehicleNumber)) {
            equalVehicleNumber(vehicleNumber).setRent(true);//z\找到编号设置为已出租
        } else {
            System.out.println("未找到该车，请重新输入");
            rentCar();
        }
        System.out.println("输入顾客编号");
        Scanner scannerNum = new Scanner(System.in);
        String num = scannerNum.nextLine();
        customer.setCustomer(num);
        System.out.println("出租起始时间(年-月-日)：");
        Scanner scannerStartTime = new Scanner(System.in);
        String startTime = scannerStartTime.nextLine();
        customer.setStartTime(startTime);//设置起租时间
        System.out.println("出租时长(天): 10");
        Scanner scannerTime = new Scanner(System.in);
        int time = scannerStartTime.nextInt();
        customer.setTime(time);//设置租时常
        customer.setRentNum(vehicleNumber);//设置顾客所租汽车的编号
        System.out.println(vehicleNumber + " 被顾客" + num + " 借出，从" + startTime + "开始，借出" + time + "天");
    }
    public Automobile returnCar() {
        System.out.println("输入汽车编号");
        Scanner scannerNum = new Scanner(System.in);
        String vehicleNumber = scannerNum.nextLine();
        if(vehicleNumber==null||equalStr(vehicleNumber)==false||equalVehicleNumber(vehicleNumber).){
            System.out.println("未找到该车");
        }
        Customer customer=equalNum(vehicleNumber);//找到租车客人
        System.out.println("归还日期(年-月-日)：");
        Scanner scannerReturn = new Scanner(System.in);
        String returnTime = scannerReturn.nextLine();
        customer.setEndTime(returnTime);//设置归还时间
        System.out.println("顾客"+customer.getCustomer()+"归还"+vehicleNumber+"成功！");


    }

    public Automobile startMaintenance() {
        return null;
    }

    public Automobile endMaintenance() {
        return null;
    }

    public void message() {
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
    public Customer equalNum(String string){//根据还车编码找到顾客
        for (Customer customer : customerList) {
            if(string.equals(customer.getRentNum())){
                return customer;
            }
        }
        return null;
    }
}
