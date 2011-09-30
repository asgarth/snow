package org.snow.window;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class SpalshScreen implements Window {

	/** SWT var */
	private final Display display;

	private final Shell shell;

	private final Label label;

	private final ProgressBar bar;

	public SpalshScreen( final Display display, final String imagePath ) {
		this( display, imagePath, 100 );
	}

	public SpalshScreen( final Display display, final String imagePath, final int totalProgress ) {
		this.display = display;

		shell = new Shell( display, SWT.NO_TRIM );
		shell.setLayout( new FormLayout() );

		label = new Label( shell, SWT.NONE );
		label.setImage( new Image( display, imagePath ) );
		final FormData labelData = new FormData();
		labelData.right = new FormAttachment( 100, 0 );
		labelData.bottom = new FormAttachment( 100, 0 );
		label.setLayoutData( labelData );
		label.addDisposeListener( new DisposeListener() {

			public void widgetDisposed( DisposeEvent e ) {
				if( label.getImage() != null && !label.getImage().isDisposed() )
					label.getImage().dispose();
			}
		} );

		bar = new ProgressBar( shell, SWT.HORIZONTAL | SWT.SMOOTH );
		bar.setMaximum( totalProgress );
		final FormData progressData = new FormData();
		progressData.left = new FormAttachment( 0, 5 );
		progressData.right = new FormAttachment( 100, -5 );
		progressData.top = new FormAttachment( 100, -17 );
		progressData.bottom = new FormAttachment( 100, -4 );
		bar.setLayoutData( progressData );
		bar.moveAbove( label );

		shell.pack();
	}

	public Composite getContent() {
		return shell;
	}

	public void setContent( Composite content ) {
		throw new UnsupportedOperationException( "Operation not supported on splash screen." );
	}

	public void setTitle( final String title ) {
		shell.setText( title );
	}

	public void setIcon( final Image image ) {
		shell.setImage( image );
	}

	public void setIcons( final Image[] images ) {
		shell.setImages( images );
	}

	public void open() {
		final Rectangle splashRect = shell.getBounds();
		final Rectangle displayRect = display.getBounds();
		int x = ( displayRect.width - splashRect.width ) / 2;
		int y = ( displayRect.height - splashRect.height ) / 2;

		shell.setLocation( x, y );
		shell.open();

		/** main loop */
		while( !shell.isDisposed() )
			if( !display.readAndDispatch() )
				display.sleep();
	}

	public void close() {
		shell.close();
	}

	public void setProgress( final int progress ) {
		bar.setSelection( progress );
	}

	public void redraw() {
		label.update();
		bar.update();
	}

}
