package Model.DAO;

import Model.Entity.ClassesEntity;
import Model.Entity.FacultyEntity;
import Model.Entity.UserEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAO {

    public List<ClassesEntity> getClassesList(int formIdFaculty, int formOrder, String formToFind) {
        List<ClassesEntity> classesEntities = new ArrayList<>();
        ClassesEntity classesEntity = null;
        ResultSet resultSet = null;

        CoreDAO coreDAO = new CoreDAO();

        try {
            if(formIdFaculty != -1) {
                if (formOrder == 0) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where idFacult  = '" + formIdFaculty + "' AND UPPER(name) LIKE '%"+ formToFind +"%' order By name");
                } else if (formOrder == 1) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where idFacult  = '" + formIdFaculty + "' AND UPPER(name) LIKE '%"+ formToFind +"%' order By name DESC");
                } else if (formOrder == 2) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where idFacult  = '" + formIdFaculty + "' AND UPPER(name) LIKE '%"+ formToFind +"%' order By floor ");
                } else if (formOrder == 3) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where idFacult  = '" + formIdFaculty + "' AND UPPER(name) LIKE '%"+ formToFind +"%' order By floor DESC");
                } else if (formOrder == 4) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where idFacult  = '" + formIdFaculty + "' AND UPPER(name) LIKE '%"+ formToFind +"%'  order By classtype ");
                } else if (formOrder == 5) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where idFacult  = '" + formIdFaculty + "' AND UPPER(name) LIKE '%"+ formToFind +"%' order By classtype DESC");
                } else if (formOrder == 6) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where idFacult  = '" + formIdFaculty + "' AND UPPER(name) LIKE '%"+ formToFind +"%' order By capacity ");
                } else if (formOrder == 7) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where idFacult  = '" + formIdFaculty + "' AND UPPER(name) LIKE '%"+ formToFind +"%' order By capacity DESC");
                } else if (formOrder == 8) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where idFacult  = '" + formIdFaculty + "' AND UPPER(name) LIKE '%"+ formToFind +"%' order By isavailable ");
                } else if (formOrder == 9) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where idFacult  = '" + formIdFaculty + "' AND UPPER(name) LIKE '%"+ formToFind +"%' order By isavailable DESC");
                } else
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where idFacult  = '" + formIdFaculty + "' AND UPPER(name) LIKE '%"+ formToFind +"%'");
            }else {
                if (formOrder == 0) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where UPPER(name) LIKE '%"+ formToFind +"%' order By name");
                } else if (formOrder == 1) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where UPPER(name) LIKE '%"+ formToFind +"%' order By name DESC");
                } else if (formOrder == 2) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where UPPER(name) LIKE '%"+ formToFind +"%' order By floor ");
                } else if (formOrder == 3) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where UPPER(name) LIKE '%"+ formToFind +"%' order By floor DESC");
                } else if (formOrder == 4) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where UPPER(name) LIKE '%"+ formToFind +"%' order By classtype ");
                } else if (formOrder == 5) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where UPPER(name) LIKE '%"+ formToFind +"%' order By classtype DESC");
                } else if (formOrder == 6) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where UPPER(name) LIKE '%"+ formToFind +"%' order By capacity ");
                } else if (formOrder == 7) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where UPPER(name) LIKE '%"+ formToFind +"%' order By capacity DESC");
                } else if (formOrder == 8) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where UPPER(name) LIKE '%"+ formToFind +"%' order By isavailable ");
                } else if (formOrder == 9) {
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where UPPER(name) LIKE '%"+ formToFind +"%' order By isavailable DESC");
                } else
                    resultSet = coreDAO.getStatement().executeQuery("SELECT * FROM classes where UPPER(name) LIKE '%"+ formToFind +"%'");
            }
            while (resultSet.next()) {
                classesEntity = new ClassesEntity();

                classesEntity.setId(resultSet.getInt(1));
                classesEntity.setIdFaculty(resultSet.getInt(2));
                classesEntity.setName(resultSet.getString(3));
                classesEntity.setFloor(resultSet.getInt(4));
                classesEntity.setCapacity(resultSet.getInt(5));
                classesEntity.setAvailable(resultSet.getBoolean(6));
                classesEntity.setClassType(resultSet.getString(7));

                classesEntities.add(classesEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        coreDAO.close();

        return classesEntities;
    }

    public static String getName(int id) {

        return CoreDAO.getString("Select name From classes where id = " + id + "");
    }

    public static int getFacultyId(int id) {
        return CoreDAO.getInt("Select idfacult From classes where id = " + id + "");
    }


    public static Boolean addClasses(int formFacultyId, String formName, int formFloor, int formCapacity, Boolean formAvailable, String formType) {

        try {
            CoreDAO coreDAO = new CoreDAO();

            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("INSERT INTO classes(idfacult, name, floor, capacity, isavailable, classtype) values (?,?,?,?,?,?)");

            preparedStatement.setInt(1, formFacultyId);
            preparedStatement.setString(2, formName);
            preparedStatement.setInt(3, formFloor);
            preparedStatement.setInt(4, formCapacity);
            preparedStatement.setBoolean(5, formAvailable);
            preparedStatement.setString(6, formType);

            preparedStatement.executeUpdate();

            coreDAO.close();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static Boolean delClasses(int formClassID) {
        try {
            CoreDAO coreDAO = new CoreDAO();

            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("DELETE FROM classes WHERE id = ?");

            preparedStatement.setInt(1, formClassID);

            preparedStatement.executeUpdate();

            coreDAO.close();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static ClassesEntity getClasses(int formClassID){
        ClassesEntity classesEntity = new ClassesEntity();

        try{
            CoreDAO coreDAO = new CoreDAO();

            PreparedStatement preparedStatement = coreDAO.getConnection().prepareStatement("SELECT * FROM classes WHERE id = ?");

            preparedStatement.setInt(1, formClassID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                classesEntity.setId(resultSet.getInt("id"));
                classesEntity.setIdFaculty(resultSet.getInt("idfacult"));
                classesEntity.setName(resultSet.getString("name"));
                classesEntity.setFloor(resultSet.getInt("floor"));
                classesEntity.setCapacity(resultSet.getInt("capacity"));
                classesEntity.setAvailable(resultSet.getBoolean("isavailable"));
                classesEntity.setClassType(resultSet.getString("classtype"));
            }

            coreDAO.close();

            return classesEntity;
        }catch(Exception e){
            System.out.println(e);
            return classesEntity;
        }
    }

    public static Boolean editClassess(int formClassID, int formFacultyID, String formName, int formFloor, int formCapacity, Boolean formISAvailable, String formClassType){
        try{
            CoreDAO coreDAO = new CoreDAO();

            PreparedStatement preparedStatement = coreDAO.getConnection()
                    .prepareStatement("UPDATE classes SET idfacult = ?, name = ?, floor = ?, capacity = ?, isavailable = ?, classtype = ? WHERE id = ?");

            preparedStatement.setInt(1, formFacultyID);
            preparedStatement.setString(2, formName);
            preparedStatement.setInt(3, formFloor);
            preparedStatement.setInt(4, formCapacity);
            preparedStatement.setBoolean(5, formISAvailable);
            preparedStatement.setString(6, formClassType);

            preparedStatement.setInt(7, formClassID);

            preparedStatement.executeUpdate();

            coreDAO.close();

            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

}
