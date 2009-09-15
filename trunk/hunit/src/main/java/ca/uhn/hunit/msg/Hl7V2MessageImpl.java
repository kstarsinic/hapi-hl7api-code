/**
 *
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the
 * specific language governing rights and limitations under the License.
 *
 * The Initial Developer of the Original Code is University Health Network. Copyright (C)
 * 2001.  All Rights Reserved.
 *
 * Alternatively, the contents of this file may be used under the terms of the
 * GNU General Public License (the  "GPL"), in which case the provisions of the GPL are
 * applicable instead of those above.  If you wish to allow use of your version of this
 * file only under the terms of the GPL and not to allow others to use your version
 * of this file under the MPL, indicate your decision by deleting  the provisions above
 * and replace  them with the notice and other provisions required by the GPL License.
 * If you do not delete the provisions above, a recipient may use your version of
 * this file under either the MPL or the GPL.
 */
package ca.uhn.hunit.msg;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.EncodingNotSupportedException;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.validation.impl.ValidationContextImpl;
import ca.uhn.hunit.ex.ConfigurationException;
import ca.uhn.hunit.iface.TestMessage;
import ca.uhn.hunit.xsd.Hl7V2MessageDefinition;

public class Hl7V2MessageImpl extends AbstractMessage {

	private String myText;
	private Message myParsedMessage;

	public Hl7V2MessageImpl(Hl7V2MessageDefinition theConfig) throws ConfigurationException {
		super(theConfig);

		myText = theConfig.getText().trim().replaceAll("(\\r|\\n)+", "\r");
		
		PipeParser parser = new PipeParser();
		parser.setValidationContext(new ValidationContextImpl());
		try {
		    // Parse and re-encode to strip out any inconsistancies in the message (extra blank fields at the end of segments, etc) 
            myParsedMessage = parser.parse(myText);
			myText = parser.encode(myParsedMessage);
        } catch (EncodingNotSupportedException e) {
            throw new ConfigurationException(e);
        } catch (HL7Exception e) {
            throw new ConfigurationException(e);
        }
	}

	@Override
	public String getText() {
		return myText;
	}

	public TestMessage getTestMessage() {
		return new TestMessage(myText, myParsedMessage);
	}
	
	public Message getMessage() {
		return myParsedMessage;
	}
	
}