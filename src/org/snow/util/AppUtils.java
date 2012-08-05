package org.snow.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppUtils {

	/** Utility class, prevent instantiation. */
	private AppUtils() { }

	/** Return the application version. */
	public static String version() {
		// try to get version from manifest file
		String version = getManifestVersion();
		if( version != null && !version.equals( "" ) )
			return version;

		// try to get version from build.version file
		version = getAntVersion();
		if( version != null && !version.equals( "" ) )
			return version;

		return "Unknown";
	}

	private static String getManifestVersion() {
		final Package p = AppUtils.class.getPackage();
		return p.getSpecificationVersion();
	}

	private static String getAntVersion() {
		final InputStream stream = AppUtils.class.getClassLoader().getResourceAsStream( "./build.version" );
		if( stream == null )
			return null;

		String version = null;
		try {
			final Properties properties = new Properties();
			properties.load( stream );

			version = properties.getProperty( "version.number" );

		} catch( IOException ignore ) {
		} finally {
			try {
				stream.close();

			} catch( Throwable ignore ) {
			}
		}

		return version;
	}

}
