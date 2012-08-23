/**
The contents of this file are subject to the Mozilla Public License Version 1.1 
(the "License"); you may not use this file except in compliance with the License. 
You may obtain a copy of the License at http://www.mozilla.org/MPL/ 
Software distributed under the License is distributed on an "AS IS" basis, 
WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the 
specific language governing rights and limitations under the License. 

The Original Code is "DBTableRepository.java".  Description: 
"Implements TableRepository by looking up values from the default HL7
  normative database" 

The Initial Developer of the Original Code is University Health Network. Copyright (C) 
2001.  All Rights Reserved. 

Contributor(s): ______________________________________. 

Alternatively, the contents of this file may be used under the terms of the 
GNU General Public License (the  �GPL�), in which case the provisions of the GPL are 
applicable instead of those above.  If you wish to allow use of your version of this 
file only under the terms of the GPL and not to allow others to use your version 
of this file under the MPL, indicate your decision by deleting  the provisions above 
and replace  them with the notice and other provisions required by the GPL License.  
If you do not delete the provisions above, a recipient may use your version of 
this file under either the MPL or the GPL. 
*/
package ca.uhn.hl7v2.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Arrays;

import junit.framework.TestCase;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.parser.mock.MockClassLoader;


/**
 * Test the package list loading from the classpath.  
 * 
 * @author <a href="mailto:alexei.guevara@uhn.on.ca">Alexei Guevara</a>
 * @version $Revision: 1.1 $ updated on $Date: 2007-02-19 02:24:49 $ by $Author: jamesagnew $
 */
public class ParserPackageLoadingTest extends TestCase {
    
    private String myPackageListResourceNameTemplate;

	public ParserPackageLoadingTest( String theTestCaseName ) throws Exception {
        super( theTestCaseName );
        
        //avoid code duplication using the private accessor.
    	myPackageListResourceNameTemplate = DefaultModelClassFactory.CUSTOM_PACKAGES_RESOURCE_NAME_TEMPLATE;
        
    }
    
	/*
	 * not a test case
	 */
    public void testPackageList( String theVersion ) throws HL7Exception, NoSuchFieldException {
    	
    	String versionPackage1 = "foo.bar.1"; 
    	String versionPackage2 = "foo.bar.2";
    	String versionPackage3 = "foo.bar.3";
    	
    	String packageListResourceName = 
    		MessageFormat.format( myPackageListResourceNameTemplate, new Object[] { theVersion } );
    	 
    	InputStream packageListStream =
	    	new ByteArrayInputStream(
	    		(versionPackage1 + "\n" +
				 versionPackage2 + "\n" +
				 versionPackage3).getBytes()
	    	);
    	
    	ClassLoader mockClassLoader = new MockClassLoader( packageListResourceName, packageListStream );
        
        Thread.currentThread().setContextClassLoader( mockClassLoader );
        
        DefaultModelClassFactory.reloadPackages();
        String[] packages = DefaultModelClassFactory.packageList( theVersion );
        
        String[] expectedPackages = 
        	new String[] { 
        		versionPackage1, 
        		versionPackage2, 
        		versionPackage3, 
        		DefaultModelClassFactory.getVersionPackageName(theVersion) };
        
        assertEquals( Arrays.asList(expectedPackages), Arrays.asList(packages) );
    }
    
    public void setUp() throws NoSuchFieldException {
    	//clear the Parser.packages class variable
    	// PrivateAccessor.setField( DefaultModelClassFactory.class, "packages", null );
    }
    
    public void testPackageListV2_1() throws Exception {
    	testPackageList( "2.1" );
    }

    public void testPackageListV2_2() throws Exception {
    	testPackageList( "2.2" );
    }

    public void testPackageListV2_3() throws Exception {
    	testPackageList( "2.3" );
    }

    public void testPackageListV2_3_1() throws Exception {
    	testPackageList( "2.3.1" );
    }

    public void testPackageListV2_4() throws Exception {
    	testPackageList( "2.4" );
    }
    
    public void testPackageListV2_5() throws Exception {
    	testPackageList( "2.5" );
    }
    
    
    
}
