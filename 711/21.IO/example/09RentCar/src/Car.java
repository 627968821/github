import java.io.Serializable;
import java.util.Scanner;

public class Car extends Automobile {
    private int seating;//座位数

    public Car addCar() {
        super.addFnY();
        setVehicleNumber();
        setSeating();
        super.setMaintenanceDate();
        return this;
    }

    public void setVehicleNumber() {
        int vehicleNumber = 0;
        try {
            System.out.println("请输入车辆编号：C_(数字)");
            Scanner scannerNum = new Scanner(System.in);
            vehicleNumber = scannerNum.nextInt();
        } catch (Exception e) {
            System.out.println("输入有误");
            setVehicleNumber();
        }
        String supVehicleNumber = "C_" + vehicleNumber;
        for (Automobile automobile : RentCar.carList) {
            if (supVehicleNumber.equalsIgnoreCase(automobile.getVehicleNumber())) {
                System.out.println("编号重复,请重新输入");
                setVehicleNumber();
            }
        }
        super.setVehicleNumber(supVehicleNumber);
    }

    public void setSeating() {
        int seating = 0;
        try {
            System.out.println("输入轿车座数(4/7):(数字)");
            Scanner scannerSeating = new Scanner(System.in);
            seating = scannerSeating.nextInt();
        } catch (Exception e) {
            System.out.println("输入有误");
            setVehicleNumber();
        }
        if (seating < 4 || seating > 7) {
            System.out.println("座位数有误,请重新输入");
            setSeating();
        } else {
            this.seating = seating;
        }
    }

    @Override
    public void message() {
        System.out.println("***********轿车**********\n" + "-----" + super.getVehicleNumber() + "------\n"
                + seating + "座车\n" +
                "年份：" + super.getFactoryYear() + "\n" +
                "厂家：" + super.getFactory() + "\n" +
                "状态：" + (super.isRent() ? "借出" : "可用") + "\n" +
                "保养日期：" + super.getMaintenanceDate() + "\n" +
                "租借记录："+super.getRentLongTime()+"\n");
    }
}
