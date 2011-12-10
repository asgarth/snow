package org.snow.form.field;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.snow.form.Form;

public class RadioField extends AbstractStringField {

	private final Composite group;

	private final Button[] buttons;

	private String value;

	public RadioField( final Form parent, final String[] message ) {
		this( parent, message, null );
	}

	public RadioField( final Form parent, final Image[] images ) {
		this( parent, null, images );
	}

	public RadioField( final Form parent, final String[] message, final Image[] images ) {
		super( parent );

		if( message == null && images == null )
			throw new IllegalArgumentException( "Message and image arrays are both null." );

		/** compute size */
		final int size = message != null ? message.length : images.length;

		/** init internal layout */
		group = new Composite( parent, SWT.NONE );
		final GridLayout layout = new GridLayout( size, true );
		layout.marginHeight = 0;
		group.setLayout( layout );

		buttons = new Button[size];
		for( int i = 0; i < size; i++ ) {
			final int index = i;
			buttons[index] = new Button( group, SWT.RADIO );

			if( message != null )
				buttons[index].setText( message[index] );

			if( images != null )
				buttons[index].setImage( images[index] );

			buttons[index].setLayoutData( new GridData( GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_CENTER ) );
			buttons[index].addSelectionListener( new SelectionListener() {

				public void widgetDefaultSelected( SelectionEvent e ) {
					value = buttons[index].getText();
				}

				public void widgetSelected( SelectionEvent e ) {
					value = buttons[index].getText();
				}
			} );
		}

		if( buttons.length > 0 )
			value = buttons[0].getText();
	}

	public String getValue() {
		return value;
	}

	public void setValue( final String value ) {
		this.value = value;
		for( int i = 0; i < buttons.length; i++ ) {
			if( buttons[i].getText().equals( value ) )
				buttons[i].setSelection( true );
		}
	}

	public int getSelectionIndex() {
		for( int i = 0; i < buttons.length; i++ )
			if( buttons[i].getSelection() )
				return i;

		return 0;
	}

	public void setSelectionIndex( final int index ) {
		for( int i = 0; i < buttons.length; i++ ) {
			buttons[i].setSelection( i == index );
			if( i == index )
				value = buttons[index].getText();
		}
	}

	public Control getControl() {
		return group;
	}

	public void addSelectionListener( final SelectionListener listener ) {
		for( Button b : buttons )
			b.addSelectionListener( listener );
	}

	public void removeSelectionListener( final SelectionListener listener ) {
		for( Button b : buttons )
			b.removeSelectionListener( listener );
	}
	
	public void addModifyListener( final ModifyListener listener ) {
		throw new UnsupportedOperationException( "Cannot attach a modify listener to this object" );
	}

	public void removeModifyListener( final ModifyListener listener ) {
		throw new UnsupportedOperationException( "Cannot remove a modify listener from this object" );
	}

}
