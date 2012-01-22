/**
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the
 * specific language governing rights and limitations under the License.
 *
 * The Original Code is ""  Description:
 * ""
 *
 * The Initial Developer of the Original Code is University Health Network. Copyright (C)
 * 2001.  All Rights Reserved.
 *
 * Contributor(s): ______________________________________.
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
package ca.uhn.hl7v2.testpanel.model.msg;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v26.message.ADT_A01;
import ca.uhn.hl7v2.testpanel.model.msg.Hl7V2MessageBase;
import ca.uhn.hl7v2.testpanel.util.Range;

public class Hl7V2MessageImplTest {

	@Test
	public void testFindRange() throws HL7Exception {
		
		String message = "MSH|^~\\&|\r" // 10 chars
				+ "PID|f1c1^f1c2&f1c2s2&f1c2s3|f2c1^f2c2&f2c2s2&f2c2s3\r";
		
		Range startRange = new Range(10, message.length());
		ADT_A01 parsed = new ADT_A01();
		parsed.parse(message);

		ArrayList<Integer> path = new ArrayList<Integer>() {{ add(1); }};
		Range range = Hl7V2MessageBase.findFieldRange(path, startRange, message, parsed);
		Assert.assertEquals("f1c1^f1c2&f1c2s2&f1c2s3", range.applyTo(message));
		
		path = new ArrayList<Integer>() {{ add(1); add(1); }};
		range = Hl7V2MessageBase.findFieldRange(path, startRange, message, parsed);
		Assert.assertEquals("f1c1", range.applyTo(message));
		
		path = new ArrayList<Integer>() {{ add(1); add(2); }};
		range = Hl7V2MessageBase.findFieldRange(path, startRange, message, parsed);
		Assert.assertEquals("f1c2&f1c2s2&f1c2s3", range.applyTo(message));

		path = new ArrayList<Integer>() {{ add(1); add(2); }};
		range = Hl7V2MessageBase.findFieldRange(path, startRange, message, parsed);
		Assert.assertEquals("f1c2&f1c2s2&f1c2s3", range.applyTo(message));

		path = new ArrayList<Integer>() {{ add(1); add(2); }};
		range = Hl7V2MessageBase.findFieldRange(path, startRange, message, parsed);
		Assert.assertEquals("f1c2&f1c2s2&f1c2s3", range.applyTo(message));

	
	}
	
}
