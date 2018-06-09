package Servlet;

import Model.DAO.UserDAO;
import Model.Entity.UserEntity;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "Servlet.SignInServlet", urlPatterns = "/SignInServlet")
public class SignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDAO userDAO = new UserDAO();

        String formEmail = request.getParameter("email");
        String formPassword = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();


        if(!userDAO.isUser(formEmail, formPassword)) {
            request.setAttribute("error", "Incorrect Email or Password");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else {
            UserEntity userEntity = userDAO.getUser(formEmail, formPassword);

            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", userEntity);
            httpSession.setMaxInactiveInterval(30*60);
            request.getRequestDispatcher("main.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
