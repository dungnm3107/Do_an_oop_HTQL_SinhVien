package com.example.student_management_sys.model;
public class Subjects {
    String LopMH;
    String MaSV;
    String TenSV;
    String MaMH;
    String TenMH;
    String Diem_QT;
    String Diem_Thi;
    String Diem_BS;
    String Diem_KT;

    public Subjects(String lopMH, String maSV, String tenSV,String maMH, String tenMH, String diem_QT, String diem_Thi, String diem_BS, String diem_KT) {
        LopMH = lopMH;
        MaSV = maSV;
        TenSV = tenSV;
        MaMH = maMH;
        TenMH = tenMH;
        Diem_QT = diem_QT;
        Diem_Thi = diem_Thi;
        Diem_BS = diem_BS;
        Diem_KT = diem_KT;
    }
    public void DisplaySubjects(){
        System.out.print(this.LopMH + ",");
        System.out.print(this.MaSV + ",");
        System.out.print(this.TenSV + ",");
        System.out.print(this.TenMH + ",");
        System.out.print(this.Diem_QT + ",");
        System.out.print(this.Diem_Thi + ",");
        System.out.print(this.Diem_BS + ",");
        System.out.print(this.Diem_KT + ",");
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String maMH) {
        MaMH = maMH;
    }

    public String getLopMH() {
        return LopMH;
    }

    public void setLopMH(String lopMH) {
        LopMH = lopMH;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String maSV) {
        MaSV = maSV;
    }

    public String getTenSV() {
        return TenSV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String tenMH) {
        TenMH = tenMH;
    }

    public String getDiem_QT() {
        return Diem_QT;
    }

    public void setDiem_QT(String diem_QT) {
        Diem_QT = diem_QT;
    }

    public String getDiem_Thi() {
        return Diem_Thi;
    }

    public void setDiem_Thi(String diem_Thi) {
        Diem_Thi = diem_Thi;
    }

    public String getDiem_BS() {
        return Diem_BS;
    }

    public void setDiem_BS(String diem_BS) {
        Diem_BS = diem_BS;
    }

    public String getDiem_KT() {
        return Diem_KT;
    }

    public void setDiem_KT(String diem_KT) {
        Diem_KT = diem_KT;
    }
}
