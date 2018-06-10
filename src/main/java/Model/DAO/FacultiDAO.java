package Model.DAO;

import Model.Entity.FacultyEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FacultiDAO {

    public List<FacultyEntity> getFaculties() {
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
}
