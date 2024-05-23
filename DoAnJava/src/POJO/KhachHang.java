/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Administrator
 */
public class KhachHang {
    private int maKH;
    private int maLKH;
    private String tenKH;
    private String diaChi;
    private String sdt;
    private int diemTichLuy;

    // Constructors, getters and setters
    public KhachHang() {}

    public KhachHang(int maKH, int maLKH, String tenKH, String diaChi, String sdt, int diemTichLuy) {
        this.maKH = maKH;
        this.maLKH = maLKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.diemTichLuy = diemTichLuy;
    }

    // Getters and Setters
    // ...

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaLKH() {
        return maLKH;
    }

    public void setMaLKH(int maLKH) {
        this.maLKH = maLKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }
    
}

