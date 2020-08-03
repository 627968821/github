package club.banyuan.mbm.service;

import club.banyuan.mbm.entity.Provider;
import club.banyuan.mbm.exception.FormPostException;
import club.banyuan.mbm.exception.MbmException;
import club.banyuan.mbm.exception.ValidationException;
import club.banyuan.mbm.uti.PropUtil;
import club.banyuan.mbm.uti.ValidationUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProviderService extends ValidationUtil{
    private static int providerId;
    private static List<Provider> providerList;
    public static final String PROVIDER_STORE_PATH = "provider.store.path";

    static {
        load();
        Optional<Provider> max = providerList.stream().max(Comparator.comparing(Provider::getId));
        providerId = max.map(provider -> provider.getId() + 1).orElse(1);
    }

    private static void load() {
        File file = new File(PropUtil.getProp(PROVIDER_STORE_PATH));
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = fileInputStream.readAllBytes();
                providerList = JSONObject.parseArray(new String(bytes), Provider.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("用戶文件不存在");
            providerList = new LinkedList<>();
        }
    }

    public static void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(PropUtil.getProp(PROVIDER_STORE_PATH));
            String string = JSONObject.toJSONString(providerList);
            byte[] bytes = string.getBytes();
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSupplier(Provider provider) {//添加供应商
        ValidationUtil.validate(provider);
        if (isExist(provider)) {
            throw new FormPostException("供应商已存在");
        } else {
            provider.setId(providerId++);
            providerList.add(provider);
            save();
        }
    }

    public boolean isExist(Provider provider) {//判断是否存在同名的供应商
        for (Provider provider1 : providerList) {
            if (provider.equals(provider1)) {
                return true;
            }
        }
        return false;
    }

    public List<Provider> getProviderList(Provider provider) {//根据provider对象检索返回链表
        if (provider.getName() == null && provider.getDescs().trim().length() == 0) {
            return getProviderList();
        }
        List<Provider> providerList = new LinkedList<>();
        for (Provider pro : getProviderList()) {
            if (pro.getName().contains(provider.getName()) && pro.getDescs().contains(provider.getDescs())) {
                providerList.add(pro);
            } else if (provider.getName() == null && pro.getDescs().contains(provider.getDescs())) {
                providerList.add(pro);
            } else if (provider.getDescs() == null && pro.getName().contains(provider.getName())) {
                providerList.add(pro);
            }
        }
        return providerList;
    }

    public List<Provider> getProviderList() {
        return providerList;
    }

    public void setProviderList(List<Provider> providerList) {
        ProviderService.providerList = providerList;
    }

    public int getProviderId() {
        return providerId;
    }


    public void setProviderId(int providerId) {
        ProviderService.providerId = providerId;
    }

    public List<Provider> upDateProvider(Provider provider) {//更新
        ValidationUtil.validate(provider);
        Provider providerById = getProviderById(provider.getId());
        providerById.setContactPerson(provider.getContactPerson());
        providerById.setDescs(provider.getDescs());
        providerById.setPhone(provider.getPhone());
        providerById.setName(provider.getName());
        save();
        return providerList;
    }

    public List<Provider> deleteProviderById(int id) {//根据id检索删除provider对象 返回删除后的providerList
        getProviderList().removeIf(provider -> id == provider.getId());
        save();
        return getProviderList();
    }

    public Provider getProviderById(int id) {
        List<Provider> collect = providerList.stream().filter(provider -> provider.getId() == id).collect(Collectors.toList());
        return collect.get(0);
    }

}