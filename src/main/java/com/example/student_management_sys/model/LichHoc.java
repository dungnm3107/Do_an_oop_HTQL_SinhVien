package com.example.student_management_sys.model;

import java.sql.Date;

public class LichHoc {
    String MaHK;
    String MaMH;
    String NameMH;
    String SoTin;
    String NameLop;
    String Thu;
    String Ca;
    String Phong;
    Date NgayBD;
    Date NgayKT;
    String MaGV;
    String NameCN;
    String TrinhDo;
    String SdtCN;
    String Email;

    public LichHoc(String maHK, String maMH, String nameMH, String soTin, String nameLop, String thu, String ca, String phong, Date ngayBD, Date ngayKT, String maGV, String nameCN, String trinhDo, String sdtCN, String email) {
        MaHK = maHK;
        MaMH = maMH;
        NameMH = nameMH;
        SoTin = soTin;
        NameLop = nameLop;
        Thu = thu;
        Ca = ca;
        Phong = phong;
        NgayBD = ngayBD;
        NgayKT = ngayKT;
        MaGV = maGV;
        NameCN = nameCN;
        TrinhDo = trinhDo;
        SdtCN = sdtCN;
        Email = email;
    }
    public void displayLichHoc(){
        System.out.println(MaHK + " " + MaMH + " " + NameMH + " " + SoTin + " " + NameLop + " " + Thu + " " + Ca + " " + Phong + " " + NgayBD + " " + NgayKT + " " + MaGV + " " + NameCN + " " + TrinhDo + " " + SdtCN + " " + Email);
    }

}
