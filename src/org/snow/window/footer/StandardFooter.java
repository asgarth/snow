package org.snow.window.footer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.snow.action.Action;
import org.snow.window.ApplicationWindow;

public class StandardFooter extends Footer {

	private static final int HEIGHT = 50;

	/** widgets */
	private final Button ok;
	private final Button cancel;


	public StandardFooter( final ApplicationWindow parent, final String okButtonText, final String closeButtonText ) {
		super( parent, SWT.NONE);
		setLayout( new FormLayout() );

		/** init layout data for main buttons */
		ok = new Button( this, SWT.PUSH );
		ok.setText( okButtonText );
		final FormData okData = new FormData();
		okData.top = new FormAttachment(0, 12);
		okData.left = new FormAttachment(100, -185);
		okData.right = new FormAttachment(100, -105);
		ok.setLayoutData( okData );

		cancel = new Button( this, SWT.PUSH );
		cancel.setText( closeButtonText );
		final FormData cancelData = new FormData();
		cancelData.top = new FormAttachment(0, 12);
		cancelData.left = new FormAttachment(100, -95);
		cancelData.right = new FormAttachment(100, -15);
		cancel.setLayoutData( cancelData );

		final Shell shell = parent.getShell();
		shell.setDefaultButton( ok );
		ok.setFocus();
		cancel.addSelectionListener( new SelectionAdapter() {
			public void widgetSelected( SelectionEvent e ) {
				shell.close();
			}
		} );
	}

	public Button getOk() {
		return ok;
	}
	
	public void addOkAction( final Action action ) {
		getOk().addSelectionListener( new SelectionAdapter() {
			public void widgetSelected( SelectionEvent e ) {
				action.execute();
			}
		} );
	}

	public Button getCancel() {
		return cancel;
	}
	
	public void addCancelAction( final Action action ) {
		getCancel().addSelectionListener( new SelectionAdapter() {
			public void widgetSelected( SelectionEvent e ) {
				action.execute();
			}
		} );
	}

	public int getHeight() {
		return HEIGHT;
	}

}
