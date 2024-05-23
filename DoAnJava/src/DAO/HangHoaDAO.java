/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import POJO.HangHoa;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author Laptop
 */
public class HangHoaDAO {

    public static ArrayList<HangHoa> layDanhSachHangHoa() {
        ArrayList<HangHoa> dsHangHoa = new ArrayList<>();
        try {
            String sql = "select * from hangHoa";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaHH = rs.getInt("MaHH");
                int MaLH = rs.getInt("MaLH");
                String TenHH = rs.getString("TenHH");
                String HinhAnh = rs.getString("HinhAnh");
                int GiaSP = rs.getInt("GiaSP");

                HangHoa hh = new HangHoa(MaHH, MaLH, TenHH, HinhAnh, GiaSP);
                dsHangHoa.add(hh);
            }
        } catch (Exception e) {
        }
        return dsHangHoa;
    }

    public static boolean insertHangHoa(HangHoa hh) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "insert into HangHoa(MaLH, TenHH, HinhAnh, GiaSP) values('" + hh.getMaLH() + "', N'" + hh.getTenHH() + "', '" + hh.getHinhAnh() + "', " + hh.getGiaSP() + ")";
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

    public static boolean deleteHangHoa(HangHoa hh) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "delete HangHoa where MaHH = " + hh.getMaHH() + "";
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

    public static boolean updateHangHoa(HangHoa hh) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "update HangHoa set TenHH = N'" + hh.getTenHH() + "', HinhAnh = '" + hh.getHinhAnh() + "', GiaSP = " + hh.getGiaSP() + ", MaLH = "+hh.getMaLH()+" where MaHH = " + hh.getMaHH() + "";
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

    public static ArrayList<HangHoa> searchHangHoa(HangHoa hh) {
        ArrayList<HangHoa> dsHangHoa = new ArrayList<>();
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "select * from HangHoa where TenHH like '%" + hh.getTenHH() + "%'";
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaHH = rs.getInt("MaHH");
                int MaLH = rs.getInt("MaLH");
                String TenHH = rs.getString("TenHH");
                String HinhAnh = rs.getString("HinhAnh");
                int GiaSP = rs.getInt("GiaSP");

                HangHoa h = new HangHoa(MaHH, MaLH, TenHH, HinhAnh, GiaSP);
                dsHangHoa.add(h);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHangHoa;
    }

    public static HangHoa searchHangHoaTheoma(int MaHH) {
        HangHoa hh = null;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "SELECT * FROM HangHoa WHERE MaHH = ?";
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setInt(1, MaHH);
            ResultSet rs = ps.executeQuery();

            // Di chuyển con trỏ đến hàng dữ liệu đầu tiên (nếu có)
            if (rs.next()) {
                // Lấy dữ liệu từ hàng hiện tại
                int maHH = rs.getInt("MaHH");
                int maLH = rs.getInt("MaLH");
                String tehHH = rs.getString("TenHH");
                String hinhAnh = rs.getString("HinhAnh");
                int giaSP = rs.getInt("GiaSP");

                // Tạo đối tượng HangHoa với dữ liệu từ ResultSet
                hh = new HangHoa(maHH, maLH, tehHH, hinhAnh, giaSP);
            }

            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hh;
    }
    
    public static ArrayList<HangHoa> searchHangHoaTheoLoai(int MaLH) {
        ArrayList<HangHoa> dsHangHoa = new ArrayList<>();
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "Select * from HangHoa where MaLH = ?";
            PreparedStatement ps = provider.connection.prepareStatement(sql);
            ps.setInt(1, MaLH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int MaHH = rs.getInt("MaHH");
                int maLH = rs.getInt("MaLH");
                String TenHH = rs.getString("TenHH");
                String HinhAnh = rs.getString("HinhAnh");
                int GiaSP = rs.getInt("GiaSP");

                HangHoa h = new HangHoa(MaHH, MaLH, TenHH, HinhAnh, GiaSP);
                dsHangHoa.add(h);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHangHoa;
    }
}
