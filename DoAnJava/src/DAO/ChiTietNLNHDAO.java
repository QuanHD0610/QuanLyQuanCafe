/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import POJO.ChiTietNLNH;

/**
 *
 * @author Laptop
 */
public class ChiTietNLNHDAO {
    public static ArrayList<ChiTietNLNH> layDanhSachChiTietNLNH() {
        ArrayList<ChiTietNLNH> dsChiTietNhapHang = new ArrayList<>();
        try {
            String sql = "select * from ChiTietNLNH";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaNL = rs.getInt("MaNL");
                int MaNH = rs.getInt("MaNH");
                int SoLuong = rs.getInt("SoLuong");

                ChiTietNLNH chiTietNLNH = new ChiTietNLNH(MaNL, MaNH, SoLuong);
                dsChiTietNhapHang.add(chiTietNLNH);
            }
        } catch (Exception e) {
        }
        return dsChiTietNhapHang;
    }

    public static boolean insertChiTietNLNH(ChiTietNLNH ctnlnh) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "insert into ChiTietNLNH(MaNL, MaNH, SoLuong) values("+ctnlnh.getMaNL()+", "+ctnlnh.getMaNH()+", "+ctnlnh.getSoLuong()+")";
            int n = provider.executeUpdate(sql);
            if (n == 1) {
                kq = true;
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public static boolean deleteChiTietNLNH(ChiTietNLNH nh) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "delete NhapHang where MaNH = " + nh.getMaNH() + "";
            int n = provider.executeUpdate(sql);
            if (n == 1) {
                kq = true;
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public static boolean updateChiTietNLNH(ChiTietNLNH ctnlnh) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "update ChiTietNLNH set MaNL = " + ctnlnh.getMaNL()+ ", SoLuong = "+ctnlnh.getSoLuong()+" where MaNH = " + ctnlnh.getMaNH()+ "";
            int n = provider.executeUpdate(sql);
            if (n == 1) {
                kq = true;
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public static ArrayList<ChiTietNLNH> searchNhapHang(ChiTietNLNH ctnlnh) {
        ArrayList<ChiTietNLNH> dsChiTietNLNH = new ArrayList<>();
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "select * from ChiTietNLNH where MaNH = '" + ctnlnh.getMaNH()+ "'";
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaNL = rs.getInt("MaNL");
                int MaNH = rs.getInt("MaNH");
                int SoLuong = rs.getInt("SoLuong");

                ChiTietNLNH chiTietNLNH = new ChiTietNLNH(MaNL, MaNH, SoLuong);
                dsChiTietNLNH.add(chiTietNLNH);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsChiTietNLNH;
    }
}
