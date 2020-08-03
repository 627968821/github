package club.banyuan.mbm.service;

import club.banyuan.mbm.entity.Provider;
import club.banyuan.mbm.entity.Provider;
import club.banyuan.mbm.exception.FormPostException;
import club.banyuan.mbm.uti.JdbcUtil;
import club.banyuan.mbm.uti.PropUtil;
import club.banyuan.mbm.uti.ValidationUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ProviderServiceSql extends ValidationUtil{

    public List<Provider> getProviderList() {
        String sql = "select id,name,descs,contactPerson,phone from Provider";
        List<Map<String, Object>> list = JdbcUtil.queryALL(sql);
        List<Provider> providers = JSONObject.parseArray(JSONObject.toJSONString(list), Provider.class);
        return providers;
    }
    public Provider getProviderById(int id) {
        String sql = "select id,name,descs,contactPerson,phone from Provider where id = ?";
        Map<String, Object> map = JdbcUtil.queryOne(sql, id);
        Provider provider = JSONObject.parseObject(JSONObject.toJSONString(map), Provider.class);
        return provider;
    }
    //根据用户名获取provider
    public Provider getProviderByName(String name){
        String sql = "select id,name,descs,contactPerson,phone from Provider where name = ?";
        Map<String, Object> map = JdbcUtil.queryOne(sql, name);
        Provider Provider = JSONObject.parseObject(JSONObject.toJSONString(map), Provider.class);
        return Provider;
    }
    public void updateProvider(Provider provider) {
        ValidationUtil.validate(provider);//检查字段合法性
        Provider providerById = getProviderById(provider.getId());
        if (providerById != null) {
            providerById.setName(provider.getName());
            providerById.setPhone(provider.getPhone());
            providerById.setDescs(provider.getDescs());
            providerById.setContactPerson(provider.getContactPerson());
            String sql = "update Provider set name = ?,descs = ?, contactPerson = ?,phone = ? where id = ?";
            JdbcUtil.update(sql, providerById.getName(), providerById.getDescs(),providerById.getContactPerson(), providerById.getPhone(),providerById.getId());
        }
    }
    //新增
    public void addProvider(Provider provider){
        ValidationUtil.validate(provider);//检查字段合法性
        String sql="insert into Provider(name,descs,contactPerson,phone)values(?,?,?,?)";
        JdbcUtil.update(sql,provider.getName(), provider.getDescs(), provider.getContactPerson(), provider.getPhone());
    }
    //查询
    public List<Provider> getProviderList(Provider provider) {
        String sql = "select id,name,descs,contactPerson,phone from Provider where name like ? and descs like ?";
        if(provider.getDescs().trim().length()==0&&provider.getName().trim().length()==0){
            return getProviderList();
        }
        List<Map<String, Object>> list = JdbcUtil.queryALL(sql,"%"+provider.getName().trim()+"%","%"+provider.getDescs().trim()+"%");
        List<Provider> providers = JSONObject.parseArray(JSONObject.toJSONString(list), Provider.class);
        return providers;
    }
    //删除
    public void deleteProvider(Provider provider){
        Provider providerById = getProviderById(provider.getId());
        String sql="delete from Provider where id=?";
        JdbcUtil.update(sql,providerById.getId());
    }








}