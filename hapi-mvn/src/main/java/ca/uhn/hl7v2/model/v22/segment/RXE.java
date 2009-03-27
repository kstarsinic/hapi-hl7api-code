package ca.uhn.hl7v2.model.v22.segment;

import ca.uhn.hl7v2.model.*;
import ca.uhn.hl7v2.model.v22.datatype.*;

import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;

/**
 * <p>Represents an HL7 RXE message segment. 
 * This segment has the following fields:</p><p>
 * RXE-1: Quantity / timing (TQ)<br> 
 * RXE-2: Give Code (CE)<br> 
 * RXE-3: Give Amount - Minimum (NM)<br> 
 * RXE-4: Give Amount - Maximum (NM)<br> 
 * RXE-5: Give Units (CE)<br> 
 * RXE-6: Give Dosage Form (CE)<br> 
 * RXE-7: Provider's Administration Instructions (CE)<br> 
 * RXE-8: Deliver-to location (CM_LA1)<br> 
 * RXE-9: Substitution Status (ID)<br> 
 * RXE-10: Dispense Amount (NM)<br> 
 * RXE-11: Dispense Units (CE)<br> 
 * RXE-12: Number of Refills (NM)<br> 
 * RXE-13: Ordering Provider's DEA Number (CN)<br> 
 * RXE-14: Pharmacist Verifier ID (CN)<br> 
 * RXE-15: Prescription Number (ST)<br> 
 * RXE-16: Number of Refills Remaining (NM)<br> 
 * RXE-17: Number of refills / doses dispensed (NM)<br> 
 * RXE-18: Date / time of most recent refill or dose dispensed (TS)<br> 
 * RXE-19: Total Daily Dose (CQ_QUANTITY)<br> 
 * RXE-20: Needs Human Review (ID)<br> 
 * RXE-21: Pharmacy Special Dispensing Instructions (CE)<br> 
 * RXE-22: Give Per (Time Unit) (ST)<br> 
 * RXE-23: Give Rate Amount (CE)<br> 
 * RXE-24: Give Rate Units (CE)<br> 
 * </p><p>The get...() methods return data from individual fields.  These methods 
 * do not throw exceptions and may therefore have to handle exceptions internally.  
 * If an exception is handled internally, it is logged and null is returned.  
 * This is not expected to happen - if it does happen this indicates not so much 
 * an exceptional circumstance as a bug in the code for this class.</p>    
 */
public class RXE extends AbstractSegment  {

