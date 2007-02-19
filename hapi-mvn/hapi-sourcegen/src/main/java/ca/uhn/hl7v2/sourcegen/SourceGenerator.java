/**
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the
 * specific language governing rights and limitations under the License.
 *
 * The Original Code is "SourceGenerator.java".  Description:
 * "Manages automatic generation of HL7 API source code for all data types,
 * segments, groups, and message structures"
 *
 * The Initial Developer of the Original Code is University Health Network. Copyright (C)
 * 2001.  All Rights Reserved.
 *
 * Contributor(s): ______________________________________.
 *
 * Alternatively, the contents of this file may be used under the terms of the
 * GNU General Public License (the  �GPL�), in which case the provisions of the GPL are
 * applicable instead of those above.  If you wish to allow use of your version of this
 * file only under the terms of the GPL and not to allow others to use your version
 * of this file under the MPL, indicate your decision by deleting  the provisions above
 * and replace  them with the notice and other provisions required by the GPL License.
 * If you do not delete the provisions above, a recipient may use your version of
 * this file under either the MPL or the GPL.
 *
 */

package ca.uhn.hl7v2.sourcegen;

import java.util.StringTokenizer;
import java.io.File;
import java.io.IOException;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.parser.Parser;

/**
 * <p>Manages automatic generation of HL7 API source code for all data types,
 * segments, groups, and message structures. </p>
 * <p>Note: should put a nice UI on this</p>
 * @author Bryan Tripp (bryan_tripp@sourceforge.net)
 */
public class SourceGenerator extends Object {
    
    /** Creates new SourceGenerator */
    public SourceGenerator() {
    }
    
