package org.snow.prefs;

import java.util.List;

public interface PreferenceManager<T extends PreferenceContainer> {

	/** Return the path of the file used to load and store application preferences. */
	public String configFile();

	/** Load preferences from file. */
	public void load();

	/** Save preferences to predefined output file. */
	public void save();

	/** Return the root preference group. */
	public T root();

	/** Return the preference group with the specified name. */
	public PreferenceGroup get( String groupName );

	/** Return the categories list. */
	public List<PreferenceGroup> getGroups();

}
