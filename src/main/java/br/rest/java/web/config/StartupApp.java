package br.rest.java.web.config;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;




@Stateless
@ApplicationPath("v1")
public class StartupApp extends Application{

	@Override
	public Set<Class<?>> getClasses(){
		final Set<Class<?>> classes = new HashSet<>();		
		classes.add( br.rest.java.web.services.NotesService.class);
		return classes;
	}
}
