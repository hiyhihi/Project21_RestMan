/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Ingredient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
/**
 *
 * @author Asus
 */
public class IngredientDAO extends DAO{
    public IngredientDAO() {
        super();
    }

    public boolean addIngredient(Ingredient i) {
        boolean result = false;
        String sql = "INSERT INTO tblIngredient(name, price, stock) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, i.getName());
            ps.setFloat(2, i.getPrice());
            ps.setInt(3, i.getStock());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean updateStock(int id, int quantity){
        Ingredient i = new Ingredient();
        String sql = "UPDATE tblIngredient SET stock = stock + ? WHERE id = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            int result = ps.executeUpdate();
            if (result > 0){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    public ArrayList<Ingredient> searchIngredient(String name) {
        ArrayList<Ingredient> list = new ArrayList<>();
        String sql = "SELECT * FROM tblIngredient WHERE name LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ingredient ing = new Ingredient();
                ing.setId(rs.getInt("id"));
                ing.setName(rs.getString("name"));
                ing.setPrice(rs.getFloat("price"));
                ing.setStock(rs.getInt("stock"));
                list.add(ing);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
