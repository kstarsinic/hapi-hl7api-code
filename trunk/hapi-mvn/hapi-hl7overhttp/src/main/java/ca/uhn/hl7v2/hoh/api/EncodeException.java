package ca.uhn.hl7v2.hoh.api;


public class EncodeException extends Exception {

	private static final long serialVersionUID = 1L;

	public EncodeException(Exception theCause) {
		super(theCause);
	}

}
