package Servlet;

import Model.DAO.ClassesDAO;
import Model.DAO.FacultiDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddClassServlet", urlPatterns = "/AddClassesServlet")
public class AddClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int formFacultyId = Integer.parseInt(request.getParameter("formFaculty"));
        String formName = request.getParameter("formName");
        int formFloor = Integer.parseInt(request.getParameter("formFloor"));
        int formCapacity = Integer.parseInt(request.getParameter("formCapacity"));
        Boolean formAvailable = Boolean.parseBoolean(request.getParameter("formAvailable"));
        String formType = request.getParameter("formType");

        if(ClassesDAO.addClasses(formFacultyId, formName, formFloor, formCapacity, formAvailable, formType)){
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }else{
            /*request.getRequestDispatcher("main.jsp").forward(request, response);*/
            PrintWriter printWriter = response.getWriter();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}


//TODO!!!!!!!