package org.snow.util.layout;

import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/** An adapter that can easily be used to resize column in a {@link Table} according to specified weight. */
public class TableColumnAdapter extends ControlAdapter {

	private final Table table;

	private final int[] weight;

	/** Constructs an adapter for the specified table using the input weights for column layout.
	 * 
	 * @param table the table which column will be resized
	 * @param weight the weight of the columns.
	 */
	public TableColumnAdapter( final Table table, final int... weight ) {
		if( table.getColumnCount() != weight.length )
			throw new IllegalArgumentException( "Number of table column must be equals to number of grow weight specified." );

		this.table = table;
		this.weight = weight;
	}

	public void controlResized( final ControlEvent e ) {
		final Point size = table.getSize();

		for( int i = 0; i < table.getColumnCount(); i++ ) {
			TableColumn column = table.getColumn( i );
			column.setWidth( ( int ) ( ( ( double ) size.x ) / 100.0d * weight[i] ) );
		}
	}

}
