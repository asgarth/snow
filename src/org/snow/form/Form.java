package org.snow.form;

import java.util.HashMap;
import java.util.Map;

import net.miginfocom.swt.MigLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.snow.form.field.Field;
import org.snow.form.layout.FormLayoutHelper;

public class Form extends Composite {

	private final Map<String, Field> fieldMap;

	private FormLayoutHelper layoutHelper;


	public Form( final Composite parent ) {
		super( parent, SWT.NONE);

		layoutHelper = null;
		fieldMap = new HashMap<String, Field>();
	}

	public void add( final String name, final Field field ) {
		fieldMap.put( name, field );

		if( layoutHelper != null ) {
			if( field.getCaptionLayout() == null )
				field.setCaptionLayout( layoutHelper.getCaptionLayoutData() );

			if( field.getControlLayout() == null )
				field.setControlLayout( layoutHelper.getControlLayoutData() );
		}
	}

	public Field get( final String name ) {
		return fieldMap.get( name );
	}

	public void setFormLayoutHelper( final FormLayoutHelper layoutHelper ) {
		if( fieldMap.size() > 0 )
			throw new UnsupportedOperationException( "cannot add a new layout after adding fields to this form" );

		this.layoutHelper = layoutHelper;
		setLayout( layoutHelper.getLayout() );
	}

}
