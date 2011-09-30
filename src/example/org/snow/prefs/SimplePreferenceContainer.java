package example.org.snow.prefs;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Element;
import org.snow.prefs.PreferenceGroup;
import org.snow.prefs.PreferenceContainer;

import example.org.snow.prefs.model.Connections;
import example.org.snow.prefs.model.General;

public class SimplePreferenceContainer implements PreferenceContainer {

	@Element( name = "general" )
	private final General general;

	@Element( name = "connection" )
	private final Connections connection;

	public static SimplePreferenceContainer getInstance() {
		return new SimplePreferenceContainer( Connections.getInstance(), General.getInstance() );
	}

	private SimplePreferenceContainer( @Element( name = "connection" ) final Connections connection,
			@Element( name = "general" ) final General general ) {
		this.general = general;
		this.connection = connection;
	}

	public String getName() {
		return "Test";
	}

	public String getTagName() {
		return getName().toLowerCase();
	}

	public General getGeneral() {
		return general;
	}

	public PreferenceGroup get( final String category ) {
		if( category.equals( "general" ) )
			return general;

		return connection;
	}

	public List<PreferenceGroup> getAll() {
		final List<PreferenceGroup> list = new ArrayList<PreferenceGroup>();
		list.add( general );
		list.add( connection );
		return list;
	}

}
