package org.snow.window.dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.snow.util.Displays;

public class AboutDialog extends Dialog {

	public static final int WIDTH = 450;

	public static final int HEIGHT = 250;

	private final Display display;

	private final String title;

	private final Image image;

	private final String version;

	private String website = null;;
	
	private String text = null;

	private boolean showCloseButton = false;
	
	private Color color = null;

	public AboutDialog( final Shell shell, final String title, final String image, final String version ) {
		super( shell );
		this.display = shell.getDisplay();
		this.title = title;

		this.image = new Image( display, image );
		this.version = version;
	}
	
	public void setWebsite( final String website ) {
		this.website = website;
	}

	public void setText( final String text ) {
		this.text = text;
	}

	public void showCloseButton() {
		showCloseButton = true;
	}
	
	public void setTextColor( final Color color ) {
		this.color = color;
	}

	public void open() {
		final Shell shell = new Shell( getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL );
		shell.setText( title );
		shell.setSize( WIDTH, HEIGHT );
		//shell.setSize( image.getImageData().width, image.getImageData().height );

		shell.setBackgroundImage(image);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);

		shell.setLayout( new FormLayout() );

		final Label aboutVersion = new Label( shell, SWT.NONE );
		final Font font = Displays.getSystemFontBold( display );
		aboutVersion.setFont( font );
		aboutVersion.setText( version );
		if( color != null )
			aboutVersion.setForeground( color );
		final FormData versionData = new FormData();
		versionData.top = new FormAttachment( 0, 70 );
		versionData.left = new FormAttachment( 0, 133 );
		aboutVersion.setLayoutData( versionData );

		if( website != null && ! website.equals( "" ) ) {
			final Label aboutWebsite = new Label( shell, SWT.NONE );
			aboutWebsite.setText( website );
			if( color != null )
				aboutWebsite.setForeground( color );
			final FormData websiteData = new FormData();
			websiteData.top = new FormAttachment( 0, 92 );
			websiteData.left = new FormAttachment( 0, 133 );
			aboutWebsite.setLayoutData( websiteData );
		}
		
		if( text != null && ! text.equals( "" ) ) {
			final Label aboutText = new Label( shell, SWT.WRAP );
			aboutText.setText( text );
			if( color != null )
				aboutText.setForeground( color );
			final FormData textData = new FormData();
			textData.top = new FormAttachment( 100, -75 );
			textData.bottom = new FormAttachment( 100, -10 );
			textData.left = new FormAttachment( 0, 12 );
			textData.right = new FormAttachment( showCloseButton ? 76 : 100, -12 );
			aboutText.setLayoutData( textData );
		}

		if( showCloseButton ) {
			final Button button = new Button( shell, SWT.PUSH );
			button.setText( "Close" );
			button.setSize( 60, 25 );
			final FormData buttonData = new FormData();
			buttonData.left = new FormAttachment( 76, 0 );
			buttonData.right = new FormAttachment( 100, -5 );
			buttonData.bottom = new FormAttachment( 100, -5 );
			button.setLayoutData( buttonData );
			button.addSelectionListener( new SelectionAdapter() {
				public void widgetSelected( SelectionEvent e ) {
					shell.close();
				}
			} );
		}

		/** add dispose listener */
		shell.addDisposeListener( new DisposeListener() {
			public void widgetDisposed( DisposeEvent e ) {
				if( image != null && !image.isDisposed() )
					image.dispose();
				if( font != null && !font.isDisposed() )
					font.dispose();
			}
		} );

		/** open shell */
		shell.setLocation( Displays.getDisplayCenter( display, shell.getBounds() ) );
		shell.open();

		while( ! shell.isDisposed() )
			if( !display.readAndDispatch() )
				display.sleep();
	}

}
