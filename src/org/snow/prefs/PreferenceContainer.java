package org.snow.prefs;

import java.util.List;


/** Interface that should be implemented as a container for different preference groupget. */
public interface PreferenceContainer {

	public PreferenceCategory get( String groupName );

	public List<PreferenceCategory> getAll();

}
