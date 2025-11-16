/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Supplier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
/**
 *
 * @author Asus
 */
public class SupplierDAO extends DAO{
    public SupplierDAO(){
        super();
    }    

    public boolean addSupplier(Supplier p) {
        boolean result = false;
        String sql = "INSERT INTO tblSupplier(companyName, phone, address) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getCompanyName());
            ps.setString(2, p.getPhone());
            ps.setString(3, p.getAddress());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<Supplier> searchSupplier(String name) {
        ArrayList<Supplier> list = new ArrayList<>();
        String sql = "SELECT * FROM tblSupplier WHERE companyName LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Supplier s = new Supplier();
                s.setId(rs.getInt("id"));
                s.setCompanyName(rs.getString("companyName"));
                s.setPhone(rs.getString("phone"));
                s.setAddress(rs.getString("address"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
