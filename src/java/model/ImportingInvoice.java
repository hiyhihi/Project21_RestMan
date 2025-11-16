/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.*;
import java.io.Serializable;
/**
 *
 * @author Asus
 */
public class ImportingInvoice implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String time;
    private float total;
    private Supplier sup;
    private User staff;
    private ArrayList<ImportedIngredient> importList;

    public ImportingInvoice() {
        super();
    }

    public ImportingInvoice(String time, Supplier sup, User staff, float total, ArrayList<ImportedIngredient> importList) {
        super();
        this.time = time;
        this.sup = sup;
        this.staff = staff;
        this.importList = importList;
        this.total = total;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public Supplier getSup() { return sup; }
    public void setSup(Supplier sup) { this.sup = sup; }

    public User getStaff() { return staff; }
    public void setStaff(User staff) { this.staff = staff; }

    public ArrayList<ImportedIngredient> getImportList() { return importList; }
    public void setImportList(ArrayList<ImportedIngredient> importList) { this.importList = importList; }

    public float getTotal() {
        return total;
    }
    public void setTotal(float total){
        this.total = total;
    }
}

