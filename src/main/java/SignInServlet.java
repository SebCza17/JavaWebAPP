import Model.DAO.UserDAO;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "SignInServlet", urlPatterns = "/SignInServlet")
public class SignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDAO userDAO = new UserDAO();
        boolean test = false;

        String getEmail = request.getParameter("email");
        String getPassword = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        if(userDAO.isUser(getEmail, getPassword)) printWriter.println("Pomyslnie");
        else printWriter.println("BLAD");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
