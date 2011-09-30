package org.snow.form.field;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;
import org.snow.form.Form;

public class LargeTextField extends AbstractStringField {

	public static final int DEFAULT_HEIGHT = 60;

	protected final Text text;

	private boolean dot;

	public LargeTextField( final Form parent, final String caption ) {
		this( parent );
		setCaption( caption );
	}

	public LargeTextField( final Form parent ) {
		super( parent );

		text = new Text( parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.WRAP );
		dot = true;

		/** add text formatter listener */
		text.addVerifyListener( new VerifyListener() {

			public void verifyText( final VerifyEvent e ) {
				// check input
				final String text = e.text;
				if( text == null || text.equals( "" ) )
					return;
				final String trim = text.trim();
				if( trim.equals( "" ) )
					return;

				// check last char (looking for special character)
				final char last = trim.charAt( trim.length() - 1 );
				if( last == '.' || last == '?' || last == '!' ) {
					dot = true;
					return;
				}

				// change first character if required
				if( !dot )
					return;

				final char first = Character.toUpperCase( text.charAt( 0 ) );
				e.text = first + text.substring( 1 );
				dot = false;
			}
		} );
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

}
