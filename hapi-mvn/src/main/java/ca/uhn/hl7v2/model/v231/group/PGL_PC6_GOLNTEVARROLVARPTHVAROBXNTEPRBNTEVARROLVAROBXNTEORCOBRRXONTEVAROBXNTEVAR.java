package ca.uhn.hl7v2.model.v231.group;

import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v231.segment.*;

import ca.uhn.hl7v2.model.*;
/**
 * <p>Represents the PGL_PC6_GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR Group.  A Group is an ordered collection of message 
 * segments that can repeat together or be optionally in/excluded together.
 * This Group contains the following elements: </p>
 * 0: GOL (Goal Detail) <b></b><br>
 * 1: NTE (NTE - notes and comments segment) <b>optional repeating</b><br>
 * 2: VAR (Variance) <b>optional repeating</b><br>
 * 3: PGL_PC6_ROLVAR (a Group object) <b>optional repeating</b><br>
 * 4: PGL_PC6_PTHVAR (a Group object) <b>optional repeating</b><br>
 * 5: PGL_PC6_OBXNTE (a Group object) <b>optional repeating</b><br>
 * 6: PGL_PC6_PRBNTEVARROLVAROBXNTE (a Group object) <b>optional repeating</b><br>
 * 7: PGL_PC6_ORCOBRRXONTEVAROBXNTEVAR (a Group object) <b>optional repeating</b><br>
 */
public class PGL_PC6_GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR extends AbstractGroup {

	/** 
	 * Creates a new PGL_PC6_GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR Group.
	 */
	public PGL_PC6_GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR(Group parent, ModelClassFactory factory) {
	   super(parent, factory);
	   try {
	      this.add(GOL.class, true, false);
	      this.add(NTE.class, false, true);
	      this.add(VAR.class, false, true);
	      this.add(PGL_PC6_ROLVAR.class, false, true);
	      this.add(PGL_PC6_PTHVAR.class, false, true);
	      this.add(PGL_PC6_OBXNTE.class, false, true);
	      this.add(PGL_PC6_PRBNTEVARROLVAROBXNTE.class, false, true);
	      this.add(PGL_PC6_ORCOBRRXONTEVAROBXNTEVAR.class, false, true);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating PGL_PC6_GOLNTEVARROLVARPTHVAROBXNTEPRBNTEVARROLVAROBXNTEORCOBRRXONTEVAROBXNTEVAR - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns GOL (Goal Detail) - creates it if necessary
	 */
	public GOL getGOL() { 
	   GOL ret = null;
	   try {
	      ret = (GOL)this.get("GOL");
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
	 * Returns  first repetition of VAR (Variance) - creates it if necessary
	 */
	public VAR getVAR() { 
	   VAR ret = null;
	   try {
	      ret = (VAR)this.get("VAR");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of VAR
	 * (Variance) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public VAR getVAR(int rep) throws HL7Exception { 
	   return (VAR)this.get("VAR", rep);
	}

	/** 
	 * Returns the number of existing repetitions of VAR 
	 */ 
	public int getVARReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("VAR").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns  first repetition of PGL_PC6_ROLVAR (a Group object) - creates it if necessary
	 */
	public PGL_PC6_ROLVAR getROLVAR() { 
	   PGL_PC6_ROLVAR ret = null;
	   try {
	      ret = (PGL_PC6_ROLVAR)this.get("ROLVAR");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of PGL_PC6_ROLVAR
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public PGL_PC6_ROLVAR getROLVAR(int rep) throws HL7Exception { 
	   return (PGL_PC6_ROLVAR)this.get("ROLVAR", rep);
	}

	/** 
	 * Returns the number of existing repetitions of PGL_PC6_ROLVAR 
	 */ 
	public int getROLVARReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("ROLVAR").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns  first repetition of PGL_PC6_PTHVAR (a Group object) - creates it if necessary
	 */
	public PGL_PC6_PTHVAR getPTHVAR() { 
	   PGL_PC6_PTHVAR ret = null;
	   try {
	      ret = (PGL_PC6_PTHVAR)this.get("PTHVAR");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of PGL_PC6_PTHVAR
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public PGL_PC6_PTHVAR getPTHVAR(int rep) throws HL7Exception { 
	   return (PGL_PC6_PTHVAR)this.get("PTHVAR", rep);
	}

	/** 
	 * Returns the number of existing repetitions of PGL_PC6_PTHVAR 
	 */ 
	public int getPTHVARReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("PTHVAR").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns  first repetition of PGL_PC6_OBXNTE (a Group object) - creates it if necessary
	 */
	public PGL_PC6_OBXNTE getOBXNTE() { 
	   PGL_PC6_OBXNTE ret = null;
	   try {
	      ret = (PGL_PC6_OBXNTE)this.get("OBXNTE");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of PGL_PC6_OBXNTE
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public PGL_PC6_OBXNTE getOBXNTE(int rep) throws HL7Exception { 
	   return (PGL_PC6_OBXNTE)this.get("OBXNTE", rep);
	}

	/** 
	 * Returns the number of existing repetitions of PGL_PC6_OBXNTE 
	 */ 
	public int getOBXNTEReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("OBXNTE").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns  first repetition of PGL_PC6_PRBNTEVARROLVAROBXNTE (a Group object) - creates it if necessary
	 */
	public PGL_PC6_PRBNTEVARROLVAROBXNTE getPRBNTEVARROLVAROBXNTE() { 
	   PGL_PC6_PRBNTEVARROLVAROBXNTE ret = null;
	   try {
	      ret = (PGL_PC6_PRBNTEVARROLVAROBXNTE)this.get("PRBNTEVARROLVAROBXNTE");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of PGL_PC6_PRBNTEVARROLVAROBXNTE
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public PGL_PC6_PRBNTEVARROLVAROBXNTE getPRBNTEVARROLVAROBXNTE(int rep) throws HL7Exception { 
	   return (PGL_PC6_PRBNTEVARROLVAROBXNTE)this.get("PRBNTEVARROLVAROBXNTE", rep);
	}

	/** 
	 * Returns the number of existing repetitions of PGL_PC6_PRBNTEVARROLVAROBXNTE 
	 */ 
	public int getPRBNTEVARROLVAROBXNTEReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("PRBNTEVARROLVAROBXNTE").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

	/**
	 * Returns  first repetition of PGL_PC6_ORCOBRRXONTEVAROBXNTEVAR (a Group object) - creates it if necessary
	 */
	public PGL_PC6_ORCOBRRXONTEVAROBXNTEVAR getORCOBRRXONTEVAROBXNTEVAR() { 
	   PGL_PC6_ORCOBRRXONTEVAROBXNTEVAR ret = null;
	   try {
	      ret = (PGL_PC6_ORCOBRRXONTEVAROBXNTEVAR)this.get("ORCOBRRXONTEVAROBXNTEVAR");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of PGL_PC6_ORCOBRRXONTEVAROBXNTEVAR
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public PGL_PC6_ORCOBRRXONTEVAROBXNTEVAR getORCOBRRXONTEVAROBXNTEVAR(int rep) throws HL7Exception { 
	   return (PGL_PC6_ORCOBRRXONTEVAROBXNTEVAR)this.get("ORCOBRRXONTEVAROBXNTEVAR", rep);
	}

	/** 
	 * Returns the number of existing repetitions of PGL_PC6_ORCOBRRXONTEVAROBXNTEVAR 
	 */ 
	public int getORCOBRRXONTEVAROBXNTEVARReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("ORCOBRRXONTEVAROBXNTEVAR").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

}
