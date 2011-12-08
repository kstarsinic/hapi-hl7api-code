/**
The contents of this file are subject to the Mozilla Public License Version 1.1 
(the "License"); you may not use this file except in compliance with the License. 
You may obtain a copy of the License at http://www.mozilla.org/MPL/ 
Software distributed under the License is distributed on an "AS IS" basis, 
WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the 
specific language governing rights and limitations under the License. 

The Original Code is "DefaultValidator.java".  Description: 
"A default conformance validator." 

The Initial Developer of the Original Code is University Health Network. Copyright (C) 
2003.  All Rights Reserved. 

Contributor(s): ______________________________________. 

Alternatively, the contents of this file may be used under the terms of the 
GNU General Public License (the  �GPL�), in which case the provisions of the GPL are 
applicable instead of those above.  If you wish to allow use of your version of this 
file only under the terms of the GPL and not to allow others to use your version 
of this file under the MPL, indicate your decision by deleting  the provisions above 
and replace  them with the notice and other provisions required by the GPL License.  
If you do not delete the provisions above, a recipient may use your version of 
this file under either the MPL or the GPL. 

*/

package ca.uhn.hl7v2.conf.check;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.conf.ProfileException;
import ca.uhn.hl7v2.conf.parser.ProfileParser;
import ca.uhn.hl7v2.conf.spec.RuntimeProfile;
import ca.uhn.hl7v2.conf.spec.message.AbstractComponent;
import ca.uhn.hl7v2.conf.spec.message.AbstractSegmentContainer;
import ca.uhn.hl7v2.conf.spec.message.Component;
import ca.uhn.hl7v2.conf.spec.message.Field;
import ca.uhn.hl7v2.conf.spec.message.ProfileStructure;
import ca.uhn.hl7v2.conf.spec.message.Seg;
import ca.uhn.hl7v2.conf.spec.message.SegGroup;
import ca.uhn.hl7v2.conf.spec.message.StaticDef;
import ca.uhn.hl7v2.conf.spec.message.SubComponent;
import ca.uhn.hl7v2.conf.store.CodeStore;
import ca.uhn.hl7v2.conf.store.ProfileStoreFactory;
import ca.uhn.hl7v2.model.Composite;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Group;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Primitive;
import ca.uhn.hl7v2.model.Segment;
import ca.uhn.hl7v2.model.Structure;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.parser.EncodingCharacters;
import ca.uhn.hl7v2.parser.GenericParser;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.Terser;

/**
 * A default conformance validator.
 * @author Bryan Tripp
 */
public class DefaultValidator implements Validator {
    
    private EncodingCharacters enc;  //used to check for content in parts of a message
    private static final Logger log = LoggerFactory.getLogger(DefaultValidator.class);
    
    /** Creates a new instance of DefaultValidator */
    public DefaultValidator() {
        enc = new EncodingCharacters('|', null);  //the | is assumed later -- don't change
    }
    
    /**
     * @see Validator#validate
     */
    public HL7Exception[] validate(Message message, StaticDef profile) throws ProfileException, HL7Exception {
        ArrayList<HL7Exception> exList = new ArrayList<HL7Exception>(20);
        Terser t = new Terser(message);
        
        //check msg type, event type, msg struct ID
        String msgType = t.get("/MSH-9-1");
        if (!msgType.equals(profile.getMsgType())) {
            HL7Exception e =
                new ProfileNotFollowedException("Message type " + msgType + " doesn't match profile type of " + profile.getMsgType());
            exList.add(e);
        }
        
        String evType = t.get("/MSH-9-2");
        if (!evType.equals(profile.getEventType()) && !profile.getEventType().equalsIgnoreCase("ALL")) {
            HL7Exception e =
                new ProfileNotFollowedException("Event type " + evType + " doesn't match profile type of " + profile.getEventType());
            exList.add(e);
        }
        
        String msgStruct = t.get("/MSH-9-3");
        if (msgStruct == null || !msgStruct.equals(profile.getMsgStructID())) {
            HL7Exception e =
                new ProfileNotFollowedException("Message structure " + msgStruct + " doesn't match profile type of " + profile.getMsgStructID());
            exList.add(e);
        }
        
        HL7Exception[] childExceptions; 
        childExceptions = testGroup(message, profile, profile.getIdentifier());
        for (int i = 0; i < childExceptions.length; i++) {
            exList.add(childExceptions[i]);
        }
        
        return toArray(exList);
    }
    
