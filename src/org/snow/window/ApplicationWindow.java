package org.snow.window;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.snow.util.Displays;
import org.snow.window.footer.Footer;
import org.snow.window.header.Header;

public class ApplicationWindow implements Window {

	public static final int WINDOW_STYLE = SWT.SHELL_TRIM;

	/** window dimensions */
	private final int width;

	private final int height;

	/** SWT var */
	protected final Display display;

	protected final Shell shell;

	/** widgets */
	protected Composite content;

	private Header header;

	private Footer footer;

	public ApplicationWindow( final Display display, final String title, final int width, final int height ) {
		this( display, null, WINDOW_STYLE, title, width, height );
	}

	public ApplicationWindow( final Shell parent, final String title, final int width, final int height ) {
		this( parent.getDisplay(), parent, WINDOW_STYLE, title, width, height );
	}

	protected ApplicationWindow( final Display display, final Shell parent, final int style, final String title, final int width, final int height ) {
		// init UI
		this.display = display;
		this.shell = ( parent == null ) ? new Shell( display, style ) : new Shell( parent, style );
		this.shell.setText( title );

		// check desired size with screen size
		final Point allowed = Displays.computeAllowedSize( shell.getDisplay(), width, height );
		this.width = allowed.x;
		this.height = allowed.y;
		this.shell.setSize( this.width, this.height );
	}

	public void open() {
		/** init ui layouts */
		init();

		/** open shell */
		shell.setLocation( Displays.getDisplayCenter( display, shell.getBounds() ) );
		shell.open();

		/** main loop */
		while( !shell.isDisposed() )
			if( !display.readAndDispatch() )
				display.sleep();
	}

	public void close() {
		shell.close();
	}

	public Display getDisplay() {
		return display;
	}

	public Shell getShell() {
		return shell;
	}

	public Composite getContent() {
		if( content == null ) {
			content = new Composite( shell, SWT.NONE );
			content.setLayout( new FillLayout() );
		}

		return content;
	}

	public void setContent( final Composite content ) {
		if( this.content != null && !this.content.isDisposed() )
			this.content.dispose();

		this.content = content;
	}

	public void setImage( final Image image ) {
		shell.setImage( image );
	}

	public void setImages( final Image[] images ) {
		shell.setImages( images );
	}

	public Menu getMenu() {
		return shell.getMenuBar();
	}

	public void setMenu( final Menu menu ) {
		shell.setMenuBar( menu );
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader( final Header header ) {
		this.header = header;
	}

	public Footer getFooter() {
		return footer;
	}

	public void setFooter( final Footer footer ) {
		this.footer = footer;
	}

	private void init() {
		/** init layout */
		shell.setLayout( new FormLayout() );

		/** init header */
		initHeader();

		/** init main composite layout */
		final FormData cData = new FormData();
		cData.top = new FormAttachment( header != null ? header : shell, 0 );
		cData.bottom = new FormAttachment( 100, footer != null ? -footer.getHeight() : 0 );
		cData.left = new FormAttachment( 0, 0 );
		cData.right = new FormAttachment( 100, 0 );
		getContent().setLayoutData( cData );

		/** init footer */
		initFooter();
	}

	private void initHeader() {
		if( header == null )
			return;

		final FormData headerData = new FormData();
		headerData.top = new FormAttachment( 0, 0 );
		if( header.isHeightFixed() )
			headerData.bottom = new FormAttachment( 0, header.getHeight() );

		headerData.left = new FormAttachment( 0, 0 );
		headerData.right = new FormAttachment( 100, 0 );
		header.setLayoutData( headerData );
	}

	private void initFooter() {
		if( footer == null )
			return;

		final FormData footerData = new FormData();
		footerData.top = new FormAttachment( content, 0 );
		footerData.left = new FormAttachment( 0, 0 );
		footerData.right = new FormAttachment( 100, 0 );
		footer.setLayoutData( footerData );
	}

}
