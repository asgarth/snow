package org.snow.form.field.enums;

public enum TimeStyle {

	H12( 12 ),
	H24( 24 );

	private final int value;

	private TimeStyle( final int value ) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
