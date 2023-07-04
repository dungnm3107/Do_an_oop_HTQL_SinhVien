package com.example.student_management_sys.model;

public class CourseData {
    private String maMH;
    private String nameMH;
    private String nameLop;
    private int soTin;

    private double hocPhi;
    private String loaiHP;
    private boolean trangThaiDangKi;


    public CourseData(String maMH, String nameMH, String soTin, String loaiHP) {
        int temp = Integer.parseInt(soTin);
        this.maMH = maMH;
        this.nameMH = nameMH;
        this.soTin = temp;
        this.loaiHP = loaiHP;
    }
    public CourseData(String maMH, String nameMH, String nameLop, int soTin, double hocPhi, String loaiHP, boolean trangThaiDangKi) {
        this.maMH = maMH;
        this.nameMH = nameMH;
        this.nameLop = nameLop;
        this.soTin = soTin;
        this.hocPhi = hocPhi;
        this.loaiHP = loaiHP;
        this.trangThaiDangKi = trangThaiDangKi;
    }

    public CourseData() {

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

    public int getSoTin() {
        return soTin;
    }
    public double getHocPhi() {
        return hocPhi;
    }

    public String getLoaiHP() {
        return loaiHP;
    }
    public boolean isTrangThaiDangKi() {
        return true;
    }

    public void setTrangThaiDangKi(boolean trangThaiDangKi) {
        this.trangThaiDangKi = trangThaiDangKi;
    }
    public void DisplayCourse(){
        System.out.println(maMH + " " + nameMH + " " + nameLop + " " + soTin + " " + hocPhi + " " + loaiHP + " " + trangThaiDangKi);
    }

}
