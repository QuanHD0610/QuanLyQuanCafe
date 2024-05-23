/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Laptop
 */
public class ChiTietNLNH {
    private int MaNL;
    private int MaNH;
    private int SoLuong;

    public ChiTietNLNH() {
    }

    public ChiTietNLNH(int MaNL, int MaNH, int SoLuong) {
        this.MaNL = MaNL;
        this.MaNH = MaNH;
        this.SoLuong = SoLuong;
    }

    public int getMaNL() {
        return MaNL;
    }

    public void setMaNL(int MaNL) {
        this.MaNL = MaNL;
    }

    public int getMaNH() {
        return MaNH;
    }

    public void setMaNH(int MaNH) {
        this.MaNH = MaNH;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }
    
    
}
