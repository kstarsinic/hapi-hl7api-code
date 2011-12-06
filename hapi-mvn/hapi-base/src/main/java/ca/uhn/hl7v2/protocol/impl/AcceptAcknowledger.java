/*
 * Created on 10-May-2004
 */
package ca.uhn.hl7v2.protocol.impl;

import java.io.IOException;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.app.DefaultApplication;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Segment;
import ca.uhn.hl7v2.parser.GenericParser;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.protocol.AcceptValidator;
import ca.uhn.hl7v2.protocol.Processor;
import ca.uhn.hl7v2.protocol.ProcessorContext;
import ca.uhn.hl7v2.protocol.Transportable;
import ca.uhn.hl7v2.util.Terser;
import ca.uhn.log.HapiLog;
import ca.uhn.log.HapiLogFactory;

/**
 * Checks whether messages can be accepted and creates appropriate
 * ACK messages.  
 * 
 * @author <a href="mailto:bryan.tripp@uhn.on.ca">Bryan Tripp</a>
 * @version $Revision: 1.1 $ updated on $Date: 2007-02-19 02:24:26 $ by $Author: jamesagnew $
 */
public class AcceptAcknowledger {

    private static final HapiLog log = HapiLogFactory.getHapiLog(AcceptAcknowledger.class);
    
    private static Parser ourParser = new GenericParser();
    
    /** 
     * Validates the given message against our accept validators, attempts to commit
     * the message to safe storage, and returns an ACK message indicating acceptance 
     * or rejection at the accept level (see enhanced mode processing rules in HL7 
     * chapter 2, v2.5).  
     */
    public static AcceptACK validate(ProcessorContext theContext, Transportable theMessage) throws HL7Exception {
        AcceptACK ruling = null;
        
        AcceptValidator[] validators = theContext.getValidators();
        for (int i = 0; i < validators.length && ruling == null; i++) {
            AcceptValidator.AcceptRuling vr = validators[i].check(theMessage);            
            if (!vr.isAcceptable()) {
                String description = (vr.getReasons().length > 0) ? vr.getReasons()[0] : null;
                Transportable ack = makeAcceptAck(theMessage, vr.getAckCode(), vr.getErrorCode(), description);
                ruling = new AcceptACK(false, ack);
            }
        }
        
        if (ruling == null) {
            try {
                theContext.getSafeStorage().store(theMessage);
                Transportable ack = makeAcceptAck(theMessage, Processor.CA, HL7Exception.MESSAGE_ACCEPTED, "");
                ruling = new AcceptACK(true, ack);
            } catch (HL7Exception e) {
                log.error(e.getMessage(), e);
                int code = HL7Exception.APPLICATION_INTERNAL_ERROR;
                Transportable ack = makeAcceptAck(theMessage, Processor.CR, code, e.getMessage());
                ruling = new AcceptACK(false, ack);
            }
        }        
        
        return ruling;
    }


    private static Transportable makeAcceptAck(Transportable theMessage, String theAckCode, int theErrorCode, String theDescription) throws HL7Exception {
        
        Segment header = ourParser.getCriticalResponseData(theMessage.getMessage());
        Message out;
        try {
            out = DefaultApplication.makeACK(header);
        } catch (IOException e) {
            throw new HL7Exception(e);
        }
        
        Terser t = new Terser(out);
        t.set("/MSA-1", theAckCode);

        //TODO: when 2.5 is available, use 2.5 fields for remaining problems 
        if (theErrorCode != HL7Exception.MESSAGE_ACCEPTED) {
            t.set("/MSA-3", theDescription.substring(0, Math.min(80, theDescription.length())));            
            t.set("/ERR-1-4-1", String.valueOf(theErrorCode));
            t.set("/ERR-1-4-3", "HL70357");
        }
        
        String originalEncoding = ourParser.getEncoding(theMessage.getMessage());
        String ackText = ourParser.encode(out, originalEncoding);
        return new TransportableImpl(ackText);
    }    
    
    
    /**
     * A structure for decisions as to whether a message can be accepted, 
     * along with a corresponding accept or reject acknowlegement message. 
     *  
     * @author <a href="mailto:bryan.tripp@uhn.on.ca">Bryan Tripp</a>
     * @version $Revision: 1.1 $ updated on $Date: 2007-02-19 02:24:26 $ by $Author: jamesagnew $
     */
    public static class AcceptACK {
        private Transportable myAck;
        private boolean myIsAcceptable;
        
        public AcceptACK(boolean isAcceptable, Transportable theAck) {
            myIsAcceptable = isAcceptable;
            myAck = theAck;
        }
        
        public boolean isAcceptable() {
            return myIsAcceptable;
        }
        
        public Transportable getMessage() {
            return myAck;
        }
    }

}
