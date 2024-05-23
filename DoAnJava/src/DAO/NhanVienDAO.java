
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.SQLServerProvider;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import POJO.NhanVien;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Laptop
 */

public class NhanVienDAO {

    public static ArrayList<NhanVien> layDanhSachNhanVien() {
        ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        try {
            String sql = "Select * from NhanVien";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int maNV = rs.getInt("MaNV");
                String tenNV = rs.getString("TenNV");
                String gioiTinh = rs.getString("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String soDienThoai = rs.getString("SoDienThoai");
                String phanQuyen = rs.getString("PhanQuyen");
                String matKhau = rs.getString("MatKhau");

                NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, diaChi, soDienThoai, phanQuyen, matKhau);
                dsNhanVien.add(nv);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public static boolean insertNhanVien(NhanVien nv) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "INSERT INTO NhanVien (TenNV, GioiTinh, DiaChi, SoDienThoai, PhanQuyen, MatKhau) VALUES (N'" + nv.getTenNV()
                    + "',N'" + nv.getGioiTinh()
                    + "',N'" + nv.getDiaChi()
                    + "',N'" + nv.getSoDienThoai()
                    + "',N'user','"
                    + nv.getMatKhau()
                    + "')";
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

    public static boolean deleteNhanVien(NhanVien nv) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "delete NhanVien where MaNV = " + nv.getMaNV() + "";
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

    public static boolean updateNhanVien(NhanVien nv) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "update Nhanvien set TenNV = N'" + nv.getTenNV() + "', GioiTinh = N'" + nv.getGioiTinh() + "', DiaChi = N'" + nv.getDiaChi() + "', SoDienThoai = '" + nv.getSoDienThoai() + "', MatKhau = '" + nv.getMatKhau() + "' where MaNV = " + nv.getMaNV() + "";
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

    public static ArrayList<NhanVien> searchNhanVien(NhanVien nv) {
        boolean kq = false;
        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "select * from NhanVien where TenNV like '%" + nv.getTenNV() + "%'";
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int maNV = rs.getInt("MaNV");
                String tenNV = rs.getString("TenNV");
                String gioiTinh = rs.getString("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String soDienThoai = rs.getString("SoDienThoai");
                String phanQuyen = rs.getString("PhanQuyen");
                String matKhau = rs.getString("MatKhau");

                NhanVien n = new NhanVien(maNV, tenNV, gioiTinh, diaChi, soDienThoai, phanQuyen, matKhau);
                dsNhanVien.add(n);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNhanVien;
    }

    public static NhanVien timNhanVienTheoMa(int MaNV) {
        NhanVien nv = new NhanVien();
        try {
            String sql = "Select * from NhanVien where MaNV = " + MaNV + "";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int maNV = rs.getInt("MaNV");
                String tenNV = rs.getString("TenNV");
                String gioiTinh = rs.getString("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String soDienThoai = rs.getString("SoDienThoai");
                String phanQuyen = rs.getString("PhanQuyen");
                String matKhau = rs.getString("MatKhau");

                nv = new NhanVien(MaNV, tenNV, gioiTinh, diaChi, soDienThoai, phanQuyen, matKhau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }

    public static boolean dangNhap(String tenDangNhap, String MatKhau) {
        boolean kq = false;
        try {
            String sql = "SELECT * FROM NhanVien WHERE SoDienThoai = '" + tenDangNhap + "' AND MatKhau = '" + MatKhau + "'";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int maNV = rs.getInt("MaNV");
                String tenNV = rs.getString("TenNV");
                String gioiTinh = rs.getString("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String soDienThoai = rs.getString("SoDienThoai");
                String phanQuyen = rs.getString("PhanQuyen");
                String matKhau = rs.getString("MatKhau");

                NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, diaChi, soDienThoai, phanQuyen, matKhau);
                DataHolder.setMaNV(maNV);
                DataHolder.setPhanQuyen(phanQuyen);
                kq=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public static NhanVien timNhanVienTheoTen(String TenNV) {
        NhanVien nv = new NhanVien();
        try {
            String sql = "Select * from NhanVien where TenNV = N'" + TenNV + "'";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int maNV = rs.getInt("MaNV");
                String tenNV = rs.getString("TenNV");
                String gioiTinh = rs.getString("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String soDienThoai = rs.getString("SoDienThoai");
                String phanQuyen = rs.getString("PhanQuyen");
                String matKhau = rs.getString("MatKhau");

                nv = new NhanVien(maNV, tenNV, gioiTinh, diaChi, soDienThoai, phanQuyen, matKhau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }
}
