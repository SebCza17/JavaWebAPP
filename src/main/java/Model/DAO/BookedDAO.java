package Model.DAO;

import Model.Entity.BookedEntity;
import Model.Entity.UserEntity;
import Model.HashPass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class BookedDAO {

    public void addBooked(int formIdClasses, Date formDate, String formHours) {
        LocalDate dateLocal = formDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.sql.Date sqlDate = java.sql.Date.valueOf( dateLocal );
        try {
            CoreDAO coreDAO = new CoreDAO();


            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("INSERT INTO booked(idclass, day, hours) values (?, ?, ?)");


            preparedStatement.setInt(1, formIdClasses);
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setString(3, formHours);

            preparedStatement.executeUpdate();

            coreDAO.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
