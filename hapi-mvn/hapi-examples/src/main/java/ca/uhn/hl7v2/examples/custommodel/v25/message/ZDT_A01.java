/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.uhn.hl7v2.examples.custommodel.v25.message;

import java.util.Arrays;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.examples.custommodel.v25.segment.ZPI;
import ca.uhn.hl7v2.model.v25.message.ADT_A01;
import ca.uhn.hl7v2.parser.ModelClassFactory;

/**
 * Example custom model class. This is a ZDT^A01, which is an ADT^A01 with
 * an extra ZPI segment after the PID segment
 *
 * Since we're extending an existing HL7 message type, we just extend from the model class
 * representing that type
 */
@SuppressWarnings("serial")
public class ZDT_A01 extends ADT_A01 {

 	/**
	 * Constructor
     *
     * We always have to have a constructor with this one argument
	 */
	public ZDT_A01(ModelClassFactory factory) throws HL7Exception {
	   super(factory);

       // Now, let's add the ZPI segment at the right spot
       String[] segmentNames = getNames();
       int indexOfPid = Arrays.asList(segmentNames).indexOf("PID");

       Class<ZPI> type = ZPI.class;
       boolean required = true;
       boolean repeating = false;
       int index = indexOfPid + 1;

       this.add(type, required,repeating,index);
	}


    /**
     * Add an accessor for the ZPI segment
     */
    public ZPI getZPI() throws HL7Exception {
        return getTyped("ZPI", ZPI.class);
    }

}
