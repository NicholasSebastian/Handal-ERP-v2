package com.handalcargo.core;

import java.sql.*;
import java.util.function.Consumer;

public class Database {
	
	private static Connection connection;
	
	// TODO: adjust session class and userprofile class.
	// TODO: do the staff class.
	// TODO: everything else.
	
	private static final String 
	    hostname = "192.168.1.124", 
	    port = "3306",	// Default value. Can be changed in the my.ini file.
	    databaseName = "handalcargo",
	    user = "handalcargo",
	    password = "hunter1389";
	
	public static Exception initialize() {
		try {
			// Register the MySQL Connector/J.
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			// Initialize connection with database.
			String url = "jdbc:mysql://" + hostname + ":" + port + "/" + databaseName + "?serverTimezone=UTC";
			System.out.println("connecting to database...");
	        connection = DriverManager.getConnection(url, user, password);
			System.out.println("connected...");
		}
		catch (Exception e) {
			e.printStackTrace();
			return e;
		}
		return null;
	}
	
	// Reading from the database with a static statement.
	public static ResultSet query(String query) {
		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery(query);
			return results;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Reading from the database with a prepared statement.
	public static ResultSet query(String query, Consumer<PreparedStatement> setStatement) {
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			setStatement.accept(statement);
			ResultSet results = statement.executeQuery();
			return results;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	// Modifying the database.
	public static void update(String query, Consumer<PreparedStatement> setStatement) {
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			setStatement.accept(statement);
			int i = statement.executeUpdate();
			System.out.println(i + " records updated.");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Closing the connection.
	public static void closeConnection() {
		try {
			connection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Utility function for setting nullable numeric types.
	public static void setNumber(PreparedStatement statement, int parameterIndex, int type, String value) {
		try {
			if (value != null && !value.isEmpty()) {
				switch (type) {
				case Types.INTEGER:
					statement.setInt(parameterIndex, Integer.parseInt(value));
					break;
				case Types.FLOAT:
					statement.setFloat(parameterIndex, Float.parseFloat(value));
					break;
				case Types.DOUBLE:
					statement.setDouble(parameterIndex, Double.parseDouble(value));
					break;
				default:
					throw new SQLException();
				}
			}
			else 
				statement.setNull(parameterIndex, type);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
