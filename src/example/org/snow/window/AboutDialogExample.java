package example.org.snow.window;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.snow.window.dialog.AboutDialog;

public class AboutDialogExample {

	public static final String APP_NAME = "Exmaple App";
	
	public static void main(String[] args) {
		final Display display = new Display();
		final AboutDialog dialog = new AboutDialog(new Shell(display), "About " + APP_NAME, "./resources/example/about.png", "Version: " + 1.0);
		dialog.setTextColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
		dialog.setWebsite("http://www.example.com");
		dialog.setText("A simple about dialog with some text...");
		dialog.open();
	}

}
