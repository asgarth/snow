package org.snow.util.notification;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snow.util.cache.ColorCache;

public class NotificationPopup {

	/** default dim */
	public final int DEFAULT_WIDTH = 350;

	public final int DEFAULT_HEIGHT = 100;

	private final Logger logger = LoggerFactory.getLogger( this.getClass().getName() );

	/** RGB used for paint */
	// title foreground color
	public static final RGB _titleFgColor = new RGB( 40, 55, 67 );

	// text foreground color
	public static final RGB _fgColor = _titleFgColor;

	// shell gradient background color - top
	public static final RGB _bgFgGradient = new RGB( 226, 239, 249 );

	// shell gradient background color - bottom
	public static final RGB _bgBgGradient = new RGB( 177, 211, 243 );

	// shell border color
	public static final RGB _borderColor = new RGB( 40, 55, 67 );

	// how long the the tray popup is displayed after fading in (in milliseconds)
	private static final int DISPLAY_TIME = 4500;

	// how long each tick is when fading in (in ms)
	private static final int FADE_TIMER = 50;

	// how long each tick is when fading out (in ms)
	private static final int FADE_IN_STEP = 30;

	// how many tick steps we use when fading out
	private static final int FADE_OUT_STEP = 50;

	// how high the alpha value is when we have finished fading in
	private static final int FINAL_ALPHA = 225;

	/** SWT widgets */
	private final Shell shell;

	private final Image icon;

	private Image oldImage;

	private boolean closing = false;

	private final Font smallFont;

	private final Font largeFont;

	private int width;

	private int height;

	private int startx = -1;

	private int starty = -1;

	private final ColorCache cache = ColorCache.getInstance();

	/** popup message */
	private final String title;

	private final String message;

	/** Creates a notification popup with a specified title, message and image.
	 * 
	 * @param title popup title
	 * @param message popup message
	 * @param imagePath icon image path. */
	public NotificationPopup( final String title, final String message, final String imagePath ) {
		this.title = title;
		this.message = message;
		this.icon = new Image( Display.getDefault(), imagePath );
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;

		/** init widnow shell */
		shell = new Shell( Display.getDefault().getActiveShell(), SWT.NO_FOCUS | SWT.NO_TRIM );
		shell.setForeground( cache.getColor( _fgColor ) );
		shell.setBackgroundMode( SWT.INHERIT_DEFAULT );
		shell.setLayout( new FillLayout() );

		/** init font */
		Font font = shell.getFont();
		FontData fd = font.getFontData()[0];
		fd.height = 8;
		smallFont = new Font( Display.getDefault(), fd );

		font = shell.getFont();
		fd = font.getFontData()[0];
		fd.setStyle( SWT.BOLD );
		fd.height = 8;
		largeFont = new Font( Display.getDefault(), fd );

		/** add paint resize listener */
		shell.addListener( SWT.Resize, new Listener() {

			public void handleEvent( Event e ) {
				try {
					// get the size of the drawing area
					final Rectangle rect = shell.getClientArea();
					// create a new image with that size
					final Image newImage = new Image( Display.getDefault(), Math.max( 1, rect.width ), rect.height );
					// create a GC object we can use to draw with
					final GC gc = new GC( newImage );

					// fill background
					gc.setForeground( cache.getColor( _bgFgGradient ) );
					gc.setBackground( cache.getColor( _bgBgGradient ) );
					gc.fillGradientRectangle( rect.x, rect.y, rect.width, rect.height, true );

					// draw shell edge
					gc.setLineWidth( 2 );
					gc.setForeground( cache.getColor( _borderColor ) );
					gc.drawRectangle( rect.x + 1, rect.y + 1, rect.width - 2, rect.height - 2 );
					// dipose the GC object
					gc.dispose();

					// now set the background image on the shell
					shell.setBackgroundImage( newImage );

					// remember/dispose old used image
					if( oldImage != null )
						oldImage.dispose();

					oldImage = newImage;

				} catch( Exception ex ) {
					logger.error( "Error opening notification widget", ex );
				}
			}
		} );

		/** dispose cached resources */
		shell.addDisposeListener( new DisposeListener() {

			public void widgetDisposed( DisposeEvent arg0 ) {
				smallFont.dispose();
				largeFont.dispose();
			}
		} );
	}

