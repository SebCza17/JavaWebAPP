package Servlet;

import Model.DAO.ClassesDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditClassServlet", urlPatterns = "/EditClassServlet")
public class EditClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int formClassID = Integer.parseInt(request.getParameter("formClassID"));
        int formFacultyID = Integer.parseInt(request.getParameter("formFacultyID"));
        String formName = request.getParameter("formName");
        int formFloor = Integer.parseInt(request.getParameter("formFloor"));
        int formCapacity = Integer.parseInt(request.getParameter("formCapacity"));
        Boolean formISAvailable = Boolean.parseBoolean(request.getParameter("formAvailable"));
        String formClassType = request.getParameter("formType");

        if(ClassesDAO.editClassess(formClassID, formFacultyID, formName, formFloor, formCapacity, formISAvailable, formClassType)){
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("sheethappens.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
