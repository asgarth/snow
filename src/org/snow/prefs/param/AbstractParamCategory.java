package org.snow.prefs.param;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.simpleframework.xml.ElementMap;

public abstract class AbstractParamCategory implements ParamCategory {

	@ElementMap( entry = "param", key = "name", attribute = true, inline = true )
	private final Map<String, String> map;

	public AbstractParamCategory(
			@ElementMap( entry = "param", key = "name", attribute = true, inline = true ) final Map<String, String> map ) {
		this.map = map;
	}

	public AbstractParamCategory( final Map<String, String> emptyMap, final List<Param> params ) {
		this( emptyMap );
		for( Param p : params )
			map.put( p.getName(), p.getDefaultValue() );
	}

	public String get( final String key ) {
		final String value = map.get( key );
		return value == null ? "" : value;
	}

	public boolean getAsBoolean( final String key ) {
		return Boolean.parseBoolean( get( key ) );
	}

	public void put( final String key, final String value ) {
		map.put( key, value );
	}

	public void putAll( final Map<String, String> values ) {
		for( Entry<String, String> e : values.entrySet() )
			put( e.getKey(), e.getValue() );
	}

	public String getTagName() {
		return getName().toLowerCase();
	}

}
