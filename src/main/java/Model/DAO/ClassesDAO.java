package Model.DAO;

import Model.Entity.ClassesEntity;
import Model.Entity.FacultyEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAO {

    public List<ClassesEntity> getClassesList(int formIdFaculty) {
        List<ClassesEntity> classesEntities = new ArrayList<>();
        ClassesEntity classesEntity = null;
        ResultSet resultSet = null;

        CoreDAO coreDAO = new CoreDAO();

        try {
            resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where idFacult  = '" + formIdFaculty + "' order By name");

            while (resultSet.next()) {
                classesEntity = new ClassesEntity();

                classesEntity.setId(resultSet.getInt(1));
                classesEntity.setIdFaculty(resultSet.getInt(2));
                classesEntity.setIdType(resultSet.getInt(3));
                classesEntity.setName(resultSet.getString(4));
                classesEntity.setFloor(resultSet.getInt(5));
                classesEntity.setCapacity(resultSet.getInt(6));
                classesEntity.setAvailable(resultSet.getBoolean(7));

                classesEntities.add(classesEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        coreDAO.close();

        return classesEntities;
    }
}
