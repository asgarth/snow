package org.snow.window.dialog.wizard;

import org.eclipse.swt.widgets.Control;

public abstract class WizardPageModel<T> extends WizardPage {

	private T model;

	public WizardPageModel( final String title, final Control control, final T model ) {
		super( title, control );
		this.model = model;
	}

	public abstract void update();

	public T getModel() {
		return model;
	}

}
