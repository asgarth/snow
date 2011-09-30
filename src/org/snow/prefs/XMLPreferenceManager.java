package org.snow.prefs;

import java.io.File;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/** Manager class for loading and saving of configuration properties on an XML file. */
public class XMLPreferenceManager<T extends PreferenceContainer> implements PreferenceManager<T> {

	private static PreferenceManager<? extends PreferenceContainer> instance;

	private String xmlConfigFile;

	private T root;

	public synchronized static <T extends PreferenceContainer> PreferenceManager init( final String xmlConfigFile, final T root ) {
		instance = new XMLPreferenceManager( xmlConfigFile, root );
		return instance;
	}

	public static PreferenceManager getInstance() {
		if( instance == null )
			throw new UnsupportedOperationException( "Preferences not loaded successfully!!!" );

		return instance;
	}

	private XMLPreferenceManager( final String xmlConfigFile, final T root ) {
		this.xmlConfigFile = xmlConfigFile;
		this.root = root;

		load();
	}

	public void load() {
		// initialize a new file if not found
		final File file = new File( xmlConfigFile );
		if( !file.exists() )
			save();

		// load values from file
		try {
			final Serializer serializer = new Persister();
			this.root = ( T ) serializer.read( root.getClass(), new File( xmlConfigFile ) );

		} catch( Exception e ) {
			throw new RuntimeException( "error loading configuration file", e );
		}
	}

	public void save() {
		try {
			final Serializer serializer = new Persister();
			serializer.write( root, new File( xmlConfigFile ) );

		} catch( Exception e ) {
			throw new RuntimeException( "error saving configuration file", e );
		}
	}

	public T root() {
		return root;
	}

	public PreferenceGroup get( final String category ) {
		return root.get( category );
	}

	public List<PreferenceGroup> getGroups() {
		return root.getAll();
	}

	public String configFile() {
		return xmlConfigFile;
	}

}
