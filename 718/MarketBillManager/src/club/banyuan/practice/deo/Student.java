package club.banyuan.practice.deo;

public class Student {
    private int sno;
    private String sname;
    private String ssex;
    private int classNo;

    public Student(int sno, String sname, String ssex, int classNo) {
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
        this.classNo = classNo;
    }

    public Student(String sname, String ssex, int classNo) {
        this.sname = sname;
        this.ssex = ssex;
        this.classNo = classNo;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }
}
