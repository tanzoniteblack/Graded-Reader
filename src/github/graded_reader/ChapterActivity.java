package github.graded_reader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ChapterActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chapter);
		Log.v("chapter","onCreate ChapterActivity");
	}
}
