/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.ImportingInvoice;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
/**
 *
 * @author Asus
 */
public class ImportingInvoiceDAO extends DAO{
    public ImportingInvoiceDAO() {
        super();
    }

    public int saveInvoice(ImportingInvoice invoice) {
        int invoiceId = -1;
        String sql = "INSERT INTO tblImportingInvoice(time, supplier_id, total, staff_id) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, invoice.getTime());
            ps.setInt(2, invoice.getSup().getId());
            ps.setFloat(3, invoice.getTotal()); 
            ps.setInt(4, invoice.getStaff().getId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    invoiceId = rs.getInt(1); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoiceId;
    }
}
