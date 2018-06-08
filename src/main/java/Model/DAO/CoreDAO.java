package Model.DAO;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
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
}
