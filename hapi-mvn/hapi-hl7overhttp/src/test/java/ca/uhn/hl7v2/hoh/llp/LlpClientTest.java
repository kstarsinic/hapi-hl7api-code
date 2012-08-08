package ca.uhn.hl7v2.hoh.llp;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.ConnectionHub;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.hoh.auth.SingleCredentialClientCallback;
import ca.uhn.hl7v2.hoh.auth.SingleCredentialServerCallback;
import ca.uhn.hl7v2.hoh.util.RandomServerPortProvider;
import ca.uhn.hl7v2.hoh.util.ServerRoleEnum;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.ADT_A05;
import ca.uhn.hl7v2.parser.PipeParser;

public class LlpClientTest {

	private static Hl7OverHttpLowerLayerProtocol ourLlp;
	private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(LlpClientTest.class);
	private static int ourPort;
	private static SingleCredentialServerCallback ourServerCallback;
	private static TestServerSocketThread ourServerSocketThread;
	private static ExecutorService ourExecutor;

	@Test
	public void testSendMessageSimple() throws Exception {

		String message = // -
		"MSH|^~\\&|||||200803051508||ADT^A31|2|P|2.5\r" + // -
				"EVN||200803051509\r" + // -
				"PID|||ZZZZZZ83M64Z148R^^^SSN^SSN^^20070103\r"; // -
		ADT_A05 msg = new ADT_A05();
		msg.parse(message);

		Connection conn;
		try {
			DefaultHapiContext context = new DefaultHapiContext();
			context.setExecutorService(ourExecutor);
			ConnectionHub connectionHub = context.getConnectionHub();
			PipeParser pipeParser = PipeParser.getInstanceWithNoValidation();
			conn = connectionHub.attach("localhost", ourPort, pipeParser, ourLlp, false);
		} catch (Exception e) {
			throw new Exception(e.getMessage() + " - " + RandomServerPortProvider.list().toString(), e);
		}
		Initiator initiator = conn.getInitiator();
		Message response = initiator.sendAndReceive(msg);

		ourLog.info("Received response");

		assertEquals(message, ourServerSocketThread.getMessage());
		assertEquals(ourServerSocketThread.getReply().encode(), response.encode());

	}

	@AfterClass
	public static void afterClass() throws InterruptedException {
		ourLog.info("Marking done as true");
		ourServerSocketThread.done();
		ourExecutor.shutdown();
	}

	@BeforeClass
	public static void beforeClass() throws InterruptedException {
		ourPort = RandomServerPortProvider.findFreePort();

		ourLlp = new Hl7OverHttpLowerLayerProtocol(ServerRoleEnum.CLIENT);
		ourLlp.setAuthorizationCallback(new SingleCredentialClientCallback("hello", "hapiworld"));
		ourServerCallback = new SingleCredentialServerCallback("hello", "hapiworld");

		ourServerSocketThread = new TestServerSocketThread(ourPort, ourServerCallback);
		ourServerSocketThread.start();
		ourServerSocketThread.getLatch().await();
		
		ourExecutor = Executors.newCachedThreadPool();
		
		Thread.sleep(1000);
	}

}
