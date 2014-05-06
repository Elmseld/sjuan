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
            Class.forName("com.mysql.jdbc.Driver"); // Hämta database-driver, kastar ClassNotFoundException
            connection = DriverManager.getConnection("jdbc:mysql://195.178.232.7:4040/ab4607","ab4607","prinsessan"); // Koppla upp mot database-servern, kastar SQLException 
           statement = connection.createStatement(); // Erhålla en Statement-implementering för att exekvera SQL-satser, kastar  // SQLException 
           // Här är anslutningen skapad och du kan jobba mot databasen.  
           // Du använder referensvaraibeln statement när du använder databasen. Du kan  
           // dock endast jobba mot ett ResultSet (en fråga) i taget.
           
        } catch(ClassNotFoundException e1) {
            System.out.println("Databas-driver hittades ej: "+e1);
        }
    }
    // Avsluta databas-kopplingen, båda anropen kastar SQLException 
    public static void disconnect() throws SQLException {
        statement.close();
        connection.close();
    }
    
    public static void main(String[] args) {
        try {
            connect();
            
            ResultSet resultId = statement.executeQuery("SELECT id FROM ab4607.statistics"); // Ställa en fråga som ger en resultatmängd
            showResultSet(resultId);
            
            ResultSet resultNamn = statement.executeQuery("SELECT namn FROM ab4607.statistics"); // Ställa en fråga som ger en resultatmängd
            showResultSet(resultNamn);
            
            disconnect();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
}