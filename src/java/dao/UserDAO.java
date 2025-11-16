/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
/**
 *
 * @author Asus
 */
public class UserDAO extends DAO{
    public UserDAO() {
	super();
    }
    
    public User checkLogin(User u) {
        User result = null;
        String sql = "SELECT * FROM tblUser WHERE username = ? AND password = ?";
	try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                result = new User();
		result.setId(rs.getInt("id"));
                result.setName(rs.getString("name"));
                result.setUsername(rs.getString("username"));
                result.setEmail(rs.getString("email"));
                result.setPhone(rs.getString("phone"));
                result.setRole(rs.getString("role"));
            }
	}catch(Exception e) {
            e.printStackTrace();
        }
	return result;
    }
}
