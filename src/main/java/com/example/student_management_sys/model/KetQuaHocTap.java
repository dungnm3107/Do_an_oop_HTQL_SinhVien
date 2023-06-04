package com.example.student_management_sys.model;

public class KetQuaHocTap {
    String MaHK;
    String MaMH;
    String NameMH;
    Integer SoTin;
    String LoaiHP;
    float DiemQT;
    float DiemBS;
    float DiemThi;
    float DiemKT;
    int hoanThanh;

    public KetQuaHocTap(String maHK, String maMH, String nameMH, int soTin, String loaiHP, float diemQT, float diemBS, float diemThi, float diemKT, int hoanThanh) {
        System.out.println(hoanThanh);
        MaHK = maHK;
        MaMH = maMH;
        NameMH = nameMH;
        SoTin = soTin;
        LoaiHP = loaiHP;
        DiemQT = diemQT;
        DiemBS = diemBS;
        DiemThi = diemThi;
        DiemKT = diemKT;
        hoanThanh = hoanThanh;
    }
    public void displayKQHT(){
        System.out.println(MaHK + " " + MaMH + " " + NameMH + " " + SoTin + " " + LoaiHP + " " + DiemQT + " " + DiemBS + " " + DiemThi + " " + DiemKT + " " + hoanThanh);
    }

    public String getMaHK() {
        return MaHK;
    }

    public void setMaHK(String maHK) {
        MaHK = maHK;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String maMH) {
        MaMH = maMH;
    }

    public String getNameMH() {
        return NameMH;
    }

    public void setNameMH(String nameMH) {
        NameMH = nameMH;
    }

    public Integer getSoTin() {
        return SoTin;
    }

    public void setSoTin(Integer soTin) {
        SoTin = soTin;
    }

    public String getLoaiHP() {
        return LoaiHP;
    }

    public void setLoaiHP(String loaiHP) {
        LoaiHP = loaiHP;
    }

    public float getDiemQT() {
        return DiemQT;
    }

    public void setDiemQT(float diemQT) {
        DiemQT = diemQT;
    }

    public float getDiemBS() {
        return DiemBS;
    }

    public void setDiemBS(float diemBS) {
        DiemBS = diemBS;
    }

    public float getDiemThi() {
        return DiemThi;
    }

    public void setDiemThi(float diemThi) {
        DiemThi = diemThi;
    }

    public float getDiemKT() {
        return DiemKT;
    }

    public void setDiemKT(float diemKT) {
        DiemKT = diemKT;
    }

    public int isHoanThanh() {
        return hoanThanh;
    }

    public void setHoanThanh(int hoanThanh) {
        this.hoanThanh = hoanThanh;
    }

}

