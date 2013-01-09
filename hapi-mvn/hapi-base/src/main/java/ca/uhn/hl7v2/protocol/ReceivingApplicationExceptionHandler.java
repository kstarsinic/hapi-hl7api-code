package ca.uhn.hl7v2.protocol;

import java.util.Map;

import ca.uhn.hl7v2.HL7Exception;

/**
 * The Interface ReceivingApplicationExceptionHandler. Allow applications to handle
 * parsing and handling errors.
 *
 * @author Gabriel Landais
 */
public interface ReceivingApplicationExceptionHandler {

	/**
	 * Process an exception.
	 * 
	 * @param incomingMessage
	 *            the incoming message. This is the raw message which was
	 *            received from the external system
	 * @param incomingMetadata
	 *            Any metadata that accompanies the incoming message. See {@link ca.uhn.hl7v2.protocol.Transportable#getMetadata()}
	 * @param outgoingMessage
	 *            the outgoing message. The response NAK message generated by
	 *            HAPI.
	 * @param e
	 *            the exception which was received
	 * @return The new outgoing message. This can be set to the value provided
	 *         by HAPI in <code>outgoingMessage</code>, or may be replaced with
	 *         another message. <b>This method may not return <code>null</code></b>.
	 */
	// ********************************
	// Note, if you update the JavaDoc here, also update it in the hapi-example
	// file SendAndReceiveAMessage.java as it's duplicated there!
	// ********************************
	public String processException(String incomingMessage, Map<String, Object> incomingMetadata, String outgoingMessage, Exception e) throws HL7Exception;

}
