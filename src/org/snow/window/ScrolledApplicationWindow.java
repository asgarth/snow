package org.snow.window;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ScrolledApplicationWindow extends ApplicationWindow implements Window {

	protected ScrolledApplicationWindow( final Display display, final String title, final int width, final int height ) {
		super( display, title, width, height );
	}

	protected ScrolledApplicationWindow( final Shell parent, final String title, final int width, final int height ) {
		super( parent, title, width, height );
	}

	public Composite getContent() {
		if( content == null ) {
			content = new ScrolledComposite( shell, SWT.NONE );
			content.setLayout( new FillLayout() );
		}

		return content;
	}

	protected ScrolledComposite getScrolledContent() {
		return ( ScrolledComposite ) getContent();
	}

}
