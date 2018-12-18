package br.rest.java.shared;

public class Strings {

	@SuppressWarnings("null")
	public static boolean IsNullOrEmpty(String param) {
		boolean res = false;
		try {
			
			if (param == null || param.isEmpty()) {
				res = true;
			}
		}
		catch (NullPointerException e) {
			@SuppressWarnings("unused")
			String em = e.getMessage();
			res = true;
		}
		return res;
	}

}
