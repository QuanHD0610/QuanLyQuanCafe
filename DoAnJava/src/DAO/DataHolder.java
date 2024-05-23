/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


/**
 *
 * @author Admin
 */
public class DataHolder {
     private static int maHoaDon;
     private static int maNV;
     private static String phanQuyen;

    public static void setMaHoaDon(int maHoaDon) {
        DataHolder.maHoaDon = maHoaDon;
    }

    public static int getMaHoaDon() {
        return maHoaDon;
    }

    public static int getMaNV() {
        return maNV;
    }

    public static void setMaNV(int maNV) {
        DataHolder.maNV = maNV;
    }

    public static String getPhanQuyen() {
        return phanQuyen;
    }

    public static void setPhanQuyen(String phanQuyen) {
        DataHolder.phanQuyen = phanQuyen;
    }
    
    
}