    /**
     * Tests a group against a group section of a profile.
     */
    public HL7Exception[] testGroup(Group group, AbstractSegmentContainer profile, String profileID) throws ProfileException {
        ArrayList<HL7Exception> exList = new ArrayList<HL7Exception>(20);
        ArrayList<String> allowedStructures = new ArrayList<String>(20);
        
        for (int i = 1; i <= profile.getChildren(); i++) {
            ProfileStructure struct = profile.getChild(i);
            
            //only test a structure in detail if it isn't X
            if (!struct.getUsage().equalsIgnoreCase("X")) {
                allowedStructures.add(struct.getName());
                
                //see which instances have content
                try {
                    Structure[] instances = group.getAll(struct.getName());
                    ArrayList<Structure> instancesWithContent = new ArrayList<Structure>(10);
                    for (int j = 0; j < instances.length; j++) {
                        if (hasContent(instances[j])) instancesWithContent.add(instances[j]);
                    }
                    
                    HL7Exception ce = testCardinality(instancesWithContent.size(),
                    struct.getMin(), struct.getMax(), struct.getUsage(), struct.getName());
                    if (ce != null) exList.add(ce);
                    
                    //test children on instances with content
                    for (int j = 0; j < instancesWithContent.size(); j++) {
                        Structure s = (Structure) instancesWithContent.get(j);
                        HL7Exception[] childExceptions = testStructure(s, struct, profileID);
                        addToList(childExceptions, exList);
                    }
                } catch (HL7Exception he) {
                    exList.add(new ProfileNotHL7CompliantException(struct.getName() + " not found in message"));
                }
            }
        }
     
        //complain about X structures that have content
        addToList(checkForExtraStructures(group, allowedStructures), exList);
        
        return toArray(exList);
    }
    
    /**
     * Checks a group's children against a list of allowed structures for the group 
     * (ie those mentioned in the profile with usage other than X).  Returns 
     * a list of exceptions representing structures that appear in the message  
     * but are not supposed to.  
     */
    private HL7Exception[] checkForExtraStructures(Group group, ArrayList<String> allowedStructures) throws ProfileException {
        ArrayList<HL7Exception> exList = new ArrayList<HL7Exception>();
        String[] childNames = group.getNames();
        for (int i = 0; i < childNames.length; i++) {
            if (!allowedStructures.contains(childNames[i])) {
                try {
                    Structure[] reps = group.getAll(childNames[i]);
                    for (int j = 0; j < reps.length; j++) {
                        if (hasContent(reps[j])) {
                            HL7Exception e =
                                new XElementPresentException("The structure " 
                                    + childNames[i] 
                                    + " appears in the message but not in the profile");
                            exList.add(e);
                        }
                    }
                } catch (HL7Exception he) {
                    throw new ProfileException("Problem checking profile", he);
                }
            }
        }
        return toArray(exList);
    }
    
    /**
     * Checks cardinality and creates an appropriate exception if out 
     * of bounds.  The usage code is needed because if min cardinality
     * is > 0, the min # of reps is only required if the usage code
     * is 'R' (see HL7 v2.5 section 2.12.6.4).  
     * @param reps the number of reps
     * @param min the minimum number of reps
     * @param max the maximum number of reps (-1 means *)
     * @param usage the usage code 
     * @param name the name of the repeating structure (used in exception msg)
     * @return null if cardinality OK, exception otherwise
     */
    protected HL7Exception testCardinality(int reps, int min, int max, String usage, String name) {
        HL7Exception e = null;
        if (reps < min && usage.equalsIgnoreCase("R")) {
            e = new ProfileNotFollowedException(name + " must have at least " + min + " repetitions (has " + reps + ")");
        } else if (max > 0 && reps > max) {
            e = new ProfileNotFollowedException(name + " must have no more than " + max + " repetitions (has " + reps + ")");
        }
        return e;
    }
    
