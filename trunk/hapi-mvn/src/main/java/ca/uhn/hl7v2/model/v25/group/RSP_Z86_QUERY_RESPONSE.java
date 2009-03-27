package ca.uhn.hl7v2.model.v25.group;

import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v25.segment.*;

import ca.uhn.hl7v2.model.*;
/**
 * <p>Represents the RSP_Z86_QUERY_RESPONSE Group.  A Group is an ordered collection of message 
 * segments that can repeat together or be optionally in/excluded together.
 * This Group contains the following elements: </p>
 * 0: RSP_Z86_PATIENT (a Group object) <b>optional </b><br>
 * 1: RSP_Z86_COMMON_ORDER (a Group object) <b>repeating</b><br>
 */
public class RSP_Z86_QUERY_RESPONSE extends AbstractGroup {

	/** 
	 * Creates a new RSP_Z86_QUERY_RESPONSE Group.
	 */
	public RSP_Z86_QUERY_RESPONSE(Group parent, ModelClassFactory factory) {
	   super(parent, factory);
	   try {
	      this.add(RSP_Z86_PATIENT.class, false, false);
	      this.add(RSP_Z86_COMMON_ORDER.class, true, true);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating RSP_Z86_QUERY_RESPONSE - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns RSP_Z86_PATIENT (a Group object) - creates it if necessary
	 */
	public RSP_Z86_PATIENT getPATIENT() { 
	   RSP_Z86_PATIENT ret = null;
	   try {
	      ret = (RSP_Z86_PATIENT)this.get("PATIENT");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns  first repetition of RSP_Z86_COMMON_ORDER (a Group object) - creates it if necessary
	 */
	public RSP_Z86_COMMON_ORDER getCOMMON_ORDER() { 
	   RSP_Z86_COMMON_ORDER ret = null;
	   try {
	      ret = (RSP_Z86_COMMON_ORDER)this.get("COMMON_ORDER");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of RSP_Z86_COMMON_ORDER
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public RSP_Z86_COMMON_ORDER getCOMMON_ORDER(int rep) throws HL7Exception { 
	   return (RSP_Z86_COMMON_ORDER)this.get("COMMON_ORDER", rep);
	}

	/** 
	 * Returns the number of existing repetitions of RSP_Z86_COMMON_ORDER 
	 */ 
	public int getCOMMON_ORDERReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("COMMON_ORDER").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

}
