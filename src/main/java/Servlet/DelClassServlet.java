package Servlet;

import Model.DAO.ClassesDAO;
import Model.DAO.FacultiDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DelClassServlet", urlPatterns = "/DelClassServlet")
public class DelClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int formDelID = Integer.parseInt(request.getParameter("formDelId"));

        if(ClassesDAO.delClasses(formDelID)){
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("sheethappens.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
