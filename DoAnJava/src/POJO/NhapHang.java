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
public class NhapHang {

    private int maNH;
    private Date NgayNhap;

    // Constructors, getters and setters

    public NhapHang() {
    }

    public NhapHang(int maNH, Date NgayNhap) {
        this.maNH = maNH;
        this.NgayNhap = NgayNhap;
    }

    public int getMaNH() {
        return maNH;
    }

    public void setMaNH(int maNH) {
        this.maNH = maNH;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }
    
}
