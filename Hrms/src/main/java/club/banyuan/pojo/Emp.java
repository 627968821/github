package club.banyuan.pojo;

public class Emp {
    /**
     * CREATE TABLE `emp` (
     *   `id` int(11) NOT NULL AUTO_INCREMENT,
     *   `name` varchar(255) DEFAULT NULL,
     *   `sex` varchar(255) DEFAULT NULL,
     *   `phone` varchar(255) DEFAULT NULL,
     *   `email` varchar(255) DEFAULT NULL,
     *   `address` varchar(255) DEFAULT NULL,
     *   `education` varchar(255) DEFAULT NULL,
     *   `birthday` varchar(255) DEFAULT NULL,
     *   `departmentId` int(11) DEFAULT NULL,
     *   `positionId` int(11) DEFAULT NULL,
     *   PRIMARY KEY (`id`),
     *   KEY `dep_key` (`departmentId`),
     *   KEY `pos_key` (`positionId`),
     *   CONSTRAINT `dep_key` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
     *   CONSTRAINT `pos_key` FOREIGN KEY (`positionId`) REFERENCES `positon` (`id`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     */
    private int id;
    private String name;
    private String sex;
    private String phone;
    private String email;
    private String address;
    private String education;
    private String birthday;
    private int departmentId;
    private int positionId;

    @Override
    public String toString() {
        return "Imp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", education='" + education + '\'' +
                ", birthday='" + birthday + '\'' +
                ", departmentId=" + departmentId +
                ", positionId=" + positionId +
                '}';
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
}
