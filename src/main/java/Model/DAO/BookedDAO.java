package Model.DAO;

import Model.Entity.BookedEntity;
import Model.Entity.UserEntity;
import Model.HashPass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookedDAO {

    public void addBooked(int formIdClasses, Date formDate, String formHours, int formIdUser) {
        LocalDate dateLocal = formDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.sql.Date sqlDate = java.sql.Date.valueOf( dateLocal );
        try {
            CoreDAO coreDAO = new CoreDAO();


            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("INSERT INTO booked(idclass, days, hours, idusers) values (?, ?, ?, ?)");


            preparedStatement.setInt(1, formIdClasses);
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setString(3, formHours);
            preparedStatement.setInt(4, formIdUser);

            preparedStatement.executeUpdate();

            coreDAO.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> getBookedHours(int formIdClass, Date formDate){
        List<Integer> returnedIntTabs = new ArrayList<>();


        LocalDate dateLocal = formDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.sql.Date sqlDate = java.sql.Date.valueOf( dateLocal );

        try{
            CoreDAO coreDAO = new CoreDAO();

            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("SELECT hours FROM booked WHERE idclass = ? and days = ?");

            preparedStatement.setInt(1, formIdClass);
            preparedStatement.setDate(2, sqlDate);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                String[] toSplit = resultSet.getString("hours").split(":");

                for (String aToSplit : toSplit) {
                    returnedIntTabs.add(Integer.parseInt(aToSplit));
                }

            }

            coreDAO.close();
        }catch (Exception e){
            System.out.println(e);

        }


        return returnedIntTabs;
    }

    public static int countBookedHours(int formRoom, Date formDate){

        List<Integer> bookedList =  getBookedHours(formRoom, formDate);


        return bookedList.size();
    }
}
