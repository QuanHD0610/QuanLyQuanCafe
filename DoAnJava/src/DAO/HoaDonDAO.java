/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.HoaDon;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Laptop
 */
public class HoaDonDAO {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static ArrayList<HoaDon> layDanhSachHoaDon() {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        try {
            String sql = "select * from HoaDon";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaHDBH = rs.getInt("MaHDBH");
                int MaNV = rs.getInt("MaNV");
                int MaBan = rs.getInt("MaBan");
                Date ngayXuatHD = rs.getDate("ngayXuatHD");
                int tongTien = rs.getInt("tongTien");
                int diemTL = rs.getInt("diemTL");
                float giamGia = rs.getFloat("giamGia");
                int maKH = rs.getInt("maKH");
                int soTienThanhToan = rs.getInt("soTienThanhToan");

                HoaDon h = new HoaDon(MaHDBH, MaNV, MaBan, ngayXuatHD, tongTien, diemTL, giamGia, maKH, soTienThanhToan);
                dsHoaDon.add(h);
            }
        } catch (Exception e) {
        }
        return dsHoaDon;
    }

    public static boolean insertHoaDon(HoaDon hoaDon) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();

            provider.open();
            String formattedDate = DATE_FORMAT.format(hoaDon.getNgayXuatHD());
            String sql = "insert into HoaDon(MaNV, MaBan, NgayXuatHD, TongTien, diemTL, GiamGia, MaKH, SoTienThanhToan) values(" + hoaDon.getMaNV()
                    + ", " + hoaDon.getMaBan() + ", '" + formattedDate + "', " + hoaDon.getTongTien() + ", " + hoaDon.getDiemTL() + ", " + hoaDon.getGiamGia() + ", " + hoaDon.getMaKH() + ", " + hoaDon.getSoTienThanhToan() + ")";
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

    public static boolean deleteHoaDon(HoaDon hoaDon) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "delete HoaDon where MaHDBH = " + hoaDon.getMaHDBH() + "";
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

    public static boolean updateNhapHang(HoaDon hoaDon) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String formattedDate = DATE_FORMAT.format(hoaDon.getNgayXuatHD());
            String sql = "update HoaDon set MaNV = " + hoaDon.getMaNV() + ", MaBan = " + hoaDon.getMaBan() + ", NgayXuatHD = '" + formattedDate + "', TongTien = " + hoaDon.getTongTien() + ", DiemTL = " + hoaDon.getDiemTL() + ", GiamGia = " + hoaDon.getGiamGia() + ", SoTienThanhToan = " + hoaDon.getSoTienThanhToan() + ", MaKH = " + hoaDon.getMaKH() + " where MaHDBH = " + hoaDon.getMaHDBH() + "";
            System.out.println(sql);
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

    public static ArrayList<HoaDon> searchHoaDon(HoaDon hoaDon) {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String formattedDate = DATE_FORMAT.format(hoaDon.getNgayXuatHD());
            String sql = "select * from HoaDon where MaNV = " + hoaDon.getMaNV() + " or MaKH = " + hoaDon.getMaKH() + " or MaBan = " + hoaDon.getMaBan() + " or NgayXuatHD = '" + formattedDate + "'";
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaHDBH = rs.getInt("MaHDBH");
                int MaNV = rs.getInt("MaNV");
                int MaBan = rs.getInt("MaBan");
                Date ngayXuatHD = rs.getDate("ngayXuatHD");
                int tongTien = rs.getInt("tongTien");
                int diemTL = rs.getInt("diemTL");
                float giamGia = rs.getFloat("giamGia");
                int maKH = rs.getInt("maKH");
                int soTienThanhToan = rs.getInt("soTienThanhToan");

                HoaDon h = new HoaDon(MaHDBH, MaNV, MaBan, ngayXuatHD, tongTien, diemTL, giamGia, maKH, soTienThanhToan);
                dsHoaDon.add(h);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsHoaDon;
    }

    public static int layMaHoaDonCuoiCung() {
        int mahdbh = -1;
        String s = "SELECT TOP 1 MaHDBH FROM HOADON ORDER BY MaHDBH DESC ";
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(s);
            while (rs.next()) {
                mahdbh = rs.getInt("MaHDBH");
            }
            provider.close();
        } catch (Exception e) {
        }
        return mahdbh;
    }

    public static HoaDon searchHoaDonTheoMaHoaDon(int maHoaDon) {
        HoaDon hoaDon = new HoaDon();
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "select * from HoaDon where MaHDBH = " + maHoaDon;
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaHDBH = rs.getInt("MaHDBH");
                int MaNV = rs.getInt("MaNV");
                int MaBan = rs.getInt("MaBan");
                Date ngayXuatHD = rs.getDate("ngayXuatHD");
                int tongTien = rs.getInt("tongTien");
                int diemTL = rs.getInt("diemTL");
                float giamGia = rs.getFloat("giamGia");
                int maKH = rs.getInt("maKH");
                int soTienThanhToan = rs.getInt("soTienThanhToan");

                hoaDon = new HoaDon(MaHDBH, MaNV, MaBan, ngayXuatHD, tongTien, diemTL, giamGia, maKH, soTienThanhToan);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDon;
    }

    public static ArrayList<HoaDon> layDanhSachHoaDonTheoMaNV(int MaNV) {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        try {
            String sql = "select * from HoaDon where MaNV = " + MaNV;
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaHDBH = rs.getInt("MaHDBH");
                int MaBan = rs.getInt("MaBan");
                Date ngayXuatHD = rs.getDate("ngayXuatHD");
                int tongTien = rs.getInt("tongTien");
                int diemTL = rs.getInt("diemTL");
                float giamGia = rs.getFloat("giamGia");
                int maKH = rs.getInt("maKH");
                int soTienThanhToan = rs.getInt("soTienThanhToan");

                HoaDon h = new HoaDon(MaHDBH, MaNV, MaBan, ngayXuatHD, tongTien, diemTL, giamGia, maKH, soTienThanhToan);
                dsHoaDon.add(h);
            }
        } catch (Exception e) {
        }
        return dsHoaDon;
    }

    public static ArrayList<HoaDon> layDanhSachHoaDonTheoNgay(Date ngayBD, Date ngayKT) {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        try {
            String sql = "select * from HoaDon where ngayXuatHD BETWEEN "+ngayBD+"AND "+ngayKT;
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaHDBH = rs.getInt("MaHDBH");
                int MaNV=rs.getInt("maNV");
                int MaBan = rs.getInt("MaBan");
                Date ngayXuatHD = rs.getDate("ngayXuatHD");
                int tongTien = rs.getInt("tongTien");
                int diemTL = rs.getInt("diemTL");
                float giamGia = rs.getFloat("giamGia");
                int maKH = rs.getInt("maKH");
                int soTienThanhToan = rs.getInt("soTienThanhToan");

                HoaDon h = new HoaDon(MaHDBH, MaNV, MaBan, ngayXuatHD, tongTien, diemTL, giamGia, maKH, soTienThanhToan);
                dsHoaDon.add(h);
            }
        } catch (Exception e) {
        }
        return dsHoaDon;
    }

}
