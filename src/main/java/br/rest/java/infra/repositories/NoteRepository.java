package br.rest.java.infra.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.rest.java.domain.entities.Note;
import br.rest.java.domain.repository.INoteRepository;
import br.rest.java.infra.config.DbConfig;
import br.rest.java.infra.dbcontext.NoteDataContext;
import br.rest.java.infra.queries.NoteQuery;

public class NoteRepository implements INoteRepository {
	
	//TODO: Implement Validations  (all methods)
	
	private NoteDataContext _context;

	public NoteRepository() throws SQLException {
		_context = new NoteDataContext(new DbConfig().getConnection());

	}

	@Override
	public NoteQuery listNoteByID(String noteId) {

		String sql = " SELECT id, title, description FROM notes WHERE id=?";
		NoteQuery note = new NoteQuery();

		try {
			PreparedStatement stmt = _context.connection.prepareStatement(sql);
			stmt.setString(1, noteId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				note.setId(rs.getString("id"));
				note.setTitle(rs.getString("title"));
				note.setDescription(rs.getString("description"));
			}

			stmt.close();

		} catch (Exception e) {
			note = null;
			String em = e.getMessage();
		}

		return note;
	}

	@Override
	public List<NoteQuery> listAllNotes() {
		String sql = " SELECT id, title, description FROM notes";

		List<NoteQuery> notes = new ArrayList<NoteQuery>();

		try {
			PreparedStatement stmt = _context.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			NoteQuery note = new NoteQuery();
			while (rs.next()) {
				note.setId(rs.getString("id"));
				note.setTitle(rs.getString("title"));
				note.setDescription(rs.getString("description"));

				notes.add(note);
				note = new NoteQuery();
			}

			stmt.close();

		} catch (Exception e) {
			notes = null;
			String em = e.getMessage();
		}

		return notes;
	}

	@Override
	public int insertNote(NoteQuery note) {

		String sql = "INSERT INTO notes (id, title, description ) "
				+ "VALUES (?,?,?) ; ";
		
		int noteInserted = 0 ;
		
		try
		{
			PreparedStatement stmt = _context.connection.prepareStatement(sql);
			stmt.setString(1, note.getId());
			stmt.setString(2, note.getTitle());
			stmt.setString(3, note.getDescription());
			
			noteInserted = stmt.executeUpdate();
			stmt.close();
			
		}catch(Exception e)
		{
			String em = e.getMessage();
		}
		
		return noteInserted;
		
	}

	@Override
	public int deleteNote(String noteId) {
		String sql = " DELETE FROM notes WHERE id = ? ;";

		int noteDeleted = 0;

		try {
			PreparedStatement stmt = _context.connection.prepareStatement(sql);
			stmt.setString(1, noteId);
			noteDeleted = stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			String em = e.getMessage();
		}

		return noteDeleted;
	}

	@Override
	public int updateNote(NoteQuery note, String noteId) {		
		
		String sql = " UPDATE notes SET title=?, description=? WHERE id=? ;" ;
		
		int noteUpdated = 0;
		
		try {			
			PreparedStatement stmt = _context.connection.prepareStatement(sql);
			stmt.setString(1, note.getTitle());
			stmt.setString(2, note.getDescription());
			stmt.setString(3, noteId);
			noteUpdated = stmt.executeUpdate();
			stmt.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			String em = e.getMessage();
			
		}
		
		return noteUpdated;
		
	}

	@Override
	protected void finalize() throws Throwable {
		_context.connection.close();
		super.finalize();
	}

}
