package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dao.SupplierDAO;
import model.ImportedIngredient;
import model.Supplier;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 *
 * @author Asus
 */
@WebServlet(urlPatterns = {"/SupplierCtrl"})
public class SupplierCtrl extends HttpServlet {

    private SupplierDAO dao = new SupplierDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("addSupplier".equals(action)) {
            addSupplier(req, res);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("searchSupplier".equals(action)) {
            searchSupplier(req, res);
        } else if ("selectSupplier".equals(action)) {
            selectSupplier(req, res);
        } else {
            req.getRequestDispatcher("WarehouseStaff/SearchSupplierView.jsp").forward(req, res);
        }
    }

    protected void addSupplier(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Supplier newSup = new Supplier();
        newSup.setCompanyName(req.getParameter("name"));
        newSup.setPhone(req.getParameter("phone"));
        newSup.setAddress(req.getParameter("address"));

        boolean result = dao.addSupplier(newSup);
        if (result) {
            req.setAttribute("message", "Add new supplier successfully!");
        } else {
            req.setAttribute("errorMessage", "Failed to add supplier!");
        }
      
        req.getRequestDispatcher("WarehouseStaff/SearchSupplierView.jsp").forward(req, res);
    }

    protected void searchSupplier(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String name = req.getParameter("keyword");
        ArrayList<Supplier> supList = dao.searchSupplier(name);

        if (supList != null && !supList.isEmpty()) {
            req.setAttribute("supplierList", supList);
            req.getSession().setAttribute("supplierList", supList);
        } else {
            req.setAttribute("errorMessage", "No suppliers found");
        }
        req.getRequestDispatcher("WarehouseStaff/SearchSupplierView.jsp").forward(req, res);
    }

    @SuppressWarnings("unchecked")
    protected void selectSupplier(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

    int id = Integer.parseInt(req.getParameter("id"));

    HttpSession session = req.getSession(); 
    ArrayList<Supplier> list = (ArrayList<Supplier>) session.getAttribute("supplierList");

    Supplier selected = null;
    if (list != null) {
        for (Supplier s : list) {
            if (s.getId() == id) {
                selected = s;
                break;
            }
        }
    }

    session.setAttribute("currentSupplier", selected);

    if (session.getAttribute("importList") == null) {
        session.setAttribute("importList", new ArrayList<ImportedIngredient>());
    }

    req.getRequestDispatcher("WarehouseStaff/SearchIngredientView.jsp").forward(req, res);
}
}


