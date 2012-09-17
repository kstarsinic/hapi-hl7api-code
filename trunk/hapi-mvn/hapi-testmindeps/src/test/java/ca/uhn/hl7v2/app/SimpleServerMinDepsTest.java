package ca.uhn.hl7v2.app;

import static org.junit.Assert.*;

import java.net.InetSocketAddress;
import java.net.Socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.uhn.hl7v2.llp.HL7Reader;
import ca.uhn.hl7v2.llp.HL7Writer;
import ca.uhn.hl7v2.llp.LowerLayerProtocol;
import ca.uhn.hl7v2.llp.MinLowerLayerProtocol;
import ca.uhn.hl7v2.parser.GenericModelClassFactory;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.RandomServerPortProvider;

public class SimpleServerMinDepsTest {

	private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(SimpleServerMinDepsTest.class);
	private int myPort;

	private SimpleServer mySs;

	@After
	public void after() {
		mySs.stopAndWait();
	}

	@Before
	public void before() throws InterruptedException {
		myPort = RandomServerPortProvider.findFreePort();
		LowerLayerProtocol llp = new MinLowerLayerProtocol();
		mySs = new SimpleServer(myPort, llp, new PipeParser(new GenericModelClassFactory()));
		mySs.startAndWait();
	}
	
	/**
	 * Ensure we can handle a message for a version that is not
	 * on the classpath
	 */
	@Test
	public void testSendAndReceiveV23Message() throws Exception {
		
		Socket s = new Socket();
		s.connect(new InetSocketAddress("localhost", myPort));
		
		MinLowerLayerProtocol llp = new MinLowerLayerProtocol();
		HL7Writer w = llp.getWriter(s.getOutputStream());
		HL7Reader r = llp.getReader(s.getInputStream());
		
		// AIL is non-standard
		String msgText = "MSH|^~\\&|IDX|XXXX|COMMON|EXTERNAL|200608140653||ADT^A04|60491_4054_SC1|P|2.3\r" + 
				"PID|||868063820614||||^003|F||1|^^^^84606|||||S\r" + 
				"PV1||O||10||||||||||||||||||||||||||||||||||||||||200501091835\r" + 
				"AIL|1||871|10|\r" + 
				"DG1||||RASH ON BACK AND RT LEG\r" + 
				"IN1||||||||||||||||||||||||||||||||||||||||||||";
		w.writeMessage(msgText);
		String resp = r.getMessage();
		
		ourLog.info("Response was:\n{}", resp.replace('\r', '\n'));
		
		assertTrue(resp, resp.contains("|2.3"));
		assertTrue(resp, resp.contains("60491_4054_SC1"));
		
		
		
	}

	/**
	 * Ensure we can handle a message for a version that doesn't exist
	 */
	@Test
	public void testSendAndReceiveV299Message() throws Exception {
		
		Socket s = new Socket();
		s.connect(new InetSocketAddress("localhost", myPort));
		
		MinLowerLayerProtocol llp = new MinLowerLayerProtocol();
		HL7Writer w = llp.getWriter(s.getOutputStream());
		HL7Reader r = llp.getReader(s.getInputStream());
		
		// AIL is non-standard
		String msgText = "MSH|^~\\&|IDX|XXXX|COMMON|EXTERNAL|200608140653||ADT^A04|60491_4054_SC1|P|2.9999\r" + 
				"PID|||868063820614||||^003|F||1|^^^^84606|||||S\r" + 
				"PV1||O||10||||||||||||||||||||||||||||||||||||||||200501091835\r" + 
				"AIL|1||871|10|\r" + 
				"DG1||||RASH ON BACK AND RT LEG\r" + 
				"IN1||||||||||||||||||||||||||||||||||||||||||||";
		w.writeMessage(msgText);
		String resp = r.getMessage();
		
		ourLog.info("Response was:\n{}", resp.replace('\r', '\n'));
		
		assertTrue(resp, resp.contains("|2.3"));
		assertTrue(resp, resp.contains("60491_4054_SC1"));
		
		
		
	}

}
