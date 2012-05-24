package github.graded_reader;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class GradedReaderActivity extends Activity
				implements OnSettingsDialogDoneListener {

	public static final String PREFERENCES = "Preferences";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//retrieve preferences (last chapter)
		SharedPreferences settings = getSharedPreferences(PREFERENCES, 0);
		int currentChapter = settings.getInt("currentChapter", 0);
		setCurrentChapter(currentChapter);
		
		//setup for tabs
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
				
		//grammar tab
		Tab tab = actionBar.newTab()
				.setText("Grammar")
				.setTabListener(new MyTabListener<GrammarFragment>(
						this,"grammar",GrammarFragment.class));
		actionBar.addTab(tab);

		//vocab tab
		tab = actionBar.newTab()
				.setText("Vocab")
				.setTabListener(new MyTabListener<VocabFragment>(
						this,"vocabulary",VocabFragment.class));
		actionBar.addTab(tab);

		//translate word tab
		tab = actionBar.newTab()
				.setText("Word")
				.setTabListener(new MyTabListener<WordFragment>(
						this,"word",WordFragment.class));
		actionBar.addTab(tab);

		//translate sentence tab
		tab = actionBar.newTab()
				.setText("Sentence")
				.setTabListener(new MyTabListener<SentenceFragment>(
						this,"translate_sentence",SentenceFragment.class));
		actionBar.addTab(tab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_menu, menu);
		return true;
	}

		
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			Log.v("Settings", "Launch");
			this.createSettingsDialog();
			break;
		default:
			break;
		}
		return true;
	}

	
	private void createSettingsDialog() {
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		
		SettingsDialogFragment adf = SettingsDialogFragment.newInstance("Settings");
		
		adf.show(ft,"Settings Menu");
	}
	
	public void onDialogDone(String tag, boolean cancelled, CharSequence message) {
		String s = tag + " responds with: " + message;
		if (cancelled)
			s = tag + " was cancelled by the user.";
		Log.v("GradedReader","s");
	}
	
	protected void onStop() {
		super.onStop();
		
		SharedPreferences settings = getSharedPreferences(PREFERENCES, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("currentChapter",getCurrentChapter());
		editor.commit();
	}
	
	protected void onPause() {
		super.onPause();
		
		//save preferences
		SharedPreferences settings = getSharedPreferences(PREFERENCES,0);
		SharedPreferences.Editor editor = settings.edit();
		
		int mChapter = 4; //this is the chapter number
		editor.putInt("currentChapter",mChapter);
		editor.commit();
	}
	
	protected void onResume() {
		super.onResume();
	}
	
	private int getCurrentChapter() {
		return 1;
	}
	
	private void setCurrentChapter(int currentChapter) {
		Toast.makeText(this,"The current chapter is " + currentChapter,Toast.LENGTH_LONG).show();
	}
}



