/**
 * 
 */
package github.graded_reader;

import java.util.ArrayList;
import java.util.HashMap;

import github.graded_reader.Text.Chapter;
import github.graded_reader.Text.Sentence;
import github.graded_reader.Text.Word;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

/**
 * @author smokey
 *
 */
public class ChapterFragment extends Fragment {
	
	View view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("ChapterFragment", "Creation");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		//this is textview
		TextView view = (TextView) getView().findViewById(R.id.chapterText);
		Log.d("Chapter","creating text view");

		// read XML data
		Text karlo = new Text(this.getResources().openRawResource(R.raw.karlo));
		
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<String> trans = new ArrayList<String>();
		HashMap<Integer, String> sentTrans = new HashMap<Integer, String>();

		int indexCount = 0;
		for (Chapter chap : karlo.getChapters()) {
			for (ArrayList<Sentence> paragraph : chap.getParagraphs()) {
				words.add("\t");
				trans.add("\t");
				for (Sentence sentence : paragraph) {
					for (Word word : sentence.getWords()) {
						words.add(word.getWordText());
						trans.add(word.getWordTranslation());
						sentTrans.put(words.size() - 1, sentence.sentenceTranslation);
					}
				}
				words.add("\n");
				trans.add("\n");
				sentTrans.put(words.size() - 1, "");
				
			}
		}
		
//		//this will be input String[] and int[]
//		String[] words = new String[3];
//		words[0] = "Kiam"; words[1] = "naskiĝis"; words[2] = "Karlo.";
//		String[] trans= new String[3];
//		trans[0] = "when"; trans[1] = "was born"; trans[2] = "Karlo";
//		String sentence = "When Karlo was born";
		
		StringBuilder sb = new StringBuilder();
		for (String word : words)
			sb.append(word).append(" ");
		final SpannableString text = new SpannableString(sb.toString());
		
		
		int index = 0;
		for (int i=0; i<words.size(); i++) {
			TouchableSpan span = new TouchableSpan() {
	
				@Override
				public void updateDrawState(TextPaint tp) {
					tp.setUnderlineText(false);
					tp.setAntiAlias(true);
				}
	
				@Override
				public boolean onTouch(View widget, MotionEvent event) {
					TextView wordFrag = (TextView) widget.getRootView().findViewById(R.id.wordText);
					if (wordFrag != null) {
						wordFrag.setText(word + " : " + trans);
					}
					
					TextView sentFrag = (TextView) widget.getRootView().findViewById(R.id.sentenceText);
					if (sentFrag != null) {
						sentFrag.setText(sentence);
					}
//					Intent intent = new Intent(getActivity(),GradedReaderActivity.class);
//					intent.putExtra("node", trans);
//					intent.putExtra("word", word);
					
					return true;
				}
			};
			text.setSpan(span,index,index+words.get(i).length(),0);
			span.setWordNode(words.get(i),trans.get(i), sentTrans.get(i));
			index += (words.get(i).length() + 1); //the +1 is for the following space
		}
		view.setMovementMethod(new LinkTouchMovementMethod());
		view.setText(text,BufferType.SPANNABLE);
		
		//this will create a clickable span (ie: blue with underline text, etc)
//        ClickableSpan clickableSpan = null;
//		  while (matcher.find()) {
//            final int begin = matcher.start();
//            final int end = matcher.end();
//            clickableSpan = new ClickableSpan() {
//
//                public void onClick(View v) {
//                	Toast.makeText(getActivity(),"you clicked a word!",Toast.LENGTH_SHORT).show();
//                    String lword = (String) text.subSequence(begin, end).toString();
//
//
//                }
//
//            };
//
//            text.setSpan(clickableSpan, begin, end, 0);
//        }
//        view.setMovementMethod(LinkMovementMethod.getInstance());
//        view.setText(text, BufferType.SPANNABLE);
		
//		for (String word : words) {
//			Log.d("Chapter","setting text");
//			view.setText(word);
//			view.setOnTouchListener(new View.OnTouchListener() {
//				public boolean onTouch(View v, MotionEvent event) {
//					Intent intent = new Intent();
//					Toast.makeText(getActivity(),"you clicked a word!",Toast.LENGTH_LONG).show();
//					return true;
//				}
//			});
//		}
		
		//this is webview
//		WebView webview = (WebView) getView().findViewById(R.id.chapterText);
//		webview.getSettings().setJavaScriptEnabled(true);
//		String custom = "<html><body><h3>Hello, Webview</h3></body></html>";
//		webview.loadData(custom,"text/html","UTF-8");
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.chapter, container, false);
		
		return view;
	}

	public void setText(String item) {

	}


	public boolean onTouch(View view, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}