    /**
     * Tests a structure (segment or group) against the corresponding part of a profile.  
     */
    public HL7Exception[] testStructure(Structure s, ProfileStructure profile, String profileID) throws ProfileException {
        ArrayList<HL7Exception> exList = new ArrayList<HL7Exception>(20);
        if (profile instanceof Seg) {
            if (Segment.class.isAssignableFrom(s.getClass())) {
                addToList(testSegment((Segment) s, (Seg) profile, profileID), exList);
            } else {
                exList.add(new ProfileNotHL7CompliantException("Mismatch between a segment in the profile and the structure " 
                        + s.getClass().getName() + " in the message"));
            }
        } else if (profile instanceof SegGroup) {
            if (Group.class.isAssignableFrom(s.getClass())) {
                addToList(testGroup((Group) s, (SegGroup) profile, profileID), exList);
            } else {
                exList.add(new ProfileNotHL7CompliantException("Mismatch between a group in the profile and the structure " 
                        + s.getClass().getName() + " in the message"));
            }
        }
        return toArray(exList);
    }
    
    /**
     * Tests a segment against a segment section of a profile.
     */
    public HL7Exception[] testSegment(ca.uhn.hl7v2.model.Segment segment, Seg profile, String profileID) throws ProfileException {
        ArrayList<HL7Exception> exList = new ArrayList<HL7Exception>(20);
        ArrayList<Integer> allowedFields = new ArrayList<Integer>(20);
        
        for (int i = 1; i <= profile.getFields(); i++) {
            Field field = profile.getField(i);
            
            //only test a field in detail if it isn't X
            if (!field.getUsage().equalsIgnoreCase("X")) {
                allowedFields.add(new Integer(i));
                
                //see which instances have content
                try {
                    Type[] instances = segment.getField(i);
                    ArrayList<Type> instancesWithContent = new ArrayList<Type>(10);
                    for (int j = 0; j < instances.length; j++) {
                        if (hasContent(instances[j])) instancesWithContent.add(instances[j]);
                    }
                    
                    HL7Exception ce = testCardinality(instancesWithContent.size(),
                    field.getMin(), field.getMax(), field.getUsage(), field.getName());
                    if (ce != null) {
                        ce.setFieldPosition(i);
                        exList.add(ce);
                    }
                    
                    //test field instances with content
                    for (int j = 0; j < instancesWithContent.size(); j++) {
                        Type s = (Type) instancesWithContent.get(j);
                        
                        boolean escape = true; //escape field value when checking length
                        if (profile.getName().equalsIgnoreCase("MSH") && i < 3) {
                            escape = false;
                        }
                        HL7Exception[] childExceptions = testField(s, field, escape, profileID);
                        for (int k = 0; k < childExceptions.length; k++) {
                            childExceptions[k].setFieldPosition(i);
                        }
                        addToList(childExceptions, exList);
                    }
                } catch (HL7Exception he) {
                    exList.add(new ProfileNotHL7CompliantException("Field " + i + " not found in message"));
                }
            }
            
        }
        
        //complain about X fields with content
        this.addToList(checkForExtraFields(segment, allowedFields), exList);
        
        HL7Exception[] ret = toArray(exList);        
        for (int i = 0; i < ret.length; i++) {
            ret[i].setSegmentName(profile.getName());
        }
        return ret;
    }
    
    /**
     * Checks a segment against a list of allowed fields 
     * (ie those mentioned in the profile with usage other than X).  Returns 
     * a list of exceptions representing field that appear 
     * but are not supposed to.  
     * @param allowedFields an array of Integers containing field #s of allowed fields
     */
    private HL7Exception[] checkForExtraFields(Segment segment, ArrayList<Integer> allowedFields) throws ProfileException {
        ArrayList<HL7Exception> exList = new ArrayList<HL7Exception>();
        for (int i = 1; i <= segment.numFields(); i++) {
            if (!allowedFields.contains(new Integer(i))) {
                try {
                    Type[] reps = segment.getField(i);
                    for (int j = 0; j < reps.length; j++) {
                        if (hasContent(reps[j])) {
                            HL7Exception e =  new XElementPresentException(
                                "Field " + i + " in " + segment.getName() + " appears in the message but not in the profile");
                            exList.add(e);
                        }
                    }
                } catch (HL7Exception he) {
                    throw new ProfileException("Problem testing against profile", he);
                }
            }
        }     
        return this.toArray(exList);
    }
    
