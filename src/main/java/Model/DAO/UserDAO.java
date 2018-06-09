package Model.DAO;

import Model.HashPass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class UserDAO {

    private Boolean check(String query) {
        ResultSet resultSet = null;
        boolean test = false;

        CoreDAO coreDAO = new CoreDAO();

        try {
            resultSet = coreDAO.getStatement().executeQuery(query);
            if (resultSet.next()) test = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        coreDAO.close();

        return test;
    }

    private String getString(String query) {
        String returnString = "";

        CoreDAO coreDAO = new CoreDAO();

        try {
            ResultSet resultSet = coreDAO.getStatement().executeQuery(query);
            while(resultSet.next())
                returnString = resultSet.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        coreDAO.close();

        return returnString;
    }

    public String getPassword(String formEmail) {

        return getString("select password from users where email = '" + formEmail + "'");

    }

    public boolean isUser(String formEmail, String formPassword) {

        if (isEmail(formEmail)) {
            return HashPass.checkPassword(formPassword, getPassword(formEmail));
        }

        return false;
    }

    public Boolean isUsername(String formUsername) {

        return check("select username from users where username = '" + formUsername + "'");
    }

    public Boolean isEmail(String formEmail) {

        return check("select email from users where email = '" + formEmail + "'");
    }



    public Boolean addUser(String formUsername, String formEmail, String formPassword) {
        try {

            CoreDAO coreDAO = new CoreDAO();
            String hashPassword = HashPass.hash(formPassword);

            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("INSERT INTO users(email, password, username) values (?, ?, ?)");

            preparedStatement.setString(1, formEmail);
            preparedStatement.setString(2, hashPassword);
            preparedStatement.setString(3, formUsername);

            preparedStatement.executeUpdate();

            coreDAO.close();

            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


}
