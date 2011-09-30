package org.snow.window.header;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.snow.window.ApplicationWindow;

public abstract class Header extends Composite {

	public Header( final ApplicationWindow parent ) {
		this( parent, SWT.NONE );
	}

	public Header( final ApplicationWindow parent, final int style ) {
		super( parent.getShell(), style );
	}

	public abstract boolean isHeightFixed();

	public abstract int getHeight();

}
