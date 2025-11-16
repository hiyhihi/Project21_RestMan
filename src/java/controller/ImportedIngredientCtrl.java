/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;
import dao.ImportedIngredientDAO;
import model.ImportedIngredient;
import model.Ingredient;
import model.ImportingInvoice;
import java.util.*;
import model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
@WebServlet(name = "ImportedIngredientCtrl", urlPatterns = {"/ImportedIngredientCtrl"})
public class ImportedIngredientCtrl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if ("addTemp".equals(action)) {
            addTemp(req, res);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("removeTemp".equals(action)) {
            removeTemp(req, res);
        }
    }

    @SuppressWarnings("unchecked")
    protected void addTemp(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

    String ctx = req.getContextPath();
    HttpSession session = req.getSession(false);
    if (session == null || session.getAttribute("user") == null || session.getAttribute("currentSupplier") == null) {
        res.sendRedirect(ctx + "/WarehouseStaff/WarehouseStaffHomeView.jsp");
        return;
    }

    Ingredient ing = (Ingredient) session.getAttribute("selectedIngredient");
    if (ing == null) {
        res.sendRedirect(ctx + "/WarehouseStaff/WarehouseStaffHomeView.jsp");
        return;
    }

    int quantity = Integer.parseInt(req.getParameter("quantity"));
    int ingredientId = Integer.parseInt(req.getParameter("id")); 

    float price = ing.getPrice();
    String priceParam = req.getParameter("price");
    if (priceParam != null && !priceParam.isEmpty()) {
        try { price = Float.parseFloat(priceParam); } catch (Exception ignore) {}
    }

    ArrayList<ImportedIngredient> importList =
            (ArrayList<ImportedIngredient>) session.getAttribute("importList");
    
    if (importList == null) {
        importList = new ArrayList<>();
        session.setAttribute("importList", importList); 
    }

    boolean foundAndAggregated = false;
    for (ImportedIngredient impIng : importList) {
        if (impIng.getIngredient() != null && impIng.getIngredient().getId() == ingredientId && impIng.getPrice() == price) {
            impIng.setQuantity(impIng.getQuantity() + quantity);
            foundAndAggregated = true;
            break; 
        }
    }
    if (!foundAndAggregated) {
        ImportedIngredient newImpIng = new ImportedIngredient();
        newImpIng.setQuantity(quantity);
        newImpIng.setPrice(price);
        newImpIng.setIngredient(ing); 
        importList.add(newImpIng);
    }
    session.removeAttribute("selectedIngredient");
    req.setAttribute("message", "Added to importing list");
    req.getRequestDispatcher("WarehouseStaff/SearchIngredientView.jsp").forward(req, res);
}

    @SuppressWarnings("unchecked")
    protected void removeTemp(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String ctx = req.getContextPath();
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null || session.getAttribute("currentSupplier") == null) {
            res.sendRedirect(ctx + "/WarehouseStaff/WarehouseStaffHomeView.jsp");
            return;
        }

        ArrayList<ImportedIngredient> importList = (ArrayList<ImportedIngredient>) session.getAttribute("importList");
        if (importList == null) {
            res.sendRedirect(ctx + "/WarehouseStaff/WarehouseStaffHomeView.jsp");
            return;
        }

        int ingId = Integer.parseInt(req.getParameter("ingredientId"));
        importList.removeIf(it -> it.getIngredient() != null && it.getIngredient().getId() == ingId);
        session.setAttribute("importList", importList);

        req.getRequestDispatcher("WarehouseStaff/SearchIngredientView.jsp").forward(req, res);
    }
}
