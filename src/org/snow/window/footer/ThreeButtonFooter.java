package org.snow.window.footer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.snow.action.Action;
import org.snow.window.ApplicationWindow;

public class ThreeButtonFooter extends StandardFooter {

	/** widgets */
	private final Button third;
	

	public ThreeButtonFooter( final ApplicationWindow parent, final String okButtonText, final String closeButtonText, final String thirdButtonText ) {
		super( parent, okButtonText, closeButtonText);
		setLayout( new FormLayout() );

		/** init layout data for third button */
		third = new Button( this, SWT.PUSH );
		third.setText( thirdButtonText );
		final FormData fData = new FormData();
		fData.top = new FormAttachment(0, 12);
		fData.left = new FormAttachment(0, 15);
		fData.right = new FormAttachment(0, 95);
		third.setLayoutData( fData );
	}
	
	public Button getThirdButton() {
		return third;
	}
	
	public void addThirdButtonAction( final Action action ) {
		getThirdButton().addSelectionListener( new SelectionAdapter() {
			public void widgetSelected( SelectionEvent e ) {
				action.execute();
			}
		} );
	}

}
