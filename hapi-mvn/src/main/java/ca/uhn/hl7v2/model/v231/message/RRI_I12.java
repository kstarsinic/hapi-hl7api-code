package ca.uhn.hl7v2.model.v231.message;

import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v231.group.*;

import ca.uhn.hl7v2.model.v231.segment.*;

import ca.uhn.hl7v2.HL7Exception;

import ca.uhn.hl7v2.parser.ModelClassFactory;

import ca.uhn.hl7v2.parser.DefaultModelClassFactory;

import ca.uhn.hl7v2.model.AbstractMessage;

/**
 * <p>Represents a RRI_I12 message structure (see chapter null). This structure contains the 
 * following elements: </p>
 * 0: MSH (MSH - message header segment) <b></b><br>
 * 1: MSA (MSA - message acknowledgment segment) <b>optional </b><br>
 * 2: RF1 (Referral Infomation) <b>optional </b><br>
 * 3: RRI_I12_AUTCTD (a Group object) <b>optional </b><br>
 * 4: RRI_I12_PRDCTD (a Group object) <b>repeating</b><br>
 * 5: PID (PID - patient identification segment) <b></b><br>
 * 6: ACC (ACC - accident segment) <b>optional </b><br>
 * 7: DG1 (DG1 - diagnosis segment) <b>optional repeating</b><br>
 * 8: DRG (DRG - diagnosis related group segment) <b>optional repeating</b><br>
 * 9: AL1 (AL1 - patient allergy information segment) <b>optional repeating</b><br>
 * 10: RRI_I12_PR1AUTCTD (a Group object) <b>optional repeating</b><br>
 * 11: RRI_I12_OBRNTEOBXNTE (a Group object) <b>optional repeating</b><br>
 * 12: RRI_I12_PV1PV2 (a Group object) <b>optional </b><br>
 * 13: NTE (NTE - notes and comments segment) <b>optional repeating</b><br>
 */
public class RRI_I12 extends AbstractMessage  {

	/** 
	 * Creates a new RRI_I12 Group with custom ModelClassFactory.
	 */
	public RRI_I12(ModelClassFactory factory) {
	   super(factory);
	   init(factory);
	}

	/**
	 * Creates a new RRI_I12 Group with DefaultModelClassFactory. 
	 */ 
	public RRI_I12() { 
	   super(new DefaultModelClassFactory());
	   init(new DefaultModelClassFactory());
	}

