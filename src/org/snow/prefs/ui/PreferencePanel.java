package org.snow.prefs.ui;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;


public interface PreferencePanel {

	public Image getImage();

	public String getName();

	public Group build( Composite parent );

}
