package ca.uhn.hl7v2.model.v231.message;

import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v231.group.*;

import ca.uhn.hl7v2.model.v231.segment.*;

import ca.uhn.hl7v2.HL7Exception;

import ca.uhn.hl7v2.parser.ModelClassFactory;

import ca.uhn.hl7v2.parser.DefaultModelClassFactory;

import ca.uhn.hl7v2.model.AbstractMessage;

/**
 * <p>Represents a MFN_M05 message structure (see chapter ?). This structure contains the 
 * following elements: </p>
 * 0: MSH (MSH - message header segment) <b></b><br>
 * 1: MFI (MFI - master file identification segment) <b></b><br>
 * 2: MFN_M05_MFELOCLCHLRLLDPLCHLCC (a Group object) <b>repeating</b><br>
 */
public class MFN_M05 extends AbstractMessage  {

	/** 
	 * Creates a new MFN_M05 Group with custom ModelClassFactory.
	 */
	public MFN_M05(ModelClassFactory factory) {
	   super(factory);
	   init(factory);
	}

	/**
	 * Creates a new MFN_M05 Group with DefaultModelClassFactory. 
	 */ 
	public MFN_M05() { 
	   super(new DefaultModelClassFactory());
	   init(new DefaultModelClassFactory());
	}

	private void init(ModelClassFactory factory) {
	   try {
	      this.add(MSH.class, true, false);
	      this.add(MFI.class, true, false);
	      this.add(MFN_M05_MFELOCLCHLRLLDPLCHLCC.class, true, true);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating MFN_M05 - this is probably a bug in the source code generator.", e);
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
	 * Returns MFI (MFI - master file identification segment) - creates it if necessary
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
	 * Returns  first repetition of MFN_M05_MFELOCLCHLRLLDPLCHLCC (a Group object) - creates it if necessary
	 */
	public MFN_M05_MFELOCLCHLRLLDPLCHLCC getMFELOCLCHLRLLDPLCHLCC() { 
	   MFN_M05_MFELOCLCHLRLLDPLCHLCC ret = null;
	   try {
	      ret = (MFN_M05_MFELOCLCHLRLLDPLCHLCC)this.get("MFELOCLCHLRLLDPLCHLCC");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of MFN_M05_MFELOCLCHLRLLDPLCHLCC
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public MFN_M05_MFELOCLCHLRLLDPLCHLCC getMFELOCLCHLRLLDPLCHLCC(int rep) throws HL7Exception { 
	   return (MFN_M05_MFELOCLCHLRLLDPLCHLCC)this.get("MFELOCLCHLRLLDPLCHLCC", rep);
	}

	/** 
	 * Returns the number of existing repetitions of MFN_M05_MFELOCLCHLRLLDPLCHLCC 
	 */ 
	public int getMFELOCLCHLRLLDPLCHLCCReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("MFELOCLCHLRLLDPLCHLCC").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

}
