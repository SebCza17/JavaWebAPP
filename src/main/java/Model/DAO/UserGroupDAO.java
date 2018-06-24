package Model.DAO;

import Model.Entity.UserEntity;
import Model.Entity.UserGroupEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserGroupDAO {

    static void addUserGroup(){

        try {
            CoreDAO coreDAO = new CoreDAO();

            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("INSERT INTO users_group(idusers, grouptext) values (?, ?)");

            preparedStatement.setInt(1,  Integer.parseInt(UserDAO.getLastId()));
            preparedStatement.setString(2, "basic");

            preparedStatement.executeUpdate();

            coreDAO.close();

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public static String getPermision(int formUserID){

        return CoreDAO.getString("Select grouptext From users_group where idusers = '" + formUserID + "'");
    }

    public static Boolean editUserGroup(int formUserID, String formNewGroup){

        try {
            CoreDAO coreDAO = new CoreDAO();

            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("UPDATE users_group SET grouptext = ? WHERE idusers = ?");

            preparedStatement.setString(1, formNewGroup);
            preparedStatement.setInt(2, formUserID);

            preparedStatement.executeUpdate();

            coreDAO.close();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
