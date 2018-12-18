package br.rest.java.infra.dbcontext;

import java.sql.Connection;
import java.sql.SQLException;

public class NoteDataContext {

	public Connection connection;

	public NoteDataContext(Connection connection) throws SQLException {
		// TODO Auto-generated constructor stub
		this.connection = connection;
	}

}
