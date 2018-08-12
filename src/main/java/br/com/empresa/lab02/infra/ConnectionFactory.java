package br.com.empresa.lab02.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnectionMySQL() {
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 return DriverManager.getConnection("jdbc:mysql://localhost/lojaDB", "root", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	

	
	
	
}
