package example.org.snow.form;

import org.eclipse.swt.widgets.Display;
import org.snow.form.Form;
import org.snow.form.field.DateField;
import org.snow.form.layout.GridLayoutHelper;
import org.snow.window.ApplicationWindow;
import org.snow.window.footer.StandardFooter;
import org.snow.window.header.TitleHeader;

public class DateFieldExample {

	public static void main( final String[] args ) {
		final Display display = new Display();
		final ApplicationWindow app = new ApplicationWindow( display, "Date Field Example", 400, 320 );
		app.setHeader( new TitleHeader( app, "Date Example", "./resources/example/logo.png" ) );

		final Form form = new Form( app.getShell() );
		form.setFormLayoutHelper( new GridLayoutHelper() );
		app.setContent( form );

		final DateField date = new DateField( form, "Select Date" );
		form.add( "date", date );
		
		final DateField time = new DateField( form, "Select Date/Time", true );
		form.add( "time", time );

		app.setFooter( new StandardFooter( app, "OK", "Close" ) );

		app.open();
	}

}
