/**
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the
 * specific language governing rights and limitations under the License.
 *
 * The Original Code is "SegmentGenerator.java".  Description:
 * "This class is responsible for generating source code for HL7 segment objects"
 *
 * The Initial Developer of the Original Code is University Health Network. Copyright (C)
 * 2001.  All Rights Reserved.
 *
 * Contributor(s):  Eric Poiseau. 
 *
 * Alternatively, the contents of this file may be used under the terms of the
 * GNU General Public License (the  �GPL�), in which case the provisions of the GPL are
 * applicable instead of those above.  If you wish to allow use of your version of this
 * file only under the terms of the GPL and not to allow others to use your version
 * of this file under the MPL, indicate your decision by deleting  the provisions above
 * and replace  them with the notice and other provisions required by the GPL License.
 * If you do not delete the provisions above, a recipient may use your version of
 * this file under either the MPL or the GPL.
 *
 */
package ca.uhn.hl7v2.sourcegen;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.database.NormativeDatabase;
import ca.uhn.hl7v2.parser.DefaultModelClassFactory;
import ca.uhn.hl7v2.sourcegen.util.VelocityFactory;
import ca.uhn.log.HapiLog;
import ca.uhn.log.HapiLogFactory;

/**
 * This class is responsible for generating source code for HL7 segment objects.
 * Each automatically generated segment inherits from AbstractSegment.
 * 
 * @author Bryan Tripp (bryan_tripp@sourceforge.net)
 * @author Eric Poiseau
 */
public class SegmentGenerator extends java.lang.Object {

	private static final HapiLog log = HapiLogFactory.getHapiLog(SegmentGenerator.class);


