package org.snow.util.notification;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

/** This class provides functionality to show a message in the system tray and associate an action with it.
 * 
 * <p>
 * Example usage: <code>
 * final TrayNotification tray = new TrayNotification( shell );
 * tray.setSelectionListener( new Listener() {
 * 			public void handleEvent( Event event ) {
 * 				System.out.print( "SELECTED" );
 * 				tray.dispose();
 * 			}
 * 		});
 * 
 * tray.show( "TITLE", "MESSAGE" );
 * </code>
 * 
 * 
 * The method {@link #dispose()} should be called at the end of the listener associated with instance of this class if the tray item must be
 * closed at the end of the listener action. */
public class TrayNotification {

	/** fade timeout */
	public final int timeout;

	/** widgets */
	private final Display display;

	private final Shell shell;

	private TrayItem item;

	private ToolTip tip;

	private Listener listener;

	/** Create an instance of this class. */
	public TrayNotification( final Shell shell ) {
		this( shell, -1 );
	}

	/** Create an instance of this class that automatically dispose after the specified timeout. */
	public TrayNotification( final Shell shell, final int timeout ) {
		this.shell = shell;
		this.display = shell.getDisplay();

		this.timeout = timeout;

		listener = null;
	}

	/** Open the notification icon and tootip in system tray.
	 * 
	 * @param title tooltip title
	 * @param message the message to be displayed */
	public void show( final String title, final String message ) {
		show( title, message, title );
	}

	/** Open the notification icon and tootip in system tray.
	 * 
	 * @param title tooltip title
	 * @param message the message to be displayed
	 * @param tooltip tooltip associated with tray icon */
	public void show( final String title, final String message, final String tooltip ) {
		display.asyncExec( new Runnable() {

			public void run() {
				if( !shell.isDisposed() ) {
					// init tray item and show tooltip
					final Tray tray = display.getSystemTray();
					if( tray == null ) {
						final MessageBox messageBox = new MessageBox( shell, SWT.OK | SWT.ICON_INFORMATION );
						messageBox.setText( title );
						messageBox.setMessage( message );
						messageBox.open();

						return;
					}

					item = new TrayItem( tray, SWT.NONE );
					item.setImage( shell.getImages()[0] );
					tip = new ToolTip( shell, SWT.BALLOON | SWT.ICON_INFORMATION );
					tip.setMessage( message );
					tip.setText( title );

					if( listener != null ) {
						tip.addListener( SWT.Selection, listener );
						item.addListener( SWT.Selection, listener );
					}

					item.setToolTip( tip );
					item.setToolTipText( tooltip );
					tip.setVisible( true );
				}
			}
		} );

		if( timeout > 0 ) {
			new Thread( new SleeperThread() ).start();
		}
	}

	/** Set the listener executed when this item is selected.
	 * 
	 * @param listener the listener. */
	public void setSelectionListener( final Listener listener ) {
		this.listener = listener;
	}

	/** Dispose this item. */
	public void dispose() {
		if( tip != null && !tip.isDisposed() )
			tip.dispose();

		if( item != null && !item.isDisposed() )
			item.dispose();
	}

	private class SleeperThread implements Runnable {

		public void run() {
			try {
				Thread.sleep( timeout );
			} catch( InterruptedException e ) {
			}

			display.asyncExec( new Runnable() {

				public void run() {
					dispose();
				}
			} );
		}

	}

}
