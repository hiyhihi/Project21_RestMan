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
public class Dish implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private float price;
    private String description;
    
    public Dish(){
        super();
    }
    
    public Dish(String name, float price, String description){
        super();
        this.name = name;
        this.price = price;
        this.description = description;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
