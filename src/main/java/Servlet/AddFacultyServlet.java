package Servlet;

import Model.DAO.FacultiDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddFacultyServlet", urlPatterns = "/AddFacultyServlet")
public class AddFacultyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String formName = request.getParameter("formName");
        String formShort = request.getParameter("formShort");
        int formW = Integer.parseInt(request.getParameter("formW"));
        String formAddress = request.getParameter("formAddress");

        if(FacultiDAO.addFaculty(formName, formShort, formW, formAddress)){
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
