package org.snow.window.footer;

import org.eclipse.swt.widgets.Composite;
import org.snow.window.ApplicationWindow;

public abstract class Footer extends Composite {

	public Footer( final ApplicationWindow parent, final int style ) {
		super( parent.getShell(), style );
	}

	public abstract int getHeight();

}