	/**
	 * <p>Creates skeletal source code (without correct data structure but no business
	 * logic) for all segments found in the normative database.  </p>
	 */
	public static void makeAll(String baseDirectory, String version, String theTemplatePackage, String theFileExt) throws IOException, SQLException, HL7Exception {
		//make base directory
		if (!(baseDirectory.endsWith("\\") || baseDirectory.endsWith("/"))) {
			baseDirectory = baseDirectory + "/";
		}
		File targetDir = SourceGenerator.makeDirectory(baseDirectory + DefaultModelClassFactory.getVersionPackagePath(version) + "segment");

		ArrayList<String> segments = getSegmentNames(version);

		if (segments.size() == 0) {
			log.warn("No version " + version + " segments found in database " + System.getProperty("ca.on.uhn.hl7.database.url"));
		}

		for (int i = 0; i < segments.size(); i++) {
			try {
				String seg = (String) segments.get(i);
				makeSegment(seg, version, theTemplatePackage, targetDir, theFileExt);
			} catch (Exception e) {
				System.err.println("Error creating source code for all segments: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

    public static ArrayList<String> getSegmentNames(String version) throws SQLException {
        //get list of segments
		NormativeDatabase normativeDatabase = NormativeDatabase.getInstance();
		Connection conn = normativeDatabase.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT seg_code, section from HL7Segments, HL7Versions where HL7Segments.version_id = HL7Versions.version_id AND hl7_version = '" + version + "'";
		//System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);

		ArrayList<String> segments = new ArrayList<String>();
		while (rs.next()) {
			String segName = rs.getString(1);

            // The DB has an invalid segment with this name
            if ("ED".equals(segName)) {
                continue;
            }

			if (Character.isLetter(segName.charAt(0))) {
				segments.add(altSegName(segName));
			}
		}
		stmt.close();
		normativeDatabase.returnConnection(conn);
        return segments;
    }

	/**
	 * <p>Returns an alternate segment name to replace the given segment name.  Substitutions
	 * made include:  </p>
	 * <ul><li>Replacing Z.. with Z</li>
	 *<li>Replacing ??? with ???</li></ul>
	 */
	public static String altSegName(String segmentName) {
		String ret = segmentName;
		if (ret.equals("Z..")) {
			ret = "Z";
		}
		if (ret.equals("CON")) {
			ret = "CON_";
		}
		return ret;
	}

	/**
	 * Returns the Java source code for a class that represents the specified segment.
	 */
	public static void makeSegment(String name, String version, String theTemplatePackage, File theTargetDir, String theFileExt) throws Exception {

		ArrayList elements = new ArrayList();
		String segDesc = null;
		SegmentElement se = null;

        NormativeDatabase normativeDatabase = NormativeDatabase.getInstance();
		try {
			Connection conn = normativeDatabase.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT HL7SegmentDataElements.seg_code, HL7SegmentDataElements.seq_no, ");
			sql.append("HL7SegmentDataElements.repetitional, HL7SegmentDataElements.repetitions, ");
			sql.append("HL7DataElements.description, HL7DataElements.length, HL7DataElements.table_id, ");
			sql.append("HL7SegmentDataElements.req_opt, HL7Segments.description, HL7DataElements.data_structure ");
			sql.append("FROM HL7Versions RIGHT JOIN (HL7Segments INNER JOIN (HL7DataElements INNER JOIN HL7SegmentDataElements ");
			sql.append("ON (HL7DataElements.version_id = HL7SegmentDataElements.version_id) ");
			sql.append("AND (HL7DataElements.data_item = HL7SegmentDataElements.data_item)) ");
			sql.append("ON (HL7Segments.version_id = HL7SegmentDataElements.version_id) ");
			sql.append("AND (HL7Segments.seg_code = HL7SegmentDataElements.seg_code)) ");
			sql.append("ON (HL7Versions.version_id = HL7Segments.version_id) ");
			sql.append("WHERE HL7SegmentDataElements.seg_code = '");
			sql.append(name);
			sql.append("' and HL7Versions.hl7_version = '");
			sql.append(version);
			sql.append("' ORDER BY HL7SegmentDataElements.seg_code, HL7SegmentDataElements.seq_no;");
			//System.out.println(sql.toString());  //for debugging
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql.toString());

			List usedFieldDescs = new ArrayList();
			int index = 0;
			while (rs.next()) {
				if (segDesc == null) {
					segDesc = rs.getString(9);
				}
				se = new SegmentElement(name, version, index++);
				se.field = rs.getInt(2);
				se.rep = rs.getString(3);
				se.repetitions = rs.getInt(4);
				if (se.repetitions == 0) {
					if (se.rep == null || !se.rep.equalsIgnoreCase("Y")) {
						se.repetitions = 1;
					}
				}
				se.desc = rs.getString(5);

				// If two fields have the same name, add "Rep 1" or "Rep 2" etc to the name
				String originalSeDesc = se.desc;
				if (usedFieldDescs.contains(se.desc)) {
					se.desc = se.desc + " Number " + (Collections.frequency(usedFieldDescs, originalSeDesc) + 1);
				}
				usedFieldDescs.add(originalSeDesc);

				se.length = rs.getInt(6);
				se.table = rs.getInt(7);
				se.opt = rs.getString(8);
				se.type = rs.getString(10);
				//shorten CE_x to CE
				if (se.type.startsWith("CE")) {
					se.type = "CE";
				}

				if (se.type.equals("-") || se.type.equals("NUL")) {
					se.type = "NULLDT";
				}

				elements.add(se);
				/*System.out.println("Segment: " + name + " Field: " + se.field + " Rep: " + se.rep +
				" Repetitions: " + se.repetitions + " Desc: " + se.desc + " Length: " + se.length +
				" Table: " + se.table + " Segment Desc: " + segDesc);*/
			}
			stmt.close();
			normativeDatabase.returnConnection(conn);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return;
		}
		
		String fileName = theTargetDir.toString() + "/" + name + "." + theFileExt;
		
		String basePackageName = DefaultModelClassFactory.getVersionPackageName(version);
		String[] datatypePackages = { basePackageName + "datatype" };
        writeSegment(fileName, version, name, elements, segDesc, basePackageName, datatypePackages, theTemplatePackage);

	}

	private static void makeFieldAccessor(String name, String version, StringBuffer source, SegmentElement se, String accessorName) {
		if (se.desc.equalsIgnoreCase("UNUSED")) {  //some entries in 2.1 DB say "unused"
			return;
		}
			String type = SourceGenerator.getAlternateType(se.type, version);
			source.append("  /**\r\n");
			source.append("   * Returns ");
			if (se.repetitions != 1) {
				source.append("a single repetition of ");
			}
			source.append(se.desc);
			source.append(" (");
			source.append(name);
			source.append("-");
			source.append(se.field);
			source.append(").\r\n");
			if (se.repetitions != 1) {
				source.append("   * @param rep the repetition number (this is a repeating field)\r\n");
				source.append("   * @throws HL7Exception if the repetition number is invalid.\r\n");
			}
			source.append("   */\r\n");
			source.append("  public ");
			source.append(type);
			source.append(" get");
			source.append(accessorName);
			source.append("(");
			if (se.repetitions != 1) {
				source.append("int rep");
			}
			source.append(") ");
			if (se.repetitions != 1) {
				source.append("throws HL7Exception");
			}
			source.append(" {\r\n");
			source.append("    ");
			source.append(type);
			source.append(" ret = null;\r\n");
			source.append("    try {\r\n");
			source.append("        Type t = this.getField(");
			source.append(se.field);
			source.append(", ");
			if (se.repetitions == 1) {
				source.append("0");
			} else {
				source.append("rep");
			}
			source.append(");\r\n");
			source.append("        ret = (");
			source.append(type);
			source.append(")t;\r\n");

			source.append("    } catch (ClassCastException cce) {\r\n");
			source.append("        HapiLogFactory.getHapiLog(this.getClass()).error(\"Unexpected problem obtaining field value.  This is a bug.\", cce);\r\n");
			source.append("        throw new RuntimeException(cce);\r\n");
			if (se.repetitions == 1) {
				source.append("    } catch (HL7Exception he) {\r\n");
				source.append("        HapiLogFactory.getHapiLog(this.getClass()).error(\"Unexpected problem obtaining field value.  This is a bug.\", he);\r\n");
				source.append("        throw new RuntimeException(he);\r\n");
			}
			source.append("    }\r\n");
			source.append("    return ret;\r\n");
			source.append("  }\r\n\r\n");

			//add an array accessor as well for repeating fields
			if (se.repetitions != 1) {
				source.append("  /**\r\n");
				source.append("   * Returns all repetitions of ");
				source.append(se.desc);
				source.append(" (");
				source.append(name);
				source.append("-");
				source.append(se.field);
				source.append(").\r\n");
				source.append("   */\r\n");
				source.append("  public ");
				source.append(type);
				source.append("[] get");
				source.append(accessorName);
				source.append("() {\r\n");
				source.append("     ");
				source.append(type);
				source.append("[] ret = null;\r\n");
				source.append("    try {\r\n");
				source.append("        Type[] t = this.getField(");
				source.append(se.field);
				source.append(");  \r\n");
				source.append("        ret = new ");
				source.append(type);
				source.append("[t.length];\r\n");
				source.append("        for (int i = 0; i < ret.length; i++) {\r\n");
				source.append("            ret[i] = (");
				source.append(type);
				source.append(")t[i];\r\n");
				source.append("        }\r\n");
				source.append("    } catch (ClassCastException cce) {\r\n");
				source.append("        HapiLogFactory.getHapiLog(this.getClass()).error(\"Unexpected problem obtaining field value.  This is a bug.\", cce);\r\n");
				source.append("        throw new RuntimeException(cce);\r\n");
				source.append("    } catch (HL7Exception he) {\r\n");
				source.append("        HapiLogFactory.getHapiLog(this.getClass()).error(\"Unexpected problem obtaining field value.  This is a bug.\", he);\r\n");
				source.append("        throw new RuntimeException(he);\r\n");
				source.append("    }\r\n");
				source.append("    return ret;\r\n");
				source.append("  }\r\n\r\n");


				// Add count reps method
				source.append("  /**\r\n");
				source.append("   * Returns a count of the number of repetitions of ");
				source.append(se.desc);
				source.append(" (");
				source.append(name);
				source.append("-");
				source.append(se.field);
				source.append(").\r\n");
				source.append("   */\r\n");
				source.append("  public int get");
				source.append(accessorName);
				source.append("Reps() {\r\n");
				source.append("    try {\r\n");
				source.append("        return this.getField(");
				source.append(se.field);
				source.append(").length;  \r\n");
				source.append("    } catch (ClassCastException cce) {\r\n");
				source.append("        HapiLogFactory.getHapiLog(this.getClass()).error(\"Unexpected problem obtaining field value.  This is a bug.\", cce);\r\n");
				source.append("        throw new RuntimeException(cce);\r\n");
				source.append("    } catch (HL7Exception he) {\r\n");
				source.append("        HapiLogFactory.getHapiLog(this.getClass()).error(\"Unexpected problem obtaining field value.  This is a bug.\", he);\r\n");
				source.append("        throw new RuntimeException(he);\r\n");
				source.append("    }\r\n");
				source.append("  }\r\n\r\n");


				// Add insert repetition method
				source.append("  /**\r\n");
				source.append("   * Inserts a repetition of ");
				source.append(se.desc);
				source.append(" (");
				source.append(name);
				source.append("-");
				source.append(se.field);
				source.append(") at a given index and returns it.\r\n");
				source.append("   * @param index The index\r\n");
				source.append("   */\r\n");
				source.append("  public ");
				source.append(type);
				source.append(" insert");
				source.append(accessorName);
				source.append("(int index) throws HL7Exception {\r\n");
				source.append("     return (");
				source.append(type);
				source.append(") super.insertRepetition(");
				source.append(se.field);
				source.append(", index);\r\n");
				source.append("  }\r\n\r\n");


				// Add remove repetition method
				source.append("  /**\r\n");
				source.append("   * Removes a repetition of ");
				source.append(se.desc);
				source.append(" (");
				source.append(name);
				source.append("-");
				source.append(se.field);
				source.append(") at a given index and returns it.\r\n");
				source.append("   * @param index The index\r\n");
				source.append("   */\r\n");
				source.append("  public ");
				source.append(type);
				source.append(" remove");
				source.append(accessorName);
				source.append("(int index) throws HL7Exception {\r\n");
				source.append("     return (");
				source.append(type);
				source.append(") super.removeRepetition(");
				source.append(se.field);
				source.append(", index);\r\n");
				source.append("  }\r\n\r\n");

			}
		
	}


	public static void writeSegment(String fileName, String version, String segmentName, ArrayList<SegmentElement> elements, String description, String basePackage, String[] datatypePackages, String theTemplatePackage) throws Exception {
		log.info("Writing segment: " + fileName);
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, false), SourceGenerator.ENCODING));
		
        theTemplatePackage = theTemplatePackage.replace(".", "/");
        Template template = VelocityFactory.getClasspathTemplateInstance(theTemplatePackage + "/segment.vsm");
        VelocityContext ctx = new VelocityContext();
        ctx.put("segmentName", segmentName);
        ctx.put("typeDescription", description);
        ctx.put("basePackageName", basePackage);
        ctx.put("elements", elements);
        ctx.put("datatypePackages", datatypePackages);
        
        template.merge(ctx, out);
		
//      String string = createSegmentString(version, segmentName, elements, description, basePackage, datatypePackageString);
//      out.write(string);
		
		out.flush();
		out.close();
	}

}
