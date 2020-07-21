package club.banyuan.mbm.service;

import club.banyuan.mbm.entity.Bill;
import club.banyuan.mbm.entity.Provider;
import club.banyuan.mbm.uti.PropUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BillService {
    public static List<Bill> billList;
    public static int billId;
    public static final String BILL_STORE_PATH = "bill.store.path";
    public static ProviderService providerService=new ProviderService();

    static {
        load();
        Optional<Bill> max = billList.stream().max((o1, o2) -> o1.getId() - o2.getId());
        billId = max.map(bill -> bill.getId() + 1).orElse(1);
    }

    public static void load() {
        String prop = PropUtil.getProp(BILL_STORE_PATH);
        File file = new File(prop);
        if (!file.exists()) {
            System.err.println("用户文件不存在");
            billList = new LinkedList<>();
        } else {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = fileInputStream.readAllBytes();
                billList = JSONObject.parseArray(new String(bytes), Bill.class);
                if(billList==null){
                    billList = new LinkedList<>();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PropUtil.getProp(BILL_STORE_PATH));
            String string = JSONObject.toJSONString(billList);
            fileOutputStream.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBill(Bill bill) {
        billList.add(bill);
        bill.setId(billId);
        billId++;
        Provider providerById = providerService.getProviderById(bill.getProviderId());
        bill.setProviderName(providerById.getName());
        bill.setUpdateTime();
        bill.setIsPayStr();
        save();
    }
    public List<Bill> getBillList(Bill bill) {//根据传入的bill信息查询相关账单列表
        List<Bill> bills = new LinkedList<>();
        System.out.println(bill.getIsPay()+"验证ispay");
        System.out.println(bill.getProduct()+"验证product");
        if(bill.getProduct().equals("") &&bill.getIsPay()==-1){
            return billList;
        }
        if(!bill.getProduct().equals("") &&bill.getIsPay()==-1){
            for (Bill bi : billList) {
                if(bi.getProduct().contains(bill.getProduct())){
                    bills.add(bi);
                }
            }
            return bills;
        }
        if (bill.getProduct().equals("") &&bill.getIsPay()!=-1) {
            for (Bill bi : billList) {
                if (bi.getIsPay() == bill.getIsPay()) {
                    bills.add(bi);
                }
            }
            return bills;
        } else {
            for (Bill bi : billList) {
                if (bi.getProduct().contains(bill.getProduct()) && bi.getIsPay()==bill.getIsPay()) {
                    bills.add(bi);
                }
            }
            return bills;
        }
    }
    public void updateBill(Bill bill) {
        Bill billById = getBillById(bill.getId());
        billById.setProduct(bill.getProduct());
        billById.setMoney(bill.getMoney());
        billById.setProviderId(bill.getProviderId());
        billById.setIsPay(bill.getIsPay());
        billById.setIsPayStr();
        save();
    }
    public Bill getBillById(int id){
        List<Bill> collect = billList.stream().filter(bill -> bill.getId() == id).collect(Collectors.toList());
        return collect.get(0);
    }
    public void deleteBill(Bill bill) {
        Bill billById = getBillById(bill.getId());
        billList.remove(billById);
        save();
    }
    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        BillService.billList = billList;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        BillService.billId = billId;
    }


}
