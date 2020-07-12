import java.io.Serializable;
import java.util.Scanner;

public class Van extends Automobile{
    private String fuelType;//燃油类型
    public Van addVan() {
        super.addFnY();
        setVehicleNumber();
        setFuelType();
        super.setMaintenanceDate();
        return this;
    }
    public void setVehicleNumber() {
        int vehicleNumber = 0;
        try {
            System.out.println("请输入车辆编号：V_(数字)");
            Scanner scannerNum = new Scanner(System.in);
            vehicleNumber = scannerNum.nextInt();
        } catch (Exception e) {
            System.out.println("输入有误");
            setVehicleNumber();
        }
        String supVehicleNumber="V_" + vehicleNumber;
        for (Automobile automobile : RentCar.carList) {
            if(supVehicleNumber.equalsIgnoreCase(automobile.getVehicleNumber())){
                System.out.println("编号重复,请重新输入");
                setVehicleNumber();
            }
        }
        super.setVehicleNumber(supVehicleNumber);
    }

    public void setFuelType() {
        System.out.println("输入燃油类型:\n" + "1. 汽油\n" + "2. 柴油");
        try {
            Scanner scanner = new Scanner(System.in);
            int type=scanner.nextInt();
            if(type==1){
                fuelType="汽油";
            }else if(type==2){
                fuelType="柴油";
            }else {
                System.out.println("输入错误请重新输入");
                setFuelType();
            }
        } catch (Exception e) {
            System.out.println("输入错误请重新输入");
            setFuelType();
        }
    }
    @Override
   public void message(){
       System.out.println("***********卡车**********\n" +
               "-----"+super.getVehicleNumber()+"------\n" +
               fuelType+"\n" +
               "年份："+super.getFactoryYear()+"\n" +
               "厂家："+super.getFactory()+"\n" +
               "状态："+(super.isRent()?"借出":"可用")+"\n" +
               "保养日期："+super.getMaintenanceDate()+"\n" +
               "租借记录："+super.getRentLongTime()+"\n");
    }
}
