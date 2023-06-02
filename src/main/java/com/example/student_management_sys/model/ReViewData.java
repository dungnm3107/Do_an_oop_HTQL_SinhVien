package com.example.student_management_sys.model;

public class ReViewData {
    private String maMH;
    private String nameMH;
    private String nameLop;

    private String nameHK;
    private double diemThi;
    private boolean trangThaiNop;

    public ReViewData(String maMH, String nameMH, String nameLop, String nameHK, double diemThi,boolean trangThaiNop) {
        this.maMH = maMH;
        this.nameMH = nameMH;
        this.nameLop = nameLop;
        this.nameHK = nameHK;
        this.diemThi = diemThi;
        this.trangThaiNop=trangThaiNop;
    }

    public String getMaMH() {
        return maMH;
    }

    public String getNameMH() {
        return nameMH;
    }

    public String getNameLop() {
        return nameLop;
    }

    public String getNameHK() {
        return nameHK;
    }

    public double getDiemThi() {
        return diemThi;
    }

    public boolean isTrangThaiNop() {
        return true;
    }

    public void setTrangThaiNop(boolean trangThaiNop) {
        this.trangThaiNop = trangThaiNop;
    }

    public void DisplayCourse(){
        System.out.println(maMH + " " +nameMH+" "+ nameLop + "  " + nameHK + " " +diemThi);
    }
}
