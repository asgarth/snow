package org.snow.form.field;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.snow.form.Form;

import com.ibm.icu.util.Calendar;

public class DateField extends AbstractField<Date> implements Field<Date> {

	public static final int DATE_STYLE = SWT.DATE | SWT.DROP_DOWN | SWT.BORDER;

	private final DateTime calendar;


	public DateField( final Form parent, final String caption ) {
		super( parent );

		calendar = new DateTime( parent, DATE_STYLE );
		calendar.setTime( 0, 0, 0 );

		setCaption( caption );
	}

	public Date getValue() {
		final Calendar c = Calendar.getInstance();
		c.clear();
		c.set( Calendar.YEAR, calendar.getYear() );
		c.set( Calendar.MONTH, calendar.getMonth() );
		c.set( Calendar.DATE, calendar.getDay() );
		return c.getTime();
	}

	public void setValue( final Date value ) {
		final Calendar c = Calendar.getInstance();
		c.setTime( value );
		calendar.setDate( c.get( Calendar.YEAR ), c.get( Calendar.MONTH ), c.get( Calendar.DATE ) );
	}

	public boolean isEmpty() {
		return calendar.getYear() == 0 && calendar.getMonth() == 0 && calendar.getDay() == 0;
	}

	public Control getControl() {
		return calendar;
	}

	public void addSelectionListener( final SelectionListener listener ) {
		calendar.addSelectionListener( listener );
	}

	public void removeSelectionListener( final SelectionListener listener ) {
		calendar.removeSelectionListener( listener );
	}

	public void addModifyListener( final ModifyListener listener ) {
		throw new UnsupportedOperationException( "Cannot attach a modify listener to this object" );
	}

	public void removeModifyListener( final ModifyListener listener ) {
		throw new UnsupportedOperationException( "Cannot remove a modify listener from this object" );
	}
	
}
