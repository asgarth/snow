package org.snow.binding.form;

import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.core.databinding.observable.value.ValueDiff;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.snow.form.field.RadioField;

public class RadioFieldObservableValue extends AbstractObservableValue {

	private final RadioField field;

	private final SelectionListener selectionListener;

	private Object selection = null;

	public RadioFieldObservableValue( final RadioField input ) {
		this.field = input;
		this.selectionListener = new SelectionAdapter() {

			public void widgetSelected( SelectionEvent e ) {
				final Object newSelection = field.getValue();
				fireValueChange( new ValueDiff() {

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
	protected void doSetValue( Object value ) {
		field.setValue( value.toString() );
		selection = value;
	}

	@Override
	protected Object doGetValue() {
		return field.getValue();
	}

	@Override
	public synchronized void dispose() {
		field.removeSelectionListener( selectionListener );
	}

}
