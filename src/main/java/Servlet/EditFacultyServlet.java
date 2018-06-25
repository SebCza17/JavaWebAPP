package Servlet;

import Model.DAO.FacultiDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditFacultyServlet", urlPatterns = "/EditFacultyServlet")
public class EditFacultyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int formFacultyID = Integer.parseInt(request.getParameter("formFacultyID"));
        String formName = request.getParameter("formName");
        String formShortName = request.getParameter("formShortName");
        int formWN = Integer.parseInt(request.getParameter("formWN"));
        String formAddress = request.getParameter("formAddress");

        if(FacultiDAO.editFaculty(formFacultyID, formName, formShortName, formWN, formAddress)){
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("sheethappens.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
