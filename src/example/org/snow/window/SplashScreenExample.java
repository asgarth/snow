package example.org.snow.window;

import org.eclipse.swt.widgets.Display;
import org.snow.window.SpalshScreen;

public class SplashScreenExample {

	public static void main( String[] args ) {
		final Display display = new Display();
		final SpalshScreen splash = new SpalshScreen( display, "./resources/example/splash.jpg" );

		display.asyncExec( new Runnable() {
			public void run() {
				sleep( 2000 );
				splash.setProgress( 50 );

				sleep( 2000 );
				splash.setProgress( 100 );

				splash.close();
			}
		} );

		splash.open();
	}
	
	private static void sleep(long millis) {
		try {
			Thread.sleep( millis );
		} catch( InterruptedException ignore ) { }
	}

}
