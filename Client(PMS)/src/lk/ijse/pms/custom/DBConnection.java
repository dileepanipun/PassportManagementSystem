package lk.ijse.pms.custom;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private Connection connection;

    private static DBConnection dbConnection;

    private DBConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/pmsdb","root","1234");
    }

    public Connection getConnection(){
        return connection;
    }

    public static DBConnection getInstance() throws Exception{
        if(dbConnection == null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }
}
