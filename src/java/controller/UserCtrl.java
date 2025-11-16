/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;
import dao.UserDAO;
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
@WebServlet(name = "UserCtrl", urlPatterns = {"/UserCtrl"})
public class UserCtrl extends HttpServlet {
    private UserDAO dao = new UserDAO();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        String action = req.getParameter("action");
        
        switch(action){
            case "login":
                checkLogin(req, res);
                break;
            case "logout":
                logout(req, res);
                break;
            default: 
                break;    
        }
    }
    
    protected void logout(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        req.getSession().invalidate();
        res.sendRedirect("Login.jsp");
    }
    
    protected void checkLogin(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        
        User checkedUser = dao.checkLogin(u);
        
        if (checkedUser != null){
            String role = checkedUser.getRole();
            if ("manager".equalsIgnoreCase(role)){
                req.getRequestDispatcher("ManagementStaff/ManagementStaffHomeView.jsp").forward(req, res);
            }
            if ("warehouse staff".equalsIgnoreCase(role)){
                HttpSession session = req.getSession();
                session.setAttribute("user", checkedUser);
                req.getRequestDispatcher("WarehouseStaff/WarehouseStaffHomeView.jsp").forward(req, res);
            }
        }
        else{
            req.setAttribute("errorMessage", "Invalid username or password");
            req.getRequestDispatcher("Login.jsp").forward(req, res);
        }
    }
}
