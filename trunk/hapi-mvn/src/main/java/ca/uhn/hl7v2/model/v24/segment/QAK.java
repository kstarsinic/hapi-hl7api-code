package ca.uhn.hl7v2.model.v24.segment;

import ca.uhn.hl7v2.model.*;
import ca.uhn.hl7v2.model.v24.datatype.*;

import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;

/**
 * <p>Represents an HL7 QAK message segment. 
 * This segment has the following fields:</p><p>
 * QAK-1: Query Tag (ST)<br> 
 * QAK-2: Query Response Status (ID)<br> 
 * QAK-3: Message Query Name (CE)<br> 
 * QAK-4: Hit Count Total (NM)<br> 
 * QAK-5: This payload (NM)<br> 
 * QAK-6: Hits remaining (NM)<br> 
 * </p><p>The get...() methods return data from individual fields.  These methods 
 * do not throw exceptions and may therefore have to handle exceptions internally.  
 * If an exception is handled internally, it is logged and null is returned.  
 * This is not expected to happen - if it does happen this indicates not so much 
 * an exceptional circumstance as a bug in the code for this class.</p>    
 */
public class QAK extends AbstractSegment  {

  /**
   * Creates a QAK (Query Acknowledgment) segment object that belongs to the given 
   * message.  
   */
  public QAK(Group parent, ModelClassFactory factory) {
    super(parent, factory);
    Message message = getMessage();
    try {
       this.add(ST.class, false, 1, 32, new Object[]{message});
       this.add(ID.class, false, 1, 2, new Object[]{message, new Integer(208)});
       this.add(CE.class, false, 1, 250, new Object[]{message});
       this.add(NM.class, false, 1, 10, new Object[]{message});
       this.add(NM.class, false, 1, 10, new Object[]{message});
       this.add(NM.class, false, 1, 10, new Object[]{message});
    } catch (HL7Exception he) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Can't instantiate " + this.getClass().getName(), he);
    }
  }

  /**
   * Returns Query Tag (QAK-1).
   */
  public ST getQueryTag()  {
    ST ret = null;
    try {
        Type t = this.getField(1, 0);
        ret = (ST)t;
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
   * Returns Query Response Status (QAK-2).
   */
  public ID getQueryResponseStatus()  {
    ID ret = null;
    try {
        Type t = this.getField(2, 0);
        ret = (ID)t;
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
   * Returns Message Query Name (QAK-3).
   */
  public CE getMessageQueryName()  {
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

  /**
   * Returns Hit Count Total (QAK-4).
   */
  public NM getHitCountTotal()  {
    NM ret = null;
    try {
        Type t = this.getField(4, 0);
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
   * Returns This payload (QAK-5).
   */
  public NM getThisPayload()  {
    NM ret = null;
    try {
        Type t = this.getField(5, 0);
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
   * Returns Hits remaining (QAK-6).
   */
  public NM getHitsRemaining()  {
    NM ret = null;
    try {
        Type t = this.getField(6, 0);
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

}