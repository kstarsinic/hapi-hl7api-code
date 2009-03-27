package ca.uhn.hl7v2.model.v231.group;

import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v231.segment.*;

import ca.uhn.hl7v2.model.*;
/**
 * <p>Represents the MFN_M08_MFEOM1OM2OM3OM4 Group.  A Group is an ordered collection of message 
 * segments that can repeat together or be optionally in/excluded together.
 * This Group contains the following elements: </p>
 * 0: MFE (MFE - master file entry segment) <b></b><br>
 * 1: OM1 (OM1 - general segment (fields that apply to most observations)) <b>optional </b><br>
 * 2: MFN_M08_OM2OM3OM4 (a Group object) <b>optional </b><br>
 */
public class MFN_M08_MFEOM1OM2OM3OM4 extends AbstractGroup {

	/** 
	 * Creates a new MFN_M08_MFEOM1OM2OM3OM4 Group.
	 */
	public MFN_M08_MFEOM1OM2OM3OM4(Group parent, ModelClassFactory factory) {
	   super(parent, factory);
	   try {
	      this.add(MFE.class, true, false);
	      this.add(OM1.class, false, false);
	      this.add(MFN_M08_OM2OM3OM4.class, false, false);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating MFN_M08_MFEOM1OM2OM3OM4 - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns MFE (MFE - master file entry segment) - creates it if necessary
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
	 * Returns OM1 (OM1 - general segment (fields that apply to most observations)) - creates it if necessary
	 */
	public OM1 getOM1() { 
	   OM1 ret = null;
	   try {
	      ret = (OM1)this.get("OM1");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns MFN_M08_OM2OM3OM4 (a Group object) - creates it if necessary
	 */
	public MFN_M08_OM2OM3OM4 getOM2OM3OM4() { 
	   MFN_M08_OM2OM3OM4 ret = null;
	   try {
	      ret = (MFN_M08_OM2OM3OM4)this.get("OM2OM3OM4");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}
