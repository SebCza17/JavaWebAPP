package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {

    private Boolean check(String query){
        ResultSet resultSet = null;
        boolean test = false;
        try {
            CoreDAO coreDAO = new CoreDAO();

            resultSet = coreDAO.getStatement().executeQuery(query);

            if(resultSet.next()) test = true;

            coreDAO.close();


        }catch (Exception e){
            System.out.println(e);
        }

        return test;
    }

    public boolean isUser(String getEmail, String getPassword){

        return check("select email from users where email = '" + getEmail + "' and password = '" + getPassword + "'");
    }

    public Boolean isUsername(String getUsername) {

        return check("select username from users where username = '" + getUsername + "'");
    }

    public Boolean isEmail(String getEmail){

        return check("select email from users where email = '" + getEmail + "'");
    }

    public Boolean addUser(String getUsername, String getEmail, String getPassword) {
        try {

            CoreDAO coreDAO = new CoreDAO();

            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("INSERT INTO users(email, password, username) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, getEmail);
            preparedStatement.setString(2, getPassword);
            preparedStatement.setString(3, getUsername);

            preparedStatement.executeUpdate();

            coreDAO.close();

            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


}
