package org.snow.binding.window;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.snow.window.ApplicationWindow;

public class BindingApplicationWindow extends ApplicationWindow implements BindingWindow {

	public BindingApplicationWindow( final Display display, final String title, final int width, final int height ) {
		super( display, title, width, height );
	}

	public BindingApplicationWindow( final Shell parent, final String title, final int width, final int height ) {
		super( parent, title, width, height );
	}

	public void openWithBinding( final Runnable runnable ) {
		Realm.runWithDefault( SWTObservables.getRealm( display ), runnable );
	}

}
