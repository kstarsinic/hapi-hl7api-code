package ca.uhn.hl7v2.model.v23.datatype;

import ca.uhn.hl7v2.model.Composite;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.model.AbstractType;
import ca.uhn.log.HapiLogFactory;

/**
 * <p>The HL7 CM_PI (person identifier) data type.  Consists of the following components: </p><ol>
 * <li>ID number (ST) (ST)</li>
 * <li>type of ID number (IS) (IS)</li>
 * <li>other qualifying info (ST)</li>
 * </ol>
 */
public class CM_PI extends AbstractType implements Composite {

	private Type[] data;

	/**
	 * Creates a CM_PI.
	 * @param message the Message to which this Type belongs
	 */
	public CM_PI(Message message) {
		super(message);
		data = new Type[3];
		data[0] = new ST(message);
		data[1] = new IS(message, 0);
		data[2] = new ST(message);
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
			throw new DataTypeException("Element " + number + " doesn't exist in 3 element CM_PI composite"); 
		} 
	} 
	/**
	 * Returns ID number (ST) (component #0).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getIDNumber() {
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
	 * Returns type of ID number (IS) (component #1).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public IS getTypeOfIDNumber() {
	   IS ret = null;
	   try {
	      ret = (IS)getComponent(1);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns other qualifying info (component #2).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getOtherQualifyingInfo() {
	   ST ret = null;
	   try {
	      ret = (ST)getComponent(2);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}