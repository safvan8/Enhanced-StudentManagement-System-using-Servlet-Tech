package in.ineuron.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcUtil
{

	private static Connection connection = null;

	static
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	// to get Physical connection
	public static Connection getConnection()
	{
		if (connection == null)
		{
			String url = "jdbc:mysql://localhost:3306/schooldbo";
			String username = "root";
			String passwd = "Safvan@123";
			try
			{
				connection = DriverManager.getConnection(url, username, passwd);
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Databse connection establised successfully..............\n");

		System.out.println("Connection object is: " + connection);

		return connection;
	}

}
