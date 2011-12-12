package org.snow.form.field;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.snow.form.Form;
import org.snow.form.field.enums.TimeStyle;

public class TimeField extends AbstractField<String> implements Field<String> {

	private final Combo time;

	public TimeField( final Form parent, final String caption ) {
		this( parent, caption, TimeStyle.H24 );
	}

	public TimeField( final Form parent, final String caption, final TimeStyle style ) {
		super( parent );

		time = new Combo( parent, SWT.DROP_DOWN );
		setTimeFormat( style );

		setCaption( caption );
	}
	
	public void setTimeFormat( final TimeStyle style ) {
		time.removeAll();
		for( int i = 0; i < style.getValue(); i++ )
			time.add( padLeft( String.valueOf( i ), 2, '0' ) + ":00", i );
	}

	public String getValue() {
		return time.getText();
	}

	public void setValue( final String value ) {
		time.setText( value );
	}

	public boolean isEmpty() {
		return time.getText() == null || time.getText().equals( "" );
	}
	
	public boolean isValid() {
		return getValue().matches( "\\d+:\\d+" );
	}

	public Control getControl() {
		return time;
	}

	public void addSelectionListener( final SelectionListener listener ) {
		time.addSelectionListener( listener );
	}

	public void removeSelectionListener( final SelectionListener listener ) {
		time.removeSelectionListener( listener );
	}
	
	public void addModifyListener( final ModifyListener listener ) {
		time.addModifyListener( listener );
	}

	public void removeModifyListener( final ModifyListener listener ) {
		time.removeModifyListener( listener );
	}

	private String padLeft( final String source, int size, char padChar ) {
		if( source.length() > size )
			return source;

		char[] temp = new char[size];

		for( int i = 0; i < size; i++ )
			temp[i] = padChar;

		int posIniTemp = size - source.length();
		for( int i = 0; i < source.length(); i++ ) {
			temp[posIniTemp] = source.charAt( i );
			posIniTemp++;
		}

		return new String( temp );
	}

}
