/**
The contents of this file are subject to the Mozilla Public License Version 1.1 
(the "License"); you may not use this file except in compliance with the License. 
You may obtain a copy of the License at http://www.mozilla.org/MPL/ 
Software distributed under the License is distributed on an "AS IS" basis, 
WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the 
specific language governing rights and limitations under the License. 

The Original Code is "Version.java".  Description: 
"An enumeration of supported HL7 versions" 

The Initial Developer of the Original Code is University Health Network. Copyright (C) 
2012.  All Rights Reserved. 

Contributor(s): ______________________________________. 

Alternatively, the contents of this file may be used under the terms of the 
GNU General Public License (the  "GPL"), in which case the provisions of the GPL are 
applicable instead of those above.  If you wish to allow use of your version of this 
file only under the terms of the GPL and not to allow others to use your version 
of this file under the MPL, indicate your decision by deleting  the provisions above 
and replace  them with the notice and other provisions required by the GPL License.  
If you do not delete the provisions above, a recipient may use your version of 
this file under either the MPL or the GPL. 
 */
package ca.uhn.hl7v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum Version {

	V21("2.1"), V22("2.2"), V23("2.3"), V231("2.3.1"), V24("2.4"), V25("2.5"), V251("2.5.1"), V26(
			"2.6");

	private String version;

	Version(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public String getPackageVersion() {
		return "v" + version.replace(".", "");
	}

	public static boolean supportsVersion(String version) {
		return versionOf(version) != null;
	}

	/**
	 * @param version The version string, e.g. "2.1" or "2.6"
	 */
	public static Version versionOf(String version) {
		for (Version v : Version.values()) {
			if (v.getVersion().equals(version)) {
				return v;
			}
		}
		return null;
	}

	/**
	 * @param someVersions
	 * @return <code>true</code> if someVersions contain all supported HL7 versions
	 */
	public static boolean allVersions(Set<Version> someVersions) {
		return someVersions != null && someVersions.size() == values().length;
	}

	/**
	 * Returns true if this version is greater than the specified version
	 */
	public boolean isGreaterThan(Version theVersion) {
		return compareTo(theVersion) > 0;
	}

	public static Version latestVersion() {
		Version[] versions = Version.values();
		return versions[versions.length - 1];
	}

	public static Version[] asOf(Version v) {
		List<Version> versions = new ArrayList<Version>();
		for (Version version : Version.values()) {
			if (version.compareTo(v) >= 0)
				versions.add(version);
		}
		return versions.toArray(new Version[versions.size()]);
	}

	public static Version[] except(Version v) {
		List<Version> versions = new ArrayList<Version>();
		for (Version version : Version.values()) {
			if (version.compareTo(v) != 0)
				versions.add(version);
		}
		return versions.toArray(new Version[versions.size()]);
	}

	public static Version[] before(Version v) {
		List<Version> versions = new ArrayList<Version>();
		for (Version version : Version.values()) {
			if (version.compareTo(v) < 0)
				versions.add(version);
		}
		return versions.toArray(new Version[versions.size()]);
	}

	public String modelPackageName() {
		return String
				.format("%s.model.%s.", getClass().getPackage().getName(), getPackageVersion());
	}

	public boolean available() {
		String className = modelPackageName() + "message.ACK";
		try {
			Class.forName(className, false, Thread.currentThread().getContextClassLoader());
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static Version[] availableVersions() {
		List<Version> availableVersions = new ArrayList<Version>();
		for (Version v : values()) {
			if (v.available())
				availableVersions.add(v);
		}
		return availableVersions.toArray(new Version[availableVersions.size()]);
	}
	
	public static String lowestAvailableVersion() {
		return availableVersions()[0].getVersion();
	}
}
