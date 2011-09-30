package org.snow.prefs.ui;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.snow.action.Action;
import org.snow.window.ApplicationDialog;
import org.snow.window.footer.StandardFooter;
import org.snow.window.header.ToolbarHeader;

public class PreferenceDialog extends ApplicationDialog {

	private final StackLayout layout;

	private Action saveAction;

	public PreferenceDialog( final Shell parent, final String title ) {
		this( parent, title, parent.getImages() );
	}

	public PreferenceDialog( final Shell parent, final String title, final Image image ) {
		this( parent, title, new Image[] { image } );
	}

	public PreferenceDialog( final Shell parent, final String title, final Image[] images ) {
		super( parent, title, 450, 450 );

		this.layout = new StackLayout();

		if( images != null )
			shell.setImages( images );
	}

	public void open( final List<PreferencePanel> panels ) {
		/** init header */
		final ToolbarHeader header = new ToolbarHeader( this, SWT.FLAT, display.getSystemColor( SWT.COLOR_WHITE ) );
		header.setShowText( true );
		setHeader( header );

		/** init stack layout */
		layout.marginWidth = layout.marginHeight = 5;
		final Composite composite = getContent();
		composite.setLayout( layout );

		/** add preferences panels */
		for( final PreferencePanel panel : panels ) {
			header.add( panel.getImage(), panel.getName(), new SelectionAdapter() {

				public void widgetSelected( SelectionEvent e ) {
					layout.topControl = panel.build( composite );
					composite.layout();
				}
			} );
		}
		if( panels.size() > 0 )
			layout.topControl = panels.get( 0 ).build( composite );

		/** init footer */
		final StandardFooter footer = new StandardFooter( this, "OK", "Cancel" );
		setFooter( footer );
		final Button ok = footer.getOk();
		ok.addSelectionListener( new SelectionAdapter() {

			public void widgetSelected( SelectionEvent e ) {
				if( saveAction != null )
					saveAction.execute();

				close();
			}
		} );

		super.open();
	}

	public void setSaveAction( final Action action ) {
		this.saveAction = action;
	}

}
