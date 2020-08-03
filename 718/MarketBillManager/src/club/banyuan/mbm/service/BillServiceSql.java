package club.banyuan.mbm.service;

import club.banyuan.mbm.entity.Bill;
import club.banyuan.mbm.uti.JdbcUtil;
import club.banyuan.mbm.uti.ValidationUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class BillServiceSql {

    public List<Bill> getBillList() {
        String sql = "select id,providerId,money,product,isPay,isPayStr,updateTime,providerName from Bill";
        List<Map<String, Object>> list = JdbcUtil.queryALL(sql);
        List<Bill> bills = JSONObject.parseArray(JSONObject.toJSONString(list), Bill.class);
        return bills;
    }

    public Bill getBillById(int id) {
        String sql = "select id,providerId,money,product,isPay,isPayStr,updateTime,providerName from Bill where id = ?";
        Map<String, Object> map = JdbcUtil.queryOne(sql, id);
        Bill bill = JSONObject.parseObject(JSONObject.toJSONString(map), Bill.class);
        return bill;
    }

    //根据用户名获取bill
    public Bill getBillByName(String name) {
        String sql = "select id,providerId,money,product,isPay,isPayStr,updateTime,providerName from Bill where name = ?";
        Map<String, Object> map = JdbcUtil.queryOne(sql, name);
        Bill Bill = JSONObject.parseObject(JSONObject.toJSONString(map), Bill.class);
        return Bill;
    }

    public void updateBill(Bill bill) {
        ValidationUtil.validate(bill);//检查字段合法性
        Bill billById = getBillById(bill.getId());
        if (billById != null) {
            billById.setProviderId(bill.getProviderId());
            billById.setMoney(bill.getMoney());
            billById.setProduct(bill.getProduct());
            billById.setIsPay(bill.getIsPay());
            billById.setUpdateTime();
            billById.setIsPayStr();
            billById.setProviderName();
            String sql = "update Bill set providerId = ?,money = ?,product= ?,isPay = ?,isPayStr = ?,updateTime = ?,providerName = ? where id = ?";
            JdbcUtil.update(sql, billById.getProviderId(), billById.getMoney(), billById.getProduct(), billById.getIsPay(), billById.getIsPayStr(), billById.getUpdateTime(), billById.getProviderName(), billById.getId());
        }
    }

    //新增
    public void addBill(Bill bill) {
        ValidationUtil.validate(bill);//检查字段合法性
        bill.setUpdateTime();
        bill.setIsPayStr();
        bill.setProviderName();
        String sql = "insert into Bill(providerId,money,product,isPay,isPayStr,updateTime,providerName)values(?,?,?,?,?,?,?)";
        JdbcUtil.update(sql, bill.getProviderId(), bill.getMoney(), bill.getProduct(), bill.getIsPay(), bill.getIsPayStr(), bill.getUpdateTime(), bill.getProviderName());
    }

    //查询
    public List<Bill> getBillList(Bill bill) {
        if (bill.getProduct().trim().length() == 0 && bill.getIsPay() == -1) {
            return getBillList();
        }
        if (bill.getProduct().trim().length() != 0 && bill.getIsPay() == -1) {
//            String sql = "select id,providerId,money,product,isPay,isPayStr,updateTime,providerName from Bill where product like ?";
//            List<Map<String, Object>> list = JdbcUtil.queryALL(sql, "%" + bill.getProduct().trim() + "%");
//            List<Bill> Bills = JSONObject.parseArray(JSONObject.toJSONString(list), Bill.class);
//            return Bills;
            return spitStr(bill.getProduct().trim());
        }
        List<Bill> billList = spitStr(bill.getProduct());
        billList.removeIf(bill1 -> bill1.getIsPay() != bill.getIsPay());
//        String sql = "select id,providerId,money,product,isPay,isPayStr,updateTime,providerName from Bill where product like ? and isPay = ?";
//        List<Map<String, Object>> list = JdbcUtil.queryALL(sql, "%" + bill.getProduct().trim() + "%",bill.getIsPay());
//        List<Bill> Bills = JSONObject.parseArray(JSONObject.toJSONString(list), Bill.class);
        return billList;
    }

    //删除
    public void deleteBill(Bill bill) {
        Bill billById = getBillById(bill.getId());
        String sql = "delete from Bill where id=?";
        JdbcUtil.update(sql, billById.getId());
    }
public List<Bill> spitStr(String string){
    String[] strings = string.split("[^\\u4e00-\\u9fa5,0-9,a-zA-Z]");
    List<String> bills=new LinkedList<>();
    List<Bill> billList=new LinkedList<>();
    for (String s : strings) {
        if(!s.equals("")){
            bills.add(s);
        }
    }
    Set<Bill> billSet=new HashSet<>();
    for (String s : bills) {
        String sql = "select id,providerId,money,product,isPay,isPayStr,updateTime,providerName from Bill where product like ?";
        List<Map<String, Object>> list = JdbcUtil.queryALL(sql, "%" + s + "%");
        List<Bill> billJson = JSONObject.parseArray(JSONObject.toJSONString(list), Bill.class);
        billSet.addAll(billJson);
    }
    List<Bill> billLists=new LinkedList<>();
    for (Bill bill : billSet) {
        for (String s : strings) {
            if(!bill.getProduct().contains(s)){
                billLists.add(bill);
            }
        }
    }
    billList.addAll(billSet);
    billList.removeAll(billLists);
    return billList;
}
//public List<Bill> spitStrRefine(String string){
//    String[] strings = string.split("[^\\u4e00-\\u9fa5,0-9,a-zA-Z]");
//    List<String> bills=new LinkedList<>();
//    List<Bill> billList=new LinkedList<>();
//    for (String s : strings) {
//        if(!s.equals("")){
//            bills.add(s);
//        }
//    }
//    Set<Bill> billSet=new HashSet<>();
//    for (String s : bills) {
//        String sql = "select id,providerId,money,product,isPay,isPayStr,updateTime,providerName from Bill where product like ?";
//        List<Map<String, Object>> list = JdbcUtil.queryALL(sql, "%" + s + "%");
//        List<Bill> billJson = JSONObject.parseArray(JSONObject.toJSONString(list), Bill.class);
//        billSet.addAll(billJson);
//    }
//    for (Bill bill : billSet) {
//        for (String s : strings) {
//            if(!bill.getProduct().contains(s)){
//                billSet.remove(bill);
//            }
//        }
//    }
//    billList.addAll(billSet);
//    return billList;
//}
}



