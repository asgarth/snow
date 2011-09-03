package org.snow.prefs;


/** Interface that every preference category should implement to be correctly stored on configuration file. */
public interface PreferenceCategory {

	public String getName();

	public String getTagName();

}
