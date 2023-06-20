package com.example.student_management_sys.model;

public class Student {

    private String MSSV;
    private String HoTen;
    private String GioiTinh;
    private String TrangThai;

    private String QueQuan;

    private String Lop;
    private String LoaiDaoTao;
    private String NgaySinh;
    private String SoDienThoai;
    private String HeDaoTao;
    private String ChuyenNganh;
    private String NganhHoc;
    private String NgayVao;

    public Student(String MSSV, String hoTen, String gioiTinh, String trangThai, String queQuan, String lop, String loaiDaoTao, String ngaySinh, String soDienThoai, String heDaoTao, String chuyenNganh, String nganhHoc, String ngayVao) {
        this.MSSV = MSSV;
        HoTen = hoTen;
        GioiTinh = gioiTinh;
        TrangThai = trangThai;
        QueQuan = queQuan;
        Lop = lop;
        LoaiDaoTao = loaiDaoTao;
        NgaySinh = ngaySinh;
        SoDienThoai = soDienThoai;
        HeDaoTao = heDaoTao;
        ChuyenNganh = chuyenNganh;
        NganhHoc = nganhHoc;
        NgayVao = ngayVao;
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

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String queQuan) {
        QueQuan = queQuan;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        Lop = lop;
    }

    public String getLoaiDaoTao() {
        return LoaiDaoTao;
    }

    public void setLoaiDaoTao(String loaiDaoTao) {
        LoaiDaoTao = loaiDaoTao;
    }

    public String getHeDaoTao() {
        return HeDaoTao;
    }

    public void setHeDaoTao(String heDaoTao) {
        HeDaoTao = heDaoTao;
    }

    public String getChuyenNganh() {
        return ChuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        ChuyenNganh = chuyenNganh;
    }

    public String getNganhHoc() {
        return NganhHoc;
    }

    public void setNganhHoc(String nganhHoc) {
        NganhHoc = nganhHoc;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getNgayVao() {
        return NgayVao;
    }

    public void setNgayVao(String ngayVao) {
        NgayVao = ngayVao;
    }


}
