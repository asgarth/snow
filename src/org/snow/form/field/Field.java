package org.snow.form.field;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Control;

public interface Field<T> {

	public void setCaption( String caption );
	public String getCaption();

	public T getValue();
	public void setValue( T value );

	public boolean isEmpty();
	public boolean isReadOnly();

	public Control getControl();

	public void addSelectionListener( SelectionListener listener );
	public void removeSelectionListener( SelectionListener listener );
	
	public void addModifyListener( ModifyListener listener );
	public void removeModifyListener( ModifyListener listener );

	public Object getLabelLayout();
	public void setLabelLayout( Object layout );

	public Object getControlLayout();
	public void setControlLayout( Object layout );

}
