package org.snow.util.layout;

import org.eclipse.swt.layout.GridData;


/** A convenience layout data builder for {@link GridData} objects. See GridData documentation for additional details. */
public class GridDataBuilder {

	private int style;

	private int halign;

	private int valign;

	private int hspan;

	private int vspan;


	public GridDataBuilder( final int style ) {
		this( style ,1 ,1 );
	}
	
	public GridDataBuilder( final int style, final int hspan, final int vspan ) {
		this.style = style;
		this.hspan = hspan;
		this.vspan = vspan;
	}

	public GridDataBuilder() {
		style = 0;

		halign = GridData.HORIZONTAL_ALIGN_FILL;
		valign = GridData.VERTICAL_ALIGN_FILL;
		hspan = 1;
		vspan = 1;
	}

	public GridDataBuilder vtop() {
		valign = GridData.VERTICAL_ALIGN_BEGINNING;
		return this;
	}

	public GridDataBuilder vbottom() {
		valign = GridData.VERTICAL_ALIGN_END;
		return this;
	}

	public GridDataBuilder vcenter() {
		valign = GridData.VERTICAL_ALIGN_CENTER;
		return this;
	}

	public GridDataBuilder vfill() {
		valign = GridData.VERTICAL_ALIGN_FILL;
		return this;
	}

	public GridDataBuilder htop() {
		halign = GridData.HORIZONTAL_ALIGN_BEGINNING;
		return this;
	}

	public GridDataBuilder hbottom() {
		halign = GridData.HORIZONTAL_ALIGN_END;
		return this;
	}

	public GridDataBuilder hcenter() {
		halign = GridData.HORIZONTAL_ALIGN_CENTER;
		return this;
	}

	public GridDataBuilder hfill() {
		halign = GridData.HORIZONTAL_ALIGN_FILL;
		return this;
	}

	public GridDataBuilder hspan( final int span ) {
		hspan = span;
		return this;
	}

	public GridDataBuilder vspan( final int span ) {
		vspan = span;
		return this;
	}

	public GridData build() {
		if( style != 0 ) {
			final GridData data = new GridData( style );
			data.horizontalSpan = hspan;
			data.verticalSpan = vspan;
			return data;
		}

		return new GridData( halign, valign, halign == GridData.HORIZONTAL_ALIGN_FILL ? true : false, valign == GridData.VERTICAL_ALIGN_FILL ? true : false, hspan, vspan );
	}

}
