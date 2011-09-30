package org.snow.window.footer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FillLayout;
import org.snow.window.ApplicationWindow;

public class StatusBarFooter extends Footer {

	private static final int HEIGHT = 20;

	/** widgets */
	private final CLabel status;

	public StatusBarFooter( final ApplicationWindow parent ) {
		this( parent, SWT.BORDER | SWT.SHADOW_OUT );
	}

	public StatusBarFooter( final ApplicationWindow parent, final int style ) {
		super( parent, style );
		final FillLayout layout = new FillLayout();
		layout.marginWidth = 5;
		setLayout( layout );

		status = new CLabel( this, SWT.NONE );
	}

	public void log( final String message ) {
		status.setText( message );
	}

	public void error( final String message ) {
		status.setText( message );
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
