package ca.uhn.hl7v2.hoh.server.raw;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ca.uhn.hl7v2.hoh.server.MessageMetadataKeys;

public class StringRawMessage implements IRawMessage {

	private final String myRawMessage;
	private final Map<String, Object> myMetadata = new HashMap<String, Object>();

	public StringRawMessage(String theRawMessage) {
		myRawMessage = theRawMessage;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String getRawMessage() {
		return myRawMessage;
	}

	/**
	 * Add a metadata value
	 * 
	 * @param theKey The key
	 * @param theValue The value
	 * @throws NullPointerException If theKey is null
	 */
	public void addMetadata(String theKey, Object theValue) {
		if (theKey == null) {
			throw new NullPointerException("Key may not be null");
		}
		
		if (theValue != null) {
			if (MessageMetadataKeys.keyStringSet().contains(theKey)) {
				Class<?> valueType = MessageMetadataKeys.valueOf(theKey).getValueType();
				if (!valueType.isAssignableFrom(theValue.getClass())) {
					throw new IllegalArgumentException("Value for key \"" + theKey + "\" must be of type: " + valueType.getName());
				}
			}
		}
		
		myMetadata.put(theKey, theValue);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> getMetadata() {
		return Collections.unmodifiableMap(myMetadata);
	}


}
