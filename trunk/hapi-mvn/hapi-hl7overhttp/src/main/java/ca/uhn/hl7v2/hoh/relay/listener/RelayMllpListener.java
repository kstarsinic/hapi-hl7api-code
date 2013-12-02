package ca.uhn.hl7v2.hoh.relay.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.app.SimpleServer;
import ca.uhn.hl7v2.hoh.util.Validate;
import ca.uhn.hl7v2.llp.MinLowerLayerProtocol;
import ca.uhn.hl7v2.parser.GenericModelClassFactory;
import ca.uhn.hl7v2.protocol.ApplicationRouter.AppRoutingData;
import ca.uhn.hl7v2.protocol.ReceivingApplication;

public class RelayMllpListener implements InitializingBean, DisposableBean, IRelayListener, BeanNameAware {

	private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(RelayMllpListener.class);
	private List<ReceivingApplication> myApplications = new ArrayList<ReceivingApplication>();
	private List<AppRoutingData> myAppRoutingData = new ArrayList<AppRoutingData>();
	private String myBeanName;
	private int myPort;
	private SimpleServer myServer;
	private AtomicInteger threadNum	= new AtomicInteger(1);

	/**
	 * Fired automatically by the container when
	 * the bean is ready to start
	 */
	public void afterPropertiesSet() throws Exception {
		if (myPort <= 0) {
			throw new IllegalStateException("Port not set");
		}
		
		DefaultHapiContext ctx = new DefaultHapiContext();
		ctx.setExecutorService(Executors.newCachedThreadPool(new MyThreadFactory()));
		ctx.setLowerLayerProtocol(new MinLowerLayerProtocol(true));
		ctx.setModelClassFactory(new GenericModelClassFactory());
		myServer = ctx.newServer(myPort, false);

		for (int i = 0; i < myAppRoutingData.size(); i++) {
			myServer.registerApplication(myAppRoutingData.get(i), myApplications.get(i));
		}
		
		ourLog.info("Starting listener on port {}", myPort);
		myServer.startAndWait();
		ourLog.info("Listener on port {} has started, and is ready for processing", myPort);

		if (myServer.getServiceExitedWithException() != null) {
			Throwable ex = myServer.getServiceExitedWithException();
			ourLog.error("Server failed to start", ex);
			if (ex instanceof Exception) {
				throw (Exception) ex;
			} else {
				throw new Error(ex);
			}
		}

	}

	
	/**
	 * Fired automatically by the container when
	 * the bean is shutting down
	 */
	public void destroy() throws Exception {
		ourLog.info("Stopping listener on port {}", myPort);
		myServer.stopAndWait();
		ourLog.info("Listener on port {} has stopped", myPort);
	}

	public String getBeanName() {
		return myBeanName;
	}


	public void registerApplication(AppRoutingData theAppRouting, ReceivingApplication theReceivingApplication) {
		Validate.notNull(theAppRouting, "appRouting");
		Validate.notNull(theReceivingApplication, "receivingApplication");
		
		if (myServer != null) {
			myServer.registerApplication(theAppRouting, theReceivingApplication);
		} else {
			myAppRoutingData.add(theAppRouting);
			myApplications.add(theReceivingApplication);
		}
	}


	public void setBeanName(String theBeanName) {
		myBeanName = theBeanName;
	}

	public void setPort(int thePort) {
		myPort = thePort;
	}

	private class MyThreadFactory implements ThreadFactory {

		private ThreadGroup group;

		private MyThreadFactory() {
			group = Thread.currentThread().getThreadGroup();
		}

		public Thread newThread(Runnable theR) {
			String name = "hoh-port-" + myPort + "-worker-" + threadNum.getAndIncrement();
			Thread t = new Thread(group, theR, name, 0);
			if (t.isDaemon()) {
				t.setDaemon(false);
			}
			if (t.getPriority() != Thread.NORM_PRIORITY) {
				t.setPriority(Thread.NORM_PRIORITY);
			}
			return t;
		}

	}


}
