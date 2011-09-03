package org.snow.window.dialog.wizard;

import org.eclipse.swt.widgets.Control;

public class WizardPage {

	private final String title;

	private final Control control;


	public WizardPage( final String title, final Control control ) {
		this.title = title;
		this.control = control;
	}

	public String getTitle() {
		return title;
	}

	public Control getControl() {
		return control;
	}

}
