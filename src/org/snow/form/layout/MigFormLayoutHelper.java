package org.snow.form.layout;

import net.miginfocom.swt.MigLayout;

import org.eclipse.swt.widgets.Layout;


public class MigFormLayoutHelper implements FormLayoutHelper<String> {

	private static final String DEFAULT_LAYOUT_CONSTRAINT = "fill, wrap 2";
	
	private static final String DEFAULT_ROW_CONSTRAINT = "[][grow]";

	private static final String CAPTION_DATA = "";

	private static final String CONTROL_DATA = "growx";
	
	private final Layout layout;


	public MigFormLayoutHelper() {
		this( DEFAULT_LAYOUT_CONSTRAINT, DEFAULT_ROW_CONSTRAINT );
	}
	
	public MigFormLayoutHelper( final String layoutConstraint, final String columnConstraint ) {
		this( layoutConstraint, columnConstraint, "" );
	}
			
	public MigFormLayoutHelper( final String layoutConstraint, final String columnConstraint, final String rowContraint ) {
		layout = new MigLayout( layoutConstraint, columnConstraint, rowContraint );
	}
	
	public Layout getLayout() {
		return layout;
	}

	public String getCaptionLayoutData() {
		return CAPTION_DATA;
	}

	public String getControlLayoutData() {
		return CONTROL_DATA;
	}

}
