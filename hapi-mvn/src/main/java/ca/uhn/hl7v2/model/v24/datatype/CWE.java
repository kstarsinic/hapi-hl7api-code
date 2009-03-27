package ca.uhn.hl7v2.model.v24.datatype;

import ca.uhn.hl7v2.model.Composite;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.model.AbstractType;
import ca.uhn.log.HapiLogFactory;

/**
 * <p>The HL7 CWE (coded with exceptions) data type.  Consists of the following components: </p><ol>
 * <li>identifier (ST) (ST)</li>
 * <li>text (ST)</li>
 * <li>name of coding system (IS)</li>
 * <li>alternate identifier (ST) (ST)</li>
 * <li>alternate text (ST)</li>
 * <li>name of alternate coding system (IS)</li>
 * <li>coding system version ID (ST)</li>
 * <li>alternate coding system version ID (ST)</li>
 * <li>original text (ST)</li>
 * </ol>
 */
public class CWE extends AbstractType implements Composite {

	private Type[] data;

	/**
	 * Creates a CWE.
	 * @param message the Message to which this Type belongs
	 */
	public CWE(Message message) {
		super(message);
		data = new Type[9];
		data[0] = new ST(message);
		data[1] = new ST(message);
		data[2] = new IS(message, 0);
		data[3] = new ST(message);
		data[4] = new ST(message);
		data[5] = new IS(message, 0);
		data[6] = new ST(message);
		data[7] = new ST(message);
		data[8] = new ST(message);
	}

	/**
	 * Returns an array containing the data elements.
	 */
	public Type[] getComponents() { 
		return this.data; 
	}

	/**
	 * Returns an individual data component.
	 * @throws DataTypeException if the given element number is out of range.
	 */
	public Type getComponent(int number) throws DataTypeException { 

		try { 
			return this.data[number]; 
		} catch (ArrayIndexOutOfBoundsException e) { 
			throw new DataTypeException("Element " + number + " doesn't exist in 9 element CWE composite"); 
		} 
	} 
	/**
	 * Returns identifier (ST) (component #0).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getIdentifier() {
	   ST ret = null;
	   try {
	      ret = (ST)getComponent(0);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns text (component #1).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getText() {
	   ST ret = null;
	   try {
	      ret = (ST)getComponent(1);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns name of coding system (component #2).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public IS getNameOfCodingSystem() {
	   IS ret = null;
	   try {
	      ret = (IS)getComponent(2);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns alternate identifier (ST) (component #3).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getAlternateIdentifier() {
	   ST ret = null;
	   try {
	      ret = (ST)getComponent(3);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns alternate text (component #4).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getAlternateText() {
	   ST ret = null;
	   try {
	      ret = (ST)getComponent(4);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns name of alternate coding system (component #5).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public IS getNameOfAlternateCodingSystem() {
	   IS ret = null;
	   try {
	      ret = (IS)getComponent(5);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns coding system version ID (component #6).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getCodingSystemVersionID() {
	   ST ret = null;
	   try {
	      ret = (ST)getComponent(6);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns alternate coding system version ID (component #7).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getAlternateCodingSystemVersionID() {
	   ST ret = null;
	   try {
	      ret = (ST)getComponent(7);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns original text (component #8).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getOriginalText() {
	   ST ret = null;
	   try {
	      ret = (ST)getComponent(8);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}