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
public class ImportedIngredient implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private int quantity;
    private float price;
    private Ingredient ingredient;
    
    public ImportedIngredient(){
        super();
    }
    
    public ImportedIngredient(int quantity, float price, Ingredient ingredient){
        super();
        this.quantity = quantity;
        this.price = price;
        this.ingredient = ingredient;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

    public Ingredient getIngredient() { return ingredient; }
    public void setIngredient(Ingredient ingredient) { this.ingredient = ingredient; }
}
