/**
The contents of this file are subject to the Mozilla Public License Version 1.1 
(the "License"); you may not use this file except in compliance with the License. 
You may obtain a copy of the License at http://www.mozilla.org/MPL/ 
Software distributed under the License is distributed on an "AS IS" basis, 
WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the 
specific language governing rights and limitations under the License. 

The Original Code is "TrimLeadingWhitespaceRule.java".  Description: 
"Performs no validation but removes leading whitespace in the correct() method." 

The Initial Developer of the Original Code is University Health Network. Copyright (C) 
2004.  All Rights Reserved. 

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
package ca.uhn.hl7v2.validation.impl;

import ca.uhn.hl7v2.validation.PrimitiveTypeRule;

/**
 * Performs no validation but removes leading whitespace in the correct() method.
 *   
 * @author <a href="mailto:bryan.tripp@uhn.on.ca">Bryan Tripp</a>
 * @version $Revision: 1.1 $ updated on $Date: 2007-02-19 02:24:40 $ by $Author: jamesagnew $
 */
public class TrimLeadingWhitespace implements PrimitiveTypeRule {

    /** 
     * Removes leading whitespace. 
     * 
     * @see ca.uhn.hl7v2.validation.PrimitiveTypeRule#correct(java.lang.String)
     */
    public String correct(String value) {
        String trmValue = null;
        if (value != null) {
            char[] stringChr = value.toCharArray();
            for (int i=0; i < stringChr.length && trmValue == null; i++){
                if (!Character.isWhitespace(stringChr[i])){
                    trmValue = String.valueOf(stringChr,i,(stringChr.length - i));
                }
            }
        }
        return trmValue;
    }

    /** 
     * Returns true. 
     * @see ca.uhn.hl7v2.validation.PrimitiveTypeRule#test(java.lang.String)
     */
    public boolean test(String value) {
        return true;
    }

    /** 
     * @see ca.uhn.hl7v2.validation.Rule#getDescription()
     */
    public String getDescription() {
        return "Leading whitespace removed";
    }

    /** 
     * @see ca.uhn.hl7v2.validation.Rule#getSectionReference()
     */
    public String getSectionReference() {
        return null;
    }

}