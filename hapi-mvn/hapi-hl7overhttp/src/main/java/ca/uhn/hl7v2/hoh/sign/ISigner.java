package ca.uhn.hl7v2.hoh.sign;


public interface ISigner {

	public String sign(byte[] theBytes);
	
	public void verify(byte[] theBytes, String theSignature) throws MessageDoesNotVerifyException;
	
	
}
