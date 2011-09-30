package org.snow.form.layout;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Layout;

public class GridLayoutHelper implements LayoutHelper<GridData> {

	private static final Layout layout = new GridLayout( 2, false );

	private static final GridData CAPTION_DATA = new GridData( GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_CENTER );

	private static final GridData CONTROL_DATA = new GridData( GridData.FILL_HORIZONTAL );

	public Layout getLayout() {
		return layout;
	}

	public GridData getCaptionLayoutData() {
		return CAPTION_DATA;
	}

	public GridData getControlLayoutData() {
		return CONTROL_DATA;
	}

}
