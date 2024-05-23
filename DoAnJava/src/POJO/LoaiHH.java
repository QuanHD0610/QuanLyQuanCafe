/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Administrator
 */
public class LoaiHH {
    private int maLH;
    private String tenLH;
    private String moTa;

    // Constructors, getters and setters
    public LoaiHH() {}

    public LoaiHH(int maLH, String tenLH, String moTa) {
        this.maLH = maLH;
        this.tenLH = tenLH;
        this.moTa = moTa;
    }

    // Getters and Setters
    // ...

    public int getMaLH() {
        return maLH;
    }

    public void setMaLH(int maLH) {
        this.maLH = maLH;
    }

    public String getTenLH() {
        return tenLH;
    }

    public void setTenLH(String tenLH) {
        this.tenLH = tenLH;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
}

