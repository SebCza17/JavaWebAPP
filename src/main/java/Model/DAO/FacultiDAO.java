package Model.DAO;

import Model.Entity.FacultyEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FacultiDAO {

    public static List<FacultyEntity> getFaculties() {
        List<FacultyEntity> facultyEntities = new ArrayList<>();
        FacultyEntity facultyEntity = null;
        ResultSet resultSet = null;


        CoreDAO coreDAO = new CoreDAO();

        try {
            resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM faculty order By name");

            while (resultSet.next()) {
                facultyEntity = new FacultyEntity();

                facultyEntity.setId(resultSet.getInt(1));
                facultyEntity.setName(resultSet.getString(2));
                facultyEntity.setShortname(resultSet.getString(3));
                facultyEntity.setWn(resultSet.getInt(4));
                facultyEntity.setAddress(resultSet.getString(5));

                facultyEntities.add(facultyEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        coreDAO.close();

        return facultyEntities;
    }

    public String getName(int id){

        return CoreDAO.getString("Select shortname From faculty where id = "+ id + "");
    }

    public static Boolean addFaculty(String formName, String formShort, int formW, String formAddress){

        try {
            CoreDAO coreDAO = new CoreDAO();

            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("INSERT INTO faculty(name, shortname, wn, address) values (?,?,?,?)");

            preparedStatement.setString(1, formName);
            preparedStatement.setString(2, formShort);
            preparedStatement.setInt(3, formW);
            preparedStatement.setString(4, formAddress);

            preparedStatement.executeUpdate();

            coreDAO.close();

            return true;
        }catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
