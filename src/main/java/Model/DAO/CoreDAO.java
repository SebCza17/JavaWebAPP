package Model.DAO;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class CoreDAO {

    private Connection connection;
    private Statement statement;

    CoreDAO() {
        try {
            InitialContext initialContext = new InitialContext();

            DataSource dataSource = (DataSource) initialContext.lookup("jdbc/RoomBook2");

            this.connection = dataSource.getConnection();
            this.statement = connection.createStatement();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    Statement getStatement() {
        return statement;
    }

    public Connection getConnection() {
        return connection;
    }

    void close(){
        try {
            this.connection.close();
            this.statement.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }


    static Boolean check(String query) {
        ResultSet resultSet = null;
        boolean test = false;

        CoreDAO coreDAO = new CoreDAO();

        try {
            resultSet = coreDAO.getStatement().executeQuery(query);
            if (resultSet.next()) test = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        coreDAO.close();

        return test;
    }

    static String getString(String query) {
        String returnString = "";

        CoreDAO coreDAO = new CoreDAO();

        try {
            ResultSet resultSet = coreDAO.getStatement().executeQuery(query);
            while(resultSet.next())
                returnString = resultSet.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        coreDAO.close();

        return returnString;
    }
}
