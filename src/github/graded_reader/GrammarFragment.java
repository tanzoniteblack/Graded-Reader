/**
 * 
 */
package github.graded_reader;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author smokey
 *
 */
public class GrammarFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.grammar, container, false);
		//setText("Text");
		return view;
	}

	public void setText(String item) {
		TextView view = (TextView) getView().findViewById(R.id.grammarText);
		view.setText(item);
	}
}
