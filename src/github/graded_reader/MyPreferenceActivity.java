/**
 * 
 */
package github.graded_reader;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author smokey
 *
 */
public class MyPreferenceActivity extends PreferenceActivity {
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}
}
