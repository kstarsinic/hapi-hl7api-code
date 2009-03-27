package ca.uhn.hl7v2.model.v25.datatype;

import ca.uhn.hl7v2.model.Composite;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.model.AbstractType;
import ca.uhn.log.HapiLogFactory;

/**
 * <p>The HL7 FN (Family Name) data type.  Consists of the following components: </p><ol>
 * <li>Surname (ST)</li>
 * <li>Own Surname Prefix (ST)</li>
 * <li>Own Surname (ST)</li>
 * <li>Surname Prefix From Partner/Spouse (ST)</li>
 * <li>Surname From Partner/Spouse (ST)</li>
 * </ol>
 */
public class FN extends AbstractType implements Composite {

	private Type[] data;

	/**
	 * Creates a FN.
	 * @param message the Message to which this Type belongs
	 */
	public FN(Message message) {
		super(message);
		data = new Type[5];
		data[0] = new ST(message);
		data[1] = new ST(message);
		data[2] = new ST(message);
		data[3] = new ST(message);
		data[4] = new ST(message);
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
			throw new DataTypeException("Element " + number + " doesn't exist in 5 element FN composite"); 
		} 
	} 
	/**
	 * Returns Surname (component #0).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getSurname() {
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
	 * Returns Own Surname Prefix (component #1).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getOwnSurnamePrefix() {
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
	 * Returns Own Surname (component #2).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getOwnSurname() {
	   ST ret = null;
	   try {
	      ret = (ST)getComponent(2);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns Surname Prefix From Partner/Spouse (component #3).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getSurnamePrefixFromPartnerSpouse() {
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
	 * Returns Surname From Partner/Spouse (component #4).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getSurnameFromPartnerSpouse() {
	   ST ret = null;
	   try {
	      ret = (ST)getComponent(4);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}