package github.graded_reader;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class GradedReaderActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
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
			Toast.makeText(this, "You want to change the settings!", Toast.LENGTH_SHORT)
			.show();
			break;
		default:
			break;
		}

		return true;
	}
}