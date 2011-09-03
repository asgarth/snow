package org.snow.window;

import org.eclipse.swt.widgets.Composite;

public interface Window {

	public void open();

	public void close();

	public Composite getContent();
	
	public void setContent( final Composite content );

}
