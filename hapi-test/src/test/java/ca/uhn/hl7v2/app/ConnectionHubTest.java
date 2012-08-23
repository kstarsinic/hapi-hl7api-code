package ca.uhn.hl7v2.app;

import static ca.uhn.hl7v2.app.TestUtils.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.concurrent.DefaultExecutorService;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.llp.LowerLayerProtocol;
import ca.uhn.hl7v2.llp.MinLowerLayerProtocol;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.ADT_A45;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.util.RandomServerPortProvider;
import ca.uhn.hl7v2.util.SocketFactory;

/**
 * JUnit test harmess for ConnectionHub connecting to a SimpleServer
 * 
 * @author Bryan Tripp
 * @author Christian Ohr
 */
public class ConnectionHubTest {

	private static final Logger ourLog = LoggerFactory
			.getLogger(ConnectionHubTest.class);
	private static int port1;
	private static int port2;
	private static SimpleServer ss1;
	private static SimpleServer ss2;
	private static ConnectionHub hub;

	@BeforeClass
	public static void beforeClass() throws Exception {
		port1 = RandomServerPortProvider.findFreePort();
		port2 = RandomServerPortProvider.findFreePort();
		ss1 = new SimpleServer(port1, LowerLayerProtocol.makeLLP(),
				new PipeParser());
		ss1.registerApplication("*", "*", new MyApp());
		ss1.start();
		ss2 = new SimpleServer(port2, LowerLayerProtocol.makeLLP(),
				new PipeParser());
		ss2.registerApplication("*", "*", new MyApp());
		ss2.start();
		hub = ConnectionHub.getInstance();
		
		// Give the servers time to start up
		Thread.sleep(1000);
		
	}

	@After
	public void tearDown() {
		hub.discardAll();
	}

	@AfterClass
	public static void afterClass() throws Exception {
		ss1.stopAndWait();
		ss2.stopAndWait();
		ConnectionHub.shutdown();
	}

	@Test
	public void testConnectWithTlsSocketFactory() throws HL7Exception, IOException {
		ConnectionHub connHub = new DefaultHapiContext().getConnectionHub();
		
		SocketFactory socketFactory = mock(SocketFactory.class);
		when(socketFactory.createTlsSocket()).thenReturn(javax.net.SocketFactory.getDefault().createSocket());
		
		Connection conn = connHub.attach("localhost", port1, PipeParser.getInstanceWithNoValidation(), new MinLowerLayerProtocol(), true, socketFactory);
		conn.close();

		verify(socketFactory, times(1)).createTlsSocket();
		verifyNoMoreInteractions(socketFactory);

	}
	
	@Test
	public void testConnectWithSocketFactory() throws HL7Exception, IOException {
		ConnectionHub connHub = new DefaultHapiContext().getConnectionHub();
		
		SocketFactory socketFactory = mock(SocketFactory.class);
		when(socketFactory.createSocket()).thenReturn(javax.net.SocketFactory.getDefault().createSocket());
		
		Connection conn = connHub.attach("localhost", port1, PipeParser.getInstanceWithNoValidation(), new MinLowerLayerProtocol(), false, socketFactory);
		conn.close();

		verify(socketFactory, times(1)).createSocket();
		verifyNoMoreInteractions(socketFactory);

	}
	
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAttach() throws Exception {
		PipeParser pipeParser = new PipeParser();
		Connection i1 = hub.attach("localhost", port1, pipeParser,
				MinLowerLayerProtocol.class);
		Connection i1again = hub.attach("localhost", port1, pipeParser,
				MinLowerLayerProtocol.class);
		assertTrue(i1 == i1again);
		Connection i2 = hub.attach("localhost", port2, pipeParser,
				MinLowerLayerProtocol.class);
		assertTrue(i2 != i1again);
		assertEquals(2, hub.allConnections().size());
		hub.allConnections().contains(i1);
		hub.allConnections().contains(i2);
		hub.detach(i1);
		assertTrue(i1again.isOpen());
		hub.allConnections().contains(i1again);
		hub.detach(i1again);
		assertFalse(i1again.isOpen());
		hub.detach(i2);
		assertEquals(0, hub.allConnections().size());
		Connection i1OnceMore = hub.attach("localhost", port1, pipeParser,
				MinLowerLayerProtocol.class);
		assertTrue(i1 != i1OnceMore);
		hub.detach(i1OnceMore);
		assertEquals(0, hub.allConnections().size());
	}

