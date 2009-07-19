package ca.uhn.hunit.util;

import ca.uhn.hunit.iface.AbstractInterface;
import ca.uhn.hunit.test.TestBatteryImpl;

public class Log {

	public void info(AbstractInterface theInterface, String theMessage) {
		info("INTERFACE[" + theInterface.getId() + "] " + theMessage);
	}
	
	private void info(String theMessage) {
		System.out.println("[INFO]  " + theMessage);
	}

	private void error(String theMessage) {
		System.out.println("[ERROR]  " + theMessage);
	}

	public void info(TestBatteryImpl theTestBatteryImpl,
			String theMessage) {
		info("BATTERY[" + theTestBatteryImpl.getName() + "] " + theMessage);
	}

	public void error(TestBatteryImpl theTestBatteryImpl,
			String theMessage) {
		error("BATTERY[" + theTestBatteryImpl.getName() + "] " + theMessage);
	}
	
}
