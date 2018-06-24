package Model.DAO;

import Model.Entity.FacultyEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FacultiDAO {

    public static List<FacultyEntity> getFaculties(int orderType) {
        List<FacultyEntity> facultyEntities = new ArrayList<>();
        FacultyEntity facultyEntity = null;
        ResultSet resultSet = null;


        CoreDAO coreDAO = new CoreDAO();

        try {
            if (orderType == 0) {
                resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM faculty order By name");
            } else if (orderType == 1) {
                resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM faculty order By name DESC");
            } else if (orderType == 2) {
                resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM faculty order By shortname");
            } else if (orderType == 3) {
                resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM faculty order By shortname DESC");
            } else if (orderType == 4) {
                resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM faculty order By wn");
            } else if (orderType == 5) {
                resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM faculty order By wn DESC");
            } else if (orderType == 6) {
                resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM faculty order By address");
            } else if (orderType == 7) {
                resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM faculty order By address DESC");
            } else {
                resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM faculty");
            }


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

    public String getName(int id) {

        return CoreDAO.getString("Select shortname From faculty where id = " + id + "");
    }

    public static Boolean addFaculty(String formName, String formShort, int formW, String formAddress) {

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
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static Boolean delFaculty(int formFacultyID) {
        try {
            CoreDAO coreDAO = new CoreDAO();

            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("DELETE FROM faculty WHERE id = ?");

            preparedStatement.setInt(1, formFacultyID);

            preparedStatement.executeUpdate();

            coreDAO.close();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

