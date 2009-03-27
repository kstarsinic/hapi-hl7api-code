package ca.uhn.hl7v2.model.v22.message;

import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.model.v22.group.*;

import ca.uhn.hl7v2.model.v22.segment.*;

import ca.uhn.hl7v2.HL7Exception;

import ca.uhn.hl7v2.parser.ModelClassFactory;

import ca.uhn.hl7v2.parser.DefaultModelClassFactory;

import ca.uhn.hl7v2.model.AbstractMessage;

/**
 * <p>Represents a NMD_N01 message structure (see chapter ?). This structure contains the 
 * following elements: </p>
 * 0: MSH (MESSAGE HEADER) <b></b><br>
 * 1: NMD_N01_CLOCK_AND_STATS_WITH_NOTES (a Group object) <b>repeating</b><br>
 */
public class NMD_N01 extends AbstractMessage  {

	/** 
	 * Creates a new NMD_N01 Group with custom ModelClassFactory.
	 */
	public NMD_N01(ModelClassFactory factory) {
	   super(factory);
	   init(factory);
	}

	/**
	 * Creates a new NMD_N01 Group with DefaultModelClassFactory. 
	 */ 
	public NMD_N01() { 
	   super(new DefaultModelClassFactory());
	   init(new DefaultModelClassFactory());
	}

	private void init(ModelClassFactory factory) {
	   try {
	      this.add(MSH.class, true, false);
	      this.add(NMD_N01_CLOCK_AND_STATS_WITH_NOTES.class, true, true);
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error creating NMD_N01 - this is probably a bug in the source code generator.", e);
	   }
	}

	/**
	 * Returns MSH (MESSAGE HEADER) - creates it if necessary
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
	 * Returns  first repetition of NMD_N01_CLOCK_AND_STATS_WITH_NOTES (a Group object) - creates it if necessary
	 */
	public NMD_N01_CLOCK_AND_STATS_WITH_NOTES getCLOCK_AND_STATS_WITH_NOTES() { 
	   NMD_N01_CLOCK_AND_STATS_WITH_NOTES ret = null;
	   try {
	      ret = (NMD_N01_CLOCK_AND_STATS_WITH_NOTES)this.get("CLOCK_AND_STATS_WITH_NOTES");
	   } catch(HL7Exception e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected error accessing data - this is probably a bug in the source code generator.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns a specific repetition of NMD_N01_CLOCK_AND_STATS_WITH_NOTES
	 * (a Group object) - creates it if necessary
	 * throws HL7Exception if the repetition requested is more than one 
	 *     greater than the number of existing repetitions.
	 */
	public NMD_N01_CLOCK_AND_STATS_WITH_NOTES getCLOCK_AND_STATS_WITH_NOTES(int rep) throws HL7Exception { 
	   return (NMD_N01_CLOCK_AND_STATS_WITH_NOTES)this.get("CLOCK_AND_STATS_WITH_NOTES", rep);
	}

	/** 
	 * Returns the number of existing repetitions of NMD_N01_CLOCK_AND_STATS_WITH_NOTES 
	 */ 
	public int getCLOCK_AND_STATS_WITH_NOTESReps() { 
	    int reps = -1; 
	    try { 
	        reps = this.getAll("CLOCK_AND_STATS_WITH_NOTES").length; 
	    } catch (HL7Exception e) { 
	        String message = "Unexpected error accessing data - this is probably a bug in the source code generator."; 
	        HapiLogFactory.getHapiLog(this.getClass()).error(message, e); 
	        throw new RuntimeException(message);
	    } 
	    return reps; 
	} 

}
