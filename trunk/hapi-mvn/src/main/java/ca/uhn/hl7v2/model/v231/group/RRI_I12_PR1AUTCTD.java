package ca.uhn.hl7v2.model.v231.group;

import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v231.segment.*;

import ca.uhn.hl7v2.model.*;
/**
 * <p>Represents the RRI_I12_PR1AUTCTD Group.  A Group is an ordered collection of message 
 * segments that can repeat together or be optionally in/excluded together.
 * This Group contains the following elements: </p>
 * 0: PR1 (PR1 - procedures segment) <b></b><br>
 * 1: RRI_I12_AUTCTD (a Group object) <b>optional </b><br>
 */
public class RRI_I12_PR1AUTCTD extends AbstractGroup {

	/** 
	 * Creates a new RRI_I12_PR1AUTCTD Group.
	 */
	public RRI_I12_PR1AUTCTD(Group parent, ModelClassFactory factory) {
	   super(parent, factory);
	   try {
	      this.add(PR1.class, true, false);
	      this.add(RRI_I12_AUTCTD.class, false, false);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating RRI_I12_PR1AUTCTD - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns PR1 (PR1 - procedures segment) - creates it if necessary
	 */
	public PR1 getPR1() { 
	   PR1 ret = null;
	   try {
	      ret = (PR1)this.get("PR1");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns RRI_I12_AUTCTD (a Group object) - creates it if necessary
	 */
	public RRI_I12_AUTCTD getAUTCTD() { 
	   RRI_I12_AUTCTD ret = null;
	   try {
	      ret = (RRI_I12_AUTCTD)this.get("AUTCTD");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}
