/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.LoaiHH;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author Laptop
 */
public class LoaiHHDAO {

    public static ArrayList<LoaiHH> layDanhSachLoaiHangHoa() {
        ArrayList<LoaiHH> dsLoaiHH = new ArrayList<LoaiHH>();
        try {
            String sql = "Select * from LoaiHH";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaLH = rs.getInt("MaLH");
                String TenLH = rs.getString("TenLH");
                String MoTa = rs.getString("MoTa");                
                
                LoaiHH lhh = new LoaiHH(MaLH, TenLH, MoTa);
                dsLoaiHH.add(lhh);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiHH;
    }
    
    public static boolean insertLoaiHangHoa(LoaiHH lhh) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "Insert into LoaiHH(TenLH, MoTa) values (?, ?)";
            PreparedStatement preparedStatement = provider.connection.prepareStatement(sql);
            preparedStatement.setString(1, lhh.getTenLH());
            preparedStatement.setString(2, lhh.getMoTa());
            int n = preparedStatement.executeUpdate();
            if (n == 1) {
                kq = true;
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public static boolean deleteLoaiHangHoa(LoaiHH lhh) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "delete LoaiHH where MaLH = ?";
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setInt(1, lhh.getMaLH());
            int n = ps.executeUpdate();
            if (n == 1) {
                kq = true;
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public static boolean updateLoaiHangHoa(LoaiHH lhh) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "Update LoaiHH set TenLH = ?, MoTa = ? where MaLH = ?";
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setString(1, lhh.getTenLH());
            ps.setString(2, lhh.getMoTa());
            ps.setInt(3, lhh.getMaLH());
            int n = ps.executeUpdate();
            if (n == 1) {
                kq = true;
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public static ArrayList<LoaiHH> searchLoaiHangHoa(LoaiHH lhh) {
        boolean kq = false;
        ArrayList<LoaiHH> dsLoaiHH = new ArrayList<>();
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "select * from LoaiHH where TenLH like ?";
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setString(1, "%" + lhh.getTenLH() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int MaLH = rs.getInt("MaLH");
                String TenLH = rs.getString("TenLH");
                String MoTa = rs.getString("MoTa");
                LoaiHH l = new LoaiHH(MaLH, TenLH, MoTa);
                dsLoaiHH.add(l);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiHH;
    }

    public static LoaiHH timLoaiHHTheoMa(int MaLH) {
        LoaiHH lhh = new LoaiHH();
        try {
            String sql = "select * from LoaiHH where MaLH = ?";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setInt(1, MaLH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String TenLH = rs.getString("TenLH");
                String MoTa = rs.getString("MoTa");
                
                lhh = new LoaiHH(MaLH, TenLH, MoTa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lhh;
    }
    
    public static LoaiHH timLoaiHHTheoTen(String TenLH) {
        LoaiHH lhh = new LoaiHH();
        try {
            String sql = "select * from LoaiHH where TenLH = ?";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setString(1, TenLH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int MaLH = rs.getInt("MaLH");
                String MoTa = rs.getString("MoTa");
                
                lhh = new LoaiHH(MaLH, TenLH, MoTa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lhh;
    }
}