	@Test
	public void testAttachSequentially() throws Exception {

		int n = 3;
		long now = System.currentTimeMillis();
		final PipeParser pipeParser = new PipeParser();
		final int myfreePort = RandomServerPortProvider.findFreePort();

		Callable<Long> t = new Callable<Long>() {
			public Long call() {
				long time = 0;
				try {
					time = System.currentTimeMillis();
					hub.attach("localhost", myfreePort, pipeParser,
							MinLowerLayerProtocol.class);
				} catch (HL7Exception e) {
					time = System.currentTimeMillis() - time;
				}
				return time;
			}
		};
		List<Future<Long>> results = DefaultExecutorService.getDefaultService()
				.invokeAll(fill(t, n));
		Future<Long> fastestResult = Collections.min(results,
				new Comparator<Future<Long>>() {

					public int compare(Future<Long> o1, Future<Long> o2) {
						try {
							return o1.get().compareTo(o2.get());
						} catch (Exception e) {
							return 0; // should never happen
						}
					}
				});
		long elapsed = System.currentTimeMillis() - now;
		// Due to synchronization, the threads are executed almost sequentially
		assertTrue(elapsed > fastestResult.get() * (n - 1));
		assertEquals(0, hub.allConnections().size());
	}

	@Test
	public void testAttachConcurrently() throws Exception {

		int n = 5;
		long now = System.currentTimeMillis();
		final PipeParser pipeParser = new PipeParser();

		Callable<Long> t = new Callable<Long>() {
			public Long call() {
				long time = 0;
				try {
					time = System.currentTimeMillis();
					int port = RandomServerPortProvider.findFreePort();
					ourLog.info("Attaching to non-existent port {}.", port);
					hub.attach("localhost", port, pipeParser,
							MinLowerLayerProtocol.class);
				} catch (HL7Exception e) {
					time = System.currentTimeMillis() - time;
				}
				return time;
			}
		};
		List<Future<Long>> results = DefaultExecutorService.getDefaultService()
				.invokeAll(fill(t, n));
		Future<Long> fastestResult = Collections.min(results,
				new Comparator<Future<Long>>() {

					public int compare(Future<Long> o1, Future<Long> o2) {
						try {
							return o1.get().compareTo(o2.get());
						} catch (Exception e) {
							return 0; // should never happen
						}
					}
				});
		long elapsed = System.currentTimeMillis() - now;
		
		// TODO find some portable way to check that the connection attempts happened concurrently 
		ourLog.info("Elapsed test time was {} ms, the fastest connection time was {} ms.", elapsed, fastestResult.get());
		for (Future<Long> next : results) {
			ourLog.info("Elapsed thread connection time was {} ms.", next.get());
		}
		
		assertEquals(0, hub.allConnections().size());
	}
	
	@Test
	public void testAttachToExistingServer() throws Exception {

		int n = 100;
		final PipeParser pipeParser = new PipeParser();

		Callable<Connection> t = new Callable<Connection>() {
			public Connection call() {
				try {
					return hub.attach("localhost", port1, pipeParser,
							MinLowerLayerProtocol.class);
				} catch (HL7Exception e) {
					return null;
				}
			}
		};
		List<Future<Connection>> results = DefaultExecutorService.getDefaultService()
				.invokeAll(fill(t, n));
		assertEquals(n, results.size());
		Connection unique = results.iterator().next().get();
		assertNotNull(unique);
		for (Future<Connection> future : results) {
			assertTrue(unique == future.get());
		}
		assertEquals(1, hub.allConnections().size());
	}	

