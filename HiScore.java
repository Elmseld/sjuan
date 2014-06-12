package sjuan;
import java.sql.*;

import javax.swing.JOptionPane;

public class HiScore{	
	public static Statement statement;
	public static Connection connection;
	public static java.sql.PreparedStatement statement1;
	private static String antalVinster, antalFörluster, Förluster, antalSpeladeSpel;
	private static String vinst;


	/*
	 * Metoden gör så att det adderas 1 till vinster kolumnen där användarNamnet är knut (AnvändarNamnet ska bytas ut mot de inloggade
	 * namnet. Dvs samma instansvariabel userName som i databasklassen.
	 */
	public static String vunnaSpel(){
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://195.178.232.7:4040/ab4607","ab4607","prinsessan");
			statement = connection.createStatement();


			String vinst = "UPDATE statistics " + "SET Vinster = Vinster+1 WHERE AnvändarNamn = 'knut'";
			statement.executeUpdate(vinst);
			String selectQuery = "SELECT Vinster FROM statistics WHERE AnvändarNamn = 'knut'";	
			ResultSet resultSet = statement.executeQuery(selectQuery);	

			while (resultSet.next()){
				String statistic = resultSet.getString("Vinster");
				JOptionPane.showMessageDialog( null, "Vinster: " + statistic);
			}	


		}
		catch (SQLException e)
		{
			System.out.println("Connection String is not correct:Unable to connect to the given database");
		}
		return antalVinster;

	}


	/*
	 * Metoden gör så att det adderas 1 till förluster kolumnen där användarNamnet är knut (AnvändarNamnet ska bytas ut mot de inloggade
	 * namnet. Dvs samma instansvariabel userName som i databasklassen.
	 */
	public static String lostGames() throws SQLException{
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://195.178.232.7:4040/ab4607","ab4607","prinsessan");
			statement = connection.createStatement();


			String Förluster = "UPDATE statistics " + "SET Förluster = Förluster + 1 WHERE AnvändarNamn = 'knut'";
			statement.executeUpdate(Förluster);
			String selectQuery = "SELECT Förluster FROM statistics WHERE AnvändarNamn = 'knut'";	
			ResultSet resultSet = statement.executeQuery(selectQuery);	

			while (resultSet.next()){
				String hej = resultSet.getString("Förluster");
				JOptionPane.showMessageDialog( null, "Förluster: " + hej);
			}	

		}
		catch (SQLException e)
		{
			System.out.println("Connection String is not correct:Unable to connect to the given database");
		}

		return antalFörluster;

	}



	public static String playedGames(){/*
	 * Metoden gör så att det vinster + förluster läggs till i speladespel kolumnen där användarNamnet är knut (AnvändarNamnet ska bytas ut mot de inloggade
	 * namnet. Dvs samma instansvariabel userName som i databasklassen.
	 */
		try
		{
			connection = DriverManager.getConnection("jdbc:mysql://195.178.232.7:4040/ab4607","ab4607","prinsessan");
			statement = connection.createStatement();


			String SpeladeSpel = "UPDATE statistics " + "SET SpeladeSpel = SpeladeSpel + 1 WHERE AnvändarNamn = 'knut'";
			statement.executeUpdate(SpeladeSpel);
			String selectQuery = "SELECT SpeladeSpel FROM statistics WHERE AnvändarNamn = 'knut'";	
			ResultSet resultSet = statement.executeQuery(selectQuery);	

			while (resultSet.next()){
				String tja = resultSet.getString("SpeladeSpel");
				JOptionPane.showMessageDialog( null, "SpeladeSpel: " + tja);
			}	

		}
		catch (SQLException e)
		{
			System.out.println("Connection String is not correct:Unable to connect to the given database");
		}
		return antalSpeladeSpel;

	}


	//		



	public static void main(String[] args) throws SQLException {
		vunnaSpel();
		lostGames();
		playedGames();
		//	returnAll();
	}
}