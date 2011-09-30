package example.org.snow.prefs.model;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.snow.prefs.PreferenceGroup;

public class Connections implements PreferenceGroup {

	public static final String NAME = "Connections";

	@ElementList( name = "servers" )
	private final List<Server> servers;

	public static Connections getInstance() {
		return new Connections( new ArrayList<Server>() );
	}

	public Connections( @ElementList( name = "servers" ) final List<Server> servers ) {
		this.servers = servers;
	}

	public String getName() {
		return NAME;
	}

	public String getTagName() {
		return NAME.toLowerCase();
	}

	public Server get( final String name ) {
		for( Server s : servers )
			if( s.getName().equals( name ) )
				return s;

		return null;
	}

	public void add( final Server server ) {
		servers.add( server );
	}

}
