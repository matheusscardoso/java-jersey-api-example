package br.rest.java.infra.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
	private String CONNECTIONSTRING = "jdbc:postgresql://127.0.0.1:5432/rest_notes";
	private String USER = "admin";
	private String PASS = "admin";

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(CONNECTIONSTRING, USER, PASS);
	}

}
