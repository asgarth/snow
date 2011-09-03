package org.snow.window.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;


public class AboutDialog extends Dialog {

	public static final int WIDTH = 400;

	public static final int HEIGHT = 300;

	private final Display display;

	private final String title;

	private Image image;

	private String text;


	public AboutDialog( final Shell shell, final String title ) {
		super( shell );
		this.display = shell.getDisplay();
		this.title = title;

		text = "";
	}

	public AboutDialog( final Shell shell, final String title , final String imagePath, final String text ) {
		this( shell, title );

		setImage( imagePath );
		setText( text );
	}

	public void setImage( final String path ) {
		image = new Image( display, "./resource/image/about.png" );
	}

	public void setText( final String text ) {
		this.text = text;
	}

	public void open () {
		final Shell shell = new Shell( getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL );
		shell.setText( title );
		shell.setSize( WIDTH, HEIGHT );
		shell.setLayout( new FormLayout() );

		final Button button = new Button( shell, SWT.PUSH );
		button.setText ( "Close" );
		button.setSize( 60, 25 );
		final FormData buttonData = new FormData();
		buttonData.left = new FormAttachment(76, 0);
		buttonData.right = new FormAttachment(100, -5);
		buttonData.bottom = new FormAttachment(100, -5);
		button.setLayoutData( buttonData );
		button.addSelectionListener( new SelectionAdapter() {
			public void widgetSelected( SelectionEvent e ) {
				shell.close ();
			}
		});

		final Link aboutText = new Link( shell, SWT.NONE );
		aboutText.setBackground( display.getSystemColor( SWT.COLOR_WHITE ) );
		aboutText.setText( text );
		final FormData aboutData = new FormData();
		aboutData.top = new FormAttachment(100, -75);
		aboutData.bottom = new FormAttachment(100, -8);
		aboutData.left = new FormAttachment(0, 10);
		aboutData.right = new FormAttachment(75, 0);
		aboutText.setLayoutData(aboutData);

		if( image != null ) {
			final Label label = new Label( shell, SWT.NONE );
			label.setImage( image );
			final FormData labelData = new FormData();
			labelData.top = new FormAttachment(0, 0);
			labelData.bottom = new FormAttachment(100, 0);
			labelData.left = new FormAttachment(0, 0);
			labelData.right = new FormAttachment(100, 0);
			label.setLayoutData(labelData);
		}

		/** open shell */
		final Rectangle shellRect = shell.getBounds();
		final Rectangle displayRect = display.getBounds();
		int x = ( displayRect.width - shellRect.width ) / 2;
		int y = ( displayRect.height - shellRect.height ) / 2;
		shell.setLocation( x, y );
		shell.open();

		while( ! shell.isDisposed() )
			if( ! display.readAndDispatch() )
				display.sleep();

		if( image != null && ! image.isDisposed() )
			image.dispose();
	}

}
