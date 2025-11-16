/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.ImportedIngredient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
/**
 *
 * @author Asus
 */
public class ImportedIngredientDAO extends DAO{
    public ImportedIngredientDAO() {
        super();
    }

    public boolean saveImportedIngredient(ImportedIngredient i, int invoiceId) {
    boolean result = false;
    String sql = "INSERT INTO tblImportedIngredient(quantity, price, ingredient_id, invoice_id) VALUES (?, ?, ?, ?)";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, i.getQuantity());
        ps.setFloat(2, i.getPrice());
        ps.setInt(3, i.getIngredient().getId());
        ps.setInt(4, invoiceId);
        int rows = ps.executeUpdate();
        if (rows > 0) {
            result = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}


}