    /**
     * Tests a Type against the corresponding section of a profile.
     * @param encoded optional encoded form of type (if you want to specify this -- if null,  
     *      default pipe-encoded form is used to check length and constant val)
     */
    public HL7Exception[] testType(Type type, AbstractComponent profile, String encoded, String profileID) {
        ArrayList<HL7Exception> exList = new ArrayList<HL7Exception>();
        if (encoded == null) encoded = PipeParser.encode(type, this.enc);

        HL7Exception ue = testUsage(encoded, profile.getUsage(), profile.getName());
        if (ue != null) exList.add(ue);

        if ( !profile.getUsage().equals("X") ) {
            //check datatype
            String typeClass = type.getClass().getName();
            if (typeClass.indexOf("." + profile.getDatatype()) < 0) {
                typeClass = typeClass.substring(typeClass.lastIndexOf('.') + 1);
                exList.add(new ProfileNotHL7CompliantException("HL7 datatype " + typeClass + " doesn't match profile datatype " + profile.getDatatype()));
            }
            
            //check length
            if (encoded.length() > profile.getLength())
                exList.add(new ProfileNotFollowedException("The type " + profile.getName() + " has length " + encoded.length() + " which exceeds max of " + profile.getLength()));
            
            //check constant value
            if (profile.getConstantValue() != null && profile.getConstantValue().length() > 0) {
                if (!encoded.equals(profile.getConstantValue()))
                    exList.add(new ProfileNotFollowedException("'" + encoded + "' doesn't equal constant value of '" + profile.getConstantValue() + "'"));
            }
            
            HL7Exception[] te = testTypeAgainstTable(type, profile, profileID);
            for (int i = 0; i < te.length; i++) {
                exList.add(te[i]);
            }
        }
    
        return this.toArray(exList);
    }
    
    /**
     * Tests whether the given type falls within a maximum length.  
     * @return null of OK, an HL7Exception otherwise 
     */
    public HL7Exception testLength(Type type, int maxLength) {
        HL7Exception e = null;
        String encoded = PipeParser.encode(type, this.enc);
        if (encoded.length() > maxLength) {
            e = new ProfileNotFollowedException("Length of " + encoded.length() + " exceeds maximum of " + maxLength);
        }
        return e;
    }
    
    /**
     * Tests an element against the corresponding usage code.  The element 
     * is required in its encoded form.  
     * @param encoded the pipe-encoded message element 
     * @param usage the usage code (e.g. "CE")
     * @param name the name of the element (for use in exception messages)
     * @returns null if there is no problem, an HL7Exception otherwise 
     */
    private HL7Exception testUsage(String encoded, String usage, String name) {
        HL7Exception e = null;
        if (usage.equalsIgnoreCase("R")) {
            if (encoded.length() == 0) 
                e = new ProfileNotFollowedException("Required element " + name + " is missing");
        } else if (usage.equalsIgnoreCase("RE")) {
            //can't test anything 
        } else if (usage.equalsIgnoreCase("O")) {
            //can't test anything
        } else if (usage.equalsIgnoreCase("C")) {
            //can't test anything yet -- wait for condition syntax in v2.6 
        } else if (usage.equalsIgnoreCase("CE")) {
            //can't test anything
        } else if (usage.equalsIgnoreCase("X")) {
            if (encoded.length() > 0) 
                e = new XElementPresentException("Element " + name + " is present but specified as not used (X)");
        } else if (usage.equalsIgnoreCase("B")) {
            //can't test anything 
        }                
        return e;
    }
    
    /**
     * Tests table values for ID, IS, and CE types.  An empty list is returned for 
     * all other types or if the table name or number is missing.  
     */
    private HL7Exception[] testTypeAgainstTable(Type type, AbstractComponent profile, String profileID) {
        ArrayList<HL7Exception> exList = new ArrayList<HL7Exception>();
        if (profile.getTable() != null && (type.getName().equals("IS") || type.getName().equals("ID"))) {
            String codeSystem = makeTableName(profile.getTable());
            String value = ((Primitive) type).getValue();
            addTableTestResult(exList, profileID, codeSystem, value);
        } else if (type.getName().equals("CE")) {
            String value = Terser.getPrimitive(type, 1, 1).getValue();
            String codeSystem = Terser.getPrimitive(type, 3, 1).getValue();
            addTableTestResult(exList, profileID, codeSystem, value);

            value = Terser.getPrimitive(type, 4, 1).getValue();
            codeSystem = Terser.getPrimitive(type, 6, 1).getValue();
            addTableTestResult(exList, profileID, codeSystem, value);
        }
        return this.toArray(exList);
    }
    
