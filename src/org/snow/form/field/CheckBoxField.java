package org.snow.form.field;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.snow.form.Form;

public class CheckBoxField extends AbstractStringField {

	private final Button check;

	private boolean rightTitle = false;
	
	private String value;

	public CheckBoxField( final Form parent, final String caption ) {
		this( parent, caption, false );
	}

	public CheckBoxField( final Form parent, final String caption, final boolean rightTitle ) {
		this( parent );
		this.rightTitle = rightTitle;
		
		setCaption( caption );
	}

	public CheckBoxField( final Form parent ) {
		super( parent );
		check = new Button( parent, SWT.CHECK );
	}

	public void setCaption( final String caption ) {
		if( rightTitle )
			check.setText( caption );
		else
			super.setCaption( caption );
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

	public void setLabelLayout( final Object layout ) {
		if( ! rightTitle ) {
			super.setLabelLayout( layout );
			return;
		}

		if( ! ( layout instanceof GridData ) ) {
			super.setLabelLayout( layout );
			return;
		}
		
		label.dispose();
	}

	public void setControlLayout( final Object layout ) {
		if( ! rightTitle ) {
			super.setControlLayout( layout );
			return;
		}
		
		if( ! ( layout instanceof GridData ) ) {
			super.setControlLayout( layout );
			return;
		}
		
		( ( GridData ) layout ).horizontalSpan = 2;
		getControl().setLayoutData( layout );
	}
	
	public void addSelectionListener( final SelectionListener listener ) {
		check.addSelectionListener( listener );
	}

	public void removeSelectionListener( final SelectionListener listener ) {
		check.removeSelectionListener( listener );
	}
	
	public void addModifyListener( final ModifyListener listener ) {
		throw new UnsupportedOperationException( "Cannot attach a modify listener to this object" );
	}

	public void removeModifyListener( final ModifyListener listener ) {
		throw new UnsupportedOperationException( "Cannot remove a modify listener from this object" );
	}

}
