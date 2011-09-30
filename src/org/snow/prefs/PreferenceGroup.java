package org.snow.prefs;

/** Interface that every preference group should implement to be correctly stored on configuration file. */
public interface PreferenceGroup {

	public String getName();

	public String getTagName();

}
