package Model.DAO;

import Model.Entity.UserEntity;
import Model.Entity.UserGroupEntity;
import Model.HashPass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class UserDAO {

    public UserEntity getUser(String formEmail, String formPassword){

        ResultSet resultSet = null;
        UserEntity userEntity = new UserEntity();

        CoreDAO coreDAO = new CoreDAO();

        try {
            resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM users where email = '" + formEmail + "' and password = '" + getPassword(formEmail) + "'" );
            while(resultSet.next()) {
                userEntity.setId(resultSet.getInt(1));
                userEntity.setUsername(resultSet.getString(2));
                userEntity.setEmail(resultSet.getString(3));
                userEntity.setPassword(resultSet.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        coreDAO.close();

        return userEntity;
    }



    public String getPassword(String formEmail) {

        return CoreDAO.getString("select password from users where email = '" + formEmail + "'");

    }

    public static String getLastId(){
        return CoreDAO.getString("select max(id) from users");
    }

    public boolean isUser(String formEmail, String formPassword) {

        if (isEmail(formEmail)) {
            return HashPass.checkPassword(formPassword, getPassword(formEmail));
        }

        return false;
    }

    public Boolean isUsername(String formUsername) {

        return CoreDAO.check("select username from users where username = '" + formUsername + "'");
    }

    public Boolean isEmail(String formEmail) {

        return CoreDAO.check("select email from users where email = '" + formEmail + "'");
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

            UserGroupDAO.addUserGroup();

            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


}
