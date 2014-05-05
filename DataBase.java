package sjuan;
import java.sql.*;

public class DataBase {
	public static Connection connection;
	public static Statement statement;
	private static String sql ="";
	public static String showResultSet(ResultSet resultSet) throws SQLException {	//Interface mot datam�ngden som utg�r resultatet av en SQL-sats.
		ResultSetMetaData meta = resultSet.getMetaData();
	    //String sql = "";


		int colCount = meta.getColumnCount();
		for(int i=1; i<=colCount; i++)
			sql += meta.getColumnLabel(i) + ", ";

		sql += "\n";

		while(resultSet.next()) {
			for(int i=1; i<=colCount; i++)
				sql += resultSet.getObject(i).toString() + ", ";

			sql += "\n";
		}
		System.out.println(sql);
		return sql;
	}

	
	/**
	 * H�r �r anslutningen skapad och du kan jobba mot databasen.  
	 * Referensvaraibeln statement anv�nds n�r du anv�nder databasen. 
	 * G�r dock endast jobba mot ett ResultSet (en fr�ga) i taget. 
	 * @throws SQLException
	 */
	public static void connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");	// H�mta database-driver, kastar ClassNotFoundException 
			connection = DriverManager.getConnection("jdbc:mysql://195.178.232.7:4040/ab4607","ab4607","prinsessan");	// Koppla upp mot database-servern, kastar SQLException 
			statement = connection.createStatement();	// Erh�lla en Statement-implementering f�r att exekvera SQL-satser, kastar  // SQLException
		} catch(ClassNotFoundException e1) {
			System.out.println("Databas-driver hittades ej: "+e1);
		}
	}

	/**
	 * Avsluta databas-kopplingen, b�da anropen kastar SQLException
	 * @throws SQLException
	 */
	public static void disconnect() throws SQLException {
		statement.close();
		connection.close();
	}

	public static void main(String[] args) {
		try {
			connect();

			ResultSet result = statement.executeQuery("SELECT * FROM ab4607.statistics");
			showResultSet(result);

			disconnect();
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
}