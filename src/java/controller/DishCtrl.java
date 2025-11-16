/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;
import dao.DishDAO;
import model.Dish;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus
 */
@WebServlet(name = "DishCtrl", urlPatterns = {"/DishCtrl"})
public class DishCtrl extends HttpServlet {
    private DishDAO dao = new DishDAO();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        String action = req.getParameter("action");
        
          if (action.equals("addDish")){
              addDish(req, res);
              return;
          }

    }
    
    protected void addDish(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        Dish newDish = new Dish();
        String name = req.getParameter("name");
        float price = Float.parseFloat(req.getParameter("price"));
        String description = req.getParameter("description");
        newDish.setName(name);
        newDish.setPrice(price);
        newDish.setDescription(description);
        
        boolean result = dao.addDish(newDish);
        
        if (result == true){
            ArrayList<Dish> dishList = dao.getDish();
            req.setAttribute("dishList", dishList);
            req.setAttribute("message", "Add new dish successfully!");
            req.getRequestDispatcher("ManagementStaff/ManageDishView.jsp").forward(req, res);
        } else {
            req.setAttribute("errorMessage", "Failed to add new dish");
            req.getRequestDispatcher("ManagementStaff/ManageDishView.jsp").forward(req, res);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        String action = req.getParameter("action");
        
        if (action.equals("viewDish")) {
            viewDish(req, res);
            return;
        }
    }
    
    protected void viewDish(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        ArrayList<Dish> dishList = dao.getDish();
        if (dishList != null && !dishList.isEmpty()){
            req.setAttribute("dishList", dishList);
        } else {
            req.setAttribute("errorMessage", "There is no dish available");
        }
        req.getRequestDispatcher("ManagementStaff/ManageDishView.jsp").forward(req, res);
    }
}
