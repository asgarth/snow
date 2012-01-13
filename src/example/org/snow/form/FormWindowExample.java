package example.org.snow.form;

import org.eclipse.swt.widgets.Display;
import org.snow.form.Form;
import org.snow.form.field.CheckBoxField;
import org.snow.form.field.TextField;
import org.snow.form.layout.GridLayoutHelper;
import org.snow.window.ApplicationWindow;
import org.snow.window.footer.StandardFooter;
import org.snow.window.header.TitleHeader;

public class FormWindowExample {

	public static void main( final String[] args ) {
		final Display display = new Display();
		final ApplicationWindow app = new ApplicationWindow( display, "Form Window Example", 600, 380 );
		app.setHeader( new TitleHeader( app, "Title", "./resources/example/logo.png" ) );

		final Form form = new Form( app.getShell() );
		form.setFormLayoutHelper( new GridLayoutHelper() );
		app.setContent( form );

		final TextField name = new TextField( form, "Name" );
		form.add( "name", name );

		final TextField surname = new TextField( form );
		surname.setCaption( "Surname" );
		form.add( "surname", surname );

		final TextField field1 = new TextField( form );
		field1.setCaption( "field1" );
		form.add( "field1", field1 );

		final TextField field2 = new TextField( form );
		field2.setCaption( "field2" );
		form.add( "field2", field2 );

		final TextField field3 = new TextField( form );
		field3.setCaption( "field3" );
		form.add( "field3", field3 );

		final TextField field4 = new TextField( form );
		field4.setCaption( "field4" );
		form.add( "field4", field4 );

		final CheckBoxField field5 = new CheckBoxField( form, "field5 with front label", false );
		form.add( "field5", field5 );

		final CheckBoxField field6 = new CheckBoxField( form, "field6 with text on the right side", true );
		form.add( "field6", field6 );

		app.setFooter( new StandardFooter( app, "OK", "Close" ) );

		app.open();
	}

}
