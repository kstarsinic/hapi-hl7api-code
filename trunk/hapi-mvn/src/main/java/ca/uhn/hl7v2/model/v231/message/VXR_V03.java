package ca.uhn.hl7v2.model.v231.message;

import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v231.group.*;

import ca.uhn.hl7v2.model.v231.segment.*;

import ca.uhn.hl7v2.HL7Exception;

import ca.uhn.hl7v2.parser.ModelClassFactory;

import ca.uhn.hl7v2.parser.DefaultModelClassFactory;

import ca.uhn.hl7v2.model.AbstractMessage;

/**
 * <p>Represents a VXR_V03 message structure (see chapter ?). This structure contains the 
 * following elements: </p>
 * 0: MSH (MSH - message header segment) <b></b><br>
 * 1: MSA (MSA - message acknowledgment segment) <b></b><br>
 * 2: QRD (QRD - original-style query definition segment) <b></b><br>
 * 3: QRF (QRF - original style query filter segment) <b>optional </b><br>
 * 4: PID (PID - patient identification segment) <b></b><br>
 * 5: PD1 (PD1 - patient additional demographic segment) <b>optional </b><br>
 * 6: NK1 (NK1 - next of kin / associated parties segment-) <b>optional repeating</b><br>
 * 7: VXR_V03_PV1PV2 (a Group object) <b>optional </b><br>
 * 8: VXR_V03_IN1IN2IN3 (a Group object) <b>optional repeating</b><br>
 * 9: VXR_V03_ORCRXARXROBXNTE (a Group object) <b>optional repeating</b><br>
 */
public class VXR_V03 extends AbstractMessage  {

	/** 
	 * Creates a new VXR_V03 Group with custom ModelClassFactory.
	 */
	public VXR_V03(ModelClassFactory factory) {
	   super(factory);
	   init(factory);
	}

	/**
	 * Creates a new VXR_V03 Group with DefaultModelClassFactory. 
	 */ 
	public VXR_V03() { 
	   super(new DefaultModelClassFactory());
	   init(new DefaultModelClassFactory());
	}

	private void init(ModelClassFactory factory) {
	   try {
	      this.add(MSH.class, true, false);
	      this.add(MSA.class, true, false);
	      this.add(QRD.class, true, false);
	      this.add(QRF.class, false, false);
	      this.add(PID.class, true, false);
	      this.add(PD1.class, false, false);
	      this.add(NK1.class, false, true);
	      this.add(VXR_V03_PV1PV2.class, false, false);
	      this.add(VXR_V03_IN1IN2IN3.class, false, true);
	      this.add(VXR_V03_ORCRXARXROBXNTE.class, false, true);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating VXR_V03 - this is probably a bug in the source code generator.", e);
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
	 * Returns QRD (QRD - original-style query definition segment) - creates it if necessary
	 */
	public QRD getQRD() { 
	   QRD ret = null;
	   try {
	      ret = (QRD)this.get("QRD");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns QRF (QRF - original style query filter segment) - creates it if necessary
	 */
	public QRF getQRF() { 
	   QRF ret = null;
	   try {
	      ret = (QRF)this.get("QRF");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
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
	 * Returns PD1 (PD1 - patient additional demographic segment) - creates it if necessary
	 */
	public PD1 getPD1() { 
	   PD1 ret = null;
	   try {
	      ret = (PD1)this.get("PD1");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns  first repetition of NK1 (NK1 - next of kin / associated parties segment-) - creates it if necessary
	 */
	public NK1 getNK1() { 
	   NK1 ret = null;
	   try {
	      ret = (NK1)this.get("NK1");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of NK1
	 * (NK1 - next of kin / associated parties segment-) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public NK1 getNK1(int rep) throws HL7Exception { 
	   return (NK1)this.get("NK1", rep);
	}

	/** 
	 * Returns the number of existing repetitions of NK1 
	 */ 
	public int getNK1Reps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("NK1").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns VXR_V03_PV1PV2 (a Group object) - creates it if necessary
	 */
	public VXR_V03_PV1PV2 getPV1PV2() { 
	   VXR_V03_PV1PV2 ret = null;
	   try {
	      ret = (VXR_V03_PV1PV2)this.get("PV1PV2");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns  first repetition of VXR_V03_IN1IN2IN3 (a Group object) - creates it if necessary
	 */
	public VXR_V03_IN1IN2IN3 getIN1IN2IN3() { 
	   VXR_V03_IN1IN2IN3 ret = null;
	   try {
	      ret = (VXR_V03_IN1IN2IN3)this.get("IN1IN2IN3");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of VXR_V03_IN1IN2IN3
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public VXR_V03_IN1IN2IN3 getIN1IN2IN3(int rep) throws HL7Exception { 
	   return (VXR_V03_IN1IN2IN3)this.get("IN1IN2IN3", rep);
	}

	/** 
	 * Returns the number of existing repetitions of VXR_V03_IN1IN2IN3 
	 */ 
	public int getIN1IN2IN3Reps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("IN1IN2IN3").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns  first repetition of VXR_V03_ORCRXARXROBXNTE (a Group object) - creates it if necessary
	 */
	public VXR_V03_ORCRXARXROBXNTE getORCRXARXROBXNTE() { 
	   VXR_V03_ORCRXARXROBXNTE ret = null;
	   try {
	      ret = (VXR_V03_ORCRXARXROBXNTE)this.get("ORCRXARXROBXNTE");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of VXR_V03_ORCRXARXROBXNTE
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public VXR_V03_ORCRXARXROBXNTE getORCRXARXROBXNTE(int rep) throws HL7Exception { 
	   return (VXR_V03_ORCRXARXROBXNTE)this.get("ORCRXARXROBXNTE", rep);
	}

	/** 
	 * Returns the number of existing repetitions of VXR_V03_ORCRXARXROBXNTE 
	 */ 
	public int getORCRXARXROBXNTEReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("ORCRXARXROBXNTE").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

}
