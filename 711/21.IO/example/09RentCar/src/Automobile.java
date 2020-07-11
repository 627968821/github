import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Automobile implements Serializable {
    private int factoryYear;//出厂年份
    private String factory;//厂家
    private String maintenanceDate;//最后保养日期
    private String vehicleNumber;//填写厂家完成后，请用户填写车辆编号，编号必须是唯一的，卡车以V_开头，轿车以C_开头
    private boolean isRent=false;


    public Automobile addAntomobile() {
        return setAutomobile();
    }

    public Automobile setAutomobile() {
        System.out.println("请输入汽车类型：\n 轿车：Car  卡车：Van");
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();
        if (!type.equalsIgnoreCase("car") && !type.equalsIgnoreCase("van")) {
            System.out.println("类型错误，请重新输入");
            setAutomobile();
        } else if (type.equalsIgnoreCase("car")) {
            return new Car().addCar();
        } else if (type.equalsIgnoreCase("van")) {
            return new Van().addVan();
        }
        return null;
    }



    public Automobile returnCar() {
        return null;
    }

    public Automobile startMaintenance() {
        return null;
    }

    public Automobile endMaintenance() {
        return null;
    }

    public void message() {
    }

    public void addFnY() {//设置出厂年份和厂家
        System.out.println("请输入出厂年份：");
        Scanner scannerYear = new Scanner(System.in);
        int factoryYear = scannerYear.nextInt();
        this.factoryYear = factoryYear;
        System.out.println("请输入厂家：");
        Scanner scannerFactory = new Scanner(System.in);
        String factory = scannerFactory.nextLine();
        this.factory = factory;
    }

    public void setMaintenanceDate() {
        System.out.println("保养日期(年-月-日)");
        Scanner scanner = new Scanner(System.in);
        String maintenanceDate = scanner.nextLine();
        this.maintenanceDate = maintenanceDate;
//        String[] strS=maintenanceDate.split("-");
//        Date date = new Date(maintenanceDate);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        maintenanceDate=simpleDateFormat.format(date);
    }

    public void setFactoryYear(int factoryYear) {
        this.factoryYear = factoryYear;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public void setMaintenanceDate(String maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public boolean isRent() {
        return isRent;
    }

    public void setRent(boolean rent) {
        isRent = rent;
    }

    @Override
    public String toString() {
        return "汽车编号：" + vehicleNumber + "\n" +
                "年份：" + factoryYear + "\n" +
                "厂家：" + factory + "\n" +
                "保养日期:" + maintenanceDate;
    }
}
