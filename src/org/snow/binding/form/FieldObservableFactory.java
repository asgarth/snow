package org.snow.binding.form;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.snow.extra.DateField;
import org.snow.extra.DateFieldObservableValue;
import org.snow.form.field.CheckBoxField;
import org.snow.form.field.Field;
import org.snow.form.field.RadioField;



public abstract class FieldObservableFactory {

	public static IObservableValue getObservableValue( final Field field ) {
		if( field instanceof RadioField )
			return new RadioFieldObservableValue( ( RadioField ) field );

		if( field instanceof CheckBoxField )
			return WidgetProperties.selection().observe( field.getControl() );

		if( field instanceof DateField )
			return new DateFieldObservableValue( ( DateField ) field );

		return WidgetProperties.text( SWT.Modify ).observe( field.getControl() );
	}

}
