package com.example.student_management_sys.model;

public class KetQuaHocTap {
//    "SELECT Ma_HK, mh.Ma_MH, Name_MH, So_Tin, Loai_HP, Diem_QT, Diem_BS, Diem_Thi, Diem_KT, HoanThanh " +
// HK01-2023 	14880203  	Trắc địa kỹ thuật	2	Bắt buộc	7,2	1	7,8	8,6	1

    String MaHK;
    String MaMH;
    String NameMH;
    Integer SoTin;
    String LoaiHP;
    float DiemQT;
    float DiemBS;
    float DiemThi;
    float DiemKT;
    boolean hoanThanh;

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
        if(hoanThanh == 1) this.hoanThanh = true;
        else this.hoanThanh = false;
    }
    public void displayKQHT(){
        System.out.println(MaHK + " " + MaMH + " " + NameMH + " " + SoTin + " " + LoaiHP + " " + DiemQT + " " + DiemBS + " " + DiemThi + " " + DiemKT + " " + hoanThanh);
    }
}