	@Test
	public void testAttachToNonexistingServer() throws Exception {
		PipeParser pipeParser = new PipeParser();
		int freePort = RandomServerPortProvider.findFreePort();
		try {
			hub.attach("localhost", RandomServerPortProvider.findFreePort(), pipeParser,
					MinLowerLayerProtocol.class);
			fail("Shouldn't be a service running at " + freePort);
		} catch (Exception e) {
		}
	}

	@Test
	public void testDetachTooOften() throws Exception {
		PipeParser pipeParser = new PipeParser();
		Connection i1 = hub.attach("localhost", port1, pipeParser,
				MinLowerLayerProtocol.class);
		assertTrue(i1.isOpen());
		hub.detach(i1);
		assertFalse(i1.isOpen());
		hub.detach(i1); // just ignore
	}

	/**
	 * Make sure that connection hub doesn't try to reuse a connection which is
	 * already closed
	 * 
	 * @throws IOException
	 * @throws LLPException
	 */
	@Test
	public void testConnectionClosedExternally() throws HL7Exception,
			LLPException, IOException {

		PipeParser pipeParser = new PipeParser();
		Connection i1 = hub.attach("localhost", port1, pipeParser,
				MinLowerLayerProtocol.class);

		String messageText = "MSH|^~\\&|4265-ADT|4265|eReferral|eReferral|201004141020||ADT^A45^ADT_A45|102416|T^|2.5^^|||NE|AL|CAN|8859/1\r"
				+ "EVN|A45|201004141020|\r"
				+ "PID|1||7010226^^^4265^MR~0000000000^^^CANON^JHN^^^^^^GP~1736465^^^4265^VN||Park^Green^^^MS.^^L||19890812|F|||123 TestingLane^^TORONTO^CA-ON^M5G2C2^CAN^H^~^^^^^^^||^PRN^PH^^1^416^2525252^|^^^^^^^||||||||||||||||N\r"
				+ "PV1|1|I||||^^^WP^1469^^^^^^^^|||||||||||^Derkach^Peter.^^^Dr.||20913000131|||||||||||||||||||||||||201004011340|201004141018";
		ADT_A45 msg = new ADT_A45();
		msg.setParser(pipeParser);
		msg.parse(messageText);
		i1.getInitiator().sendAndReceive(msg);
		i1.close(); // PROBLEM: connection still in hub table
		System.out.println("Connection closed 1");

		Connection i1again = hub.attach("localhost", port1, pipeParser,
				MinLowerLayerProtocol.class);
		assertTrue(i1 != i1again);
		i1again.getInitiator().sendAndReceive(msg);
		i1again.close();
		System.out.println("Connection closed 2");
	}

	@Test
	public void testDiscard() throws Exception {
		PipeParser pipeParser = new PipeParser();
		Connection i1 = hub.attach("localhost", port1, pipeParser,
				MinLowerLayerProtocol.class);
		hub.attach("localhost", port1, pipeParser, MinLowerLayerProtocol.class);
		hub.discard(i1);
		Connection i1thrice = hub.attach("localhost", port1, pipeParser,
				MinLowerLayerProtocol.class);
		assertTrue(i1thrice != i1);
		hub.discard(i1thrice);
	}

	@Test
	public void testDiscardAll() throws Exception {
		PipeParser pipeParser = new PipeParser();
		hub.attach("localhost", port1, pipeParser, MinLowerLayerProtocol.class);
		hub.attach("localhost", port2, pipeParser, MinLowerLayerProtocol.class);
		hub.discardAll();
		assertEquals(0, hub.allConnections().size());
	}





	public static class MyApp implements Application {

		public boolean canProcess(Message theIn) {
			return true;
		}

		public Message processMessage(Message theIn)
				throws ApplicationException, HL7Exception {
			ourLog.info("Received: " + theIn.encode());
			try {
				return theIn.generateACK();
			} catch (IOException e) {
				throw new HL7Exception(e);
			}
		}

	}

}
