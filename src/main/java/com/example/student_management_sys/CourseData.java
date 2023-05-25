package com.example.student_management_sys;

public class CourseData {
    private String maMH;
    private String nameMH;
    private String nameLop;
    private int soTin;
    private String loaiHP;

    public CourseData(String maMH, String nameMH, String nameLop, int soTin, String loaiHP) {
        this.maMH = maMH;
        this.nameMH = nameMH;
        this.nameLop = nameLop;
        this.soTin = soTin;
        this.loaiHP = loaiHP;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getNameMH() {
        return nameMH;
    }

    public void setNameMH(String nameMH) {
        this.nameMH = nameMH;
    }

    public String getNameLop() {
        return nameLop;
    }

    public void setNameLop(String nameLop) {
        this.nameLop = nameLop;
    }

    public int getSoTin() {
        return soTin;
    }

    public void setSoTin(int soTin) {
        this.soTin = soTin;
    }

    public String getLoaiHP() {
        return loaiHP;
    }

    public void setLoaiHP(String loaiHP) {
        this.loaiHP = loaiHP;
    }
}
