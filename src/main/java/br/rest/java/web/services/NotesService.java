package br.rest.java.web.services;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.rest.java.infra.queries.NoteQuery;
import br.rest.java.infra.repositories.NoteRepository;

@Path("notes")
public class NotesService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		// return listOfNotes;
		try {
			NoteRepository repo = new NoteRepository();
			List<NoteQuery> notes = repo.listAllNotes();

			if (notes.size() != 0) {
				return Response.ok(notes).build();
			}
			// return Response.status(Response.Status.OK)
			// .entity(notes)
			// .build();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			String em = e.getMessage();
		}
		
		return Response.status(Response.Status.NOT_FOUND).build();
		
	}

	@GET
	@Path("{noteId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listById(@PathParam("noteId") String noteId) {

		NoteQuery note = new NoteQuery();

		try {
			NoteRepository repo = new NoteRepository();
			note = repo.listNoteByID(noteId);

			if (note.getId() != null) {
				return Response.ok(note).build();
			}

		} catch (Exception e) {
			@SuppressWarnings("unused")
			String em = e.getMessage();
			note = null;
		}

		return Response.status(Response.Status.NOT_FOUND).build();

	}

	@PUT
	@Path("{noteId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateNote(@PathParam("noteId") String noteId, NoteQuery note) {

		// TODO: implement Validations
		// NoteQuery _note = new NoteQuery(note.getId(),
		// note.getTitle(),
		// note.getDescription());
		// if(_note.isValid) {
		//
		// }
		// if(noteId.equals(note.getId())

		int noteUpdated = 0;

		try {
			NoteQuery notequery = new NoteQuery();
			notequery.setId(note.getId());
			notequery.setTitle(note.getTitle());
			notequery.setDescription(note.getDescription());

			noteUpdated = new NoteRepository().updateNote(notequery, noteId);

			if (noteUpdated == 1) {
				return Response.status(Response.Status.ACCEPTED).entity(notequery).build();
			}

		} catch (Exception e) {
			@SuppressWarnings("unused")
			String em = e.getMessage();
			note = null;
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		return Response.status(Response.Status.ACCEPTED).entity(note).build();

	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertNote(NoteQuery note) {

		// TODO: implement Validations
		// NoteQuery _note = new NoteQuery(note.getId(),
		// note.getTitle(),
		// note.getDescription());
		// if(_note.isValid) {
		//
		// }
		int noteInserted = 0;

		try {
			NoteQuery _note = new NoteQuery(); // TODO: Implement Validations
			_note.setId(note.getId());
			_note.setTitle(note.getTitle());
			_note.setDescription(note.getDescription());

			noteInserted = new NoteRepository().insertNote(_note);
			if (noteInserted == 1)
				return Response.status(Response.Status.CREATED).entity(_note).build();

			// ("{ result: " + noteInserted + ", note : " + nq + " }").build();
		} catch (Exception e) {
			@SuppressWarnings("unused")
			String em = e.getMessage();
			note = null;
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		return Response.status(Response.Status.NO_CONTENT).build();

	}

	@DELETE
	@Path("{noteId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteNote(@PathParam("noteId") String noteId) {
		int noteDeleted = 0;

		try {

			if (NoteExistsDB(noteId)) {

				noteDeleted = new NoteRepository().deleteNote(noteId);

				if (noteDeleted == 1) {
					return Response.status(Response.Status.OK).build();
					// return Response.status(Response.Status.OK).entity(nq).build();
				}
			}

		} catch (Exception e) {
			@SuppressWarnings("unused")
			String em = e.getMessage();
		}

		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	private boolean NoteExistsDB(String noteID) throws SQLException {
		NoteQuery noteExists = new NoteRepository().listNoteByID(noteID);

		if (noteExists.getId() == null)
			return false;
		else
			return true;
	}

}
