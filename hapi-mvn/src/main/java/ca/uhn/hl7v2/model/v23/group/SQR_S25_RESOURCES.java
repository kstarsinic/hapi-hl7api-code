package ca.uhn.hl7v2.model.v23.group;

import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v23.segment.*;

import ca.uhn.hl7v2.model.*;
/**
 * <p>Represents the SQR_S25_RESOURCES Group.  A Group is an ordered collection of message 
 * segments that can repeat together or be optionally in/excluded together.
 * This Group contains the following elements: </p>
 * 0: RGS (Resource Group) <b></b><br>
 * 1: SQR_S25_SERVICE (a Group object) <b>optional repeating</b><br>
 * 2: SQR_S25_GENERAL_RESOURCE (a Group object) <b>optional repeating</b><br>
 * 3: SQR_S25_PERSONNEL_RESOURCE (a Group object) <b>optional repeating</b><br>
 * 4: SQR_S25_LOCATION_RESOURCE (a Group object) <b>optional repeating</b><br>
 */
public class SQR_S25_RESOURCES extends AbstractGroup {

	/** 
	 * Creates a new SQR_S25_RESOURCES Group.
	 */
	public SQR_S25_RESOURCES(Group parent, ModelClassFactory factory) {
	   super(parent, factory);
	   try {
	      this.add(RGS.class, true, false);
	      this.add(SQR_S25_SERVICE.class, false, true);
	      this.add(SQR_S25_GENERAL_RESOURCE.class, false, true);
	      this.add(SQR_S25_PERSONNEL_RESOURCE.class, false, true);
	      this.add(SQR_S25_LOCATION_RESOURCE.class, false, true);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating SQR_S25_RESOURCES - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns RGS (Resource Group) - creates it if necessary
	 */
	public RGS getRGS() { 
	   RGS ret = null;
	   try {
	      ret = (RGS)this.get("RGS");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns  first repetition of SQR_S25_SERVICE (a Group object) - creates it if necessary
	 */
	public SQR_S25_SERVICE getSERVICE() { 
	   SQR_S25_SERVICE ret = null;
	   try {
	      ret = (SQR_S25_SERVICE)this.get("SERVICE");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of SQR_S25_SERVICE
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public SQR_S25_SERVICE getSERVICE(int rep) throws HL7Exception { 
	   return (SQR_S25_SERVICE)this.get("SERVICE", rep);
	}

	/** 
	 * Returns the number of existing repetitions of SQR_S25_SERVICE 
	 */ 
	public int getSERVICEReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("SERVICE").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns  first repetition of SQR_S25_GENERAL_RESOURCE (a Group object) - creates it if necessary
	 */
	public SQR_S25_GENERAL_RESOURCE getGENERAL_RESOURCE() { 
	   SQR_S25_GENERAL_RESOURCE ret = null;
	   try {
	      ret = (SQR_S25_GENERAL_RESOURCE)this.get("GENERAL_RESOURCE");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of SQR_S25_GENERAL_RESOURCE
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public SQR_S25_GENERAL_RESOURCE getGENERAL_RESOURCE(int rep) throws HL7Exception { 
	   return (SQR_S25_GENERAL_RESOURCE)this.get("GENERAL_RESOURCE", rep);
	}

	/** 
	 * Returns the number of existing repetitions of SQR_S25_GENERAL_RESOURCE 
	 */ 
	public int getGENERAL_RESOURCEReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("GENERAL_RESOURCE").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns  first repetition of SQR_S25_PERSONNEL_RESOURCE (a Group object) - creates it if necessary
	 */
	public SQR_S25_PERSONNEL_RESOURCE getPERSONNEL_RESOURCE() { 
	   SQR_S25_PERSONNEL_RESOURCE ret = null;
	   try {
	      ret = (SQR_S25_PERSONNEL_RESOURCE)this.get("PERSONNEL_RESOURCE");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of SQR_S25_PERSONNEL_RESOURCE
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public SQR_S25_PERSONNEL_RESOURCE getPERSONNEL_RESOURCE(int rep) throws HL7Exception { 
	   return (SQR_S25_PERSONNEL_RESOURCE)this.get("PERSONNEL_RESOURCE", rep);
	}

	/** 
	 * Returns the number of existing repetitions of SQR_S25_PERSONNEL_RESOURCE 
	 */ 
	public int getPERSONNEL_RESOURCEReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("PERSONNEL_RESOURCE").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns  first repetition of SQR_S25_LOCATION_RESOURCE (a Group object) - creates it if necessary
	 */
	public SQR_S25_LOCATION_RESOURCE getLOCATION_RESOURCE() { 
	   SQR_S25_LOCATION_RESOURCE ret = null;
	   try {
	      ret = (SQR_S25_LOCATION_RESOURCE)this.get("LOCATION_RESOURCE");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of SQR_S25_LOCATION_RESOURCE
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public SQR_S25_LOCATION_RESOURCE getLOCATION_RESOURCE(int rep) throws HL7Exception { 
	   return (SQR_S25_LOCATION_RESOURCE)this.get("LOCATION_RESOURCE", rep);
	}

	/** 
	 * Returns the number of existing repetitions of SQR_S25_LOCATION_RESOURCE 
	 */ 
	public int getLOCATION_RESOURCEReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("LOCATION_RESOURCE").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

}
