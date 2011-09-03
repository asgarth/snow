package example.org.snow.prefs;

import java.util.LinkedList;
import java.util.List;

import net.miginfocom.swt.MigLayout;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.snow.action.Action;
import org.snow.prefs.PreferenceManager;
import org.snow.prefs.XMLPreferenceManager;
import org.snow.prefs.param.ParamCategory;
import org.snow.prefs.ui.ParamCategoryPanel;
import org.snow.prefs.ui.PreferenceDialog;
import org.snow.prefs.ui.PreferencePanel;


public class PreferenceDialogExample {

	public static void main( String[] args ) {
		final PreferenceManager<SimplePreferenceContainer> manager = XMLPreferenceManager.init( "resource/example/preferences.xml", SimplePreferenceContainer.getInstance() );
		manager.load();

		final Display display = new Display();
		final ParamCategoryPanel general = new ParamCategoryPanel( ( ParamCategory ) manager.get( "general" ), new MigLayout( "fillx, wrap 2" ), null );
		final List<PreferencePanel> panels = new LinkedList<PreferencePanel>();
		panels.add( general );

		final PreferenceDialog dialog = new PreferenceDialog( new Shell( display ), "prefs" );
		dialog.setSaveAction( new Action() {
			public boolean execute() {
				manager.root().getGeneral().putAll( general.getValues() );

				manager.save();
				return true;
			}
		} );

		dialog.open( panels );
	}

}
