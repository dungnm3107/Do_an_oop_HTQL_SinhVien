package com.example.student_management_sys.model;

public class StudentNew {
    private String MSSV;
    private String HoTen;
    private String GioiTinh;
    private String NgaySinh;
    private String QueQuan;
    private String SoDienThoai;
    private String Email;
    private String CCCD;
    private String Lop;



    private String Name_He;

    private String TrangThai;
    private String NgayVao;
    private String Pass;
    public StudentNew(String MSSV, String hoTen, String gioiTinh, String ngaySinh, String queQuan, String soDienThoai, String email, String CCCD, String lop, String trangThai, String ngayVao, String pass) {
        this.MSSV = MSSV;
        HoTen = hoTen;
        GioiTinh = gioiTinh;
        NgaySinh = ngaySinh;
        QueQuan = queQuan;
        SoDienThoai = soDienThoai;
        Email = email;
        this.CCCD = CCCD;
        Lop = lop;
        TrangThai = trangThai;
        NgayVao = ngayVao;
        Pass = pass;
    }
    public String getName_He() {
        return Name_He;
    }

    public void setName_He(String name_He) {
        Name_He = name_He;
    }
    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String queQuan) {
        QueQuan = queQuan;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        Lop = lop;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getNgayVao() {
        return NgayVao;
    }

    public void setNgayVao(String ngayVao) {
        NgayVao = ngayVao;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
