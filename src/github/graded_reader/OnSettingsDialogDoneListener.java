/**
 * 
 */
package github.graded_reader;

/**
 * @author smokey
 *	When dialog is completed, it can report back to the Activity
 */
public interface OnSettingsDialogDoneListener {
	public void onDialogDone(String tag, boolean cancelled, CharSequence message);
}
