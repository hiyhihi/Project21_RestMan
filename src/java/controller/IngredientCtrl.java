/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;
import java.util.*;
import model.Ingredient;
import dao.IngredientDAO;
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
@WebServlet(name = "IngredientCtrl", urlPatterns = {"/IngredientCtrl"})
public class IngredientCtrl extends HttpServlet {
    private IngredientDAO dao = new IngredientDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("addIngredient".equals(action)) {
            addIngredient(req, res);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("searchIngredient".equals(action)) {
            searchIngredient(req, res);
        } else if ("selectIngredient".equals(action)) {
            selectIngredient(req, res);
        } else {
            req.getRequestDispatcher("WarehouseStaff/SearchIngredientView.jsp").forward(req, res);
        }
    }

    protected void searchIngredient(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String ctx = req.getContextPath();
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null || session.getAttribute("currentSupplier") == null) {
            res.sendRedirect(ctx + "/WarehouseStaff/WarehouseStaffHomeView.jsp");
            return;
        }

        String name = req.getParameter("keyword");
        ArrayList<Ingredient> ingList = dao.searchIngredient(name);

        if (ingList != null && !ingList.isEmpty()) {
            session.setAttribute("ingredientList", ingList);
            req.setAttribute("ingredientList", ingList);
            System.out.println("Hehe");
            for (Ingredient i: ingList){
                System.out.println(i);
            }
        } else {
            req.setAttribute("errorMessage", "No ingredients found");
        }

        req.getRequestDispatcher("WarehouseStaff/SearchIngredientView.jsp").forward(req, res);
    }

    @SuppressWarnings("unchecked")
    protected void selectIngredient(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String ctx = req.getContextPath();
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null || session.getAttribute("currentSupplier") == null) {
            res.sendRedirect(ctx + "/WarehouseStaff/warehouseStaffHomeView.jsp");
            return;
        }

        ArrayList<Ingredient> list = (ArrayList<Ingredient>) session.getAttribute("ingredientList");
        if (list == null || list.isEmpty()) {
            res.sendRedirect(ctx + "/WarehouseStaff/WarehouseStaffHomeView.jsp");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        Ingredient selected = null;
        for (Ingredient ing : list) {
            if (ing.getId() == id) { selected = ing; break; }
        }
        if (selected == null) {
            res.sendRedirect(ctx + "/WarehouseStaff/WarehouseStaffHomeView.jsp");
            return;
        }

        session.setAttribute("selectedIngredient", selected);
        req.getRequestDispatcher("WarehouseStaff/ImportingView.jsp").forward(req, res);
    }

    protected void addIngredient(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        float price = Float.parseFloat(req.getParameter("price"));
        int stock = 0;

        Ingredient newIng = new Ingredient();
        newIng.setName(name);
        newIng.setPrice(price);
        newIng.setStock(stock);

        boolean result = dao.addIngredient(newIng);

        if (result) {
            req.setAttribute("message", "Add new ingredient successfully!");
        } else {
            req.setAttribute("errorMessage", "Failed to add ingredient");
        }
        req.getRequestDispatcher("WarehouseStaff/SearchIngredientView.jsp").forward(req, res);
    }
}

