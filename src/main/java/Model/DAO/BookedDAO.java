package Model.DAO;

import Model.Entity.BookedEntity;
import Model.Entity.UserEntity;
import Model.HashPass;

import java.awt.print.Book;
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
        if(!isAlreadyIn(formIdClasses, formDate, formHours)) {
            LocalDate dateLocal = formDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            java.sql.Date sqlDate = java.sql.Date.valueOf(dateLocal);
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
    }

    private Boolean isAlreadyIn(int formIdClasses, Date formDate, String formHours){


        LocalDate dateLocal = formDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.sql.Date sqlDate = java.sql.Date.valueOf( dateLocal );

        try {
            CoreDAO coreDAO = new CoreDAO();
            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("SELECT * FROM booked where idclass = ? and days = ? and hours = ?");


            preparedStatement.setInt(1, formIdClasses);
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setString(3, formHours);


            ResultSet resultSet = preparedStatement.executeQuery();

            coreDAO.close();

            return resultSet.next();


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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


    public static List<BookedEntity> getBookedList(int formUserId){
        List<BookedEntity> bookedEntities = new ArrayList<>();

        Date date = new Date();
        LocalDate dateLocal = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.sql.Date sqlDate = java.sql.Date.valueOf( dateLocal );

        try {
            CoreDAO coreDAO = new CoreDAO();

            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("SELECT * FROM booked WHERE idusers = ? and days >= ? ORDER BY days, idclass");

            preparedStatement.setInt(1, formUserId);
            preparedStatement.setDate(2, sqlDate);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                BookedEntity bookedEntity = new BookedEntity();
                List<Integer> integerList = new ArrayList<>();

                bookedEntity.setIdClasses(resultSet.getInt("idclass"));
                bookedEntity.setDay(resultSet.getDate("days"));

                String[] toSplit = resultSet.getString("hours").split(":");

                for (String aToSplit : toSplit) {
                    integerList.add(Integer.parseInt(aToSplit));
                }

                bookedEntity.setHours(integerList);
                bookedEntity.setIdUser(resultSet.getInt("idusers"));


                bookedEntities.add(bookedEntity);

            }

            coreDAO.close();
        }catch (Exception e){
            System.out.println(e);

        }


        return bookedEntities;
    }
}
