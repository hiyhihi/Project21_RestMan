/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;
import java.util.*;
import model.ImportingInvoice;
import model.ImportedIngredient;
import model.User;
import model.Supplier;
import model.Ingredient;
import dao.ImportingInvoiceDAO;
import dao.ImportedIngredientDAO;
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
@WebServlet(name = "ImportingInvoiceCtrl", urlPatterns = {"/ImportingInvoiceCtrl"})
public class ImportingInvoiceCtrl extends HttpServlet {

    private ImportingInvoiceDAO invoiceDAO = new ImportingInvoiceDAO();
    private ImportedIngredientDAO detailDAO = new ImportedIngredientDAO();
    private IngredientDAO ingredientDAO = new IngredientDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("printInvoice".equals(action)) {
            printInvoice(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("confirmInvoice".equals(action)) {
            confirmInvoice(req, res);
        }
    }

    @SuppressWarnings("unchecked")
    protected void printInvoice(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String ctx = req.getContextPath();
        HttpSession session = req.getSession(false);
        if (session == null) {
            res.sendRedirect(ctx + "/WarehouseStaff/WarehouseStaffHomeView.jsp");
            return;
        }

        User staff = (User) session.getAttribute("user");
        Supplier sup = (Supplier) session.getAttribute("currentSupplier");
        ArrayList<ImportedIngredient> importList =
                (ArrayList<ImportedIngredient>) session.getAttribute("importList");

        if (staff == null || sup == null || importList == null || importList.isEmpty()) {
            res.sendRedirect(ctx + "/WarehouseStaff/WarehouseStaffHomeView.jsp");
            return;
        }

        req.getRequestDispatcher("WarehouseStaff/PrintInvoiceView.jsp").forward(req, res);
    }

    @SuppressWarnings("unchecked")
    protected void confirmInvoice(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String ctx = req.getContextPath();
        HttpSession session = req.getSession(false);
        if (session == null) {
            res.sendRedirect(ctx + "/WarehouseStaff/WarehouseStaffHomeView.jsp");
            return;
        }

        User staff = (User) session.getAttribute("user");
        Supplier sup = (Supplier) session.getAttribute("currentSupplier");
        ArrayList<ImportedIngredient> importList =
                (ArrayList<ImportedIngredient>) session.getAttribute("importList");

        if (staff == null || sup == null || importList == null || importList.isEmpty()) {
            res.sendRedirect(ctx + "/WarehouseStaff/WarehouseStaffHomeView.jsp");
            return;
        }

        float total = 0;
        for (ImportedIngredient i : importList) {
            total += i.getQuantity() * i.getPrice();
        }

        ImportingInvoice invoice = new ImportingInvoice();
        invoice.setTime(java.time.LocalDateTime.now().toString());
        invoice.setStaff(staff);
        invoice.setSup(sup);
        invoice.setImportList(importList);
        invoice.setTotal(total);

        int invoiceId = invoiceDAO.saveInvoice(invoice);

        for (ImportedIngredient item : importList) {
            detailDAO.saveImportedIngredient(item, invoiceId);
            Ingredient ing = item.getIngredient();
            if (ing != null) {
                ingredientDAO.updateStock(ing.getId(), item.getQuantity());
            }
        }

        session.removeAttribute("currentSupplier");
        session.removeAttribute("importList");
        session.removeAttribute("selectedIngredient");
        session.removeAttribute("ingredientList");
        session.removeAttribute("supplierList");
        req.setAttribute("message", "Invoice saved successfully!");
        req.getRequestDispatcher("WarehouseStaff/WarehouseStaffHomeView.jsp").forward(req, res);
    }
}

