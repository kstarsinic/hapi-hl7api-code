package ca.uhn.hl7v2.model.v231.segment;

import ca.uhn.hl7v2.model.*;
import ca.uhn.hl7v2.model.v231.datatype.*;

import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;

/**
 * <p>Represents an HL7 OBX message segment. 
 * This segment has the following fields:</p><p>
 * OBX-1: Set ID - OBX (SI)<br> 
 * OBX-2: Value Type (ID)<br> 
 * OBX-3: Observation Identifier (CE)<br> 
 * OBX-4: Observation Sub-ID (ST)<br> 
 * OBX-5: Observation Value (varies)<br> 
 * OBX-6: Units (CE)<br> 
 * OBX-7: References Range (ST)<br> 
 * OBX-8: Abnormal Flags (ID)<br> 
 * OBX-9: Probability (NM)<br> 
 * OBX-10: Nature of Abnormal Test (ID)<br> 
 * OBX-11: Observation Result Status (ID)<br> 
 * OBX-12: Date Last Obs Normal Values (TS)<br> 
 * OBX-13: User Defined Access Checks (ST)<br> 
 * OBX-14: Date/Time of the Observation (TS)<br> 
 * OBX-15: Producer's ID (CE)<br> 
 * OBX-16: Responsible Observer (XCN)<br> 
 * OBX-17: Observation Method (CE)<br> 
 * </p><p>The get...() methods return data from individual fields.  These methods 
 * do not throw exceptions and may therefore have to handle exceptions internally.  
 * If an exception is handled internally, it is logged and null is returned.  
 * This is not expected to happen - if it does happen this indicates not so much 
 * an exceptional circumstance as a bug in the code for this class.</p>    
 */
public class OBX extends AbstractSegment  {

