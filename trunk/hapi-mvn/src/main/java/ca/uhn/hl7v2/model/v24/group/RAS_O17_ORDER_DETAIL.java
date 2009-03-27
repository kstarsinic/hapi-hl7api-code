package ca.uhn.hl7v2.model.v24.group;

import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v24.segment.*;

import ca.uhn.hl7v2.model.*;
/**
 * <p>Represents the RAS_O17_ORDER_DETAIL Group.  A Group is an ordered collection of message 
 * segments that can repeat together or be optionally in/excluded together.
 * This Group contains the following elements: </p>
 * 0: RXO (Pharmacy/Treatment Order) <b></b><br>
 * 1: RAS_O17_ORDER_DETAIL_SUPPLEMENT (a Group object) <b>optional </b><br>
 */
public class RAS_O17_ORDER_DETAIL extends AbstractGroup {

	/** 
	 * Creates a new RAS_O17_ORDER_DETAIL Group.
	 */
	public RAS_O17_ORDER_DETAIL(Group parent, ModelClassFactory factory) {
	   super(parent, factory);
	   try {
	      this.add(RXO.class, true, false);
	      this.add(RAS_O17_ORDER_DETAIL_SUPPLEMENT.class, false, false);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating RAS_O17_ORDER_DETAIL - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns RXO (Pharmacy/Treatment Order) - creates it if necessary
	 */
	public RXO getRXO() { 
	   RXO ret = null;
	   try {
	      ret = (RXO)this.get("RXO");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns RAS_O17_ORDER_DETAIL_SUPPLEMENT (a Group object) - creates it if necessary
	 */
	public RAS_O17_ORDER_DETAIL_SUPPLEMENT getORDER_DETAIL_SUPPLEMENT() { 
	   RAS_O17_ORDER_DETAIL_SUPPLEMENT ret = null;
	   try {
	      ret = (RAS_O17_ORDER_DETAIL_SUPPLEMENT)this.get("ORDER_DETAIL_SUPPLEMENT");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}
