package ca.uhn.hl7v2.model.v25.message;

import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v25.group.*;

import ca.uhn.hl7v2.model.v25.segment.*;

import ca.uhn.hl7v2.HL7Exception;

import ca.uhn.hl7v2.parser.ModelClassFactory;

import ca.uhn.hl7v2.parser.DefaultModelClassFactory;

import ca.uhn.hl7v2.model.AbstractMessage;

/**
 * <p>Represents a OUL_R23 message structure (see chapter 7.3.8). This structure contains the 
 * following elements: </p>
 * 0: MSH (Message Header) <b></b><br>
 * 1: SFT (Software Segment) <b>optional repeating</b><br>
 * 2: NTE (Notes and Comments) <b>optional </b><br>
 * 3: OUL_R23_PATIENT (a Group object) <b>optional </b><br>
 * 4: OUL_R23_VISIT (a Group object) <b>optional </b><br>
 * 5: OUL_R23_SPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI (a Group object) <b>repeating</b><br>
 * 6: DSC (Continuation Pointer) <b>optional </b><br>
 */
public class OUL_R23 extends AbstractMessage  {

	/** 
	 * Creates a new OUL_R23 Group with custom ModelClassFactory.
	 */
	public OUL_R23(ModelClassFactory factory) {
	   super(factory);
	   init(factory);
	}

	/**
	 * Creates a new OUL_R23 Group with DefaultModelClassFactory. 
	 */ 
	public OUL_R23() { 
	   super(new DefaultModelClassFactory());
	   init(new DefaultModelClassFactory());
	}

	private void init(ModelClassFactory factory) {
	   try {
	      this.add(MSH.class, true, false);
	      this.add(SFT.class, false, true);
	      this.add(NTE.class, false, false);
	      this.add(OUL_R23_PATIENT.class, false, false);
	      this.add(OUL_R23_VISIT.class, false, false);
	      this.add(OUL_R23_SPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI.class, true, true);
	      this.add(DSC.class, false, false);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating OUL_R23 - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns MSH (Message Header) - creates it if necessary
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
	 * Returns  first repetition of SFT (Software Segment) - creates it if necessary
	 */
	public SFT getSFT() { 
	   SFT ret = null;
	   try {
	      ret = (SFT)this.get("SFT");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of SFT
	 * (Software Segment) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public SFT getSFT(int rep) throws HL7Exception { 
	   return (SFT)this.get("SFT", rep);
	}

	/** 
	 * Returns the number of existing repetitions of SFT 
	 */ 
	public int getSFTReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("SFT").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns NTE (Notes and Comments) - creates it if necessary
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
	 * Returns OUL_R23_PATIENT (a Group object) - creates it if necessary
	 */
	public OUL_R23_PATIENT getPATIENT() { 
	   OUL_R23_PATIENT ret = null;
	   try {
	      ret = (OUL_R23_PATIENT)this.get("PATIENT");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns OUL_R23_VISIT (a Group object) - creates it if necessary
	 */
	public OUL_R23_VISIT getVISIT() { 
	   OUL_R23_VISIT ret = null;
	   try {
	      ret = (OUL_R23_VISIT)this.get("VISIT");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns  first repetition of OUL_R23_SPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI (a Group object) - creates it if necessary
	 */
	public OUL_R23_SPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI getSPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI() { 
	   OUL_R23_SPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI ret = null;
	   try {
	      ret = (OUL_R23_SPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI)this.get("SPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of OUL_R23_SPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public OUL_R23_SPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI getSPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI(int rep) throws HL7Exception { 
	   return (OUL_R23_SPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI)this.get("SPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI", rep);
	}

	/** 
	 * Returns the number of existing repetitions of OUL_R23_SPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI 
	 */ 
	public int getSPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTIReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("SPMOBXSACINVOBRORCNTETQ1TQ2OBXTCDSIDNTECTI").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns DSC (Continuation Pointer) - creates it if necessary
	 */
	public DSC getDSC() { 
	   DSC ret = null;
	   try {
	      ret = (DSC)this.get("DSC");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}
