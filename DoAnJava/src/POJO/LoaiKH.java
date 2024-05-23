/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Administrator
 */
public class LoaiKH {
    private int maLKH;
    private String tenLKH;

    // Constructors, getters and setters
    public LoaiKH() {}

    public LoaiKH(int maLKH, String tenLKH) {
        this.maLKH = maLKH;
        this.tenLKH = tenLKH;
    }

    // Getters and Setters
    // ...

    public int getMaLKH() {
        return maLKH;
    }

    public void setMaLKH(int maLKH) {
        this.maLKH = maLKH;
    }

    public String getTenLKH() {
        return tenLKH;
    }

    public void setTenLKH(String tenLKH) {
        this.tenLKH = tenLKH;
    }
    
}

