/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Administrator
 */
public class HangHoa {
    private int maHH;
    private int maLH;
    private String tenHH;
    private String hinhAnh;
    private int giaSP;

    // Constructors, getters and setters
    public HangHoa() {}

    public HangHoa(int maHH, int maLH, String tenHH, String hinhAnh, int giaSP) {
        this.maHH = maHH;
        this.maLH = maLH;
        this.tenHH = tenHH;
        this.hinhAnh = hinhAnh;
        this.giaSP = giaSP;
    }

    // Getters and Setters
    // ...

    public int getMaHH() {
        return maHH;
    }

    public void setMaHH(int maHH) {
        this.maHH = maHH;
    }

    public int getMaLH() {
        return maLH;
    }

    public void setMaLH(int maLH) {
        this.maLH = maLH;
    }

    public String getTenHH() {
        return tenHH;
    }

    public void setTenHH(String tenHH) {
        this.tenHH = tenHH;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }
    @Override
    public String toString(){
        return getTenHH();
    }
}