	private void init(ModelClassFactory factory) {
	   try {
	      this.add(MSH.class, true, false);
	      this.add(MSA.class, false, false);
	      this.add(RF1.class, false, false);
	      this.add(RRI_I12_AUTCTD.class, false, false);
	      this.add(RRI_I12_PRDCTD.class, true, true);
	      this.add(PID.class, true, false);
	      this.add(ACC.class, false, false);
	      this.add(DG1.class, false, true);
	      this.add(DRG.class, false, true);
	      this.add(AL1.class, false, true);
	      this.add(RRI_I12_PR1AUTCTD.class, false, true);
	      this.add(RRI_I12_OBRNTEOBXNTE.class, false, true);
	      this.add(RRI_I12_PV1PV2.class, false, false);
	      this.add(NTE.class, false, true);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating RRI_I12 - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns MSH (MSH - message header segment) - creates it if necessary
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
	 * Returns MSA (MSA - message acknowledgment segment) - creates it if necessary
	 */
	public MSA getMSA() { 
	   MSA ret = null;
	   try {
	      ret = (MSA)this.get("MSA");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns RF1 (Referral Infomation) - creates it if necessary
	 */
	public RF1 getRF1() { 
	   RF1 ret = null;
	   try {
	      ret = (RF1)this.get("RF1");
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

	/**
	 * Returns  first repetition of RRI_I12_PRDCTD (a Group object) - creates it if necessary
	 */
	public RRI_I12_PRDCTD getPRDCTD() { 
	   RRI_I12_PRDCTD ret = null;
	   try {
	      ret = (RRI_I12_PRDCTD)this.get("PRDCTD");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of RRI_I12_PRDCTD
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public RRI_I12_PRDCTD getPRDCTD(int rep) throws HL7Exception { 
	   return (RRI_I12_PRDCTD)this.get("PRDCTD", rep);
	}

	/** 
	 * Returns the number of existing repetitions of RRI_I12_PRDCTD 
	 */ 
	public int getPRDCTDReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("PRDCTD").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns PID (PID - patient identification segment) - creates it if necessary
	 */
	public PID getPID() { 
	   PID ret = null;
	   try {
	      ret = (PID)this.get("PID");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns ACC (ACC - accident segment) - creates it if necessary
	 */
	public ACC getACC() { 
	   ACC ret = null;
	   try {
	      ret = (ACC)this.get("ACC");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns  first repetition of DG1 (DG1 - diagnosis segment) - creates it if necessary
	 */
	public DG1 getDG1() { 
	   DG1 ret = null;
	   try {
	      ret = (DG1)this.get("DG1");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of DG1
	 * (DG1 - diagnosis segment) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public DG1 getDG1(int rep) throws HL7Exception { 
	   return (DG1)this.get("DG1", rep);
	}

	/** 
	 * Returns the number of existing repetitions of DG1 
	 */ 
	public int getDG1Reps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("DG1").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns  first repetition of DRG (DRG - diagnosis related group segment) - creates it if necessary
	 */
	public DRG getDRG() { 
	   DRG ret = null;
	   try {
	      ret = (DRG)this.get("DRG");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of DRG
	 * (DRG - diagnosis related group segment) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public DRG getDRG(int rep) throws HL7Exception { 
	   return (DRG)this.get("DRG", rep);
	}

	/** 
	 * Returns the number of existing repetitions of DRG 
	 */ 
	public int getDRGReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("DRG").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns  first repetition of AL1 (AL1 - patient allergy information segment) - creates it if necessary
	 */
	public AL1 getAL1() { 
	   AL1 ret = null;
	   try {
	      ret = (AL1)this.get("AL1");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of AL1
	 * (AL1 - patient allergy information segment) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public AL1 getAL1(int rep) throws HL7Exception { 
	   return (AL1)this.get("AL1", rep);
	}

	/** 
	 * Returns the number of existing repetitions of AL1 
	 */ 
	public int getAL1Reps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("AL1").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns  first repetition of RRI_I12_PR1AUTCTD (a Group object) - creates it if necessary
	 */
	public RRI_I12_PR1AUTCTD getPR1AUTCTD() { 
	   RRI_I12_PR1AUTCTD ret = null;
	   try {
	      ret = (RRI_I12_PR1AUTCTD)this.get("PR1AUTCTD");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of RRI_I12_PR1AUTCTD
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public RRI_I12_PR1AUTCTD getPR1AUTCTD(int rep) throws HL7Exception { 
	   return (RRI_I12_PR1AUTCTD)this.get("PR1AUTCTD", rep);
	}

	/** 
	 * Returns the number of existing repetitions of RRI_I12_PR1AUTCTD 
	 */ 
	public int getPR1AUTCTDReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("PR1AUTCTD").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns  first repetition of RRI_I12_OBRNTEOBXNTE (a Group object) - creates it if necessary
	 */
	public RRI_I12_OBRNTEOBXNTE getOBRNTEOBXNTE() { 
	   RRI_I12_OBRNTEOBXNTE ret = null;
	   try {
	      ret = (RRI_I12_OBRNTEOBXNTE)this.get("OBRNTEOBXNTE");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of RRI_I12_OBRNTEOBXNTE
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public RRI_I12_OBRNTEOBXNTE getOBRNTEOBXNTE(int rep) throws HL7Exception { 
	   return (RRI_I12_OBRNTEOBXNTE)this.get("OBRNTEOBXNTE", rep);
	}

	/** 
	 * Returns the number of existing repetitions of RRI_I12_OBRNTEOBXNTE 
	 */ 
	public int getOBRNTEOBXNTEReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("OBRNTEOBXNTE").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns RRI_I12_PV1PV2 (a Group object) - creates it if necessary
	 */
	public RRI_I12_PV1PV2 getPV1PV2() { 
	   RRI_I12_PV1PV2 ret = null;
	   try {
	      ret = (RRI_I12_PV1PV2)this.get("PV1PV2");
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

}
