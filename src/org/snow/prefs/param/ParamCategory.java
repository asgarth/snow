package org.snow.prefs.param;

import java.util.List;
import java.util.Map;

import org.snow.prefs.PreferenceCategory;


public interface ParamCategory extends PreferenceCategory {

	public void putAll( Map<String, String> values );
	
	public List<Param> getParams();

	public String get( String key );

	public boolean getAsBoolean( String key );

}
