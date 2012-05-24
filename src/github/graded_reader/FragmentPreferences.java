/**
 * 
 */
package github.graded_reader;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * @author smokey
 *
 */


public class FragmentPreferences extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getFragmentManager().beginTransaction().replace(android.R.id.content, 
				new SettingsPreferenceFragment()).commit();
	}
	
	public static class SettingsPreferenceFragment extends PreferenceFragment {
		
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preferences);
		}
	}
	
	
}
