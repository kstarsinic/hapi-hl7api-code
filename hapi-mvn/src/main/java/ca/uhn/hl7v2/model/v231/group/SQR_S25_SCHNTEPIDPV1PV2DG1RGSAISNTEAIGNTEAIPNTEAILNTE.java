package ca.uhn.hl7v2.model.v231.group;

import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v231.segment.*;

import ca.uhn.hl7v2.model.*;
/**
 * <p>Represents the SQR_S25_SCHNTEPIDPV1PV2DG1RGSAISNTEAIGNTEAIPNTEAILNTE Group.  A Group is an ordered collection of message 
 * segments that can repeat together or be optionally in/excluded together.
 * This Group contains the following elements: </p>
 * 0: SCH (SCH - schedule activity information segment) <b></b><br>
 * 1: NTE (NTE - notes and comments segment) <b>optional repeating</b><br>
 * 2: SQR_S25_PIDPV1PV2DG1 (a Group object) <b>optional </b><br>
 * 3: SQR_S25_RGSAISNTEAIGNTEAIPNTEAILNTE (a Group object) <b>repeating</b><br>
 */
public class SQR_S25_SCHNTEPIDPV1PV2DG1RGSAISNTEAIGNTEAIPNTEAILNTE extends AbstractGroup {

	/** 
	 * Creates a new SQR_S25_SCHNTEPIDPV1PV2DG1RGSAISNTEAIGNTEAIPNTEAILNTE Group.
	 */
	public SQR_S25_SCHNTEPIDPV1PV2DG1RGSAISNTEAIGNTEAIPNTEAILNTE(Group parent, ModelClassFactory factory) {
	   super(parent, factory);
	   try {
	      this.add(SCH.class, true, false);
	      this.add(NTE.class, false, true);
	      this.add(SQR_S25_PIDPV1PV2DG1.class, false, false);
	      this.add(SQR_S25_RGSAISNTEAIGNTEAIPNTEAILNTE.class, true, true);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating SQR_S25_SCHNTEPIDPV1PV2DG1RGSAISNTEAIGNTEAIPNTEAILNTE - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns SCH (SCH - schedule activity information segment) - creates it if necessary
	 */
	public SCH getSCH() { 
	   SCH ret = null;
	   try {
	      ret = (SCH)this.get("SCH");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns  first repetition of NTE (NTE - notes and comments segment) - creates it if necessary
	 */
	public NTE getNTE() { 
	   NTE ret = null;
	   try {
	      ret = (NTE)this.get("NTE");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of NTE
	 * (NTE - notes and comments segment) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public NTE getNTE(int rep) throws HL7Exception { 
	   return (NTE)this.get("NTE", rep);
	}

	/** 
	 * Returns the number of existing repetitions of NTE 
	 */ 
	public int getNTEReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("NTE").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns SQR_S25_PIDPV1PV2DG1 (a Group object) - creates it if necessary
	 */
	public SQR_S25_PIDPV1PV2DG1 getPIDPV1PV2DG1() { 
	   SQR_S25_PIDPV1PV2DG1 ret = null;
	   try {
	      ret = (SQR_S25_PIDPV1PV2DG1)this.get("PIDPV1PV2DG1");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns  first repetition of SQR_S25_RGSAISNTEAIGNTEAIPNTEAILNTE (a Group object) - creates it if necessary
	 */
	public SQR_S25_RGSAISNTEAIGNTEAIPNTEAILNTE getRGSAISNTEAIGNTEAIPNTEAILNTE() { 
	   SQR_S25_RGSAISNTEAIGNTEAIPNTEAILNTE ret = null;
	   try {
	      ret = (SQR_S25_RGSAISNTEAIGNTEAIPNTEAILNTE)this.get("RGSAISNTEAIGNTEAIPNTEAILNTE");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of SQR_S25_RGSAISNTEAIGNTEAIPNTEAILNTE
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public SQR_S25_RGSAISNTEAIGNTEAIPNTEAILNTE getRGSAISNTEAIGNTEAIPNTEAILNTE(int rep) throws HL7Exception { 
	   return (SQR_S25_RGSAISNTEAIGNTEAIPNTEAILNTE)this.get("RGSAISNTEAIGNTEAIPNTEAILNTE", rep);
	}

	/** 
	 * Returns the number of existing repetitions of SQR_S25_RGSAISNTEAIGNTEAIPNTEAILNTE 
	 */ 
	public int getRGSAISNTEAIGNTEAIPNTEAILNTEReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("RGSAISNTEAIGNTEAIPNTEAILNTE").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

}
