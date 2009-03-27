package ca.uhn.hl7v2.model.v231.message;

import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v231.group.*;

import ca.uhn.hl7v2.model.v231.segment.*;

import ca.uhn.hl7v2.HL7Exception;

import ca.uhn.hl7v2.parser.ModelClassFactory;

import ca.uhn.hl7v2.parser.DefaultModelClassFactory;

import ca.uhn.hl7v2.model.AbstractMessage;

/**
 * <p>Represents a SUR_P09 message structure (see chapter ?). This structure contains the 
 * following elements: </p>
 * 0: MSH (MSH - message header segment) <b></b><br>
 * 1: SUR_P09_FACPSHPDCPSHFACPDCNTE (a Group object) <b>repeating</b><br>
 */
public class SUR_P09 extends AbstractMessage  {

	/** 
	 * Creates a new SUR_P09 Group with custom ModelClassFactory.
	 */
	public SUR_P09(ModelClassFactory factory) {
	   super(factory);
	   init(factory);
	}

	/**
	 * Creates a new SUR_P09 Group with DefaultModelClassFactory. 
	 */ 
	public SUR_P09() { 
	   super(new DefaultModelClassFactory());
	   init(new DefaultModelClassFactory());
	}

	private void init(ModelClassFactory factory) {
	   try {
	      this.add(MSH.class, true, false);
	      this.add(SUR_P09_FACPSHPDCPSHFACPDCNTE.class, true, true);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating SUR_P09 - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns MSH (MSH - message header segment) - creates it if necessary
	 */
	public MSH getMSH() { 
	   MSH ret = null;
	   try {
	      ret = (MSH)this.get("MSH");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns  first repetition of SUR_P09_FACPSHPDCPSHFACPDCNTE (a Group object) - creates it if necessary
	 */
	public SUR_P09_FACPSHPDCPSHFACPDCNTE getFACPSHPDCPSHFACPDCNTE() { 
	   SUR_P09_FACPSHPDCPSHFACPDCNTE ret = null;
	   try {
	      ret = (SUR_P09_FACPSHPDCPSHFACPDCNTE)this.get("FACPSHPDCPSHFACPDCNTE");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of SUR_P09_FACPSHPDCPSHFACPDCNTE
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public SUR_P09_FACPSHPDCPSHFACPDCNTE getFACPSHPDCPSHFACPDCNTE(int rep) throws HL7Exception { 
	   return (SUR_P09_FACPSHPDCPSHFACPDCNTE)this.get("FACPSHPDCPSHFACPDCNTE", rep);
	}

	/** 
	 * Returns the number of existing repetitions of SUR_P09_FACPSHPDCPSHFACPDCNTE 
	 */ 
	public int getFACPSHPDCPSHFACPDCNTEReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("FACPSHPDCPSHFACPDCNTE").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

}
