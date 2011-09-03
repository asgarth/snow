package org.snow.form.field;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Control;


public interface Field<T> {

	public void setCaption( String caption );

	public String getCaption();

	public T getValue();

	public void setValue( T t );

	public boolean isEmpty();

	public Control getControl();

	public void addSelectionListener( SelectionListener listener );

	public void removeSelectionListener( SelectionListener listener );

	public Object getCaptionLayout();
	
	public void setCaptionLayout(Object layout);

	public Object getControlLayout();
	
	public void setControlLayout(Object layout);

}
