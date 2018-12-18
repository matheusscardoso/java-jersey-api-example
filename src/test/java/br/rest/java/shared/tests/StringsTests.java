package br.rest.java.shared.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.rest.java.shared.Strings;

class StringsTests {

	@Test
	void StringIsNull() {
		assertTrue(Strings.IsNullOrEmpty(null));
	}

	@Test
	void StringIsEmpty() {
		assertTrue(Strings.IsNullOrEmpty(""));		
	}

}
