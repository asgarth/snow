package org.snow.window.header;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Label;
import org.snow.window.ApplicationWindow;

public class CoolbarHeader extends Header {

	/** widgets */
	private final CoolBar coolBar;

	public CoolbarHeader( final ApplicationWindow parent ) {
		this( parent, SWT.NONE, null );
	}

	public CoolbarHeader( final ApplicationWindow parent, final int style ) {
		this( parent, style, null );
	}

	public CoolbarHeader( final ApplicationWindow parent, final int style, final Color color ) {
		super( parent, SWT.NONE );

		final FormData data = new FormData();
		data.top = new FormAttachment( parent.getShell(), 0 );
		data.left = new FormAttachment( 0, 0 );
		data.right = new FormAttachment( 100, 0 );
		setLayoutData( data );

		setLayout( new FormLayout() );

		coolBar = new CoolBar( this, style | SWT.HORIZONTAL );
		final FormData tData = new FormData();
		tData.top = new FormAttachment( 0, 0 );
		tData.left = new FormAttachment( 0, 0 );
		tData.right = new FormAttachment( 100, 0 );
		coolBar.setLayoutData( tData );
		if( color != null )
			coolBar.setBackground( color );

		if( style == SWT.FLAT ) {
			final Label sep = new Label( this, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.SHADOW_OUT );
			final FormData sepData = new FormData();
			sepData.top = new FormAttachment( coolBar, 0 );
			sepData.left = new FormAttachment( 0, 0 );
			sepData.right = new FormAttachment( 100, 0 );
			sep.setLayoutData( sepData );
			sep.computeSize( sep.getSize().x, sep.getSize().y );
		}
	}

	public void add( final Control control ) {
		final CoolItem item = new CoolItem( coolBar, SWT.NONE );
		item.setControl( control );

		control.pack();
		Point size = control.getSize();
		item.setSize( item.computeSize( size.x, size.y ) );
	}

	public void addSeparator() {
		new CoolItem( coolBar, SWT.SEPARATOR );
	}

	public CoolBar getToolBar() {
		return coolBar;
	}

	public boolean isHeightFixed() {
		return false;
	}

	public int getHeight() {
		throw new UnsupportedOperationException( "operation not supported for this header" );
	}

}
