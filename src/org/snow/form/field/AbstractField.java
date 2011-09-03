package org.snow.form.field;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.snow.form.Form;


public abstract class AbstractField<T> implements Field<T> {

	/** SWT widgets */
	protected final Label label;


	protected AbstractField( final Form parent ) {
		label = new Label( parent, SWT.NONE );
	}

	public void setCaption( final String caption ) {
		label.setText( caption );
	}

	public String getCaption() {
		return label.getText();
	}

	public boolean isReadOnly() {
		return false;
	}

	public Object getCaptionLayout() {
		return label.getLayoutData();
	}
	
	public void setCaptionLayout( final Object layout) {
		label.setLayoutData( layout );
	}

	public Object getControlLayout() {
		return getControl().getLayoutData();
	}
	
	public void setControlLayout( final Object layout) {
		getControl().setLayoutData( layout );
	}

}
