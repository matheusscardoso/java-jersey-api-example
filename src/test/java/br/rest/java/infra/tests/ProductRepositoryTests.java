package br.rest.java.infra.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.rest.java.infra.queries.NoteQuery;
import br.rest.java.infra.repositories.NoteRepository;

class ProductRepositoryTests {

	@Test
	void ListNoteByID() throws SQLException {
		String noteId = "123abc11";		
		NoteRepository repo = new NoteRepository();		
		NoteQuery note = repo.listNoteByID(noteId);
		assertNotNull(note, "Object Null");
	}
	
	@Test
	void ListAllNotes() throws SQLException 
	{
		List<NoteQuery> notes = new ArrayList<>();
		NoteRepository repo = new NoteRepository();		
		notes = repo.listAllNotes();		
		assertTrue(notes.size() > 0 , "Empty Result");		
		
	}

}
