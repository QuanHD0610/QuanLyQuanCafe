/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.ChiTietHoaDon;
import POJO.ChiTietNLNH;
import POJO.HangHoa;
import POJO.LoaiHH;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class ChiTietHoaDonDAO {
    public static ArrayList<ChiTietHoaDon> layDanhSachChiTietHoaDon(int MaHoaDon) {
        ArrayList<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<>();
        try {
            String sql = "select * from ChiTietHoaDon where MaHDBH = " + MaHoaDon;
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            java.sql.ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaHDBH = rs.getInt("MaHDBH");
                int MaHH = rs.getInt("MaHH");
                int SoLuong = rs.getInt("SoLuong");
                int ThanhTien = rs.getInt("ThanhTien");

                ChiTietHoaDon chiTietHD = new ChiTietHoaDon(MaHDBH, MaHH, SoLuong, ThanhTien);
                dsChiTietHoaDon.add(chiTietHD);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dsChiTietHoaDon;
    }
    public static boolean insertChiTietHoaDon(ChiTietHoaDon cthd) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "insert into ChiTietHoaDon(MaHDBH, MaHH, SoLuong, ThanhTien) values (?,?,?,?)";
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setInt(1, cthd.getMaHDBH());
            ps.setInt(2, cthd.getMaHH());
            ps.setInt(3, cthd.getSoLuong());
            ps.setInt(4, cthd.getThanhTien());
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
    
    public static boolean deleteChiTietHoaDon(ChiTietHoaDon cthd) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "delete ChiTietHoaDon where MaHH = ? and MaHDBH= ?";
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setInt(1, cthd.getMaHH());
            ps.setInt(2, cthd.getMaHDBH());
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
    public static boolean updatechiTietHoaDon(ChiTietHoaDon cthd) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "update ChiTietHoaDon set SoLuong = ? where MaHH = ? and MaHDBH = ?";
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setInt(1, cthd.getSoLuong());
            ps.setInt(2, cthd.getMaHH());
            ps.setInt(3, cthd.getMaHDBH());
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
    public static boolean kiemTraTonTaiMaMon(int MaHDBH, int maMon)
    {
        boolean check = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "Select * from CHITIETHOADON where MaHDBH = ? and MaHH = ?";
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setInt(1, MaHDBH);
            ps.setInt(2, maMon);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return check;
    }
    public static ChiTietHoaDon searchChiTietHoaDon(int MaHDBH, int maHH) {
        ChiTietHoaDon cthd = null;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "SELECT * FROM CHITIETHOADON WHERE MaHDBH = ? and MaHH = ?";
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setInt(1, MaHDBH);
            ps.setInt(2, maHH);
            java.sql.ResultSet rs = ps.executeQuery();

            // Di chuyển con trỏ đến hàng dữ liệu đầu tiên (nếu có)
            if (rs.next()) {
                // Lấy dữ liệu từ hàng hiện tại
                int soLuong = rs.getInt("SoLuong");
                int thanhTien = rs.getInt("ThanhTien");

                // Tạo đối tượng HangHoa với dữ liệu từ ResultSet
                cthd = new ChiTietHoaDon(MaHDBH, maHH, soLuong, thanhTien);
            }

            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cthd;
    }
}
