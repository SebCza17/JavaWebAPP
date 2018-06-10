package Model.DAO;

import Model.Entity.UserEntity;
import Model.Entity.UserGroupEntity;

import java.sql.PreparedStatement;
import java.sql.Statement;

class UserGroupDAO {

    static void addUserGroup(){

        try {
            CoreDAO coreDAO = new CoreDAO();

            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("INSERT INTO users_group(idusers, grouptext) values (?, ?)");

            preparedStatement.setInt(1,  Integer.parseInt(UserDAO.getLastId()));
            preparedStatement.setString(2, "basic");

            preparedStatement.executeUpdate();

        }catch (Exception e){
            System.out.println(e);
        }

    }
}
