/**
 * 
 */
package github.graded_reader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Fragment;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
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
		//view.setOnTouchListener(new CustomTouchListener());
		String[] words = new String[2];
		words[0] = "The";
		words[1] = "man";
		
        ClickableSpan clickableSpan = null;

        String regex = "\\w+";
        Pattern p = Pattern.compile(regex);
        final SpannableString text = new SpannableString("Kiam naskigis Karlo, rozkolora kaj sana, liaj gepatroj estis ankorau tre junaj.");
        Matcher matcher = p.matcher(text);
        while (matcher.find()) {
            final int begin = matcher.start();
            final int end = matcher.end();
            clickableSpan = new ClickableSpan() {

                public void onClick(View v) {
                	Toast.makeText(getActivity(),"you clicked a word!",Toast.LENGTH_LONG).show();
                    String lword = (String) text.subSequence(begin, end).toString();


                }

            };

            text.setSpan(clickableSpan, begin, end, 0);
        }
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setText(text, BufferType.SPANNABLE);
		
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
		
		//view.setText("the");
		return view;
	}

	public void setText(String item) {

	}
	
//	public void setText(String item) {
//		TextView view = (TextView) getView().findViewById(R.id.chapterText);
//		//view.getSettings().setJavaScriptEnabled(true);
//		
//		String custom = "<html><body><h1>Hello, Webview!</h1></body></html>";
//		view.loadData(custom,"text/html","UTF-8");
//	}

	public boolean onTouch(View view, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
}
