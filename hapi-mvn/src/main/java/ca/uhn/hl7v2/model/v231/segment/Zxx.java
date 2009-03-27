package ca.uhn.hl7v2.model.v231.segment;

import ca.uhn.hl7v2.model.*;
import ca.uhn.hl7v2.model.v231.datatype.*;

import ca.uhn.log.HapiLogFactory;
import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.HL7Exception;

/**
 * <p>Represents an HL7 Zxx message segment. 
 * This segment has the following fields:</p><p>
 * </p><p>The get...() methods return data from individual fields.  These methods 
 * do not throw exceptions and may therefore have to handle exceptions internally.  
 * If an exception is handled internally, it is logged and null is returned.  
 * This is not expected to happen - if it does happen this indicates not so much 
 * an exceptional circumstance as a bug in the code for this class.</p>    
 */
public class Zxx extends AbstractSegment  {

  /**
   * Creates a Zxx (null) segment object that belongs to the given 
   * message.  
   */
  public Zxx(Group parent, ModelClassFactory factory) {
    super(parent, factory);
    Message message = getMessage();
  }

}