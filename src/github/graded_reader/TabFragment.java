/**
 * 
 */
package github.graded_reader;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author smokey
 *
 */
public class TabFragment extends Fragment {
//    private static final int GRAMMAR_STATE = 0x1;
//    private static final int VOCAB_STATE = 0x2;
//    
//    private int mTabState;
    
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
		
//		Button grammarTab = (Button) view.findViewById(R.id.grammarText);
//		Button vocabTab = (Button) view.findViewById(R.id.vocabularyText);
//		
//		grammarTab.setOnClickListener(new OnClickListener() {
//			//@Override
//			public void onClick(View v) {
//				goToGrammarView();
//			}
//		});
//		
//		vocabTab.setOnClickListener(new OnClickListener() {
//			//@Override
//			public void onClick(View v) {
//				goToVocabView();
//			}
//		});
		return view;
	}
	
//	public void goToGrammarView() {
//		if (mTabState != GRAMMAR_STATE) {
//			mTabState = GRAMMAR_STATE;
//			FragmentManager fm = getFragmentManager();
//			
//			if (fm != null) {
//				FragmentTransaction ft = fm.beginTransaction();
//				ft.replace(R.id.tab_fragment, new GrammarFragment());
//				ft.commit();
//			}
//		}
//	}
//	
//	public void goToVocabView() {
//		if (mTabState != VOCAB_STATE) {
//			mTabState = VOCAB_STATE;
//			FragmentManager fm = getFragmentManager();
//			
//			if (fm != null) {
//				FragmentTransaction ft = fm.beginTransaction();
//				ft.replace(R.id.tab_fragment, new VocabFragment());
//				ft.commit();
//			}
//		}
//	}

	public void setText(String item) {
		Log.v("winner!!!!",item);
		TextView view = (TextView) getView().findViewById(R.id.grammarText);
		view.setText(item);
	}
}
