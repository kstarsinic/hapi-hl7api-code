package ca.uhn.hl7v2.model.v24.group;

import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v24.segment.*;

import ca.uhn.hl7v2.model.*;
/**
 * <p>Represents the ORL_O22_PATIENT Group.  A Group is an ordered collection of message 
 * segments that can repeat together or be optionally in/excluded together.
 * This Group contains the following elements: </p>
 * 0: PID (Patient identification) <b></b><br>
 * 1: ORL_O22_GENERAL_ORDER (a Group object) <b>repeating</b><br>
 */
public class ORL_O22_PATIENT extends AbstractGroup {

	/** 
	 * Creates a new ORL_O22_PATIENT Group.
	 */
	public ORL_O22_PATIENT(Group parent, ModelClassFactory factory) {
	   super(parent, factory);
	   try {
	      this.add(PID.class, true, false);
	      this.add(ORL_O22_GENERAL_ORDER.class, true, true);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating ORL_O22_PATIENT - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns PID (Patient identification) - creates it if necessary
	 */
	public PID getPID() { 
	   PID ret = null;
	   try {
	      ret = (PID)this.get("PID");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns  first repetition of ORL_O22_GENERAL_ORDER (a Group object) - creates it if necessary
	 */
	public ORL_O22_GENERAL_ORDER getGENERAL_ORDER() { 
	   ORL_O22_GENERAL_ORDER ret = null;
	   try {
	      ret = (ORL_O22_GENERAL_ORDER)this.get("GENERAL_ORDER");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of ORL_O22_GENERAL_ORDER
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public ORL_O22_GENERAL_ORDER getGENERAL_ORDER(int rep) throws HL7Exception { 
	   return (ORL_O22_GENERAL_ORDER)this.get("GENERAL_ORDER", rep);
	}

	/** 
	 * Returns the number of existing repetitions of ORL_O22_GENERAL_ORDER 
	 */ 
	public int getGENERAL_ORDERReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("GENERAL_ORDER").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

}
