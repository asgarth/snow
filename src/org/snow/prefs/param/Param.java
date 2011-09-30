package org.snow.prefs.param;

public class Param {

	public enum ParamType {
		TEXT,
		NUMBER,
		CHECK,
	}

	private final String name;

	private final String defaultValue;

	private final String desc;

	private final ParamType type;

	public Param( final String name, final String defaultValue, final String desc, final ParamType type ) {
		this.name = name;
		this.defaultValue = defaultValue;
		this.desc = desc;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getDesc() {
		return desc;
	}

	public ParamType getType() {
		return type;
	}

}
