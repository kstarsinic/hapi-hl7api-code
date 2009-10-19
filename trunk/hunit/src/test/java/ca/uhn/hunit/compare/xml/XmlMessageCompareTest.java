/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.uhn.hunit.compare.xml;

import ca.uhn.hunit.ex.UnexpectedTestFailureException;
import ca.uhn.hunit.iface.TestMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 *
 * @author James
 */
public class XmlMessageCompareTest {

    private XmlMessageCompare myCompare;
    private Log myLog = LogFactory.getLog(getClass());

    @Before
    public void setUp() {
        myCompare = new XmlMessageCompare();
    }

    @Test
    public void testCompareIdentical() throws UnexpectedTestFailureException {
        String expected = "<test><child1>content1</child1><child2>content2</child2></test>";
        String actual = "<test><child1>content1</child1><child2>content2</child2></test>";

        myCompare.compare(new TestMessage<Document>(expected), new TestMessage<Document>(actual));

        Assert.assertTrue(myCompare.describeDifference(), myCompare.isSame());
    }

    @Test
    public void testCompareSameWithWhitespace() throws UnexpectedTestFailureException {
        String expected = "<test><child1>content1</child1><child2>content2</child2></test>";
        String actual = "<test>\r\n  <child1>content1</child1>\r\n  <child2>content2</child2>\r\n</test>\r\n";

        myCompare.compare(new TestMessage<Document>(expected), new TestMessage<Document>(actual));

        Assert.assertTrue(myCompare.describeDifference(), myCompare.isSame());
    }


    @Test
    public void testDifferentSameWithWhitespace() throws UnexpectedTestFailureException {
        String expected = "<test><child1>content1</child1><child2>content2</child2></test>";
        String actual = "<test>\r\n  <child1>content2</child1>\r\n  <child2>content1</child2>\r\n</test>\r\n";

        myCompare.compare(new TestMessage<Document>(expected), new TestMessage<Document>(actual));

        Assert.assertFalse(myCompare.isSame());

        myLog.info(myCompare.describeDifference());
    }

}
