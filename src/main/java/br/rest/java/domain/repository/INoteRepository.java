package br.rest.java.domain.repository;

import java.util.List;

import br.rest.java.domain.entities.Note;
import br.rest.java.infra.queries.NoteQuery;

public interface INoteRepository 
{
	NoteQuery listNoteByID(String noteId);

	List<NoteQuery> listAllNotes();

	int insertNote(NoteQuery note);

	int deleteNote(String noteId);

	int updateNote(NoteQuery note, String noteId);

}
