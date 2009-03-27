package ca.uhn.hl7v2.model.v25.datatype;

import ca.uhn.hl7v2.model.Composite;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.model.AbstractType;
import ca.uhn.log.HapiLogFactory;

/**
 * <p>The HL7 RFR (Reference Range) data type.  Consists of the following components: </p><ol>
 * <li>Numeric Range (NR)</li>
 * <li>Administrative Sex (IS)</li>
 * <li>Age Range (NR)</li>
 * <li>Gestational Age Range (NR)</li>
 * <li>Species (ST)</li>
 * <li>Race/subspecies (ST)</li>
 * <li>Conditions (TX)</li>
 * </ol>
 */
public class RFR extends AbstractType implements Composite {

	private Type[] data;

	/**
	 * Creates a RFR.
	 * @param message the Message to which this Type belongs
	 */
	public RFR(Message message) {
		super(message);
		data = new Type[7];
		data[0] = new NR(message);
		data[1] = new IS(message, 1);
		data[2] = new NR(message);
		data[3] = new NR(message);
		data[4] = new ST(message);
		data[5] = new ST(message);
		data[6] = new TX(message);
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
			throw new DataTypeException("Element " + number + " doesn't exist in 7 element RFR composite"); 
		} 
	} 
	/**
	 * Returns Numeric Range (component #0).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public NR getNumericRange() {
	   NR ret = null;
	   try {
	      ret = (NR)getComponent(0);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns Administrative Sex (component #1).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public IS getAdministrativeSex() {
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
	 * Returns Age Range (component #2).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public NR getAgeRange() {
	   NR ret = null;
	   try {
	      ret = (NR)getComponent(2);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns Gestational Age Range (component #3).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public NR getGestationalAgeRange() {
	   NR ret = null;
	   try {
	      ret = (NR)getComponent(3);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns Species (component #4).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getSpecies() {
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
	 * Returns Race/subspecies (component #5).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public ST getRaceSubspecies() {
	   ST ret = null;
	   try {
	      ret = (ST)getComponent(5);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

	/**
	 * Returns Conditions (component #6).  This is a convenience method that saves you from 
	 * casting and handling an exception.
	 */
	public TX getConditions() {
	   TX ret = null;
	   try {
	      ret = (TX)getComponent(6);
	   } catch (DataTypeException e) {
	      HapiLogFactory.getHapiLog(this.getClass()).error("Unexpected problem accessing known data type component - this is a bug.", e);
	      throw new RuntimeException(e);
	   }
	   return ret;
	}

}