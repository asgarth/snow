package org.snow.form.layout;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Layout;

public class GridLayoutHelper implements LayoutHelper<GridData> {

	private static final int CAPTION_STYLE = GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_CENTER;
	private static final int CONTROL_STYLE = GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_CENTER;

	private final Layout layout;

	public GridLayoutHelper() {
		this( 2 );
	}

	public GridLayoutHelper( final int columns ) {
		this( columns, false );
	}

	public GridLayoutHelper( final int columns, final boolean equalsColumns ) {
		layout = new GridLayout( columns, equalsColumns );
	}

	public Layout getLayout() {
		return layout;
	}

	public GridData getCaptionLayoutData() {
		return new GridData( CAPTION_STYLE );
	}

	public GridData getControlLayoutData() {
		return new GridData( CONTROL_STYLE );
	}

}
