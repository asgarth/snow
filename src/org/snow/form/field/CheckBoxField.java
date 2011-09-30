package org.snow.form.field;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.snow.form.Form;

public class CheckBoxField extends AbstractStringField {

	private final Button check;

	private String value;

	public CheckBoxField( final Form parent, final String caption ) {
		this( parent );
		setCaption( caption );
	}

	public CheckBoxField( final Form parent ) {
		super( parent );
		check = new Button( parent, SWT.CHECK );
	}

	public boolean getSelection() {
		return value != null && value.equalsIgnoreCase( "true" );
	}

	public String getValue() {
		return value;
	}

	public void setSelection( final boolean value ) {
		this.value = value ? "true" : "false";
	}

	public void setValue( final String value ) {
		this.value = value;
	}

	public Control getControl() {
		return check;
	}

	public void addSelectionListener( final SelectionListener listener ) {
		check.addSelectionListener( listener );
	}

	public void removeSelectionListener( final SelectionListener listener ) {
		check.removeSelectionListener( listener );
	}

}
