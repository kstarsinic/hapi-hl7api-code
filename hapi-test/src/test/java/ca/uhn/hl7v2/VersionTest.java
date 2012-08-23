package ca.uhn.hl7v2;

import org.junit.Test;
import static org.junit.Assert.*;

public class VersionTest {

	@Test public void testGreaterThan() {
		assertTrue(Version.V24.isGreaterThan(Version.V23));
		assertTrue(Version.V231.isGreaterThan(Version.V23));
		assertFalse(Version.V23.isGreaterThan(Version.V23));
		assertFalse(Version.V22.isGreaterThan(Version.V23));
	}
	
	@Test public void testLatestVersion() {
		assertEquals(Version.V26, Version.latestVersion());
	}
	
	@Test public void testVersionOf() {
		for (Version version : Version.values()) {
			assertEquals(version, Version.versionOf(version.getVersion()));
		}
	}
	
	@Test public void testSupportsVersion() {
		for (Version version : Version.values()) {
			assertTrue(Version.supportsVersion(version.getVersion()));
		}
		assertFalse(Version.supportsVersion("3.0"));
	}	
}
