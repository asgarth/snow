package org.snow.form.field;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.snow.form.Form;

public class ComboField extends AbstractStringField {

	private final Combo combo;

	private String value;

	public ComboField( final Form parent, final String caption ) {
		this( parent );
		setCaption( caption );
	}

	public ComboField( final Form parent ) {
		super( parent );
		combo = new Combo( parent, SWT.READ_ONLY );
	}

	public String getValue() {
		return value;
	}

	public void setValue( final String value ) {
		this.value = value;
	}

	public Control getControl() {
		return combo;
	}

	public void addSelectionListener( final SelectionListener listener ) {
		combo.addSelectionListener( listener );
	}

	public void removeSelectionListener( final SelectionListener listener ) {
		combo.removeSelectionListener( listener );
	}
	
	public void addModifyListener( final ModifyListener listener ) {
		combo.addModifyListener( listener );
	}

	public void removeModifyListener( final ModifyListener listener ) {
		combo.removeModifyListener( listener );
	}

}
