package ca.uhn.hl7v2.model.v24.group;

import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v24.segment.*;

import ca.uhn.hl7v2.model.*;
/**
 * <p>Represents the PEX_P07_ASSOCIATED_RX_ADMIN Group.  A Group is an ordered collection of message 
 * segments that can repeat together or be optionally in/excluded together.
 * This Group contains the following elements: </p>
 * 0: RXA (Pharmacy/Treatment Administration) <b></b><br>
 * 1: RXR (Pharmacy/Treatment Route) <b>optional </b><br>
 */
public class PEX_P07_ASSOCIATED_RX_ADMIN extends AbstractGroup {

	/** 
	 * Creates a new PEX_P07_ASSOCIATED_RX_ADMIN Group.
	 */
	public PEX_P07_ASSOCIATED_RX_ADMIN(Group parent, ModelClassFactory factory) {
	   super(parent, factory);
	   try {
	      this.add(RXA.class, true, false);
	      this.add(RXR.class, false, false);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating PEX_P07_ASSOCIATED_RX_ADMIN - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns RXA (Pharmacy/Treatment Administration) - creates it if necessary
	 */
	public RXA getRXA() { 
	   RXA ret = null;
	   try {
	      ret = (RXA)this.get("RXA");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns RXR (Pharmacy/Treatment Route) - creates it if necessary
	 */
	public RXR getRXR() { 
	   RXR ret = null;
	   try {
	      ret = (RXR)this.get("RXR");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}
