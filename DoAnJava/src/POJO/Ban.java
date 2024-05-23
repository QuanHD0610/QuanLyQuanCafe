/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Administrator
 */
public class Ban {
    private int maBan;
    private String tenBan;
    private String trangThai;
    private int hdHienTai;

    // Constructors, getters and setters
    public Ban() {}

    public Ban(int maBan, String tenBan, String trangThai, int hdHienTai) {
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.trangThai = trangThai;
        this.hdHienTai = hdHienTai;
    }

    // Getters and Setters
    // ...

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getHdHienTai() {
        return hdHienTai;
    }

    public void setHdHienTai(int hdHienTai) {
        this.hdHienTai = hdHienTai;
    }
    
}

