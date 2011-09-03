package org.snow.window.header;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.snow.window.ApplicationWindow;

public class ToolbarHeader extends Header {

	/** widgets */
	private final ToolBar toolBar;

	private boolean showText;


	public ToolbarHeader( final ApplicationWindow parent ) {
		this( parent, SWT.NONE, null );
	}

	public ToolbarHeader( final ApplicationWindow parent, final int style ) {
		this( parent, style, null );
	}

	public ToolbarHeader( final ApplicationWindow parent, final int style, final Color color ) {
		super( parent, SWT.NONE );

		showText = false;

		final FormData data = new FormData();
		data.top = new FormAttachment( parent.getShell(), 0 );
		data.left = new FormAttachment( 0, 0 );
		data.right = new FormAttachment( 100, 0 );
		setLayoutData( data );

		setLayout( new FormLayout() );

		toolBar = new ToolBar( this, style | SWT.HORIZONTAL );
		final FormData tData = new FormData();
		tData.top = new FormAttachment( 0, 0 );
		tData.left = new FormAttachment( 0, 0 );
		tData.right = new FormAttachment( 100, 0 );
		toolBar.setLayoutData( tData );
		if( color != null )
			toolBar.setBackground( color );

		if( style == SWT.FLAT ) {
			final Label sep = new Label( this, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.SHADOW_OUT );
			final FormData sepData = new FormData();
			sepData.top = new FormAttachment( toolBar, 0 );
			sepData.left = new FormAttachment( 0, 0 );
			sepData.right = new FormAttachment( 100, 0 );
			sep.setLayoutData( sepData );
			sep.computeSize( sep.getSize().x, sep.getSize().y );
		}
	}

	public void add( final Image image, final String text, final SelectionListener listener ) {
		final ToolItem item = add( image, text );
		item.addSelectionListener( listener );
	}

	public ToolItem add( final Image image, final String text ) {
		final ToolItem item = new ToolItem( toolBar, SWT.PUSH );
		item.setImage( image );
		if( isShowText() )
			item.setText( text );
		else
			item.setToolTipText( text );

		return item;
	}

	public void add( final Control control, final int width ) {
		final ToolItem sep = new ToolItem( toolBar, SWT.SEPARATOR );
		sep.setControl( control );
		sep.setWidth( width );
	}

	public void addSeparator() {
		new ToolItem( toolBar, SWT.SEPARATOR );
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public boolean isShowText() {
		return showText;
	}

	public void setShowText( final boolean showText ) {
		this.showText = showText;
	}

	public boolean isHeightFixed() {
		return false;
	}

	public int getHeight() {
		throw new UnsupportedOperationException( "operation not supported for this header" );
	}

}
