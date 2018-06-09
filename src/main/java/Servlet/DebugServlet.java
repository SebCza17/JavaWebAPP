package Servlet;

import Model.DAO.UserDAO;
import Model.HashPass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DebugServlet", urlPatterns = "/DebugServlet")
public class DebugServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter printWriter = response.getWriter();

        UserDAO userDAO = new UserDAO();

        printWriter.println(userDAO.getPassword("123@gmail.com"));
        String test = userDAO.getPassword("123@gmail.com");
        printWriter.println(userDAO.isUser("123@gmail.com", "123"));
    }
}
