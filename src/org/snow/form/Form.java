package org.snow.form;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.snow.form.field.Field;
import org.snow.form.layout.LayoutHelper;
import org.snow.form.layout.GridLayoutHelper;

public class Form extends Composite {

	private final Map<String, Field> fieldMap;

	private LayoutHelper layoutHelper;

	public Form( final Composite parent ) {
		this( parent, SWT.NONE );
	}

	public Form( final Composite parent, final int style ) {
		super( parent, style );

		fieldMap = new HashMap<String, Field>();
		setFormLayoutHelper( new GridLayoutHelper() );
	}

	public void add( final String name, final Field field ) {
		fieldMap.put( name, field );

		if( layoutHelper != null ) {
			if( field.getLabelLayout() == null )
				field.setLabelLayout( layoutHelper.getCaptionLayoutData() );

			if( field.getControlLayout() == null )
				field.setControlLayout( layoutHelper.getControlLayoutData() );
		}
	}

	public Field get( final String name ) {
		return fieldMap.get( name );
	}

	public void setFormLayoutHelper( final LayoutHelper layoutHelper ) {
		if( fieldMap.size() > 0 )
			throw new UnsupportedOperationException( "Cannot add a new layout after adding fields to this form" );

		this.layoutHelper = layoutHelper;
		setLayout( layoutHelper.getLayout() );
	}

}