  /**
   * Creates a OBX (OBX - observation/result segment) segment object that belongs to the given 
   * message.  
   */
  public OBX(Group parent, ModelClassFactory factory) {
    super(parent, factory);
    Message message = getMessage();
    try {
       this.add(SI.class, false, 1, 4, new Object[]{message});
       this.add(ID.class, true, 1, 3, new Object[]{message, new Integer(125)});
       this.add(CE.class, true, 1, 80, new Object[]{message});
       this.add(ST.class, true, 1, 20, new Object[]{message});
       this.add(Varies.class, false, 0, 65536, new Object[]{message});
       this.add(CE.class, false, 1, 60, new Object[]{message});
       this.add(ST.class, false, 1, 60, new Object[]{message});
       this.add(ID.class, false, 5, 5, new Object[]{message, new Integer(78)});
       this.add(NM.class, false, 5, 5, new Object[]{message});
       this.add(ID.class, false, 1, 2, new Object[]{message, new Integer(80)});
       this.add(ID.class, true, 1, 1, new Object[]{message, new Integer(85)});
       this.add(TS.class, false, 1, 26, new Object[]{message});
       this.add(ST.class, false, 1, 20, new Object[]{message});
       this.add(TS.class, false, 1, 26, new Object[]{message});
       this.add(CE.class, false, 1, 60, new Object[]{message});
       this.add(XCN.class, false, 0, 80, new Object[]{message});
       this.add(CE.class, false, 0, 60, new Object[]{message});
    } catch (HL7Exception he) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Can't instantiate " + this.getClass().getName(), he);
    }
  }

  /**
   * Returns Set ID - OBX (OBX-1).
   */
  public SI getSetIDOBX()  {
    SI ret = null;
    try {
        Type t = this.getField(1, 0);
        ret = (SI)t;
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
   * Returns Value Type (OBX-2).
   */
  public ID getValueType()  {
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
   * Returns Observation Identifier (OBX-3).
   */
  public CE getObservationIdentifier()  {
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
   * Returns Observation Sub-ID (OBX-4).
   */
  public ST getObservationSubID()  {
    ST ret = null;
    try {
        Type t = this.getField(4, 0);
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
   * Returns a single repetition of Observation Value (OBX-5).
   * @param rep the repetition number (this is a repeating field)
   * @throws HL7Exception if the repetition number is invalid.
   */
  public Varies getObservationValue(int rep) throws HL7Exception {
    Varies ret = null;
    try {
        Type t = this.getField(5, rep);
        ret = (Varies)t;
    } catch (ClassCastException cce) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem obtaining field value.  This is a bug.", cce);
        throw new RuntimeException(cce);
    }
    return ret;
  }

  /**
   * Returns all repetitions of Observation Value (OBX-5).
   */
  public Varies[] getObservationValue() {
     Varies[] ret = null;
    try {
        Type[] t = this.getField(5);  
        ret = new Varies[t.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = (Varies)t[i];
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
   * Returns Units (OBX-6).
   */
  public CE getUnits()  {
    CE ret = null;
    try {
        Type t = this.getField(6, 0);
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
   * Returns References Range (OBX-7).
   */
  public ST getReferencesRange()  {
    ST ret = null;
    try {
        Type t = this.getField(7, 0);
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
   * Returns a single repetition of Abnormal Flags (OBX-8).
   * @param rep the repetition number (this is a repeating field)
   * @throws HL7Exception if the repetition number is invalid.
   */
  public ID getAbnormalFlags(int rep) throws HL7Exception {
    ID ret = null;
    try {
        Type t = this.getField(8, rep);
        ret = (ID)t;
    } catch (ClassCastException cce) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem obtaining field value.  This is a bug.", cce);
        throw new RuntimeException(cce);
    }
    return ret;
  }

  /**
   * Returns all repetitions of Abnormal Flags (OBX-8).
   */
  public ID[] getAbnormalFlags() {
     ID[] ret = null;
    try {
        Type[] t = this.getField(8);  
        ret = new ID[t.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = (ID)t[i];
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
   * Returns a single repetition of Probability (OBX-9).
   * @param rep the repetition number (this is a repeating field)
   * @throws HL7Exception if the repetition number is invalid.
   */
  public NM getProbability(int rep) throws HL7Exception {
    NM ret = null;
    try {
        Type t = this.getField(9, rep);
        ret = (NM)t;
    } catch (ClassCastException cce) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem obtaining field value.  This is a bug.", cce);
        throw new RuntimeException(cce);
    }
    return ret;
  }

  /**
   * Returns all repetitions of Probability (OBX-9).
   */
  public NM[] getProbability() {
     NM[] ret = null;
    try {
        Type[] t = this.getField(9);  
        ret = new NM[t.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = (NM)t[i];
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
   * Returns Nature of Abnormal Test (OBX-10).
   */
  public ID getNatureOfAbnormalTest()  {
    ID ret = null;
    try {
        Type t = this.getField(10, 0);
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
   * Returns Observation Result Status (OBX-11).
   */
  public ID getObservationResultStatus()  {
    ID ret = null;
    try {
        Type t = this.getField(11, 0);
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
   * Returns Date Last Obs Normal Values (OBX-12).
   */
  public TS getDateLastObsNormalValues()  {
    TS ret = null;
    try {
        Type t = this.getField(12, 0);
        ret = (TS)t;
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
   * Returns User Defined Access Checks (OBX-13).
   */
  public ST getUserDefinedAccessChecks()  {
    ST ret = null;
    try {
        Type t = this.getField(13, 0);
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
   * Returns Date/Time of the Observation (OBX-14).
   */
  public TS getDateTimeOfTheObservation()  {
    TS ret = null;
    try {
        Type t = this.getField(14, 0);
        ret = (TS)t;
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
   * Returns Producer's ID (OBX-15).
   */
  public CE getProducerSID()  {
    CE ret = null;
    try {
        Type t = this.getField(15, 0);
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
   * Returns a single repetition of Responsible Observer (OBX-16).
   * @param rep the repetition number (this is a repeating field)
   * @throws HL7Exception if the repetition number is invalid.
   */
  public XCN getResponsibleObserver(int rep) throws HL7Exception {
    XCN ret = null;
    try {
        Type t = this.getField(16, rep);
        ret = (XCN)t;
    } catch (ClassCastException cce) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem obtaining field value.  This is a bug.", cce);
        throw new RuntimeException(cce);
    }
    return ret;
  }

  /**
   * Returns all repetitions of Responsible Observer (OBX-16).
   */
  public XCN[] getResponsibleObserver() {
     XCN[] ret = null;
    try {
        Type[] t = this.getField(16);  
        ret = new XCN[t.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = (XCN)t[i];
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
   * Returns a single repetition of Observation Method (OBX-17).
   * @param rep the repetition number (this is a repeating field)
   * @throws HL7Exception if the repetition number is invalid.
   */
  public CE getObservationMethod(int rep) throws HL7Exception {
    CE ret = null;
    try {
        Type t = this.getField(17, rep);
        ret = (CE)t;
    } catch (ClassCastException cce) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem obtaining field value.  This is a bug.", cce);
        throw new RuntimeException(cce);
    }
    return ret;
  }

  /**
   * Returns all repetitions of Observation Method (OBX-17).
   */
  public CE[] getObservationMethod() {
     CE[] ret = null;
    try {
        Type[] t = this.getField(17);  
        ret = new CE[t.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = (CE)t[i];
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

}