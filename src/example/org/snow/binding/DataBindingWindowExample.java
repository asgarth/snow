package example.org.snow.binding;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.snow.binding.form.BindingForm;
import org.snow.binding.window.BindingApplicationWindow;
import org.snow.form.field.RadioField;
import org.snow.form.field.TextField;
import org.snow.form.layout.GridLayoutHelper;
import org.snow.window.footer.StandardFooter;

public class DataBindingWindowExample {

	private Person person;

	public DataBindingWindowExample( final Person person ) {
		this.person = person;
	}

	public void run() {
		final Display display = new Display();

		final BindingApplicationWindow app = new BindingApplicationWindow( display, "Binding Window Example", 400, 180 );

		app.openWithBinding( new Runnable() {

			@Override
			public void run() {
				/** init widgets */
				final Composite main = app.getContent();
				main.setLayout( new FillLayout() );

				final BindingForm<Person> form = new BindingForm<Person>( main, person );
				form.setFormLayoutHelper( new GridLayoutHelper() );

				final TextField name = new TextField( form );
				name.setCaption( "Name" );
				form.addAndBind( "name", name, "name" );

				final TextField surname = new TextField( form );
				surname.setCaption( "Surname" );
				form.addAndBind( "surname", surname, "surname" );

				final RadioField sex = new RadioField( form, new String[] { "M", "F" } );
				sex.setCaption( "Sesso" );
				form.addAndBind( "sex", sex, "sex" );

				/** init footer */
				final StandardFooter footer = new StandardFooter( app, "OK", "Cancel" );
				app.setFooter( footer );
				final Button ok = footer.getOk();
				ok.addSelectionListener( new SelectionAdapter() {

					public void widgetSelected( SelectionEvent e ) {
						System.out.println( "name: " + person.getName() );
						System.out.println( "surname: " + person.getSurname() );
						System.out.println( "sex: " + person.getSex() );

						/** close */
						app.close();
					}
				} );

				app.open();
			}
		} );
	}

	public static void main( String[] args ) {
		final Person person = new Person( "xxx", "yyy" );
		person.setSex( "F" );

		final DataBindingWindowExample test = new DataBindingWindowExample( person );
		test.run();
	}

	private static class Person {

		private String name;

		private String surname;

		private String sex;

		public Person( final String name, final String surname ) {
			this.name = name;
			this.surname = surname;
			this.sex = "-";
		}

		public String getName() {
			return name;
		}

		public void setName( String name ) {
			this.name = name;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname( String surname ) {
			this.surname = surname;
		}

		public String getSex() {
			return sex;
		}

		public void setSex( String sex ) {
			this.sex = sex;
		}

	}

}
