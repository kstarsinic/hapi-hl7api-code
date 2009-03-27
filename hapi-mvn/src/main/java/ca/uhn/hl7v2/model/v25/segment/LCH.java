package ca.uhn.hl7v2.model.v25.segment;

import ca.uhn.hl7v2.model.*;
import ca.uhn.hl7v2.model.v25.datatype.*;

import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;

/**
 * <p>Represents an HL7 LCH message segment. 
 * This segment has the following fields:</p><p>
 * LCH-1: Primary Key Value - LCH (PL)<br> 
 * LCH-2: Segment Action Code (ID)<br> 
 * LCH-3: Segment Unique Key (EI)<br> 
 * LCH-4: Location Characteristic ID (CE)<br> 
 * LCH-5: Location Characteristic Value-LCH (CE)<br> 
 * </p><p>The get...() methods return data from individual fields.  These methods 
 * do not throw exceptions and may therefore have to handle exceptions internally.  
 * If an exception is handled internally, it is logged and null is returned.  
 * This is not expected to happen - if it does happen this indicates not so much 
 * an exceptional circumstance as a bug in the code for this class.</p>    
 */
public class LCH extends AbstractSegment  {

  /**
   * Creates a LCH (Location Characteristic) segment object that belongs to the given 
   * message.  
   */
  public LCH(Group parent, ModelClassFactory factory) {
    super(parent, factory);
    Message message = getMessage();
    try {
       this.add(PL.class, true, 1, 200, new Object[]{message});
       this.add(ID.class, false, 1, 3, new Object[]{message, new Integer(206)});
       this.add(EI.class, false, 1, 80, new Object[]{message});
       this.add(CE.class, true, 1, 250, new Object[]{message});
       this.add(CE.class, true, 1, 250, new Object[]{message});
    } catch (HL7Exception he) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Can't instantiate " + this.getClass().getName(), he);
    }
  }

  /**
   * Returns Primary Key Value - LCH (LCH-1).
   */
  public PL getPrimaryKeyValueLCH()  {
    PL ret = null;
    try {
        Type t = this.getField(1, 0);
        ret = (PL)t;
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
   * Returns Segment Action Code (LCH-2).
   */
  public ID getSegmentActionCode()  {
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
   * Returns Segment Unique Key (LCH-3).
   */
  public EI getSegmentUniqueKey()  {
    EI ret = null;
    try {
        Type t = this.getField(3, 0);
        ret = (EI)t;
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
   * Returns Location Characteristic ID (LCH-4).
   */
  public CE getLocationCharacteristicID()  {
    CE ret = null;
    try {
        Type t = this.getField(4, 0);
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
   * Returns Location Characteristic Value-LCH (LCH-5).
   */
  public CE getLocationCharacteristicValueLCH()  {
    CE ret = null;
    try {
        Type t = this.getField(5, 0);
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