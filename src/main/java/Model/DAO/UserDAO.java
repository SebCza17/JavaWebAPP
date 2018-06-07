package Model.DAO;

import Model.Entity.UserEntity;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    public boolean isUser(String getEmail, String getPassword){
        ResultSet resultSet = null;
        boolean test = false;
        try {
            InitialContext initialContext = new InitialContext();

            DataSource dataSource = (DataSource) initialContext.lookup("jdbc/RoomBook2");

            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery("select email from users where email = '" + getEmail + "' and password = '" + getPassword + "'");

            if(resultSet.next()) test = true;

            statement.close();
            connection.close();


        }catch (Exception e){
            System.out.println(e);
        }


        return test;
    }
}
