package ca.uhn.hunit.test;

import java.util.ArrayList;
import java.util.List;

import ca.uhn.hunit.ex.ConfigurationException;
import ca.uhn.hunit.ex.TestFailureException;
import ca.uhn.hunit.ex.UnexpectedTestFailureException;
import ca.uhn.hunit.run.ExecutionContext;
import ca.uhn.hunit.xsd.ExpectMessageAny;
import ca.uhn.hunit.xsd.SendMessageAny;
import ca.uhn.hunit.xsd.Test;

public class TestImpl {

	private String myName;
	private List<AbstractEvent> myEvents = new ArrayList<AbstractEvent>(); 
	
	public TestImpl(TestBatteryImpl theBattery, Test theConfig) throws ConfigurationException {
		myName = theConfig.getName();
		
		for (Object next : theConfig.getSendMessageOrExpectMessage()) {
			if (next instanceof SendMessageAny) {
				SendMessageAny nextSm = (SendMessageAny)next;
				if (nextSm.getHl7V2() != null) {
					myEvents.add(new Hl7V2SendMessageImpl(theBattery, nextSm.getHl7V2()));
				}
			} else if (next instanceof ExpectMessageAny) {
				ExpectMessageAny nextEm = (ExpectMessageAny) next;
				if (nextEm.getHl7V2Specific() != null) {
					myEvents.add(new Hl7V2ExpectSpecificMessageImpl(theBattery, nextEm.getHl7V2Specific()));
				}
				if (nextEm.getHl7V2Rules() != null) {
					myEvents.add(new Hl7V2ExpectRulesImpl(theBattery, nextEm.getHl7V2Rules()));
				}
				if (nextEm.getHl7V2Ack() != null) {
					myEvents.add(new Hl7V2ExpectRulesImpl(theBattery, nextEm.getHl7V2Ack()));
				}
			} else {
				throw new ConfigurationException("Unknown event type: " + next.getClass());
			}
		}
	}
	
	
	public void execute(ExecutionContext theCtx) throws TestFailureException {
		theCtx.getLog().info(this, "About to execute test");
		
		for (AbstractEvent next : myEvents) {
			try {
				next.execute(theCtx);
			} catch (TestFailureException e) {
				theCtx.getLog().error(this, "Test failed with message: " + e.getMessage());
				throw e;
			} catch (Exception e) {
				theCtx.getLog().error(this, "Test failed with message: " + e.getMessage());
				throw new UnexpectedTestFailureException(e);
			}
		}
		
		theCtx.getLog().info(this, "Test passed");
	}


	public String getName() {
		return myName;
	}
	
}
