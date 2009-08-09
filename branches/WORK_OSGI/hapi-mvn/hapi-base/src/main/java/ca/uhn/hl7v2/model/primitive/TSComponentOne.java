/**
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the
 * specific language governing rights and limitations under the License.
 *
 * The Original Code is "TSComponentOne.java".  Description:
 * "Represents an HL7 timestamp, which is related to the HL7 TS type."
 *
 * The Initial Developer of the Original Code is University Health Network. Copyright (C)
 * 2005.  All Rights Reserved.
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
 */

package ca.uhn.hl7v2.model.primitive;

import ca.uhn.hl7v2.model.AbstractPrimitive;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;

/**
 * Represents an HL7 timestamp, which is related to the HL7 TS type.  In version 2.5, 
 * TS is a composite type.  The first component is type DTM, which corresponds to this class
 * (actually model.v25.datatype.DTM inherits from this class at time of writing).  In HL7 versions 
 * 2.2-2.4, it wasn't perfectly clear whether TS was composite or primitive.  HAPI interprets  
 * it as composite, with the first component having a type that isn't defined by HL7, and we call 
 * this type TSComponentOne.  In v2.1, TS is primitive, and corresponds one-to-one with this class.   
 *  
 * @author <a href="mailto:neal.acharya@uhn.on.ca">Neal Acharya</a>
 * @author <a href="mailto:bryan.tripp@uhn.on.ca">Bryan Tripp</a>
 * @version $Revision: 1.1 $ updated on $Date: 2007-02-19 02:24:51 $ by $Author: jamesagnew $
 */
public abstract class TSComponentOne extends AbstractPrimitive {

    private CommonTS myDetail;
    
    /**
     * @param theMessage message to which this Type belongs
     */
    public TSComponentOne(Message theMessage) {
        super(theMessage);
    }

    private CommonTS getDetail() throws DataTypeException {
        if (myDetail == null) {
            myDetail = new CommonTS(getValue());
        }
        return myDetail;
    }
    
    /**
     * @see AbstractPrimitive#setValue(java.lang.String)
     * @throws DataTypeException if the value is incorrectly formatted and either validation is 
     *      enabled for this primitive or detail setters / getters have been called, forcing further
     *      parsing.   
     */
    public void setValue(String theValue) throws DataTypeException {
        super.setValue(theValue);
        
        if (myDetail != null) {
            myDetail.setValue(theValue);
        }
    }
    
    /**
     * @see AbstractPrimitive#getValue
     */
    public String getValue() {
        String result = super.getValue();
        
        if (myDetail != null) {
            result = myDetail.getValue();
        }
        
        return result;
    }
    
    /**
     * @see CommonTS#setDatePrecision(int, int, int)
     * @throws DataTypeException if the value is incorrectly formatted.  If validation is enabled, this 
     *      exception should be thrown at setValue(), but if not, detailed parsing may be deferred until 
     *      this method is called.  
     */
    public void setDatePrecision(int yr, int mnth, int dy) throws DataTypeException {
        getDetail().setDatePrecision(yr,mnth,dy);        
    }
    
    /**
     * @see CommonTS#setDateMinutePrecision(int, int, int, int, int)
     * @throws DataTypeException if the value is incorrectly formatted.  If validation is enabled, this 
     *      exception should be thrown at setValue(), but if not, detailed parsing may be deferred until 
     *      this method is called.  
     */
    public void setDateMinutePrecision(int yr, int mnth, int dy, int hr, int min) throws DataTypeException {
        getDetail().setDateMinutePrecision(yr,mnth,dy,hr,min);        
    }
    
    /**
     * @see CommonTS#setDateSecondPrecision(int, int, int, int, int, float)
     * @throws DataTypeException if the value is incorrectly formatted.  If validation is enabled, this 
     *      exception should be thrown at setValue(), but if not, detailed parsing may be deferred until 
     *      this method is called.  
     */
    public void setDateSecondPrecision(int yr, int mnth, int dy, int hr, int min, float sec) throws DataTypeException {
        getDetail().setDateSecondPrecision(yr,mnth,dy,hr,min,sec);        
    }
    
    /**
     * @see CommonTS#setOffset(int)
     * @throws DataTypeException if the value is incorrectly formatted.  If validation is enabled, this 
     *      exception should be thrown at setValue(), but if not, detailed parsing may be deferred until 
     *      this method is called.  
     */
    public void setOffset(int signedOffset) throws DataTypeException {
        getDetail().setOffset(signedOffset);        
    }
    
    /**
     * Returns the year as an integer.
     * @throws DataTypeException if the value is incorrectly formatted.  If validation is enabled, this 
     *      exception should be thrown at setValue(), but if not, detailed parsing may be deferred until 
     *      this method is called.  
     */
    public int getYear() throws DataTypeException {
        return getDetail().getYear();
    }
    
    /**
     * Returns the month as an integer.
     * @throws DataTypeException if the value is incorrectly formatted.  If validation is enabled, this 
     *      exception should be thrown at setValue(), but if not, detailed parsing may be deferred until 
     *      this method is called.  
     */
    public int getMonth() throws DataTypeException {
        return getDetail().getMonth();
    }
    
    /**
     * Returns the day as an integer.
     * @throws DataTypeException if the value is incorrectly formatted.  If validation is enabled, this 
     *      exception should be thrown at setValue(), but if not, detailed parsing may be deferred until 
     *      this method is called.  
     */
    public int getDay() throws DataTypeException {
        return getDetail().getDay();
    }
    
    /**
     * Returns the hour as an integer.
     * @throws DataTypeException if the value is incorrectly formatted.  If validation is enabled, this 
     *      exception should be thrown at setValue(), but if not, detailed parsing may be deferred until 
     *      this method is called.  
     */
    public int getHour() throws DataTypeException {
        return getDetail().getHour();
    }
    
    /**
     * Returns the minute as an integer.
     * @throws DataTypeException if the value is incorrectly formatted.  If validation is enabled, this 
     *      exception should be thrown at setValue(), but if not, detailed parsing may be deferred until 
     *      this method is called.  
     */
    public int getMinute() throws DataTypeException {
       return getDetail().getMinute();
    }
    
    /**
     * Returns the second as an integer.
     * @throws DataTypeException if the value is incorrectly formatted.  If validation is enabled, this 
     *      exception should be thrown at setValue(), but if not, detailed parsing may be deferred until 
     *      this method is called.  
     */
    public int getSecond() throws DataTypeException {
        return getDetail().getSecond();
    }
    
    /**
     * Returns the fractional second value as a float.
     * @throws DataTypeException if the value is incorrectly formatted.  If validation is enabled, this 
     *      exception should be thrown at setValue(), but if not, detailed parsing may be deferred until 
     *      this method is called.  
     */
    public float getFractSecond() throws DataTypeException {
        return getDetail().getFractSecond();
    }
    
    /**
     * Returns the GMT offset value as an integer.
     * @throws DataTypeException if the value is incorrectly formatted.  If validation is enabled, this 
     *      exception should be thrown at setValue(), but if not, detailed parsing may be deferred until 
     *      this method is called.  
     */
    public int getGMTOffset() throws DataTypeException {
        return getDetail().getGMTOffset();
    }
    
    /** Returns the name of the type (used in XML encoding and profile checking)  */
//    public String getName() {
//        return "NM"; //seems to be called an NM in XML representation prior to 2.5  
//    }
    
}
