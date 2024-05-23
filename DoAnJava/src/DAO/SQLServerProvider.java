/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Laptop
 */
public class SQLServerProvider {
    java.sql.Connection connection;
    ResultSet resultSet;
    Statement statement;
    
    // Mở cơ sở dữ liệu của Yên
//    public void open() {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DoAnQuanLyBanCoffee;"
//                    + "user=sa;"
//                    + "password=sa;"
//                    + "encrypt=true;"
//                    + "trustServerCertificate=true");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
    
//    // Mở cơ sở dữ liệu của Quân
    String databaseName ="DoAnQuanLyBanCoffee";
    String userNam="sa";
    String passWordString="123";
    public void open(){
        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(driver);
            String connectionURL;
            connectionURL = "jdbc:sqlserver://localhost:1433;databaseName="+databaseName+";encrypt=true;trustServerCertificate=true";
            connection = DriverManager.getConnection(connectionURL, "sa", "123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    // Mở cơ sở dữ liệu của Quang
//    public void open()
//    {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=DoAnQuanLyBanCoffee;"
//                    + "user=sa;"
//                    + "password=123;"
//                    + "encrypt=true;"
//                    + "trustServerCertificate=true");
//            //connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=QUANLYBANGDIA;encrypt=true;trustServerCertificate=true;integratedSecurity=true;");
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
    
    public void close(){
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLServerProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet executeQuery(String sql){
        resultSet = null;
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }
    
    public int executeUpdate(String sql){
        int n = -1;
        try{
            statement = connection.createStatement();
            n = statement.executeUpdate(sql);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }
    public static void main(String[] args) {
        SQLServerProvider s = new SQLServerProvider();
        s.open();
        s.executeQuery("select * from khachhang");
        
    }
}
