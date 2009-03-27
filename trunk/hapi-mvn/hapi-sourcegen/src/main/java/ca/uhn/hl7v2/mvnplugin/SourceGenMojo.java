/**
The contents of this file are subject to the Mozilla Public License Version 1.1 
(the "License"); you may not use this file except in compliance with the License. 
You may obtain a copy of the License at http://www.mozilla.org/MPL/ 
Software distributed under the License is distributed on an "AS IS" basis, 
WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the 
specific language governing rights and limitations under the License. 

The Original Code is "DataTypeGenerator.java".  Description: 
"Generates skeletal source code for Datatype classes based on the 
  HL7 database" 

The Initial Developer of the Original Code is University Health Network. Copyright (C) 
2001.  All Rights Reserved. 

Contributor(s):  James Agnew 

Alternatively, the contents of this file may be used under the terms of the 
GNU General Public License (the  �GPL�), in which case the provisions of the GPL are 
applicable instead of those above.  If you wish to allow use of your version of this 
file only under the terms of the GPL and not to allow others to use your version 
of this file under the MPL, indicate your decision by deleting  the provisions above 
and replace  them with the notice and other provisions required by the GPL License.  
If you do not delete the provisions above, a recipient may use your version of 
this file under either the MPL or the GPL. 

*/

package ca.uhn.hl7v2.mvnplugin;

import java.util.HashSet;
import java.util.Set;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import ca.uhn.hl7v2.database.NormativeDatabase;
import ca.uhn.hl7v2.sourcegen.SourceGenerator;

/**
 * Maven Plugin Mojo for generating HAPI HL7 message/segment/etc source files
 * 
 * @author <a href="mailto:jamesagnew@sourceforge.net">James Agnew</a>
 * @goal sourcegen
 * @phase generate-sources
 * @requiresDependencyResolution runtime
 * @requiresProject
 * @inheritedByDefault false
 */
public class SourceGenMojo extends AbstractMojo
{
    private static final Set alreadyMade = new HashSet();
    
    /**
     * The maven project.
     * 
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    
    /**
     * The target directory for the generated source
     * 
     * @parameter
     * @required
     */
    private String targetDirectory;

    /**
     * The version for the generated source
     * 
     * @parameter
     * @required
     */
    private String version;
    
    
    /**
     * The JDBC URL for the HL7 database
     * 
     * @parameter
     * @required
     */
    private String jdbcUrl;

    /**
     * The JDBC User for the HL7 database
     * 
     * @parameter
     * @required
     */
    private String jdbcUser;

    /**
     * The JDBC Password for the HL7 database
     * 
     * @parameter
     * @required
     */
    private String jdbcPassword;

    
    /**
     * {@inheritDoc}
     */
    public void execute() throws MojoExecutionException, MojoFailureException {

        if (!alreadyMade.contains(version)) {
            // I haven't entirely figured out why, but Maven runs this several times for each version, which takes forever
            alreadyMade.add(version);
            
           System.setProperty(NormativeDatabase.PROP_DATABASE_USER, jdbcUser);
           System.setProperty(NormativeDatabase.PROP_DATABASE_PASSWORD, jdbcPassword);
           System.setProperty(NormativeDatabase.PROP_DATABASE_URL, jdbcUrl);
            
            SourceGenerator.makeAll(targetDirectory, version);
            
        } else {
            getLog().warn("Already made version " + version + ", skipping!");
        }
        
        project.addCompileSourceRoot(targetDirectory);
        
    }

}
