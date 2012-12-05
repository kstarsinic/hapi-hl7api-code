package ca.uhn.hl7v2.hoh.relay.sender;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.hoh.api.DecodeException;
import ca.uhn.hl7v2.hoh.api.EncodeException;
import ca.uhn.hl7v2.hoh.api.IReceivable;
import ca.uhn.hl7v2.hoh.hapi.api.MessageSendable;
import ca.uhn.hl7v2.hoh.hapi.client.HohClientMultithreaded;
import ca.uhn.hl7v2.hoh.util.Validate;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.protocol.ApplicationRouter;
import ca.uhn.hl7v2.protocol.ReceivingApplicationException;
import ca.uhn.hl7v2.util.Terser;

public class HttpSender extends HohClientMultithreaded implements ISender, BeanNameAware, InitializingBean {

	private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(HttpSender.class);
	private String myBeanName;
	
	/**
	 * {@inheritDoc}
	 */
	public void afterPropertiesSet() throws Exception {
		Validate.propertySet(getUrl(), "Url");
		ourLog.info("Sender [{}] will transmit by HL7 over HTTP to {}", myBeanName, getUrl().toExternalForm());
	}

	/**
	 * Returns true
	 * 
	 * {@inheritDoc}
	 */
	public boolean canProcess(Message theMessage) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getBeanName() {
		return myBeanName;
	}

	/**
	 * {@inheritDoc}
	 */
	public Message processMessage(Message theMessage, Map<String, Object> theMetadata) throws ReceivingApplicationException, HL7Exception {
		String sendingIp = (String) theMetadata.get(ApplicationRouter.METADATA_KEY_SENDING_IP);
		String controlId = (String) theMetadata.get(ApplicationRouter.METADATA_KEY_MESSAGE_CONTROL_ID);

		ourLog.info("Relaying message with ID {} from {} to URL {}", new Object[] {controlId, sendingIp, getUrl()});
		
		IReceivable<Message> response;
		long delay = System.currentTimeMillis();
		try {
			response = sendAndReceiveMessage(new MessageSendable(theMessage));
			delay = System.currentTimeMillis() - delay;
		} catch (DecodeException e) {
			throw new HL7Exception(e);
		} catch (IOException e) {
			throw new HL7Exception(e);
		} catch (EncodeException e) {
			throw new HL7Exception(e);
		}
		
		String responseControlId = new Terser(response.getMessage()).get("/MSH-10");
		ourLog.info("Received response to ID {} with ID {} in {} ms", new Object[] {controlId, responseControlId, delay});
		
		return response.getMessage();
	}

	/**
	 * <i>Automatically called by the container</i>
	 */
	public void setBeanName(String theBeanName) {
		myBeanName = theBeanName;
	}


}
