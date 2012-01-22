package ca.uhn.hl7v2.util;

import ca.uhn.hl7v2.parser.*;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.HL7Exception;
import java.util.regex.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.apache.xml.serialize.*;
import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.parsers.StandardParserConfiguration;
import java.io.*;

/**
 * Tools for testing message strings for semantic equivalence without assuming the correctness
 * of parsers.  
 * @author Bryan Tripp
 */
public class EncodedMessageComparator {
    
    private static GenericParser parser = new GenericParser();  
    
    /**
     * Returns a "standardized" equivalent of the given message string.  For delimited
     * messages, the returned value is the shortest string that has an equivalent
     * meaning in HL7.  For XML-encoded messages, the returned value is equivalent XML output
     * using a standard pretty-print format.  An automatic determination is made about whether 
     * the given string is XML or ER7 (i.e. traditionally) encoded.
     * @param message an XML-encoded or ER7-encoded message string
     */
    public static String standardize(String message) throws SAXException {
        String result = null;
        String encoding = parser.getEncoding(message);
        if (encoding.equals("XML")) {
            result = standardizeXML(message);
        } else {
            result = standardizeER7(message);
        }
        return result;
    }
    
    /**
     * Returns the shortest string that is semantically equivalent to a given ER7-encoded 
     * message string.
     */
    public static String standardizeER7(String message) {
        
        //make delimiter sequences (must quote with \ if not alphanumeric; can't otherwise because of regexp rules)
        char fieldDelimChar = message.charAt(3);
        String fieldDelim = String.valueOf(fieldDelimChar);
        if (!Character.isLetterOrDigit(fieldDelimChar)) fieldDelim = "\\" + fieldDelimChar;
        
        char compSepChar = message.charAt(4);
        String compSep = String.valueOf(compSepChar);
        if (!Character.isLetterOrDigit(compSepChar)) compSep = "\\" + compSepChar;
        
        char repSepChar = message.charAt(5);
        String repSep = String.valueOf(repSepChar);
        if (!Character.isLetterOrDigit(repSepChar)) repSep = "\\" + repSepChar;
        
        char subSepChar = message.charAt(7);
        String subSep = String.valueOf(subSepChar);
        if (!Character.isLetterOrDigit(subSepChar)) subSep = "\\" + subSepChar;
        
        //char space = ' ';
        
        /* Things to strip (cumulative):
         *  - all delimiters and repetition separators before end line (i.e. end segment)
         *  - repetition separators, comp and subcomp delims before new field
         *  - subcomponent delimiters before new component
         */
        Pattern endSegment = Pattern.compile("[" + fieldDelim + compSep + repSep + subSep + "]*[\n\r]+");
        message = endSegment.matcher(message).replaceAll("\r");
        
        Pattern endField = Pattern.compile("[" + repSep + compSep + subSep + "]*" + fieldDelim);
        message = endField.matcher(message).replaceAll(String.valueOf(fieldDelim));
        
        Pattern endComp = Pattern.compile("[" + subSep + "]*" + compSep);
        message = endComp.matcher(message).replaceAll(String.valueOf(compSep));
        
        //Pattern endSub = Pattern.compile("[ ]*" + subSep);
        //message = endSub.matcher(message).replaceAll(String.valueOf(subSep));
        
        //handle special case of subcomp delim in encoding characters
        message = message.substring(0, 7) + subSepChar + message.substring(7);
        
        return message;
    }
    
    /**
     * Returns a semantic equivalent of a given XML-encoded message in a default format.
     * Attributes, comments, and processing instructions are not considered to change the 
     * HL7 meaning of the message, and are removed in the standardized representation.    
     */
    public static String standardizeXML(String message) throws SAXException {
        DOMParser parser = new DOMParser(new StandardParserConfiguration());
        parser.setFeature("http://apache.org/xml/features/dom/include-ignorable-whitespace", false);
        
        Document doc = null;
        StringWriter out = new StringWriter();
        try {
            synchronized (parser) {
                parser.parse(new InputSource(new StringReader(message)));
                doc = parser.getDocument();
            }
            clean(doc.getDocumentElement());
            OutputFormat outputFormat = new OutputFormat("", null, true);
            XMLSerializer ser = new XMLSerializer(out, outputFormat);            
            ser.serialize(doc);
        } catch (IOException e) {
            throw new RuntimeException("IOException doing IO to a string!!! " + e.getMessage());
        }
        return out.toString();
    }
    
    /** Removes attributes, comments, and processing instructions. */
    private static void clean(Element elem) {
        NodeList children = elem.getChildNodes();        
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE 
                || child.getNodeType() == Node.COMMENT_NODE)
            {
                elem.removeChild(child);
            } else if (child.getNodeType() == Node.ELEMENT_NODE) {
                clean((Element) child);
            }
        }
        
        NamedNodeMap attributes = elem.getAttributes();
        //get names
        String[] names = new String[attributes.getLength()];
        for (int i = 0; i < names.length; i++) {
            names[i] = attributes.item(i).getNodeName();
        }
        //remove by name
        for (int i = 0; i < names.length; i++) {
            attributes.removeNamedItem(names[i]);
        }
    }
    
    /**
     * <p>Compares two HL7 messages to see if they are equivalent (in terms of their  
     * HL7 meaning).  Semantically irrelevant differences (e.g. spaces in an XML tag; 
     * extra field delimiters at the end of a segment; XML vs. ER7 encoding; XML attributes)
     * are ignored. This check is performed without assuming the correctness of the HAPI parsers, 
     * and can therefore be used to test them.  This is done by parsing a message, encoding it
     * again, and comparing the result with this original.  </p>
     * <p>If one message is in XML and the other in ER7, the former is converted to ER7 to 
     * perform the comparison.  This process relies on the HAPI parsers.  However, the 
     * parsed message is first encoded as XML and compared to the original, so that the 
     * integrity of the parser can be verified.  An exception is thrown if this comparison 
     * is unsuccessful.  </p>
     * @return true if given messages are semantically equivalent 
     */
    public static boolean equivalent(String message1, String message2) throws HL7Exception {
        String encoding1 = parser.getEncoding(message1);
        String encoding2 = parser.getEncoding(message2);
        
        if (!encoding1.equals(encoding2)) {
            if (encoding1.equals("XML")) {
                message1 = safeER7Conversion(message1);
            } else {
                message2 = safeER7Conversion(message2);
            }
        }
        
        String std1, std2;
        try {
            std1 = standardize(message1);
            std2 = standardize(message2);
        } catch (SAXException e) {
            throw new HL7Exception("Equivalence check failed due to SAXException: " + e.getMessage());
        }
        
        return std1.equals(std2);
    }
    
    /** 
     * Converts XML message to ER7, first checking integrity of parse and throwing 
     * an exception if parse not correct
     */
    private static String safeER7Conversion(String xmlMessage) throws HL7Exception {
        Message m = parser.parse(xmlMessage);

        String check = parser.encode(m, "XML");
        if (!equivalent(xmlMessage, check)) {
            throw new HL7Exception("Parsed and encoded message not equivalent to original (possibilities: invalid message, bug in parser)");
        }
        
        return parser.encode(m, "VB");        
    }
    
    /** 
     * Compares messages in two files
     */
    public static void main(String args[]) {
        
    }
    
}
