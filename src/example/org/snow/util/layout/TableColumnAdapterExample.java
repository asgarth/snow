package example.org.snow.util.layout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.snow.util.layout.TableColumnAdapter;

public class TableColumnAdapterExample {

	public static void main( final String[] args ) {
		final Display display = new Display();
		final Shell shell = new Shell( display );
		shell.setLayout( new FillLayout() );
		shell.setSize( 600, 400 );

		final Table table = new Table( shell, SWT.BORDER | SWT.V_SCROLL );
		table.setHeaderVisible( true );
		table.setLinesVisible( true );
		final TableColumn column1 = new TableColumn( table, SWT.NONE );
		column1.setText( "Column 1" );
		final TableColumn column2 = new TableColumn( table, SWT.NONE );
		column2.setText( "Column 2" );
		final TableColumn column3 = new TableColumn( table, SWT.NONE );
		column3.setText( "Column 3" );

		table.addControlListener( new TableColumnAdapter( table, 50, 30, 20 ) );

		shell.open();
		while( !shell.isDisposed() )
			if( !display.readAndDispatch() )
				display.sleep();

		display.dispose();
	}

}
