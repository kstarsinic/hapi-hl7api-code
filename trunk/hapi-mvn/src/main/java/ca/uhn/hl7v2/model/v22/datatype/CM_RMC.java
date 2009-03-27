package ca.uhn.hl7v2.model.v22.datatype;

import ca.uhn.hl7v2.model.Composite;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.model.AbstractType;
import ca.uhn.log.HapiLogFactory;

/**
 * <p>The HL7 CM_RMC (Room Coverage) data type.  Consists of the following components: </p><ol>
 * <li>room type (ID)</li>
 * <li>amount type (ID)</li>
 * <li>coverage amount (NM)</li>
 * </ol>
 */
public class CM_RMC extends AbstractType implements Composite {

	private Type[] data;

	/**
	 * Creates a CM_RMC.
	 * @param message the Message to which this Type belongs
	 */
	public CM_RMC(Message message) {
		super(message);
		data = new Type[3];
		data[0] = new ID(message, 0);
		data[1] = new ID(message, 0);
		data[2] = new NM(message);
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
			throw new DataTypeException("Element " + number + " doesn't exist in 3 element CM_RMC composite"); 
		} 
	} 
	/**
	 * Returns room type (component #0).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ID getRoomType() {
	   ID ret = null;
	   try {
	      ret = (ID)getComponent(0);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns amount type (component #1).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ID getAmountType() {
	   ID ret = null;
	   try {
	      ret = (ID)getComponent(1);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns coverage amount (component #2).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public NM getCoverageAmount() {
	   NM ret = null;
	   try {
	      ret = (NM)getComponent(2);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}