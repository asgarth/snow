package org.snow.extra;

import java.util.Date;

import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Control;
import org.snow.form.Form;
import org.snow.form.field.AbstractField;
import org.snow.form.field.Field;


public class DateField extends AbstractField<Date> implements Field<Date> {

	/** widgets */
	private final CDateTime calendar;


	public DateField( final Form parent ) {
		this( parent, CDT.BORDER | CDT.COMPACT | CDT.DROP_DOWN | CDT.TAB_FIELDS );
	}

	public DateField( final Form parent, final int style ) {
		this( parent, style, "dd/MM/yyyy" );
	}

	public DateField( final Form parent, final int style, final String pattern ) {
		super( parent );

		calendar = new CDateTime( parent, style );
		calendar.setPattern( pattern );
		calendar.setSelection( new Date() );
	}

	public Date getValue() {
		return calendar.getSelection();
	}

	public void setValue( final Date value ) {
		calendar.setSelection( value );
	}

	public boolean isEmpty() {
		return calendar.getSelection() == null;
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

}
