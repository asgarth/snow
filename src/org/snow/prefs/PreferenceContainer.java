package org.snow.prefs;

import java.util.List;

/** Interface that should be implemented as a container for different preference group. */
public interface PreferenceContainer {

	public PreferenceGroup get( String groupName );

	public List<PreferenceGroup> getAll();

}
