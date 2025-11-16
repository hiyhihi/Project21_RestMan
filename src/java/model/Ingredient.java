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
public class Ingredient implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private float price;
    private int stock;
    
    public Ingredient(){
        super();
    }
    
    public Ingredient(String name, float price, int stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
