package ca.uhn.hl7v2.model.v25.segment;

import ca.uhn.hl7v2.model.*;
import ca.uhn.hl7v2.model.v25.datatype.*;

import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;

/**
 * <p>Represents an HL7 QRI message segment. 
 * This segment has the following fields:</p><p>
 * QRI-1: Candidate Confidence (NM)<br> 
 * QRI-2: Match Reason Code (IS)<br> 
 * QRI-3: Algorithm Descriptor (CE)<br> 
 * </p><p>The get...() methods return data from individual fields.  These methods 
 * do not throw exceptions and may therefore have to handle exceptions internally.  
 * If an exception is handled internally, it is logged and null is returned.  
 * This is not expected to happen - if it does happen this indicates not so much 
 * an exceptional circumstance as a bug in the code for this class.</p>    
 */
public class QRI extends AbstractSegment  {

  /**
   * Creates a QRI (Query Response Instance) segment object that belongs to the given 
   * message.  
   */
  public QRI(Group parent, ModelClassFactory factory) {
    super(parent, factory);
    Message message = getMessage();
    try {
       this.add(NM.class, false, 1, 10, new Object[]{message});
       this.add(IS.class, false, 0, 2, new Object[]{message, new Integer(392)});
       this.add(CE.class, false, 1, 250, new Object[]{message});
    } catch (HL7Exception he) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Can't instantiate " + this.getClass().getName(), he);
    }
  }

  /**
   * Returns Candidate Confidence (QRI-1).
   */
  public NM getCandidateConfidence()  {
    NM ret = null;
    try {
        Type t = this.getField(1, 0);
        ret = (NM)t;
    } catch (ClassCastException cce) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem obtaining field value.  This is a bug.", cce);
        throw new RuntimeException(cce);
    } catch (HL7Exception he) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem obtaining field value.  This is a bug.", he);
        throw new RuntimeException(he);
    }
    return ret;
  }

  /**
   * Returns a single repetition of Match Reason Code (QRI-2).
   * @param rep the repetition number (this is a repeating field)
   * @throws HL7Exception if the repetition number is invalid.
   */
  public IS getMatchReasonCode(int rep) throws HL7Exception {
    IS ret = null;
    try {
        Type t = this.getField(2, rep);
        ret = (IS)t;
    } catch (ClassCastException cce) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem obtaining field value.  This is a bug.", cce);
        throw new RuntimeException(cce);
    }
    return ret;
  }

  /**
   * Returns all repetitions of Match Reason Code (QRI-2).
   */
  public IS[] getMatchReasonCode() {
     IS[] ret = null;
    try {
        Type[] t = this.getField(2);  
        ret = new IS[t.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = (IS)t[i];
        }
    } catch (ClassCastException cce) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem obtaining field value.  This is a bug.", cce);
        throw new RuntimeException(cce);
    } catch (HL7Exception he) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem obtaining field value.  This is a bug.", he);
        throw new RuntimeException(he);
    }
    return ret;
  }

  /**
   * Returns Algorithm Descriptor (QRI-3).
   */
  public CE getAlgorithmDescriptor()  {
    CE ret = null;
    try {
        Type t = this.getField(3, 0);
        ret = (CE)t;
    } catch (ClassCastException cce) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem obtaining field value.  This is a bug.", cce);
        throw new RuntimeException(cce);
    } catch (HL7Exception he) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem obtaining field value.  This is a bug.", he);
        throw new RuntimeException(he);
    }
    return ret;
  }

}