/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class HoaDon {
    private int maHDBH;
    private int maNV;
    private int maBan;
    private Date ngayXuatHD;
    private int tongTien;
    private int diemTL;
    private float giamGia;
    private int maKH;
    private int soTienThanhToan;

    // Constructors, getters and setters
    public HoaDon() {}

    public HoaDon(int maHDBH, int maNV, int maBan, Date ngayXuatHD, int tongTien, int diemTL, float giamGia, int maKH, int soTienThanhToan) {
        this.maHDBH = maHDBH;
        this.maNV = maNV;
        this.maBan = maBan;
        this.ngayXuatHD = ngayXuatHD;
        this.tongTien = tongTien;
        this.diemTL = diemTL;
        this.giamGia = giamGia;
        this.maKH = maKH;
        this.soTienThanhToan = soTienThanhToan;
    }

    // Getters and Setters
    // ...

    public int getMaHDBH() {
        return maHDBH;
    }

    public void setMaHDBH(int maHDBH) {
        this.maHDBH = maHDBH;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public Date getNgayXuatHD() {
        return ngayXuatHD;
    }

    public void setNgayXuatHD(Date ngayXuatHD) {
        this.ngayXuatHD = ngayXuatHD;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getDiemTL() {
        return diemTL;
    }

    public void setDiemTL(int diemTL) {
        this.diemTL = diemTL;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getSoTienThanhToan() {
        return soTienThanhToan;
    }

    public void setSoTienThanhToan(int soTienThanhToan) {
        this.soTienThanhToan = soTienThanhToan;
    }
    
}

