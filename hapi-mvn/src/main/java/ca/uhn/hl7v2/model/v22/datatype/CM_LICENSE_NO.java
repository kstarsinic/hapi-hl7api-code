package ca.uhn.hl7v2.model.v22.datatype;

import ca.uhn.hl7v2.model.Composite;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.model.AbstractType;
import ca.uhn.log.HapiLogFactory;

/**
 * <p>The HL7 CM_LICENSE_NO (CM f�r F�hrerscheinlizenzen) data type.  Consists of the following components: </p><ol>
 * <li>License Number (ST)</li>
 * <li>issuing state,province,country (ST)</li>
 * </ol>
 */
public class CM_LICENSE_NO extends AbstractType implements Composite {

	private Type[] data;

	/**
	 * Creates a CM_LICENSE_NO.
	 * @param message the Message to which this Type belongs
	 */
	public CM_LICENSE_NO(Message message) {
		super(message);
		data = new Type[2];
		data[0] = new ST(message);
		data[1] = new ST(message);
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
			throw new DataTypeException("Element " + number + " doesn't exist in 2 element CM_LICENSE_NO composite"); 
		} 
	} 
	/**
	 * Returns License Number (component #0).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getLicenseNumber() {
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
	 * Returns issuing state,province,country (component #1).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getIssuingStateProvinceCountry() {
	   ST ret = null;
	   try {
	      ret = (ST)getComponent(1);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}