  /**
   * Creates a RXE (PHARMACY ENCODED ORDER) segment object that belongs to the given 
   * message.  
   */
  public RXE(Group parent, ModelClassFactory factory) {
    super(parent, factory);
    Message message = getMessage();
    try {
       this.add(TQ.class, false, 0, 200, new Object[]{message});
       this.add(CE.class, true, 1, 100, new Object[]{message});
       this.add(NM.class, true, 1, 20, new Object[]{message});
       this.add(NM.class, false, 1, 20, new Object[]{message});
       this.add(CE.class, true, 1, 60, new Object[]{message});
       this.add(CE.class, false, 1, 60, new Object[]{message});
       this.add(CE.class, false, 0, 200, new Object[]{message});
       this.add(CM_LA1.class, false, 1, 12, new Object[]{message});
       this.add(ID.class, false, 1, 1, new Object[]{message, new Integer(167)});
       this.add(NM.class, false, 1, 20, new Object[]{message});
       this.add(CE.class, false, 1, 60, new Object[]{message});
       this.add(NM.class, false, 1, 3, new Object[]{message});
       this.add(CN.class, false, 1, 60, new Object[]{message});
       this.add(CN.class, false, 1, 60, new Object[]{message});
       this.add(ST.class, true, 1, 20, new Object[]{message});
       this.add(NM.class, false, 1, 20, new Object[]{message});
       this.add(NM.class, false, 1, 20, new Object[]{message});
       this.add(TS.class, false, 1, 26, new Object[]{message});
       this.add(CQ_QUANTITY.class, false, 1, 10, new Object[]{message});
       this.add(ID.class, false, 1, 1, new Object[]{message, new Integer(0)});
       this.add(CE.class, false, 1, 200, new Object[]{message});
       this.add(ST.class, false, 1, 20, new Object[]{message});
       this.add(CE.class, false, 1, 6, new Object[]{message});
       this.add(CE.class, false, 1, 60, new Object[]{message});
    } catch (HL7Exception he) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Can't instantiate " + this.getClass().getName(), he);
    }
  }

  /**
   * Returns a single repetition of Quantity / timing (RXE-1).
   * @param rep the repetition number (this is a repeating field)
   * @throws HL7Exception if the repetition number is invalid.
   */
  public TQ getQuantityTiming(int rep) throws HL7Exception {
    TQ ret = null;
    try {
        Type t = this.getField(1, rep);
        ret = (TQ)t;
    } catch (ClassCastException cce) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem obtaining field value.  This is a bug.", cce);
        throw new RuntimeException(cce);
    }
    return ret;
  }

  /**
   * Returns all repetitions of Quantity / timing (RXE-1).
   */
  public TQ[] getQuantityTiming() {
     TQ[] ret = null;
    try {
        Type[] t = this.getField(1);  
        ret = new TQ[t.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = (TQ)t[i];
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
   * Returns Give Code (RXE-2).
   */
  public CE getGiveCode()  {
    CE ret = null;
    try {
        Type t = this.getField(2, 0);
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
   * Returns Give Amount - Minimum (RXE-3).
   */
  public NM getGiveAmountMinimum()  {
    NM ret = null;
    try {
        Type t = this.getField(3, 0);
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
   * Returns Give Amount - Maximum (RXE-4).
   */
  public NM getGiveAmountMaximum()  {
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
   * Returns Give Units (RXE-5).
   */
  public CE getGiveUnits()  {
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

  /**
   * Returns Give Dosage Form (RXE-6).
   */
  public CE getGiveDosageForm()  {
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
   * Returns a single repetition of Provider's Administration Instructions (RXE-7).
   * @param rep the repetition number (this is a repeating field)
   * @throws HL7Exception if the repetition number is invalid.
   */
  public CE getProviderSAdministrationInstructions(int rep) throws HL7Exception {
    CE ret = null;
    try {
        Type t = this.getField(7, rep);
        ret = (CE)t;
    } catch (ClassCastException cce) {
        HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem obtaining field value.  This is a bug.", cce);
        throw new RuntimeException(cce);
    }
    return ret;
  }

  /**
   * Returns all repetitions of Provider's Administration Instructions (RXE-7).
   */
  public CE[] getProviderSAdministrationInstructions() {
     CE[] ret = null;
    try {
        Type[] t = this.getField(7);  
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

  /**
   * Returns Deliver-to location (RXE-8).
   */
  public CM_LA1 getDeliverToLocation()  {
    CM_LA1 ret = null;
    try {
        Type t = this.getField(8, 0);
        ret = (CM_LA1)t;
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
   * Returns Substitution Status (RXE-9).
   */
  public ID getSubstitutionStatus()  {
    ID ret = null;
    try {
        Type t = this.getField(9, 0);
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
   * Returns Dispense Amount (RXE-10).
   */
  public NM getDispenseAmount()  {
    NM ret = null;
    try {
        Type t = this.getField(10, 0);
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
   * Returns Dispense Units (RXE-11).
   */
  public CE getDispenseUnits()  {
    CE ret = null;
    try {
        Type t = this.getField(11, 0);
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
   * Returns Number of Refills (RXE-12).
   */
  public NM getNumberOfRefills()  {
    NM ret = null;
    try {
        Type t = this.getField(12, 0);
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
   * Returns Ordering Provider's DEA Number (RXE-13).
   */
  public CN getOrderingProviderSDEANumber()  {
    CN ret = null;
    try {
        Type t = this.getField(13, 0);
        ret = (CN)t;
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
   * Returns Pharmacist Verifier ID (RXE-14).
   */
  public CN getPharmacistVerifierID()  {
    CN ret = null;
    try {
        Type t = this.getField(14, 0);
        ret = (CN)t;
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
   * Returns Prescription Number (RXE-15).
   */
  public ST getPrescriptionNumber()  {
    ST ret = null;
    try {
        Type t = this.getField(15, 0);
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
   * Returns Number of Refills Remaining (RXE-16).
   */
  public NM getNumberOfRefillsRemaining()  {
    NM ret = null;
    try {
        Type t = this.getField(16, 0);
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
   * Returns Number of refills / doses dispensed (RXE-17).
   */
  public NM getNumberOfRefillsDosesDispensed()  {
    NM ret = null;
    try {
        Type t = this.getField(17, 0);
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
   * Returns Date / time of most recent refill or dose dispensed (RXE-18).
   */
  public TS getDateTimeOfMostRecentRefillOrDoseDispensed()  {
    TS ret = null;
    try {
        Type t = this.getField(18, 0);
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
   * Returns Total Daily Dose (RXE-19).
   */
  public CQ_QUANTITY getTotalDailyDose()  {
    CQ_QUANTITY ret = null;
    try {
        Type t = this.getField(19, 0);
        ret = (CQ_QUANTITY)t;
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
   * Returns Needs Human Review (RXE-20).
   */
  public ID getNeedsHumanReview()  {
    ID ret = null;
    try {
        Type t = this.getField(20, 0);
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
   * Returns Pharmacy Special Dispensing Instructions (RXE-21).
   */
  public CE getPharmacySpecialDispensingInstructions()  {
    CE ret = null;
    try {
        Type t = this.getField(21, 0);
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
   * Returns Give Per (Time Unit) (RXE-22).
   */
  public ST getGivePerTimeUnit()  {
    ST ret = null;
    try {
        Type t = this.getField(22, 0);
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
   * Returns Give Rate Amount (RXE-23).
   */
  public CE getGiveRateAmount()  {
    CE ret = null;
    try {
        Type t = this.getField(23, 0);
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
   * Returns Give Rate Units (RXE-24).
   */
  public CE getGiveRateUnits()  {
    CE ret = null;
    try {
        Type t = this.getField(24, 0);
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