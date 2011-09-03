package org.snow.extra;

import java.util.Date;

import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.core.databinding.observable.value.ValueDiff;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;



public class DateFieldObservableValue extends AbstractObservableValue {

	private final DateField field;

	private final SelectionListener selectionListener;

	private Object selection = null;


	public DateFieldObservableValue( final DateField input ) {
		this.field = input;
		this.selectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				final Object newSelection = field.getValue();
				fireValueChange(new ValueDiff() {
					public Object getNewValue() {
						return newSelection;
					}
					public Object getOldValue() {
						return selection;
					}
				} );
				selection = newSelection;
			}
		};

		field.addSelectionListener( selectionListener );
	}

	@Override
	public Object getValueType() {
		return Object.class;
	}

	@Override
	protected Object doGetValue() {
		return field.getValue();
	}

	@Override
	protected void doSetValue(Object value) {
		field.setValue( ( Date ) value );
		selection = value;
	}

	@Override
	public synchronized void dispose() {
		field.removeSelectionListener( selectionListener );
	}

}
