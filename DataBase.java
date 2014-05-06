package sjuan;
import java.sql.*;

public class DataBase {
	public static Connection connection;
	public static Statement statement;
	private static String sql ="";

	public static String showResultSet(ResultSet resultSet) throws SQLException {	//Interface mot datamï¿½ngden som utgï¿½r resultatet av en SQL-sats.

		ResultSetMetaData meta = resultSet.getMetaData();
		//String sql = "";

		int colCount = meta.getColumnCount();	// returnerar antalet kolumner i resultatmängden
		for(int i=1; i<=colCount; i++)
			sql += meta.getColumnLabel(i) + ", ";	//returnerar namnet på kolumnen med angivet index

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
	 * Hï¿½r ï¿½r anslutningen skapad och du kan jobba mot databasen.  
	 * Referensvaraibeln statement anvï¿½nds nï¿½r du anvï¿½nder databasen. 
	 * Gï¿½r dock endast jobba mot ett ResultSet (en frï¿½ga) i taget. 
	 * @throws SQLException
	 */
	public static void connect() throws SQLException {
		try {

			Class.forName("com.mysql.jdbc.Driver"); // Hï¿½mta database-driver, kastar ClassNotFoundException
			connection = DriverManager.getConnection("jdbc:mysql://195.178.232.7:4040/ab4607","ab4607","prinsessan"); // Koppla upp mot database-servern, kastar SQLException 
			statement = connection.createStatement(); // Erhï¿½lla en Statement-implementering fï¿½r att exekvera SQL-satser, kastar  // SQLException 
			// Hï¿½r ï¿½r anslutningen skapad och du kan jobba mot databasen.  
			// Du anvï¿½nder referensvaraibeln statement nï¿½r du anvï¿½nder databasen. Du kan  
			// dock endast jobba mot ett ResultSet (en frï¿½ga) i taget.

		} catch(ClassNotFoundException e1) {
			System.out.println("Databas-driver hittades ej: "+e1);
		}
	}

	/**
	 * Avsluta databas-kopplingen, bï¿½da anropen kastar SQLException
	 * @throws SQLException
	 */

	public static void disconnect() throws SQLException {
		statement.close();
		connection.close();
	}

	public static void main(String[] args) {
		try {
			connect();

			ResultSet resultId = statement.executeQuery("SELECT id FROM ab4607.statistics"); // Stï¿½lla en frï¿½ga som ger en resultatmï¿½ngd
			showResultSet(resultId);

			ResultSet resultNamn = statement.executeQuery("SELECT namn FROM ab4607.statistics"); // Stï¿½lla en frï¿½ga som ger en resultatmï¿½ngd
			showResultSet(resultNamn);

			disconnect();
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
}