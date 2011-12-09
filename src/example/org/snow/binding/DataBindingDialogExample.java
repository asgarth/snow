package example.org.snow.binding;

import java.util.Date;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.snow.binding.form.BindingForm;
import org.snow.binding.window.BindingApplicationDialog;
import org.snow.form.field.CheckBoxField;
import org.snow.form.field.DateField;
import org.snow.form.field.RadioField;
import org.snow.form.field.TextField;
import org.snow.form.layout.GridLayoutHelper;
import org.snow.window.footer.StandardFooter;
import org.snow.window.header.TitleHeader;

public class DataBindingDialogExample {

	private Person person;

	public DataBindingDialogExample( final Person person ) {
		this.person = person;
	}

	public void run() {
		final Display display = new Display();

		final BindingApplicationDialog dialog = new BindingApplicationDialog( new Shell( display ), "Binding Dialog Example", 460, 280 );
		dialog.setHeader( new TitleHeader( dialog, "Binding Dialog Example", "./resources/example/logo.png" ) );

		dialog.openWithBinding( new Runnable() {

			@Override
			public void run() {
				/** init widgets */
				final Composite main = dialog.getContent();
				main.setLayout( new FillLayout() );

				final BindingForm<Person> form = new BindingForm<Person>( main, person );
				form.setFormLayoutHelper( new GridLayoutHelper() );

				final TextField name = new TextField( form, "Name" );
				form.addAndBind( "name", name, "name" );

				final TextField surname = new TextField( form, "Surname" );
				form.addAndBind( "surname", surname, "surname" );

				final RadioField sex = new RadioField( form, new String[] { "M", "F" } );
				sex.setCaption( "Radio Field" );
				form.addAndBind( "sex", sex, "sex" );

				final CheckBoxField check = new CheckBoxField( form, "Check" );
				form.addAndBind( "check", check, "check" );
				
				final DateField date = new DateField( form, "Date" );
				form.addAndBind( "date", date, "date" );

				/** init footer */
				final StandardFooter footer = new StandardFooter( dialog, "OK", "Cancel" );
				dialog.setFooter( footer );
				final Button ok = footer.getOk();
				ok.addSelectionListener( new SelectionAdapter() {

					public void widgetSelected( SelectionEvent e ) {
						System.out.println( "name: " + person.getName() );
						System.out.println( "surname: " + person.getSurname() );
						System.out.println( "sex: " + person.getSex() );
						System.out.println( "check: " + person.getCheck() );
						System.out.println( "date: " + person.getDate() );

						/** close */
						dialog.close();
					}
				} );

				dialog.open();
			}
		} );
	}

	public static void main( String[] args ) {
		final Person person = new Person( "xxx", "yyy" );
		person.setSex( "F" );
		person.setCheck( "true" );

		final DataBindingDialogExample test = new DataBindingDialogExample( person );
		test.run();
	}

	private static class Person {

		private String name;

		private String surname;

		private String sex;

		private String check;
		
		private Date date;

		public Person( final String name, final String surname ) {
			this.name = name;
			this.surname = surname;
			this.sex = "-";
			this.check = "-";
			this.date = new Date();
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

		public String getCheck() {
			return check;
		}

		public void setCheck( String check ) {
			this.check = check;
		}
		
		public Date getDate() {
			return date;
		}
		
		public void setDate( Date date ) {
			this.date = date;
		}

	}

}
