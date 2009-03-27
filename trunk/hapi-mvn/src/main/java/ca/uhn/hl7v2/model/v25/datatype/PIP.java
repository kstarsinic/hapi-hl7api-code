package ca.uhn.hl7v2.model.v25.datatype;

import ca.uhn.hl7v2.model.Composite;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.model.AbstractType;
import ca.uhn.log.HapiLogFactory;

/**
 * <p>The HL7 PIP (Practitioner Institutional Privileges) data type.  Consists of the following components: </p><ol>
 * <li>Privilege (CE)</li>
 * <li>Privilege Class (CE)</li>
 * <li>Expiration Date (DT)</li>
 * <li>Activation Date (DT)</li>
 * <li>Facility (EI)</li>
 * </ol>
 */
public class PIP extends AbstractType implements Composite {

	private Type[] data;

	/**
	 * Creates a PIP.
	 * @param message the Message to which this Type belongs
	 */
	public PIP(Message message) {
		super(message);
		data = new Type[5];
		data[0] = new CE(message);
		data[1] = new CE(message);
		data[2] = new DT(message);
		data[3] = new DT(message);
		data[4] = new EI(message);
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
			throw new DataTypeException("Element " + number + " doesn't exist in 5 element PIP composite"); 
		} 
	} 
	/**
	 * Returns Privilege (component #0).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public CE getPrivilege() {
	   CE ret = null;
	   try {
	      ret = (CE)getComponent(0);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns Privilege Class (component #1).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public CE getPrivilegeClass() {
	   CE ret = null;
	   try {
	      ret = (CE)getComponent(1);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns Expiration Date (component #2).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public DT getExpirationDate() {
	   DT ret = null;
	   try {
	      ret = (DT)getComponent(2);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns Activation Date (component #3).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public DT getActivationDate() {
	   DT ret = null;
	   try {
	      ret = (DT)getComponent(3);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns Facility (component #4).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public EI getFacility() {
	   EI ret = null;
	   try {
	      ret = (EI)getComponent(4);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}