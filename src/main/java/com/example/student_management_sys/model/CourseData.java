package com.example.student_management_sys.model;

public class CourseData {
    private String maMH;
    private String nameMH;
    private String nameLop;
    private int soTin;

    private int hocPhi;
    private String loaiHP;
    private boolean trangThaiDangKi;

    public CourseData(String maMH, String nameMH, String nameLop, int soTin, int hocPhi, String loaiHP, boolean trangThaiDangKi) {
        this.maMH = maMH;
        this.nameMH = nameMH;
        this.nameLop = nameLop;
        this.soTin = soTin;
        this.hocPhi = hocPhi;
        this.loaiHP = loaiHP;
        this.trangThaiDangKi = trangThaiDangKi;
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
    public int getHocPhi() {
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

}
