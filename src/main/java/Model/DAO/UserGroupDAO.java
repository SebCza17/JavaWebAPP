package Model.DAO;

import Model.Entity.UserEntity;
import Model.Entity.UserGroupEntity;

import java.sql.PreparedStatement;
import java.sql.Statement;

class UserGroupDAO {

    static void addUserGroup(UserEntity userEntity){

        try {
            CoreDAO coreDAO = new CoreDAO();

            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("INSERT INTO users_group(idUser, 'group') values (?, ?)");

            preparedStatement.setInt(1, userEntity.getId());
            preparedStatement.setString(2, "basic");

        }catch (Exception e){
            System.out.println(e);
        }

    }
}
