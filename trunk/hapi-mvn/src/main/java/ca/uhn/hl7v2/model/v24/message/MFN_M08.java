package ca.uhn.hl7v2.model.v24.message;

import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v24.group.*;

import ca.uhn.hl7v2.model.v24.segment.*;

import ca.uhn.hl7v2.HL7Exception;

import ca.uhn.hl7v2.parser.ModelClassFactory;

import ca.uhn.hl7v2.parser.DefaultModelClassFactory;

import ca.uhn.hl7v2.model.AbstractMessage;

/**
 * <p>Represents a MFN_M08 message structure (see chapter 8). This structure contains the 
 * following elements: </p>
 * 0: MSH (Message Header) <b></b><br>
 * 1: MFI (Master File Identification) <b></b><br>
 * 2: MFN_M08_MF_TEST_NUMERIC (a Group object) <b>repeating</b><br>
 */
public class MFN_M08 extends AbstractMessage  {

	/** 
	 * Creates a new MFN_M08 Group with custom ModelClassFactory.
	 */
	public MFN_M08(ModelClassFactory factory) {
	   super(factory);
	   init(factory);
	}

	/**
	 * Creates a new MFN_M08 Group with DefaultModelClassFactory. 
	 */ 
	public MFN_M08() { 
	   super(new DefaultModelClassFactory());
	   init(new DefaultModelClassFactory());
	}

	private void init(ModelClassFactory factory) {
	   try {
	      this.add(MSH.class, true, false);
	      this.add(MFI.class, true, false);
	      this.add(MFN_M08_MF_TEST_NUMERIC.class, true, true);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating MFN_M08 - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns MSH (Message Header) - creates it if necessary
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
	 * Returns MFI (Master File Identification) - creates it if necessary
	 */
	public MFI getMFI() { 
	   MFI ret = null;
	   try {
	      ret = (MFI)this.get("MFI");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns  first repetition of MFN_M08_MF_TEST_NUMERIC (a Group object) - creates it if necessary
	 */
	public MFN_M08_MF_TEST_NUMERIC getMF_TEST_NUMERIC() { 
	   MFN_M08_MF_TEST_NUMERIC ret = null;
	   try {
	      ret = (MFN_M08_MF_TEST_NUMERIC)this.get("MF_TEST_NUMERIC");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of MFN_M08_MF_TEST_NUMERIC
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public MFN_M08_MF_TEST_NUMERIC getMF_TEST_NUMERIC(int rep) throws HL7Exception { 
	   return (MFN_M08_MF_TEST_NUMERIC)this.get("MF_TEST_NUMERIC", rep);
	}

	/** 
	 * Returns the number of existing repetitions of MFN_M08_MF_TEST_NUMERIC 
	 */ 
	public int getMF_TEST_NUMERICReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("MF_TEST_NUMERIC").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

}
