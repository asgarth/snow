package org.snow.form.field;

import org.snow.form.Form;

public abstract class AbstractStringField extends AbstractField<String> {

	protected AbstractStringField( final Form parent ) {
		super( parent );
	}

	public boolean isEmpty() {
		return getValue() == null || getValue().equals( "" );
	}

}
