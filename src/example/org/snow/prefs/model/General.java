package example.org.snow.prefs.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.simpleframework.xml.ElementMap;
import org.snow.prefs.param.AbstractParamCategory;
import org.snow.prefs.param.Param;
import org.snow.prefs.param.ParamCategory;

public class General extends AbstractParamCategory implements ParamCategory {

	public static final String NAME = "General";

	private static final List<Param> params;

	static {
		final List<Param> temp = new ArrayList<Param>();
		temp.add( new Param( "ask_to_close", "true", "Ask before quit when active download transfers", Param.ParamType.CHECK ) );

		params = Collections.unmodifiableList( temp );
	}

	public static General getInstance() {
		return new General( new HashMap<String, String>(), params );
	}

	private General( @ElementMap( entry = "param", key = "name", attribute = true, inline = true ) final Map<String, String> map,
			final List<Param> params ) {
		super( map, params );
	}

	private General( @ElementMap( entry = "param", key = "name", attribute = true, inline = true ) final Map<String, String> map ) {
		super( map );
	}

	public String getName() {
		return NAME;
	}

	public List<Param> getParams() {
		return params;
	}

}
