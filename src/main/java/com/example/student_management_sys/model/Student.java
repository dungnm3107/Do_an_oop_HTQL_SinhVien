package com.example.student_management_sys.model;

public class Student {

    private String MSSV;
    private String HoTen;
    private String GioiTinh;
    private String TrangThai;

    private String QueQuan;

    private String Lop;
    private String LoaiDaoTao;

    private String HeDaoTao;
    private String ChuyenNganh;
    private String NganhHoc;
    private String NgayVao;

public Student(String MSSV, String HoTen, String GioiTinh, String QueQuan,String TrangThai, String Lop, String LoaiDaoTao, String HeDaoTao, String ChuyenNganh, String NganhHoc, String NgayVao) {
        this.MSSV = MSSV;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.TrangThai = TrangThai;
        this.QueQuan = QueQuan;
        this.Lop = Lop;
        this.LoaiDaoTao = LoaiDaoTao;
        this.HeDaoTao = HeDaoTao;
        this.ChuyenNganh = ChuyenNganh;
        this.NganhHoc = NganhHoc;
        this.NgayVao = NgayVao;
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

    public String getNgayVao() {
        return NgayVao;
    }

    public void setNgayVao(String ngayVao) {
        NgayVao = ngayVao;
    }

    public void DisplayStudent() {
        System.out.print(this.MSSV + "\t");
        System.out.print(this.HoTen + "\t");
        System.out.print(this.GioiTinh + "\t");
        System.out.print(this.TrangThai + "\t");
        System.out.print(this.QueQuan + "\t");
        System.out.print(this.Lop + "\t");
        System.out.print(this.LoaiDaoTao + "\t");
        System.out.print(this.HeDaoTao + "\t");
        System.out.print(this.ChuyenNganh + "\t");
        System.out.print(this.NganhHoc + "\t");
        System.out.print(this.NgayVao + "\t");
        System.out.println();
    }

}
