package org.snow.prefs.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.snow.prefs.param.Param;
import org.snow.prefs.param.Param.ParamType;
import org.snow.prefs.param.ParamCategory;
import org.snow.util.cache.ImageCache;

public class ParamPrefencePanel implements PreferencePanel {

	private final ParamCategory prefGroup;

	private final GridLayout layout;

	private final Image image;

	private Group group;

	private List<Control> controls;

	public ParamPrefencePanel( final ParamCategory prefGroup, final GridLayout layout, final String image ) {
		this.prefGroup = prefGroup;
		this.layout = layout;
		this.image = image != null ? ImageCache.getInstance().getImage( image ) : null;
	}

	public Group build( final Composite parent ) {
		/** create group */
		group = new Group( parent, SWT.NONE );
		group.setLayout( layout );

		/** init controls */
		final List<Param> params = prefGroup.getParams();
		controls = new ArrayList<Control>( params.size() );
		for( Param param : params ) {
			if( param.getType() == ParamType.TEXT || param.getType() == ParamType.NUMBER ) {
				final Label label = new Label( group, SWT.NONE );
				label.setText( param.getDesc() );
				final Text text = new Text( group, SWT.SINGLE | SWT.BORDER );
				text.setText( prefGroup.get( param.getName() ) );
				text.setLayoutData( new GridData( GridData.HORIZONTAL_ALIGN_FILL ) );
				controls.add( text );

				if( param.getType() == ParamType.NUMBER ) {
					text.addListener( SWT.Verify, new Listener() {

						public void handleEvent( final Event e ) {
							final char[] c = e.text.toCharArray();
							for( int i = 0; i < c.length; i++ ) {
								if( !Character.isDigit( c[i] ) ) {
									e.doit = false;
									return;
								}
							}
						}
					} );
				}

				continue;
			}

			if( param.getType() == ParamType.CHECK ) {
				final Button button = new Button( group, SWT.CHECK );
				button.setText( param.getDesc() );
				button.setSelection( prefGroup.getAsBoolean( param.getName() ) );
				button.setLayoutData( new GridData( GridData.HORIZONTAL_ALIGN_END ) );
				controls.add( button );

				continue;
			}
		}

		return group;
	}

	public Map<String, String> getValues() {
		final Map<String, String> map = new HashMap<String, String>();

		final Iterator<Param> pIterator = prefGroup.getParams().iterator();
		final Iterator<Control> cIterator = controls.iterator();
		while( pIterator.hasNext() && cIterator.hasNext() ) {
			final Param param = pIterator.next();
			final Control control = cIterator.next();

			if( control instanceof Button )
				map.put( param.getName(), String.valueOf( ( ( Button ) control ).getSelection() ) );
			else if( control instanceof Text )
				map.put( param.getName(), String.valueOf( ( ( Text ) control ).getText() ) );
		}

		return map;
	}

	public String getName() {
		return prefGroup.getName();
	}

	public Image getImage() {
		return image;
	}

}
