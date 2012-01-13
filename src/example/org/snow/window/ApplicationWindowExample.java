package example.org.snow.window;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.snow.window.ApplicationWindow;
import org.snow.window.footer.StandardFooter;
import org.snow.window.header.TitleHeader;

public class ApplicationWindowExample {

	public static void main( final String[] args ) {
		// create a new application windows
		ApplicationWindow app = new ApplicationWindow( new Display(), "Simple Application Window", 600, 400 );
		
		// add an header at the top
		app.setHeader( new TitleHeader( app, "Window Title", "./resources/example/logo.png" ) );

		// get the window content
		Composite main = app.getContent();
		
		// add widgets to this component as in standard SWT application
		// ...
		
		// add standard button to window
		app.setFooter( new StandardFooter( app, "OK", "Close" ) );
		
		// open and start the main loop
		app.open();
	}

}
