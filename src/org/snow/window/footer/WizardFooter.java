package org.snow.window.footer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.snow.window.ApplicationWindow;

public class WizardFooter extends Footer {

	private static final int HEIGHT = 50;

	/** widgets */
	private final Button prev;

	private final Button next;

	public WizardFooter( final ApplicationWindow parent, final String nextButtonText, final String prevButtonText ) {
		super( parent, SWT.NONE );
		setLayout( new FormLayout() );

		/** init layout data for main buttons */
		prev = new Button( this, SWT.PUSH );
		prev.setText( prevButtonText );
		final FormData prevData = new FormData();
		prevData.top = new FormAttachment( 0, 12 );
		prevData.left = new FormAttachment( 100, -185 );
		prevData.right = new FormAttachment( 100, -105 );
		prev.setLayoutData( prevData );

		next = new Button( this, SWT.PUSH );
		next.setText( nextButtonText );
		final FormData nextData = new FormData();
		nextData.top = new FormAttachment( 0, 12 );
		nextData.left = new FormAttachment( 100, -95 );
		nextData.right = new FormAttachment( 100, -15 );
		next.setLayoutData( nextData );
	}

	public Button getPrev() {
		return prev;
	}

	public Button getNext() {
		return next;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
