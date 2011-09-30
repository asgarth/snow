package org.snow.prefs.param;

import java.util.List;
import java.util.Map;

import org.snow.prefs.PreferenceGroup;

public interface ParamCategory extends PreferenceGroup {

	public void putAll( Map<String, String> values );

	public List<Param> getParams();

	public String get( String key );

	public boolean getAsBoolean( String key );

}
