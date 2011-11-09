package org.snow.util.layout;

import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Control;

/** A convenience layout data builder for {@link FormData} objects. See FormData documentation for additional details. */
public class FormDataBuilder {

	private final FormData data;

	public FormDataBuilder() {
		data = new FormData();
		data.top = new FormAttachment( 0, 0 );
		data.bottom = new FormAttachment( 100, 0 );
		data.left = new FormAttachment( 0, 0 );
		data.right = new FormAttachment( 100, 0 );
	}

	public FormDataBuilder top( final int numerator, final int offset ) {
		data.top = new FormAttachment( numerator, offset );
		return this;
	}

	public FormDataBuilder top( final Control control ) {
		data.top = new FormAttachment( control );
		return this;
	}

	public FormDataBuilder top( final Control control, final int offset ) {
		data.top = new FormAttachment( control, offset );
		return this;
	}

	public FormDataBuilder bottom( final int numerator, final int offset ) {
		data.bottom = new FormAttachment( numerator, offset );
		return this;
	}

	public FormDataBuilder bottom( final Control control ) {
		data.bottom = new FormAttachment( control );
		return this;
	}

	public FormDataBuilder bottom( final Control control, final int offset ) {
		data.bottom = new FormAttachment( control, offset );
		return this;
	}

	public FormDataBuilder left( final int numerator, final int offset ) {
		data.left = new FormAttachment( numerator, offset );
		return this;
	}

	public FormDataBuilder left( final Control control ) {
		data.left = new FormAttachment( control );
		return this;
	}

	public FormDataBuilder left( final Control control, final int offset ) {
		data.left = new FormAttachment( control, offset );
		return this;
	}

	public FormDataBuilder right( final int numerator, final int offset ) {
		data.right = new FormAttachment( numerator, offset );
		return this;
	}

	public FormDataBuilder right( final Control control ) {
		data.right = new FormAttachment( control );
		return this;
	}

	public FormDataBuilder right( final Control control, final int offset ) {
		data.right = new FormAttachment( control, offset );
		return this;
	}

	public FormData build() {
		return data;
	}

}
