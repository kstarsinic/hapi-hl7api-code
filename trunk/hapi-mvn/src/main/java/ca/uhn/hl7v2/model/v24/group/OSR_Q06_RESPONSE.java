package ca.uhn.hl7v2.model.v24.group;

import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v24.segment.*;

import ca.uhn.hl7v2.model.*;
/**
 * <p>Represents the OSR_Q06_RESPONSE Group.  A Group is an ordered collection of message 
 * segments that can repeat together or be optionally in/excluded together.
 * This Group contains the following elements: </p>
 * 0: OSR_Q06_PATIENT (a Group object) <b>optional </b><br>
 * 1: OSR_Q06_ORDER (a Group object) <b>repeating</b><br>
 */
public class OSR_Q06_RESPONSE extends AbstractGroup {

	/** 
	 * Creates a new OSR_Q06_RESPONSE Group.
	 */
	public OSR_Q06_RESPONSE(Group parent, ModelClassFactory factory) {
	   super(parent, factory);
	   try {
	      this.add(OSR_Q06_PATIENT.class, false, false);
	      this.add(OSR_Q06_ORDER.class, true, true);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating OSR_Q06_RESPONSE - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns OSR_Q06_PATIENT (a Group object) - creates it if necessary
	 */
	public OSR_Q06_PATIENT getPATIENT() { 
	   OSR_Q06_PATIENT ret = null;
	   try {
	      ret = (OSR_Q06_PATIENT)this.get("PATIENT");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns  first repetition of OSR_Q06_ORDER (a Group object) - creates it if necessary
	 */
	public OSR_Q06_ORDER getORDER() { 
	   OSR_Q06_ORDER ret = null;
	   try {
	      ret = (OSR_Q06_ORDER)this.get("ORDER");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of OSR_Q06_ORDER
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public OSR_Q06_ORDER getORDER(int rep) throws HL7Exception { 
	   return (OSR_Q06_ORDER)this.get("ORDER", rep);
	}

	/** 
	 * Returns the number of existing repetitions of OSR_Q06_ORDER 
	 */ 
	public int getORDERReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("ORDER").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

}
