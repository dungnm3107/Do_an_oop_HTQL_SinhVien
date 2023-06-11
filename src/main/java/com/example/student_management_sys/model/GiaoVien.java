package com.example.student_management_sys.model;

public class GiaoVien {
//
//SELECT CONCAT_WS(';',
//                 Ma_GV, TrinhDo, Name_CN, Sdt_CN
//                 ) AS concatenated_values
//    FROM giaoVien
//    INNER JOIN caNhan cn ON cn.CCCD = giaoVien.CCCD

    String maGV;
    String trinhDo;
    String nameCN;
    String sdtCN;

    public GiaoVien(String maGV, String trinhDo, String nameCN, String sdtCN) {
        this.maGV = maGV;
        this.trinhDo = trinhDo;
        this.nameCN = nameCN;
        this.sdtCN = sdtCN;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public String getNameCN() {
        return nameCN;
    }

    public void setNameCN(String nameCN) {
        this.nameCN = nameCN;
    }

    public String getSdtCN() {
        return sdtCN;
    }

    public void setSdtCN(String sdtCN) {
        this.sdtCN = sdtCN;
    }
}
