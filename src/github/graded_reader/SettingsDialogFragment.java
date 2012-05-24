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
	public static final String DEFAULT_S = "default";
	private boolean DEFAULT_TO_WORD = true;
	public static final String HIGHLIGHT_S = "highlight";
	private boolean HIGHLIGHT = true;
	
	private Activity parentActivity;
	
	public SettingsDialogFragment() {
		super();
	}
	
	public SettingsDialogFragment(Activity activity) {
		parentActivity = activity;
	}

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
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	this.setCancelable(true);
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        setStyle(style,theme);
    }

    @Override    
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	super.onCreateDialog(savedInstanceState);
    	if (savedInstanceState != null) {
			DEFAULT_TO_WORD = savedInstanceState.getBoolean(DEFAULT_S,true);
			HIGHLIGHT = savedInstanceState.getBoolean(HIGHLIGHT_S,true);
    	}
    		
    	final String options [] = {"Default to Word","Highlight Word/Sentence"};
    	AlertDialog.Builder b = 
    	    new AlertDialog.Builder(getActivity())
    	    .setTitle("Settings")
    	    .setMultiChoiceItems(options,
                        new boolean[]{DEFAULT_TO_WORD, HIGHLIGHT},
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

    /**
     * save settings when "Save" button is clicked
     */
    public void onClick(DialogInterface dialog, int which) {
    	OnSettingsDialogDoneListener act = (OnSettingsDialogDoneListener) getActivity();
        boolean cancelled = false;
    	if (which == AlertDialog.BUTTON_NEGATIVE) {
    		cancelled = true;
    	} else if (which == AlertDialog.BUTTON_POSITIVE) {
    		Log.d("SettingsDialogFrag","POSITIVE");
    	}
    	act.onDialogDone(getTag(), cancelled, "Alert dismissed");
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	Log.d("SettingsDialogFrag","SAVE_STATE");
    	outState.putBoolean(DEFAULT_S,DEFAULT_TO_WORD);
    	outState.putBoolean(HIGHLIGHT_S,HIGHLIGHT);
    }

    
}
