package ca.uhn.hl7v2.model.v24.group;

import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v24.segment.*;

import ca.uhn.hl7v2.model.*;
/**
 * <p>Represents the MFN_M02_MF_STAFF Group.  A Group is an ordered collection of message 
 * segments that can repeat together or be optionally in/excluded together.
 * This Group contains the following elements: </p>
 * 0: MFE (Master File Entry) <b></b><br>
 * 1: STF (Staff Identification) <b></b><br>
 * 2: PRA (Practitioner Detail) <b>optional </b><br>
 * 3: ORG (Practitioner Organization Unit) <b>optional </b><br>
 */
public class MFN_M02_MF_STAFF extends AbstractGroup {

	/** 
	 * Creates a new MFN_M02_MF_STAFF Group.
	 */
	public MFN_M02_MF_STAFF(Group parent, ModelClassFactory factory) {
	   super(parent, factory);
	   try {
	      this.add(MFE.class, true, false);
	      this.add(STF.class, true, false);
	      this.add(PRA.class, false, false);
	      this.add(ORG.class, false, false);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating MFN_M02_MF_STAFF - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns MFE (Master File Entry) - creates it if necessary
	 */
	public MFE getMFE() { 
	   MFE ret = null;
	   try {
	      ret = (MFE)this.get("MFE");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns STF (Staff Identification) - creates it if necessary
	 */
	public STF getSTF() { 
	   STF ret = null;
	   try {
	      ret = (STF)this.get("STF");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns PRA (Practitioner Detail) - creates it if necessary
	 */
	public PRA getPRA() { 
	   PRA ret = null;
	   try {
	      ret = (PRA)this.get("PRA");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns ORG (Practitioner Organization Unit) - creates it if necessary
	 */
	public ORG getORG() { 
	   ORG ret = null;
	   try {
	      ret = (ORG)this.get("ORG");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}
