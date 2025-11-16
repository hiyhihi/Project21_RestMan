/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Dish;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Asus
 */
public class DishDAO extends DAO{
    public DishDAO(){
        super();
    }
    
    public ArrayList<Dish> getDish(){
        ArrayList<Dish> dishList = new ArrayList<>();
        String sql = "SELECT * FROM tblDish";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Dish d = new Dish();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setPrice(rs.getFloat("price"));
                d.setDescription(rs.getString("description"));
                dishList.add(d);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return dishList;
    }
    
    public boolean addDish(Dish d){
        String sql = "INSERT INTO tblDish(name, price, description) VALUES (?, ?, ?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, d.getName());
            ps.setFloat(2, d.getPrice());
            ps.setString(3, d.getDescription());
            
            int row = ps.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
