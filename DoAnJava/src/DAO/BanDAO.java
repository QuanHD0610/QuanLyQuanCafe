/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import POJO.Ban;
import POJO.LoaiHH;
import java.sql.PreparedStatement;

/**
 *
 * @author Laptop
 */
public class BanDAO {
    public static ArrayList<Ban> layDanhSachBan() {
        ArrayList<Ban> dsBan = new ArrayList<>();
        try {
            String sql = "select * from Ban";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaBan = rs.getInt("MaBan");
                String TenBan = rs.getString("TenBan");
                String TrangThai = rs.getString("TrangThai");
                int HDHienTai = rs.getInt("HDHienTai");
                
                Ban b = new Ban(MaBan, TenBan, TrangThai, HDHienTai);
                dsBan.add(b);
            }
        } catch (Exception e) {
        }
        return dsBan;
    }
    
    public static boolean insertBan(Ban ban) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "insert into Ban(TenBan, TrangThai, HDHienTai) values('"+ban.getTenBan()+"', N'"+ban.getTrangThai()+"', "+ban.getHdHienTai()+")";
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
    
    public static boolean deleteban(Ban ban) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "delete Ban where Maban = "+ban.getMaBan()+"";
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
    
    public static boolean updateBan(Ban ban) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "update Ban set TenBan = N'"+ban.getTenBan()+"', TrangThai = '"+ban.getTrangThai()+"', HDHienTai = "+ban.getHdHienTai()+" where MaBan = "+ban.getMaBan()+"";
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
    
    public static ArrayList<Ban> searchBan(Ban ban) {
        ArrayList<Ban> dsBan = new ArrayList<>();
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "select * from Ban where TenBan like '%"+ban.getTenBan()+"%'";
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                int MaBan = rs.getInt("MaBan");
                String TenBan = rs.getString("TenBan");
                String TrangThai = rs.getString("TrangThai");
                int HDHienTai = rs.getInt("HDHienTai");
                
                Ban b = new Ban(MaBan, TenBan, TrangThai, HDHienTai);
                dsBan.add(b);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsBan;
    }
    public static Ban timBanTheoTen(String TenBan) {
        Ban ban = new Ban();
        try {
            String sql = "select * from BAN where TenBan = ?";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setString(1, TenBan);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int MaBan = rs.getInt("MaBan");
                int HDHT = rs.getInt("HDHienTai");
                String TrangThai = rs.getString("TrangThai");
                
                ban = new Ban(MaBan, TenBan, TrangThai, HDHT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ban;
    }
    public static ArrayList<Ban> searchBanTheoTinhTrang(String trangThai) {
        ArrayList<Ban> dsBan = new ArrayList<>();
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "select * from Ban where TrangThai = N'"+trangThai+"'";
            ResultSet rs = provider.executeQuery(sql);
            while(rs.next()){
                int MaBan = rs.getInt("MaBan");
                String TenBan = rs.getString("TenBan");
                String TrangThai = rs.getString("TrangThai");
                int HDHienTai = rs.getInt("HDHienTai");
                
                Ban b = new Ban(MaBan, TenBan, TrangThai, HDHienTai);
                dsBan.add(b);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsBan;
    }
}
