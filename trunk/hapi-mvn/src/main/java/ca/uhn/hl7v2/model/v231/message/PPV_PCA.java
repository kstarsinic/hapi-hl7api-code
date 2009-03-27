package ca.uhn.hl7v2.model.v231.message;

import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v231.group.*;

import ca.uhn.hl7v2.model.v231.segment.*;

import ca.uhn.hl7v2.HL7Exception;

import ca.uhn.hl7v2.parser.ModelClassFactory;

import ca.uhn.hl7v2.parser.DefaultModelClassFactory;

import ca.uhn.hl7v2.model.AbstractMessage;

/**
 * <p>Represents a PPV_PCA message structure (see chapter ?). This structure contains the 
 * following elements: </p>
 * 0: MSH (MSH - message header segment) <b></b><br>
 * 1: MSA (MSA - message acknowledgment segment) <b></b><br>
 * 2: ERR (ERR - error segment) <b>optional </b><br>
 * 3: QAK (Query Acknowledgement) <b>optional </b><br>
 * 4: QRD (QRD - original-style query definition segment) <b></b><br>
 * 5: PPV_PCA_PIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR (a Group object) <b>repeating</b><br>
 */
public class PPV_PCA extends AbstractMessage  {

	/** 
	 * Creates a new PPV_PCA Group with custom ModelClassFactory.
	 */
	public PPV_PCA(ModelClassFactory factory) {
	   super(factory);
	   init(factory);
	}

	/**
	 * Creates a new PPV_PCA Group with DefaultModelClassFactory. 
	 */ 
	public PPV_PCA() { 
	   super(new DefaultModelClassFactory());
	   init(new DefaultModelClassFactory());
	}

	private void init(ModelClassFactory factory) {
	   try {
	      this.add(MSH.class, true, false);
	      this.add(MSA.class, true, false);
	      this.add(ERR.class, false, false);
	      this.add(QAK.class, false, false);
	      this.add(QRD.class, true, false);
	      this.add(PPV_PCA_PIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR.class, true, true);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating PPV_PCA - this is probably a bug in the source code generator.", e);
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
	 * Returns ERR (ERR - error segment) - creates it if necessary
	 */
	public ERR getERR() { 
	   ERR ret = null;
	   try {
	      ret = (ERR)this.get("ERR");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns QAK (Query Acknowledgement) - creates it if necessary
	 */
	public QAK getQAK() { 
	   QAK ret = null;
	   try {
	      ret = (QAK)this.get("QAK");
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
	 * Returns  first repetition of PPV_PCA_PIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR (a Group object) - creates it if necessary
	 */
	public PPV_PCA_PIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR getPIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR() { 
	   PPV_PCA_PIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR ret = null;
	   try {
	      ret = (PPV_PCA_PIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR)this.get("PIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of PPV_PCA_PIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public PPV_PCA_PIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR getPIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR(int rep) throws HL7Exception { 
	   return (PPV_PCA_PIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR)this.get("PIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR", rep);
	}

	/** 
	 * Returns the number of existing repetitions of PPV_PCA_PIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR 
	 */ 
	public int getPIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVARReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("PIDPV1PV2GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

}
