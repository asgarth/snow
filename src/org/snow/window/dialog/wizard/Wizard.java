package org.snow.window.dialog.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.snow.window.ApplicationDialog;
import org.snow.window.footer.WizardFooter;
import org.snow.window.header.TitleHeader;

public class Wizard extends ApplicationDialog {

	/** wizard page list */
	private final List<WizardPage> pages;

	/** current page index */
	private int index;

	/** stack layout panel */
	private TitleHeader header;

	private Composite panel;

	private StackLayout layout;

	/** forward, prev buttons */
	private Button next;

	private Button prev;

	public Wizard( final Shell parent, final String title, final int width, final int height ) {
		super( parent, title, width, height );

		pages = new ArrayList<WizardPage>();
		index = 0;

		init();
	}

	public void init() {
		/** init header */
		header = new TitleHeader( this, "Selezionare un paziente per visualizzarne i dati" );
		setHeader( header );

		/** init wizard composite */
		panel = getContent();
		layout = new StackLayout();
		panel.setLayout( layout );

		/** init footer */
		final WizardFooter footer = new WizardFooter( this, "Next", "Previous" );
		setFooter( footer );
		next = footer.getNext();
		next.addSelectionListener( new SelectionAdapter() {

			public void widgetSelected( SelectionEvent e ) {
				next();
			}
		} );

		prev = footer.getPrev();
		prev.addSelectionListener( new SelectionAdapter() {

			public void widgetSelected( SelectionEvent e ) {
				prev();
			}
		} );
	}

	public void open() {
		refresh();

		super.open();
	}

	public void addPage( final WizardPage page ) {
		pages.add( page );
		page.getControl().setParent( panel );

		if( pages.size() == 1 ) {
			layout.topControl = page.getControl();
			header.setTitle( page.getTitle() );
		}
	}

	private void next() {
		if( index == pages.size() - 1 && next.getText().equals( "Finish" ) ) {
			shell.close();
			return;
		}

		if( index < pages.size() - 1 )
			index++;

		refresh();
	}

	private void prev() {
		if( index > 0 )
			index--;

		refresh();
	}

	private void refresh() {
		// update button text for last page
		next.setText( index == pages.size() - 1 ? "Finish" : "Next" );

		// disable prev button on first page
		prev.setEnabled( index != 0 );

		// refresh header and wizard panel
		layout.topControl = pages.get( index ).getControl();
		header.setTitle( pages.get( index ).getTitle() );
		panel.layout();
	}

}
