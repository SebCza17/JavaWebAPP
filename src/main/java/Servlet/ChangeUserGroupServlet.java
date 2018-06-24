package Servlet;

import Model.DAO.BookedDAO;
import Model.DAO.UserGroupDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangeUserGroupServlet", urlPatterns = "/ChangeUserGroupServlet")
public class ChangeUserGroupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int formUserID = Integer.parseInt(request.getParameter("formUserID"));
        String formGroup = request.getParameter("formGroup");

        if(UserGroupDAO.editUserGroup(formUserID, formGroup)){
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("sheethappens.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
