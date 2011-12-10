package org.snow.form.field;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Text;
import org.snow.form.Form;

public class TextField extends AbstractStringField {

	public static final int NORMAL = SWT.BORDER;
	
	public static final int READ_ONLY = SWT.BORDER | SWT.READ_ONLY;
	
	public static final int PASSWORD = SWT.BORDER | SWT.SINGLE | SWT.PASSWORD;
	
	public static final int PASSWORD_READ_ONLY = SWT.BORDER | SWT.SINGLE | SWT.PASSWORD | SWT.READ_ONLY;
	
	private final Text text;

	public TextField( final Form parent, final String caption ) {
		this( parent, NORMAL, caption );
	}
	
	public TextField( final Form parent, final int style, final String caption ) {
		this( parent, style );
		setCaption( caption );
	}

	public TextField( final Form parent ) {
		this( parent, NORMAL );
	}
	
	public TextField( final Form parent, final int style ) {
		super( parent );
		text = new Text( parent, style );
	}

	public String getValue() {
		return text.getText();
	}

	public void setValue( final String value ) {
		text.setText( value );
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
	
	public void addModifyListener( final ModifyListener listener ) {
		text.addModifyListener( listener );
	}

	public void removeModifyListener( final ModifyListener listener ) {
		text.removeModifyListener( listener );
	}

}
