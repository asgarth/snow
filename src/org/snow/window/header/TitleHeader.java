package org.snow.window.header;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.snow.util.Displays;
import org.snow.window.ApplicationWindow;

public class TitleHeader extends Header {

	public static final int HEIGHT = 56;
	public static final int IMAGE_SIZE = 48;

	/** widgets */
	private final Label label;
	private final Label text;


	public TitleHeader( final ApplicationWindow parent, final String message ) {
		this( parent, message, null );
	}

	public TitleHeader( final ApplicationWindow parent, final String message, final Image image ) {
		super( parent, SWT.NONE );
		final Display display = parent.getDisplay();
		
		final Label separator = new Label( this, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.SHADOW_OUT );
		
		/** init header */
		setLayout( new FormLayout() );
		setBackground( display.getSystemColor( SWT.COLOR_WHITE ) );

		/** init image label */
		label = new Label( this, SWT.NONE );
		label.setBackground( display.getSystemColor( SWT.COLOR_WHITE ) );
		if( image != null )
			label.setImage( image );

		final int vOffset = ( HEIGHT - IMAGE_SIZE ) / 2 - 1;
		final int hOffset = image != null ? IMAGE_SIZE + 3 : 10;

		final FormData labelData = new FormData();
		labelData.top = new FormAttachment( this, vOffset );
		labelData.bottom = new FormAttachment( separator, -vOffset );
		labelData.left = new FormAttachment( 0, 3 );
		labelData.right = new FormAttachment( 0, hOffset );
		label.setLayoutData( labelData );
		label.addDisposeListener( new DisposeListener() {
			public void widgetDisposed( DisposeEvent e ) {
				if( label.getImage() != null && ! label.getImage().isDisposed() )
					label.getImage().dispose();
			}
		} );

		/** init header message */
		text = new Label( this, SWT.NONE );
		text.setBackground( display.getSystemColor( SWT.COLOR_WHITE ) );

		final Font font = Displays.getSystemFontBold( display );
		text.setFont( font );
		text.setText( message );

		final FormData textData = new FormData();
		textData.top = new FormAttachment( label, 0, SWT.CENTER );
		textData.left = new FormAttachment( label, 10 );
		textData.right = new FormAttachment( 100, -3 );
		text.setLayoutData( textData );
		text.addDisposeListener( new DisposeListener() {
			public void widgetDisposed( DisposeEvent arg0 ) {
				if( font != null && ! font.isDisposed() )
					font.dispose();
			}
		} );

		/** add separator line */
		final FormData sepData = new FormData();
		sepData.bottom = new FormAttachment( 100, 0 );
		sepData.left = new FormAttachment( 0, 0 );
		sepData.right = new FormAttachment( 100, 0 );
		separator.setLayoutData( sepData );
	}

	public String getTitle() {
		return text.getText();
	}

	public void setTitle( final String message ) {
		text.setText( message );
	}

	public boolean isHeightFixed() {
		return true;
	}

	public int getHeight() {
		return HEIGHT;
	}

}
