package sjuan;
import java.sql.*;

public class DataBase {
    public static Connection connection;
    public static Statement statement;
    
    public static void showResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData meta = resultSet.getMetaData();
        String res = "";

        int colCount = meta.getColumnCount();
        for(int i=1; i<=colCount; i++)
            res += meta.getColumnLabel(i) + ", ";

        res += "\n";

        while(resultSet.next()) {
            for(int i=1; i<=colCount; i++)
                res += resultSet.getObject(i).toString() + ", ";

            res += "\n";
        }
        System.out.println(res);
    }
    
    public static void connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://195.178.232.7:4040/ab4607","ab4607","prinsessan");
            statement = connection.createStatement();
        } catch(ClassNotFoundException e1) {
            System.out.println("Databas-driver hittades ej: "+e1);
        }
    }
    
    public static void disconnect() throws SQLException {
        statement.close();
        connection.close();
    }
    
    public static void main(String[] args) {
        try {
            connect();
            
            ResultSet result = statement.executeQuery("SELECT name, points FROM ab4607.statistics");
            showResultSet(result);
            
            disconnect();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
}