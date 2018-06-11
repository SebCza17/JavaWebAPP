package Servlet;

import Model.DAO.BookedDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@WebServlet(name = "BookRoomServlet", urlPatterns = "/BookRoomServlet")
public class BookRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BookedDAO bookedDAO = new BookedDAO();

        Date date = new Date();
        Calendar calendar = Calendar.getInstance(); calendar.setTime(date); calendar.add(Calendar.DATE, Integer.parseInt(request.getParameter("day"))); date = calendar.getTime();

        int classes = Integer.parseInt(request.getParameter("classes"));

        String faculty = request.getParameter("faculty");

        StringBuilder hours = new StringBuilder();
        for( int i = 0; i < request.getParameterValues("hour").length; i++) hours.append(request.getParameterValues("hour")[i]).append(":");

        String hoursString = hours.toString();

        bookedDAO.addBooked(classes, date, hoursString);


        request.getRequestDispatcher("main.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
