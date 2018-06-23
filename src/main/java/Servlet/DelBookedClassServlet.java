package Servlet;

import Model.DAO.BookedDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DelBookedClassServlet", urlPatterns = "/DellBookedClassServlet")
public class DelBookedClassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int formDelID = Integer.parseInt(request.getParameter("formDelId"));
        String formHour = request.getParameter("formHour");

        if(BookedDAO.updateBookedHour(formDelID, formHour)){
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("sheethappens.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
