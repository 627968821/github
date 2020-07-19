package club.banyuan.mbm.service;

import club.banyuan.mbm.entity.Provider;
import club.banyuan.mbm.exception.SupplierNameException;
import club.banyuan.mbm.uti.PropUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProviderService {
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

    public  void addSupplier(Provider provider) {//添加供应商
        if (isExist(provider)) {
            throw  new SupplierNameException("供应商已存在");
        } else {
            providerList.add(provider);
            save();
        }
    }

    public static boolean isExist(Provider provider) {//判断是否存在同名的供应商
        for (Provider provider1 : providerList) {
            if (provider.equals(provider1)) {
                return true;
            }
        }
        return false;
    }


    public static List<Provider> getProviderList() {
        return providerList;
    }

    public static void setProviderList(List<Provider> providerList) {
        ProviderService.providerList = providerList;
    }

    public static int getProviderId() {
        return providerId;
    }

    public static void setProviderId(int providerId) {
        ProviderService.providerId = providerId;
    }
}