    private void addTableTestResult(ArrayList<HL7Exception> exList, String profileID, String codeSystem, String value) {
        if (codeSystem != null && value != null) {
            HL7Exception e = testValueAgainstTable(profileID, codeSystem, value);
            if (e != null) exList.add(e);
        }        
    }
    
    private HL7Exception testValueAgainstTable(String profileID, String codeSystem, String value) {
        HL7Exception ret = null;
        CodeStore store = ProfileStoreFactory.getCodeStore(profileID, codeSystem);
        if (store == null) {
            log.warn("Not checking value {}: no code store was found for profile {} code system {}"
            		, new Object[] {value, profileID, codeSystem});
        } else {
            if (!store.isValidCode(codeSystem, value))
                ret = new ProfileNotFollowedException("Code " + value + " not found in table " + codeSystem + ", profile " + profileID);
        }
        return ret;
    }
    
    private String makeTableName(String hl7Table) {
        StringBuffer buf = new StringBuffer("HL7");
        if (hl7Table.length() < 4) buf.append("0");
        if (hl7Table.length() < 3) buf.append("0");
        if (hl7Table.length() < 2) buf.append("0");
        buf.append(hl7Table);
        return buf.toString();
    }
    
    public HL7Exception[] testField(Type type, Field profile, boolean escape, String profileID) throws ProfileException {
        ArrayList<HL7Exception> exList = new ArrayList<HL7Exception>(20);
        
        //account for MSH 1 & 2 which aren't escaped
        String encoded = null;
        if (!escape && Primitive.class.isAssignableFrom(type.getClass())) encoded = ((Primitive) type).getValue();
        
        addToList(testType(type, profile, encoded, profileID), exList);
        
        //test children
        if (profile.getComponents() > 0 && !profile.getUsage().equals("X")) {
            if (Composite.class.isAssignableFrom(type.getClass())) {
                Composite comp = (Composite) type;
                for (int i = 1; i <= profile.getComponents(); i++) {
                    Component childProfile = profile.getComponent(i);
                    try {
                        Type child = comp.getComponent(i-1);
                        addToList(testComponent(child, childProfile, profileID), exList);
                    } catch (DataTypeException de) {
                        exList.add(new ProfileNotHL7CompliantException("More components in profile than allowed in message: " + de.getMessage()));
                    }
                }
                addToList(checkExtraComponents(comp, profile.getComponents()), exList);
            } else {
                exList.add(new ProfileNotHL7CompliantException(
                "A field has type primitive " + type.getClass().getName() + " but the profile defines components"));
            }
        }
        
        return toArray(exList);
    }
    
    public HL7Exception[] testComponent(Type type, Component profile, String profileID) throws ProfileException {
        ArrayList<HL7Exception> exList = new ArrayList<HL7Exception>(20);
        
        addToList(testType(type, profile, null, profileID), exList);
        
        //test children
        if (profile.getSubComponents() > 0 && !profile.getUsage().equals("X") && hasContent(type)) {
            if (Composite.class.isAssignableFrom(type.getClass())) {
                Composite comp = (Composite) type;
                for (int i = 1; i <= profile.getSubComponents(); i++) {
                    SubComponent childProfile = profile.getSubComponent(i);
                    try {
                        Type child = comp.getComponent(i-1);
                        addToList(testType(child, childProfile, null, profileID), exList);
                    } catch (DataTypeException de) {
                        exList.add(new ProfileNotHL7CompliantException("More subcomponents in profile than allowed in message: " + de.getMessage()));
                    }
                }
                addToList(checkExtraComponents(comp, profile.getSubComponents()), exList);
            } else {
                exList.add(new ProfileNotFollowedException(
                "A component has primitive type " + type.getClass().getName() + " but the profile defines subcomponents"));
            }
        }
        
        return toArray(exList);
    }
    
