package org.snow.window;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class ApplicationDialog extends ApplicationWindow implements Window {

	public ApplicationDialog( final Shell parent, final String title, final int width, final int height ) {
		super( parent.getDisplay(), parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL, title, width, height );
	}

}
