/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import POJO.NhapHang;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Laptop
 */
public class NhapHangDAO {

    public static ArrayList<NhapHang> layDanhSachNhapHang() {
        ArrayList<NhapHang> dsNhapHang = new ArrayList<>();
        try {
            String sql = "select * from NhapHang";
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaNH = rs.getInt("MaNH");
                Date NgayNhap = rs.getDate("NgayNhap");

                NhapHang nh = new NhapHang(MaNH, NgayNhap);
                dsNhapHang.add(nh);
            }
        } catch (Exception e) {
        }
        return dsNhapHang;
    }

    public static boolean insertNhapHang(NhapHang nh) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();

            provider.open();
            String sql = "insert into NhapHang(NgayNhap) values('" + nh.getNgayNhap() + "')";
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

    public static boolean deleteNhapHang(NhapHang nh) {
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

    public static boolean updateNhapHang(NhapHang nh) {
        boolean kq = false;
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "update NhapHang set NgayNhap = '" + nh.getNgayNhap() + "' where MaNH = " + nh.getMaNH() + "";
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

    public static ArrayList<NhapHang> searchNhapHang(NhapHang nh) {
        ArrayList<NhapHang> dsNhapHang = new ArrayList<>();
        try {
            SQLServerProvider provider = new SQLServerProvider();
            provider.open();
            String sql = "select * from NhapHang where NgayNhap = '" + nh.getNgayNhap() + "'";
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                int MaNH = rs.getInt("MaNH");
                Date NgayNhap = rs.getDate("NgayNhap");

                NhapHang n = new NhapHang(MaNH, NgayNhap);
                dsNhapHang.add(n);
            }
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNhapHang;
    }

    public static void main(String[] args) throws ParseException {
        ArrayList<NhapHang> dsNhapHangs = new ArrayList<>();
        dsNhapHangs = NhapHangDAO.layDanhSachNhapHang();
        String sDate = "2000-12-31";
        Date dString = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
        String ngayNhap = new SimpleDateFormat("yyyy-MM-dd").format(dString);
        NhapHang a = new NhapHang(0, dString);
        insertNhapHang(a);
        System.out.println(sDate + " "+dString+" "+ngayNhap);
        
        for (NhapHang nh : dsNhapHangs) {
            System.out.println(nh.getMaNH());
            System.out.println(nh.getNgayNhap().toString());
        }
    }

}