	/** Display the popup shell. */
	public void open() {
		/** check for display errors */
		if( Display.getDefault().getActiveShell() == null || Display.getDefault().getActiveShell().getMonitor() == null )
			return;

		/** init shell components */
		final Composite composite = new Composite( shell, SWT.NONE );
		final GridLayout layout = new GridLayout( 2, false );
		layout.marginLeft = 5;
		layout.marginTop = 0;
		layout.marginRight = 5;
		layout.marginBottom = 5;

		composite.setLayout( layout );

		final MouseListener mouseListener = new DisposeMouseListener();

		final CLabel iconLabel = new CLabel( composite, SWT.NONE );
		iconLabel.setLayoutData( new GridData( GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_BEGINNING ) );
		iconLabel.setImage( icon );

		final CLabel titleLabel = new CLabel( composite, SWT.NONE );
		titleLabel.setLayoutData( new GridData( GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_CENTER ) );
		titleLabel.setText( title );
		titleLabel.setForeground( cache.getColor( _titleFgColor ) );
		titleLabel.setFont( largeFont );

		final Label textLabel = new Label( composite, SWT.WRAP );
		textLabel.setFont( smallFont );
		textLabel.setForeground( cache.getColor( _fgColor ) );
		textLabel.setText( message );
		final GridData gd = new GridData( GridData.FILL_BOTH );
		gd.horizontalSpan = 2;
		textLabel.setLayoutData( gd );

		iconLabel.addMouseListener( mouseListener );
		titleLabel.addMouseListener( mouseListener );
		textLabel.addMouseListener( mouseListener );

		/** compute window size */
		shell.setSize( width, height );

		/** compute window position on screen */
		final int x;
		final int y;
		if( startx == -1 && starty == -1 ) {
			final Rectangle clientArea = Display.getDefault().getActiveShell().getMonitor().getClientArea();
			x = clientArea.x + clientArea.width - width - 2;
			y = clientArea.y + clientArea.height - height - 2;
		} else {
			x = startx;
			y = starty;
		}

		shell.setLocation( x, y );
		shell.setAlpha( 0 );
		shell.setVisible( true );

		/** open */
		fadeIn( shell );
	}

	public void setLocation( final int x, final int y ) {
		this.startx = x;
		this.starty = y;
	}

	public void setSize( final int width, final int height ) {
		this.width = width;
		this.height = height;
	}

	public void close() {
		if( closing )
			return;

		closing = true;
		fadeOut( shell );
	}

	/** Fade in effect when opening shell. */
	private void fadeIn( final Shell shell ) {
		Runnable run = new Runnable() {

			public void run() {
				try {
					if( shell == null || shell.isDisposed() )
						return;

					int current = shell.getAlpha() + FADE_IN_STEP;

					// check if fadeIn is completed
					if( current > FINAL_ALPHA ) {
						shell.setAlpha( FINAL_ALPHA );
						startTimer( shell );
						return;
					}

					shell.setAlpha( current );

					Display.getDefault().timerExec( FADE_TIMER, this );

				} catch( Exception e ) {
					logger.error( "Error opening notification widget", e );
				}
			}

		};
		Display.getDefault().timerExec( FADE_TIMER, run );
	}

	/** Shell dispose timer. */
	private void startTimer( final Shell shell ) {
		Runnable run = new Runnable() {

			public void run() {
				try {
					if( shell == null || shell.isDisposed() )
						return;

					fadeOut( shell );

				} catch( Exception e ) {
					logger.error( "Error opening notification widget", e );
				}
			}

		};

		Display.getDefault().timerExec( DISPLAY_TIME, run );
	}

	/** Fade out effect when disposing shell. */
	private void fadeOut( final Shell shell ) {
		final Runnable run = new Runnable() {

			public void run() {
				try {
					if( shell == null || shell.isDisposed() )
						return;

					int current = shell.getAlpha() - FADE_OUT_STEP;

					// check if fadeOut is completed
					if( current <= 0 ) {
						shell.setAlpha( 0 );

						if( icon != null )
							icon.dispose();
						if( oldImage != null )
							oldImage.dispose();

						shell.dispose();
						return;
					}

					shell.setAlpha( current );

					Display.getDefault().timerExec( FADE_TIMER, this );

				} catch( Exception e ) {
					logger.error( "Error opening notification widget", e );
				}
			}
		};

		Display.getDefault().timerExec( FADE_TIMER, run );
	}

	private class DisposeMouseListener implements MouseListener {

		public void mouseUp( MouseEvent arg0 ) {
			close();
		}

		public void mouseDown( MouseEvent arg0 ) {
		}

		public void mouseDoubleClick( MouseEvent arg0 ) {
		}
	}

}
