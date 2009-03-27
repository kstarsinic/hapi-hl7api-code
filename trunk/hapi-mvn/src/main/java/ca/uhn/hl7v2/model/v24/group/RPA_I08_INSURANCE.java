package ca.uhn.hl7v2.model.v24.group;

import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v24.segment.*;

import ca.uhn.hl7v2.model.*;
/**
 * <p>Represents the RPA_I08_INSURANCE Group.  A Group is an ordered collection of message 
 * segments that can repeat together or be optionally in/excluded together.
 * This Group contains the following elements: </p>
 * 0: IN1 (Insurance) <b></b><br>
 * 1: IN2 (Insurance Additional Information) <b>optional </b><br>
 * 2: IN3 (Insurance Additional Information, Certification) <b>optional </b><br>
 */
public class RPA_I08_INSURANCE extends AbstractGroup {

	/** 
	 * Creates a new RPA_I08_INSURANCE Group.
	 */
	public RPA_I08_INSURANCE(Group parent, ModelClassFactory factory) {
	   super(parent, factory);
	   try {
	      this.add(IN1.class, true, false);
	      this.add(IN2.class, false, false);
	      this.add(IN3.class, false, false);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating RPA_I08_INSURANCE - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns IN1 (Insurance) - creates it if necessary
	 */
	public IN1 getIN1() { 
	   IN1 ret = null;
	   try {
	      ret = (IN1)this.get("IN1");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns IN2 (Insurance Additional Information) - creates it if necessary
	 */
	public IN2 getIN2() { 
	   IN2 ret = null;
	   try {
	      ret = (IN2)this.get("IN2");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns IN3 (Insurance Additional Information, Certification) - creates it if necessary
	 */
	public IN3 getIN3() { 
	   IN3 ret = null;
	   try {
	      ret = (IN3)this.get("IN3");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}
