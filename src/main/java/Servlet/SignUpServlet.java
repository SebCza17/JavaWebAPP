package Servlet;

import Model.DAO.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignUpServlet", urlPatterns = "/SignUpServlet")
public class SignUpServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();

        String getEmail = request.getParameter("email");
        String getPassword = request.getParameter("password");
        String getUsername = request.getParameter("username");

        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        if (userDAO.isUsername(getUsername)) {
            request.setAttribute("error", "Username Unavailable!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (userDAO.isEmail(getEmail)) {
            request.setAttribute("error", "Email Unavailable!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if(!userDAO.addUser(getUsername, getEmail, getPassword)) {
            request.setAttribute("error", "Something go wrong!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Registered ; )");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
