/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Administrator
 */
public class ChiTietNLHH {
    private int maNL;
    private int maHH;
    private float SoLuong;

    // Constructors, getters and setters

    public ChiTietNLHH() {
    }

    public ChiTietNLHH(int maNL, int maHH, float SoLuong) {
        this.maNL = maNL;
        this.maHH = maHH;
        this.SoLuong = SoLuong;
    }

    public int getMaNL() {
        return maNL;
    }

    public void setMaNL(int maNL) {
        this.maNL = maNL;
    }

    public int getMaHH() {
        return maHH;
    }

    public void setMaHH(int maHH) {
        this.maHH = maHH;
    }

    public float getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(float SoLuong) {
        this.SoLuong = SoLuong;
    }
    
}

