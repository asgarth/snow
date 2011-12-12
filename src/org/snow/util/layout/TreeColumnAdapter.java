package org.snow.util.layout;

import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

/** An adapter that can easily be used to resize column in a {@link Table} according to specified weight. */
public class TreeColumnAdapter extends ControlAdapter {

	private final Tree tree;

	private final int[] weight;

	/** Constructs an adapter for the specified tree using the input weights for column layout.
	 * 
	 * @param tree the tree which columns will be resized
	 * @param weight the weight of the columns.
	 */
	public TreeColumnAdapter( final Tree tree, final int ... weight ) {
		if( tree.getColumnCount() != weight.length )
			throw new IllegalArgumentException( "Number of tree columns must be equals to number of grow weight specified." );

		this.tree = tree;
		this.weight = weight;
	}

	public void controlResized( final ControlEvent e ) {
		final Point size = tree.getSize();

		for( int i = 0; i < tree.getColumnCount(); i++ ) {
			TreeColumn column = tree.getColumn( i );
			column.setWidth( ( int ) ( ( ( double ) size.x ) / 100.0d * weight[i] ) );
		}
	}

}
