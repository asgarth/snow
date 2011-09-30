package example.org.snow.window;

import org.eclipse.swt.widgets.Display;
import org.snow.window.SpalshScreen;

public class SplashScreenExample {

	public static void main( String[] args ) {
		final Display display = new Display();
		final SpalshScreen splash = new SpalshScreen( display, "./resource/example/splash.jpg" );

		display.asyncExec( new Runnable() {

			public void run() {
				try {
					Thread.sleep( 1000 );
				} catch( InterruptedException e ) {
				}
				splash.setProgress( 50 );

				try {
					Thread.sleep( 1000 );
				} catch( InterruptedException e ) {
				}
				splash.setProgress( 100 );

				splash.close();
			}
		} );

		splash.open();
	}

}
