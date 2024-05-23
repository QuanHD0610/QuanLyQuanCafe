/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import POJO.ChiTietNLHH;
import POJO.NguyenLieu;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class NguyenLieuDao {
    public static ArrayList<NguyenLieu> layDanhSachNguyenLieu() {
        ArrayList<NguyenLieu> dsHangHoa = new ArrayList<>();
        try {
            String sql = "select * from nguyenlieu";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaNL = rs.getInt("MaNL");
                String TenNL = rs.getString("TenNL");
                String DVT = rs.getString("DonViTinh");
                float SLTon = rs.getFloat("SoLuongTon");

                NguyenLieu hh = new NguyenLieu(MaNL, TenNL, DVT,SLTon);
                dsHangHoa.add(hh);
            }
        } catch (Exception e) {
        }
        return dsHangHoa;
    }

    public static boolean insertNguyenLieu(NguyenLieu hh) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "insert into NguyenLieu( TenNL, DonViTinh, SoLuongTon) values( N'" + hh.getTenNL()+ "', '" + hh.getDonViTinh()+ "', " + hh.getSoLuongTon()+ ")";
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

    public static boolean deleteHangHoa(NguyenLieu hh) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "delete NguyenLieu where MaNL = " + hh.getMaNL()+ "";
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

    public static boolean updateNguyenLieu(NguyenLieu hh) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "update NguyenLieu set TenNL = N'" + hh.getTenNL()+ "', DonViTinh = '" + hh.getDonViTinh()+ "', SoLuongTon = " + hh.getSoLuongTon()+ " where MaNL = " + hh.getMaNL()+ "";
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
    
    //Lấy danh sách nguyên liệu từ mã hàng hóa
    public static ArrayList<ChiTietNLHH> layDanhSachNguyenLieuTheoHangHoa(int MaHH) {
        ArrayList<ChiTietNLHH> dsChiTietNLHH = new ArrayList<>();
        try {
            String sql = "select * from chitietnlhh where MaHH = ?";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setInt(1, MaHH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int MaNL = rs.getInt("MaNL");
                int maHH = rs.getInt("MaHH");                
                float SoLuong = rs.getFloat("SoLuong");

                ChiTietNLHH ctnlhh = new ChiTietNLHH(MaNL, maHH, SoLuong);
                dsChiTietNLHH.add(ctnlhh);
            }
        } catch (Exception e) {
        }
        return dsChiTietNLHH;
    }

    //Lấy nguyên liệu bằng mã nguyên liệu
    public static NguyenLieu layNguyenLieuTheoMa(int MaNL) {
        NguyenLieu nguyenLieu = new NguyenLieu();
        try {
            String sql = "select * from nguyenlieu where MaNL = ?";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setInt(1, MaNL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String TenNL = rs.getString("TenNL");
                String DonViTinh = rs.getString("DonViTinh");
                float SoLuongTon = rs.getFloat("SoLuongTon");

                nguyenLieu = new NguyenLieu(MaNL, TenNL, DonViTinh, SoLuongTon);
            }
        } catch (Exception e) {
        }
        return nguyenLieu;
    }
}
