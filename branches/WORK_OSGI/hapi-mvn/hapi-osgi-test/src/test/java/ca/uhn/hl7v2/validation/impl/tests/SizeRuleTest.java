/*
 * Created on 7-Jun-2005
 */
package ca.uhn.hl7v2.validation.impl.tests;

import static org.ops4j.pax.exam.CoreOptions.equinox;
import static org.ops4j.pax.exam.CoreOptions.felix;
import static org.ops4j.pax.exam.CoreOptions.frameworks;
import static org.ops4j.pax.exam.CoreOptions.knopflerfish;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;
import static org.ops4j.pax.exam.CoreOptions.wrappedBundle;
import static org.ops4j.pax.exam.container.def.PaxRunnerOptions.logProfile;
import static org.junit.Assert.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Inject;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.osgi.framework.BundleContext;

import ca.uhn.hl7v2.validation.impl.SizeRule;

/**
 * Unit tests for SizeRule.
 * 
 * @author <a href="mailto:bryan.tripp@uhn.on.ca">Bryan Tripp</a>
 * @version $Revision: 1.1.2.1 $ updated on $Date: 2009-08-27 01:41:56 $ by $Author:
 *          jamesagnew $
 * @author Niranjan Sharma niranjan.sharma@med.ge.com This testcase has been
 *         extended for OSGI environment using Junit4 and PAX-Exam.
 */
@RunWith(JUnit4TestRunner.class)
public class SizeRuleTest {
    
    // you get that because you "installed" a log profile in configuration.
    public Log logger = LogFactory.getLog(SizeRuleTest.class);
    
    @Inject
    BundleContext bundleContext;
    
    @Configuration
    public static Option[] configure() {
	return options(frameworks(equinox(), felix(), knopflerfish()), logProfile(), systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level").value("INFO"), mavenBundle().groupId("org.ops4j.pax.url").artifactId("pax-url-mvn").version("0.4.0"), wrappedBundle(mavenBundle().groupId("org.ops4j.base").artifactId("ops4j-base-util").version("0.5.3")), mavenBundle().groupId("ca.uhn.hapi").artifactId("hapi-osgi-base").version("1.0-beta1")
	// ,
	// vmOption("-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5006"
	// )
	
	);
    }
    
    private SizeRule myRule;
    
    @Before
    public void BeforeTheTest() throws Exception {
	
	myRule = new SizeRule(5);
    }
    
    @Test
    public void testCorrect() {
	String x = "xxxxxx";
	assertEquals(x, myRule.correct(x));
    }
    
    @Test
    public void testTest() {
	assertEquals(true, myRule.test(null));
	assertEquals(true, myRule.test("foo"));
	assertEquals(true, myRule.test("foooo"));
	assertEquals(false, myRule.test("fooooo"));
    }
    
}
