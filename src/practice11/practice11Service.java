/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice11;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahpka
 */
public class practice11Service {
    
//    String connectionString = "jdbc:postgresql://localhost:5432/practice8schoolDB";
//                                "jdbc:postgresql://localhost:5432/subjects";
//    String dbPassword = "nice123";
//    String dbUsername = "postgres";
//    String dbPassword = "123";
    
    private static final String connectionString = "jdbc:sqlserver://localhost:1434; databaseName=java_practice; encrypt=true; trustServerCertificate=true ";
    private static final String dbUsername = "sa";
    private static final String dbPassword = "userNice";
    
    
//    Connection conn = null;
//        try{
//            conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
//        } catch (SQLException e)  {
//            System.out.println(e);
//            System.out.println("------------ (┬┬﹏┬┬) Error (┬┬﹏┬┬) -----------");
//            return null;
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException e) {
//                System.out.println(e);
//                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                System.out.println("Something went wrong, Could be closing connection failed");
//            }
//
//        }
    
    public List<Map<String, String>>  getAllData() {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
            Statement stmt = conn.createStatement();
            ResultSet queriedSupplier = stmt.executeQuery("SELECT * FROM Supplier_Tbl");
            List<Map<String, String>> prepSubResp = new ArrayList();
            while(queriedSupplier.next()) {
                Map<String, String> prepDataset = new HashMap();
                
                prepDataset.put("supplier_id", String.valueOf(queriedSupplier.getInt("supplier_id")));
                prepDataset.put("supplier_name", queriedSupplier.getString("supplier_name"));
                prepDataset.put("address", queriedSupplier.getString("address"));
                prepDataset.put("tel", queriedSupplier.getString("tel"));
                prepDataset.put("email", queriedSupplier.getString("email"));

                prepSubResp.add(prepDataset);
//                System.out.println(prepSubResp);
            }
            
            return prepSubResp;
            
        } catch (SQLException e)  {
            System.out.println(e);
            System.out.println("------------ (┬┬﹏┬┬) Error (┬┬﹏┬┬) -----------");
            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("Something went wrong, Could be closing connection failed");
            }

        }
    }

    public List<Map<String, String>> searchData(String Subname) {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
            String sql = "Select * From Supplier_Tbl Where supplier_name Collate SQL_Latin1_General_CP437_BIN2 like '%' + ?  + '%' ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Subname);
            ResultSet queriedSupplier = pstmt.executeQuery();
            List<Map<String, String>> prepSubResp = new ArrayList();
            while(queriedSupplier.next()) {
                Map<String, String> prepDataset = new HashMap();
                
                
//                prepDataset.put(queriedSubject.getInt("subject_id"), queriedSubject.getString("subject_name"));
                prepDataset.put("supplier_id", String.valueOf(queriedSupplier.getInt("supplier_id")));
                prepDataset.put("supplier_name", queriedSupplier.getString("supplier_name"));
                prepDataset.put("address", queriedSupplier.getString("address"));
                prepDataset.put("tel", queriedSupplier.getString("tel"));
                prepDataset.put("email", queriedSupplier.getString("email"));
                
                
                
                prepSubResp.add(prepDataset);
            }
            
            return prepSubResp;
            
        } catch (SQLException e)  {
            System.out.println(e);
            System.out.println("------------ (┬┬﹏┬┬) Error (┬┬﹏┬┬) -----------");
            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("Something went wrong, Could be closing connection failed");
            }

        }
    }

    public boolean deleteData(String targetId) {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Supplier_Tbl WHERE supplier_id = ?");
            pstmt.setString(1, targetId);
            int AffectedRow = pstmt.executeUpdate();
            if (AffectedRow != 1) {
                throw new Exception("unexpected row affected from database");
            }
        } catch (Exception e)  {
            System.out.println(e);
            System.out.println("------------ (┬┬﹏┬┬) Error (┬┬﹏┬┬) -----------");
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("Something went wrong, Could be closing connection failed");
            }

        }
        return true;
    }

   
}


