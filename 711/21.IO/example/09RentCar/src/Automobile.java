import java.io.Serializable;
import java.util.Scanner;

public class Automobile implements Serializable {
    private int factoryYear;//出厂年份
    private String factory;//厂家
    private String maintenanceDate;//最后保养日期
    private String vehicleNumber;//填写厂家完成后，请用户填写车辆编号，编号必须是唯一的，卡车以V_开头，轿车以C_开头
    private boolean isRent=false;
    private boolean  isMaintenance=false;//是否在保养
    private String endMaintenance;//结束保养时间
    private   String customer;//顾客编号
    private  String startTime;//起租时间
    private int time;//租用天数
    private String endTime;//归还时间
    private String rentLongTime;//租车信息
    public void setEndMaintenance(String endMaintenance) {
        this.endMaintenance = endMaintenance;
    }
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

    public String getRentLongTime() {
        return rentLongTime;
    }

    public void setRentLongTime(String rentLongTime) {//更新租用信息
      this.rentLongTime+=rentLongTime;
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

    public boolean isMaintenance() {
        return isMaintenance;
    }

    public void setMaintenance(boolean maintenance) {
        isMaintenance = maintenance;
    }

    public int getFactoryYear() {
        return factoryYear;
    }

    public String getFactory() {
        return factory;
    }

    public String getMaintenanceDate() {
        return maintenanceDate;
    }
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
        setRentLongTime(endTime);
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
        setRentLongTime(startTime);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "汽车编号：" + vehicleNumber + "\n" +
                "年份：" + factoryYear + "\n" +
                "厂家：" + factory + "\n" +
                "保养日期:" + maintenanceDate;
    }
}
