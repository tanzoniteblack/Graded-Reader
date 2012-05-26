/**
 * 
 */
package github.graded_reader;

import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author smokey
 *
 */
public abstract class TouchableSpan extends CharacterStyle implements UpdateAppearance {

	public String word;
	public String trans;
	public String sentence;

	/**
	 * can make text underlined or change link color
	 */
	@Override
	public abstract void updateDrawState(TextPaint tp);
	/**
	 * touch action associated with span
	 * @param widget
	 * @param event
	 * @return
	 */
	public abstract boolean onTouch(View widget, MotionEvent event);
	
	public void setWordNode(String word, String trans, String sentence) {
		this.word = word;
		this.trans = trans;
		this.sentence = sentence;
	}

}
