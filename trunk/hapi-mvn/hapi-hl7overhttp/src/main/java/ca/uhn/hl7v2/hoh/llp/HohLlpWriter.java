package ca.uhn.hl7v2.hoh.llp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import ca.uhn.hl7v2.hoh.AbstractHl7OverHttpEncoder;
import ca.uhn.hl7v2.hoh.Hl7OverHttpRequestEncoder;
import ca.uhn.hl7v2.hoh.Hl7OverHttpResponseEncoder;
import ca.uhn.hl7v2.hoh.util.ServerRoleEnum;
import ca.uhn.hl7v2.llp.HL7Writer;
import ca.uhn.hl7v2.llp.LLPException;

class HohLlpWriter implements HL7Writer {

	private OutputStream myOutputStream;
	private Charset myPreferredCharset;
	private Hl7OverHttpLowerLayerProtocol myProtocol;

	/**
	 * Constructor
	 */
	public HohLlpWriter(Hl7OverHttpLowerLayerProtocol theProtocol) {
		myProtocol = theProtocol;
	}

	/**
	 * {@inheritDoc}
	 */
	public void close() throws IOException {
		myOutputStream.close();
	}

	OutputStream getOutputStream() {
		return myOutputStream;
	}

	/**
	 * @return the preferredCharset
	 */
	public Charset getPreferredCharset() {
		return myPreferredCharset;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setOutputStream(OutputStream theOutputStream) throws IOException {
		myOutputStream = theOutputStream;
	}

	/**
	 * @param thePreferredCharset
	 *            the preferredCharset to set
	 */
	public void setPreferredCharset(Charset thePreferredCharset) {
		myPreferredCharset = thePreferredCharset;
	}

	/**
	 * {@inheritDoc}
	 */
	public void writeMessage(String theArg0) throws LLPException, IOException {

		AbstractHl7OverHttpEncoder e;
		if (myProtocol.getRole() == ServerRoleEnum.CLIENT) {
			e = new Hl7OverHttpRequestEncoder();
			if (myProtocol.getAuthorizationClientCallback() != null) {
				e.setUsername(myProtocol.getAuthorizationClientCallback().provideUsername(myProtocol.getUriPath()));
				e.setPassword(myProtocol.getAuthorizationClientCallback().providePassword(myProtocol.getUriPath()));
			}
		} else {
			e = new Hl7OverHttpResponseEncoder();
		}

		e.setMessage(theArg0);
		if (getPreferredCharset() != null) {
			e.setCharset(getPreferredCharset());
		}

		e.setUri(myProtocol.getUriPath());
		DataOutputStream dos = new DataOutputStream(myOutputStream);
		e.encodeToOutputStream(dos);

		dos.flush();

	}

}
