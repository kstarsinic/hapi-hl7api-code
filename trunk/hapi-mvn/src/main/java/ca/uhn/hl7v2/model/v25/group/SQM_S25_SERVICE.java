package ca.uhn.hl7v2.model.v25.group;

import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v25.segment.*;

import ca.uhn.hl7v2.model.*;
/**
 * <p>Represents the SQM_S25_SERVICE Group.  A Group is an ordered collection of message 
 * segments that can repeat together or be optionally in/excluded together.
 * This Group contains the following elements: </p>
 * 0: AIS (Appointment Information) <b></b><br>
 * 1: APR (Appointment Preferences) <b>optional </b><br>
 */
public class SQM_S25_SERVICE extends AbstractGroup {

	/** 
	 * Creates a new SQM_S25_SERVICE Group.
	 */
	public SQM_S25_SERVICE(Group parent, ModelClassFactory factory) {
	   super(parent, factory);
	   try {
	      this.add(AIS.class, true, false);
	      this.add(APR.class, false, false);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating SQM_S25_SERVICE - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns AIS (Appointment Information) - creates it if necessary
	 */
	public AIS getAIS() { 
	   AIS ret = null;
	   try {
	      ret = (AIS)this.get("AIS");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns APR (Appointment Preferences) - creates it if necessary
	 */
	public APR getAPR() { 
	   APR ret = null;
	   try {
	      ret = (APR)this.get("APR");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}
