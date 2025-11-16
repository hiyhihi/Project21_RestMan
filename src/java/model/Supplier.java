/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.Serializable;
/**
 *
 * @author Asus
 */
public class Supplier implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String companyName;
    private String phone;
    private String address;

    public Supplier(){
        super();
    }
    
    public Supplier(String companyName, String phone, String address){
        super();
        this.companyName = companyName;
        this.phone = phone;
        this.address = address;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
