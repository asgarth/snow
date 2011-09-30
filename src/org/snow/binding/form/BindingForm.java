package org.snow.binding.form;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.swt.widgets.Composite;
import org.snow.form.Form;
import org.snow.form.field.Field;

/** Create a {@link Form} that can bind value from/to his field to a specified entity.
 * 
 * @param <T> the class accepted by this form for binding. */
public class BindingForm<T> extends Form {

	private final DataBindingContext context;

	private T model;

	/** Create a new form binded to the specified entity model received. */
	public BindingForm( final Composite parent, final T model ) {
		this( parent );
		setBean( model );
	}

	/** Create a new form. Binding to an entity must be executed later with {@link setBean} method. */
	public BindingForm( final Composite parent ) {
		super( parent );
		context = new DataBindingContext();
	}

	/** Set the entity binded to this form. */
	public void setBean( final T model ) {
		this.model = model;
	}

	/** Get the entity binded to this form. */
	public T getBean() {
		return model;
	}

	/** Set the binding between one of the form filed (referenced by id) and a property of the associated entity. */
	public void bind( final Field field, final String property ) {
		bind( field, property, null );
	}

	/** Set the binding between one of the form field and a property of the associated entity.
	 * <p>
	 * Additional conversion required to bind field content to the specified properties can be performed with specified converter. */
	public void bind( final Field field, final String property, final Converter converter ) {
		final IObservableValue widgetValue = FieldObservableFactory.getObservableValue( field );
		final IObservableValue modelValue = PojoProperties.value( model.getClass(), property ).observe( model );

		if( converter != null )
			context.bindValue( widgetValue, modelValue, new UpdateValueStrategy().setConverter( converter ), null );
		else
			context.bindValue( widgetValue, modelValue );
	}

	/** Add the input <code>field</field> content to the properties with specified name.
	 * 
	 * @param name the name of the property attached to content of <code>field</field>. This is used also as key for this field in the form
	 * @param field the field to attach to the form. */
	public void addAndBind( final String name, final Field field ) {
		addAndBind( name, field, name, null );
	}

	/** Add the input <code>field</field> content to the properties with specified name.
	 * 
	 * @param name key for this field in the form
	 * @param field the field to attach to the form
	 * @param property the property name. */
	public void addAndBind( final String name, final Field field, final String property ) {
		addAndBind( name, field, property, null );
	}

	/** Add the input <code>field</field> content to the properties with specified name.
	 * 
	 * @param name key for this field in the form
	 * @param field the field to attach to the form
	 * @param property the property name
	 * @param converter a converter for additional operation to be performed on field content. */
	public void addAndBind( final String name, final Field field, final String property, final Converter converter ) {
		add( name, field );
		bind( field, property, converter );
	}

}
