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

}
