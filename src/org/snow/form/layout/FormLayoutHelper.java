package org.snow.form.layout;

import org.eclipse.swt.widgets.Layout;


public interface FormLayoutHelper<T> {

	public Layout getLayout();

	public T getCaptionLayoutData();

	public T getControlLayoutData();

}
