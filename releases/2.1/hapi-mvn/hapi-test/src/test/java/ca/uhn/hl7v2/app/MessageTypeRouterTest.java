package ca.uhn.hl7v2.app;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.uhn.hl7v2.model.*;
import ca.uhn.hl7v2.util.*;

/**
 * JUnit test harness for MessageTypeRouter
 * @author Bryan Tripp
 */
@SuppressWarnings("deprecation")
public class MessageTypeRouterTest {

	private MessageTypeRouter router;
    
    @Before
    public void setUp() {
        router = new MessageTypeRouter();
        DefaultApplication app = new DefaultApplication();
        router.registerApplication("ADT", "A01", app);
    }
    
	@Test
    public void testRegisterApplication() throws Exception {
        Message ack = new ca.uhn.hl7v2.model.v24.message.ACK();
        Terser t = new Terser(ack);
        t.set("/MSH-9-1", "ACK");
        assertEquals(false, router.canProcess(ack));
        t.set("/MSH-9-1", "ADT");
        t.set("/MSH-9-2-", "A01");
        assertEquals(true, router.canProcess(ack));
    }

	@Test
    public void testProcessMessage() throws Exception {
        Message in = new ca.uhn.hl7v2.model.v24.message.ACK();        
        Terser t = new Terser(in);
        t.set("/MSH-9-1", "ADT");
        t.set("/MSH-9-2", "A01");
        t.set("/MSH-12", "2.4");
        Message out = router.processMessage(in);
        Terser tout = new Terser(out);
        assertEquals("AR", tout.get("/MSA-1"));
    }
}
