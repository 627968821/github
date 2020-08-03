package club.banyuan.mbm.entity;

import java.util.Objects;

public class Provider {
    private int id;
    @Validation(regex = "[^!@#$%^&*()?]*", msg = "供应商姓名不合法或供应商姓名为空", isRequired = true)
    private String name;
    private String descs;
    private String contactPerson;
    @Validation(regex = "[1][358][0-9]{9}", msg = "手机号不合法", isRequired = false)
    private String phone;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return name.equals(provider.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
