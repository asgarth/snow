package org.snow.form.field;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.snow.form.Form;
import org.snow.form.field.enums.TimeStyle;

import com.ibm.icu.util.Calendar;

public class DateField extends AbstractField<Date> implements Field<Date> {

	public static final int DATE_STYLE = SWT.DATE | SWT.DROP_DOWN | SWT.BORDER;

	private final boolean includeTime;

	private final Composite container;

	private final DateTime calendar;

	private final Combo time;

	public DateField( final Form parent, final String caption ) {
		this( parent, caption, false );
	}

	public DateField( final Form parent, final String caption, final boolean includeTime ) {
		super( parent );

		this.includeTime = includeTime;

		if( includeTime ) {
			container = new Composite( parent, SWT.NONE );
			final FillLayout containerLayout = new FillLayout();
			containerLayout.marginHeight = 0;
			containerLayout.marginWidth = 0;
			containerLayout.spacing = 10;
			container.setLayout( containerLayout );
			calendar = new DateTime( container, DATE_STYLE );
			calendar.setTime( 0, 0, 0 );

			time = new Combo( container, SWT.DROP_DOWN );
			setTimeFormat( TimeStyle.H24 );

		} else {
			calendar = new DateTime( parent, DATE_STYLE );
			calendar.setTime( 0, 0, 0 );

			container = null;
			time = null;
		}

		setCaption( caption );
	}
	
	public void setTimeFormat( final TimeStyle style ) {
		if( ! includeTime )
			throw new UnsupportedOperationException( "Cannot set time style - time not include in this field" );

		time.removeAll();
		for( int i = 0; i < style.getValue(); i++ )
			time.add( padLeft( String.valueOf( i ), 2, '0' ) + ":00", i );
	}

	public Date getValue() {
		final Calendar c = Calendar.getInstance();
		c.clear();
		c.set( Calendar.YEAR, calendar.getYear() );
		c.set( Calendar.MONTH, calendar.getMonth() );
		c.set( Calendar.DATE, calendar.getDay() );

		if( includeTime ) {
			c.set( Calendar.HOUR, Integer.parseInt( time.getText().split( ":" )[0] ) );
			c.set( Calendar.MINUTE, Integer.parseInt( time.getText().split( ":" )[1] ) );
		}

		return c.getTime();
	}

	public String getValueAsString( final SimpleDateFormat sdf ) {
		return sdf.format( getValue() );
	}

	public void setValue( final Date value ) {
		setValue( value, false );
	}

	public void setValue( final Date value, final boolean round ) {
		final Calendar c = Calendar.getInstance();
		c.setTime( value );
		calendar.setDate( c.get( Calendar.YEAR ), c.get( Calendar.MONTH ), c.get( Calendar.DATE ) );

		if( includeTime )
			time.setText( padLeft( String.valueOf( c.get( Calendar.HOUR ) ), 2, '0' ) + ":" + ( round ? "00" : padLeft( String.valueOf( c.get( Calendar.MINUTE ) ), 2, '0' ) ) );
	}

	public boolean isEmpty() {
		return calendar.getYear() == 0 && calendar.getMonth() == 0 && calendar.getDay() == 0;
	}

	public Control getControl() {
		return includeTime ? container : calendar;
	}

	public void addSelectionListener( final SelectionListener listener ) {
		calendar.addSelectionListener( listener );
	}

	public void removeSelectionListener( final SelectionListener listener ) {
		calendar.removeSelectionListener( listener );
	}

	public void addTimeSelectionListener( final SelectionListener listener ) {
		if( ! includeTime )
			throw new UnsupportedOperationException( "Cannot attach a selection listener - time not include in this field" );

		time.addSelectionListener( listener );
	}

	public void removeTimeSelectionListener( final SelectionListener listener ) {
		if( ! includeTime )
			throw new UnsupportedOperationException( "Cannot remove a selection listener - time not include in this field" );

		time.removeSelectionListener( listener );
	}

	public void addModifyListener( final ModifyListener listener ) {
		throw new UnsupportedOperationException( "Cannot attach a modify listener to this object" );
	}

	public void removeModifyListener( final ModifyListener listener ) {
		throw new UnsupportedOperationException( "Cannot remove a modify listener from this object" );
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
