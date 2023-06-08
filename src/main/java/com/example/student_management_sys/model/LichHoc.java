package com.example.student_management_sys.model;

import java.sql.Date;

public class LichHoc {
    String MaHK;
    String NameMH;
    String SoTin;
    String NameLop;
    String Thu;
    String Ca;
    String Phong;
    String NameCN;
    String SdtCN;
    String Email;

    public LichHoc( String maHK,String nameMH, String soTin, String nameLop, String thu, String ca, String phong, String nameCN, String sdtCN, String email) {
        MaHK = maHK;
        NameMH = nameMH;
        SoTin = soTin;
        NameLop = nameLop;
        Thu = thu;
        Ca = ca;
        Phong = phong;
        NameCN = nameCN;
        SdtCN = sdtCN;
        Email = email;
    }

    public String getMaHK() {
        return MaHK;
    }

    public void displayLichHoc(){
        System.out.println(  " " + NameMH + " " + SoTin + " " + NameLop + " " + Thu + " " + Ca + " " + Phong  + NameCN  + " " + SdtCN + " " + Email);
    }

    public String getNameMH() {
        return NameMH;
    }

    public String getSoTin() {
        return SoTin;
    }

    public String getNameLop() {
        return NameLop;
    }

    public String getThu() {
        return Thu;
    }

    public String getCa() {
        return Ca;
    }

    public String getPhong() {
        return Phong;
    }

    public String getNameCN() {
        return NameCN;
    }

    public String getSdtCN() {
        return SdtCN;
    }

    public String getEmail() {
        return Email;
    }

}