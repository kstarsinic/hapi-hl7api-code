package ca.uhn.hl7v2.model.v25.group;

import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v25.segment.*;

import ca.uhn.hl7v2.model.*;
/**
 * <p>Represents the DFT_P11_COMMON_ORDER Group.  A Group is an ordered collection of message 
 * segments that can repeat together or be optionally in/excluded together.
 * This Group contains the following elements: </p>
 * 0: ORC (Common Order) <b>optional </b><br>
 * 1: DFT_P11_TIMING_QUANTITY (a Group object) <b>optional repeating</b><br>
 * 2: DFT_P11_ORDER (a Group object) <b>optional </b><br>
 * 3: DFT_P11_OBSERVATION (a Group object) <b>optional repeating</b><br>
 */
public class DFT_P11_COMMON_ORDER extends AbstractGroup {

	/** 
	 * Creates a new DFT_P11_COMMON_ORDER Group.
	 */
	public DFT_P11_COMMON_ORDER(Group parent, ModelClassFactory factory) {
	   super(parent, factory);
	   try {
	      this.add(ORC.class, false, false);
	      this.add(DFT_P11_TIMING_QUANTITY.class, false, true);
	      this.add(DFT_P11_ORDER.class, false, false);
	      this.add(DFT_P11_OBSERVATION.class, false, true);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating DFT_P11_COMMON_ORDER - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns ORC (Common Order) - creates it if necessary
	 */
	public ORC getORC() { 
	   ORC ret = null;
	   try {
	      ret = (ORC)this.get("ORC");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns  first repetition of DFT_P11_TIMING_QUANTITY (a Group object) - creates it if necessary
	 */
	public DFT_P11_TIMING_QUANTITY getTIMING_QUANTITY() { 
	   DFT_P11_TIMING_QUANTITY ret = null;
	   try {
	      ret = (DFT_P11_TIMING_QUANTITY)this.get("TIMING_QUANTITY");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of DFT_P11_TIMING_QUANTITY
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public DFT_P11_TIMING_QUANTITY getTIMING_QUANTITY(int rep) throws HL7Exception { 
	   return (DFT_P11_TIMING_QUANTITY)this.get("TIMING_QUANTITY", rep);
	}

	/** 
	 * Returns the number of existing repetitions of DFT_P11_TIMING_QUANTITY 
	 */ 
	public int getTIMING_QUANTITYReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("TIMING_QUANTITY").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns DFT_P11_ORDER (a Group object) - creates it if necessary
	 */
	public DFT_P11_ORDER getORDER() { 
	   DFT_P11_ORDER ret = null;
	   try {
	      ret = (DFT_P11_ORDER)this.get("ORDER");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns  first repetition of DFT_P11_OBSERVATION (a Group object) - creates it if necessary
	 */
	public DFT_P11_OBSERVATION getOBSERVATION() { 
	   DFT_P11_OBSERVATION ret = null;
	   try {
	      ret = (DFT_P11_OBSERVATION)this.get("OBSERVATION");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of DFT_P11_OBSERVATION
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public DFT_P11_OBSERVATION getOBSERVATION(int rep) throws HL7Exception { 
	   return (DFT_P11_OBSERVATION)this.get("OBSERVATION", rep);
	}

	/** 
	 * Returns the number of existing repetitions of DFT_P11_OBSERVATION 
	 */ 
	public int getOBSERVATIONReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("OBSERVATION").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

}