    /** Tests for extra components (ie any not defined in the profile) */
    private HL7Exception[] checkExtraComponents(Composite comp, int numInProfile) throws ProfileException {
        ArrayList<HL7Exception> exList = new ArrayList<HL7Exception>(20);
        
        StringBuffer extra = new StringBuffer();
        for (int i = numInProfile; i < comp.getComponents().length; i++) {
            try {
                String s = PipeParser.encode(comp.getComponent(i), enc);
                if (s.length() > 0) {
                    extra.append(s);
                    extra.append(enc.getComponentSeparator());
                }
            } catch (DataTypeException de) {
                throw new ProfileException("Problem testing against profile", de);
            }
        }
        
        if (extra.toString().length() > 0) {
            exList.add(new XElementPresentException("The following components are not defined in the profile: " + extra.toString()));
        }
        
        return toArray(exList);
    }
    
    /**
     * Tests a composite against the corresponding section of a profile.
     */
    /*public HL7Exception[] testComposite(Composite comp, AbstractComposite profile) {
    }*/
    
    /**
     * Tests a primitive datatype against a profile.  Tests include
     * length, datatype, whether the profile defines any children
     * (this would indicate an error), constant value if defined.
     * Table values are not verified.
     */
    /*public Hl7Exception[] testPrimitive(Primitive, AbstractComponent profile) {
     
    }*/
    
    /** Returns true is there is content in the given structure */
    private boolean hasContent(Structure struct) throws HL7Exception {
        if (Group.class.isAssignableFrom(struct.getClass())) {
            return hasContent( (Group) struct );
        } else if (Segment.class.isAssignableFrom(struct.getClass())) {
            return hasContent( (Segment) struct );
        } else {
            throw new HL7Exception("Structure " + struct.getClass().getName() + " not recognized", HL7Exception.APPLICATION_INTERNAL_ERROR);
        }
    }
    
    /** Returns true is there is content in the given group */
    private boolean hasContent(Group group) throws HL7Exception {
        boolean has = false;
        String encoded = PipeParser.encode(group, enc);
        if (encoded.indexOf('|') >= 0) has = true;
        return has;
    }
    
    /** Returns true is there is content in the given segment */
    private boolean hasContent(Segment segment) {
        boolean has = false;
        String encoded = PipeParser.encode(segment, enc);
        if (encoded != null && encoded.length() > 3) has = true;
        return has;
    }
    
    /** Returns true is there is content in the given type */
    private boolean hasContent(Type type) {
        boolean has = false;
        String encoded = PipeParser.encode(type, enc);
        if (encoded != null && encoded.length() > 0) has = true;
        return has;
    }
    
    /** Appends an array of HL7 exceptions to a list */
    private void addToList(HL7Exception[] exceptions, ArrayList<HL7Exception> list) {
        for (int i = 0; i < exceptions.length; i++) {
            list.add(exceptions[i]);
        }
    }
    
    /** Returns the HL7 exceptions in the given ArrayList<HL7Exception> in an array */
    private HL7Exception[] toArray(ArrayList<HL7Exception> list) {
        return (HL7Exception[]) list.toArray(new HL7Exception[0]);
    }
    
    public static void main(String args[]) {
        
        if (args.length != 2) {
            System.out.println("Usage: DefaultValidator message_file profile_file");
            System.exit(1);
        }
        
        DefaultValidator val = new DefaultValidator();
        try {
            String msgString = loadFile(args[0]);
            Parser parser = new GenericParser();
            Message message = parser.parse(msgString);
            
            String profileString = loadFile(args[1]);
            ProfileParser profParser = new ProfileParser(true);
            RuntimeProfile profile = profParser.parse(profileString);
            
            HL7Exception[] exceptions = val.validate(message, profile.getMessage());
            
            System.out.println("Exceptions: "); 
            for (int i = 0; i < exceptions.length; i++) {
                System.out.println((i + 1) + ". " + exceptions[i].getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /** loads file at the given path */
    private static String loadFile(String path) throws IOException {
        File file = new File(path);
        //char[] cbuf = new char[(int) file.length()];
        BufferedReader in = new BufferedReader(new FileReader(file));
        StringBuffer buf = new StringBuffer(5000);
        int c = -1;
        while ( (c = in.read()) != -1) {
            buf.append( (char) c );
        }
        //in.read(cbuf, 0, (int) file.length());
        in.close();
        //return String.valueOf(cbuf);
        return buf.toString();
    }
    
}
