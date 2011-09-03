package org.snow.form.field;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;
import org.snow.form.Form;


public class TextField extends AbstractField<String> implements Field<String> {

	/** widgets */
	private final Text text;


	public TextField( final Form parent, final String caption ) {
		this( parent );
		setCaption( caption );
	}

	public TextField( final Form parent ) {
		super( parent );
		text = new Text( parent, SWT.BORDER );
	}

	public String getValue() {
		return text.getText();
	}

	public void setValue( final String value ) {
		text.setText( value );
	}

	public boolean isEmpty() {
		return text.getText() == null || text.getText().equals( "" );
	}

	public Text getControl() {
		return text;
	}
	
	public void addSelectionListener( final SelectionListener listener ) {
		text.addSelectionListener( listener );
	}
	
	public void removeSelectionListener( final SelectionListener listener ) {
		text.removeSelectionListener( listener );
	}

}
