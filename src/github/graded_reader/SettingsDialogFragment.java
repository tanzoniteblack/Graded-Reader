/**
 * 
 */
package github.graded_reader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * @author smokey
 *
 */
public class SettingsDialogFragment extends DialogFragment 
							implements DialogInterface.OnClickListener {
	public static SettingsDialogFragment
    newInstance(String message)
	{
		SettingsDialogFragment adf = new SettingsDialogFragment();
		Bundle bundle = new Bundle();
		bundle.putString("alert-message", message);
		adf.setArguments(bundle);
		
		return adf;
	}
	
	@Override
	public void onAttach(Activity act) {
		// If the activity we're being attached to has
		// not implemented the OnDialogDoneListener
		try {
            OnSettingsDialogDoneListener test = (OnSettingsDialogDoneListener)act;
		}
		catch(ClassCastException cce) {
			// Here is where we fail gracefully.
			Log.e("GradedReaderActivity", "Activity is not listening");
		}
		super.onAttach(act);
	}

    @Override    
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	this.setCancelable(true);
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        setStyle(style,theme);
    }

    @Override    
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	final String options [] = {"Default to Word","Highlight Word/Sentence"};
    	AlertDialog.Builder b = 
    	    new AlertDialog.Builder(getActivity())
    	    .setTitle("Settings")
    	    .setMultiChoiceItems(options,
                        new boolean[]{true, false},
                        new DialogInterface.OnMultiChoiceClickListener() {
                            public void onClick(DialogInterface dialog, int which,
                                    boolean isChecked) {
                            	if (isChecked) {
                            		Log.v("Settings", options[which]);
                            	}
                            }})
    	    .setPositiveButton("Save", this)
    	    .setNegativeButton("Cancel", this);
    	   // .setMessage(this.getArguments().getString("alert-message"));
    	return b.create();
    }

    public void onClick(DialogInterface dialog, int which)
    {
    	OnSettingsDialogDoneListener act = (OnSettingsDialogDoneListener) getActivity();
        boolean cancelled = false;
    	if (which == AlertDialog.BUTTON_NEGATIVE)
    	{
    		cancelled = true;
    	}
    	act.onDialogDone(getTag(), cancelled, "Alert dismissed");
    }
}
