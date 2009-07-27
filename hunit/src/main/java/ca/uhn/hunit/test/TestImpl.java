package ca.uhn.hunit.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import ca.uhn.hunit.ex.ConfigurationException;
import ca.uhn.hunit.xsd.ExpectMessageAny;
import ca.uhn.hunit.xsd.SendMessageAny;
import ca.uhn.hunit.xsd.Test;

public class TestImpl implements ITest {

	private String myName;
	private HashMap<String, ArrayList<AbstractEvent>> myEvents = new HashMap<String, ArrayList<AbstractEvent>>();
	private TestBatteryImpl myBattery; 
	
	public TestImpl(TestBatteryImpl theBattery, Test theConfig) throws ConfigurationException {
		myName = theConfig.getName();
		myBattery = theBattery;
		
		for (Object next : theConfig.getSendMessageOrExpectMessage()) {
			AbstractEvent event = null;
			
			if (next instanceof SendMessageAny) {
				SendMessageAny nextSm = (SendMessageAny)next;
				if (nextSm.getHl7V2() != null) {
					event = (new Hl7V2SendMessageImpl(theBattery, this, nextSm.getHl7V2()));
				}
			} else if (next instanceof ExpectMessageAny) {
				ExpectMessageAny nextEm = (ExpectMessageAny) next;
				if (nextEm.getHl7V2Specific() != null) {
					event = (new Hl7V2ExpectSpecificMessageImpl(theBattery, this, nextEm.getHl7V2Specific()));
				}
				if (nextEm.getHl7V2Rules() != null) {
					event = (new Hl7V2ExpectRulesImpl(theBattery, this, nextEm.getHl7V2Rules()));
				}
				if (nextEm.getHl7V2Ack() != null) {
					event = (new Hl7V2ExpectRulesImpl(theBattery, this, nextEm.getHl7V2Ack()));
				}
			} else {
				throw new ConfigurationException("Unknown event type: " + next.getClass());
			}
			
			if (event == null) {
				continue;
			}
			
			String interfaceId = event.getInterfaceId();
			if (!myEvents.containsKey(interfaceId)) {
				myEvents.put(interfaceId, new ArrayList<AbstractEvent>());
			}
			myEvents.get(interfaceId).add(event);
			
		}
	}
	
	



	/* (non-Javadoc)
	 * @see ca.uhn.hunit.test.ITest#getName()
	 */
	public String getName() {
		return myName;
	}


	public Set<String> getInterfacesUsed() {
		return myEvents.keySet();
	}


	public List<AbstractEvent> getEventsForInterface(String theInterfaceId) {
		return myEvents.get(theInterfaceId);
	}
	
}