    /**
     * Generates source code for all data types, segments, groups, and messages.
     * @param baseDirectory the directory where source should be written
     */
    public static void makeAll(String baseDirectory, String version) {
        //load driver and set DB URL
        /*if (System.getProperty("ca.on.uhn.hl7.database.url") == null) {
            System.setProperty("ca.on.uhn.hl7.database.url", "jdbc:odbc:hl7");
        }*/
        
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            DataTypeGenerator.makeAll(baseDirectory, version);
            SegmentGenerator.makeAll(baseDirectory, version);
            MessageGenerator.makeAll(baseDirectory, version);
            // group and message not implemented
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Make a Java-ish accessor method name out of a field or component description
     * by removing non-letters and adding "get".  One complication is that some description
     * entries in the DB have their data types in brackets, and these should not be
     * part of the method name.  On the other hand, sometimes critical distinguishing
     * information is in brackets, so we can't omit everything in brackets.  The approach
     * taken here is to eliminate bracketed text if a it looks like a data type.
     */
    public static String makeAccessorName(String fieldDesc) {
        StringBuffer aName = new StringBuffer("get");
        char[] chars = fieldDesc.toCharArray();
        boolean lastCharWasNotLetter = true;
        int inBrackets = 0;
        StringBuffer bracketContents = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(' ) inBrackets++;
            if (chars[i] == ')' ) inBrackets--;
            
            if (Character.isLetterOrDigit(chars[i])) {
                if (inBrackets > 0) {
                    //buffer everthing in brackets
                    bracketContents.append(chars[i]);
                } else {
                    //add capitalized bracketed text if appropriate 
                    if (bracketContents.length() > 0) {
                        aName.append(capitalize(filterBracketedText(bracketContents.toString())));
                        bracketContents = new StringBuffer();
                    }
                    if (lastCharWasNotLetter) {
                        //first letter of each word is upper-case
                        aName.append(Character.toUpperCase(chars[i]));
                    } else {
                        aName.append(chars[i]);
                    }
                    lastCharWasNotLetter = false;
                }
            } else {
                lastCharWasNotLetter = true;
            }
        }
        aName.append(capitalize(filterBracketedText(bracketContents.toString())));        
        return aName.toString();
    }
    
    /**
     * Bracketed text in a field description should be included in the accessor 
     * name unless it corresponds to a data type name. Given the text that appears in 
     * brackets in a field description, this method returns an empty string if it 
     * corresponds to a data type name, or returns original text if not.  It isn't 
     * convenient to actually check (e.g. with DataTypeGenerator) whether the given 
     * text actually corresponds to a data type name, so we are going to conclude that 
     * it is a data type if and only if it is all caps and has 2 or 3 characters.  
     */
    private static String filterBracketedText(String text) {
        String filtered = "";
        boolean isDataType = true;
        if (!text.equals(text.toUpperCase())) isDataType = false;
        if (text.length() < 2 || text.length() > 3) isDataType = false;

        if (!isDataType) filtered = text;
        return filtered;
    }
    
    /** Capitalizes first character of the given text. */
    private static String capitalize(String text) {
        StringBuffer cap = new StringBuffer();
        if (text.length() > 0) {
            cap.append(Character.toUpperCase(text.charAt(0)));
            cap.append(text.substring(1, text.length()));
        }
        return cap.toString();
    }
    
    /**
     * Creates the given directory if it does not exist.
     */
    public static File makeDirectory(String directory) throws IOException {
        StringTokenizer tok = new StringTokenizer(directory, "\\/", false);
        
        File currDir = null;
        while (tok.hasMoreTokens()) {
            String thisDirName = tok.nextToken();
            
            currDir = new File(currDir, thisDirName); //if currDir null, defaults to 1 arg call
            
            if (!currDir.exists()) {
                //create
                currDir.mkdir();;
            } else if (currDir.isFile()) {
                throw new IOException("Can't create directory " + thisDirName +
                " - file with same name exists.");
            }
        }
        
        return currDir;
    }
    
    /**
     * <p>Returns either the given data type name or an alternate data type that Composites
     * and Segments should use in place of the given Type.  </p>
     * <p>As currently implemented, substitutions
     * may be desired if there is a validating subclass of the given Type.
     * By convention such classes would be named ValidX (where X is the Type name).  This
     * method checks the classpath for classes of this name in the datatype package and
     * returns this name if one is found.</p>
     * <p>Also converts "varies" to Varies which is an implementation of Type that contains
     * a Type that can be set at run-time.</p>
     */
    public static String getAlternateType(String dataTypeName, String version) {
        String ret = dataTypeName;
        
        //convert to varies to Varies
        if (ret.equals("varies")) ret = "Varies";
        
        //Valid.. classes are removed as of HAPI 0.3 (validating code implemented directly in Primitive classes
        /*try {
            Class.forName(getVersionPackageName(version) + "datatype.Valid" + dataTypeName);
            ret = "Valid" + dataTypeName;
        } catch (Exception e) {
            // fine ... there isn't a ValidX implementation
            // I don't like using Class.forName here but I don't know a better way to
            // search for the class 
        }*/
        
        return ret;
    }
    
    /**
     * Returns the path to the base package for model elements of the given version
     * - e.g. "ca/uhn/hl7v2/model/v24/".
     * This package should have the packages datatype, segment, group, and message
     * under it. The path ends in with a slash.
     */
    public static String getVersionPackagePath(String ver) throws HL7Exception {
        if (Parser.validVersion(ver) == false) { 
            throw new HL7Exception("The HL7 version " + ver + " is not recognized", HL7Exception.UNSUPPORTED_VERSION_ID);
        }
        StringBuffer path = new StringBuffer("ca/uhn/hl7v2/model/v");
        char[] versionChars = new char[ver.length()];
        ver.getChars(0, ver.length(), versionChars, 0);
        for (int i = 0; i < versionChars.length; i++) {
            if (versionChars[i] != '.') path.append(versionChars[i]);
        }
        path.append('/');
        return path.toString();
    }
    
    /**
     * Returns the package name for model elements of the given version - e.g.
     * "ca.uhn.hl7v2.model.v24.".  This method
     * is identical to <code>getVersionPackagePath(...)</code> except that path
     * separators are replaced with dots.
     */
    public static String getVersionPackageName(String ver) throws HL7Exception {
        String path = getVersionPackagePath(ver);
        String packg = path.replace('/', '.');
        packg = packg.replace('\\', '.');
        return packg;
    }
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: SourceGenerator base_directory version");
            System.exit(1);
        }
        makeAll(args[0], args[1]);
    }
    
}
