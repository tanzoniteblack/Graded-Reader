/**
 * 
 */
package github.graded_reader;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @author smokey
 *
 */
public class CustomTouchListener implements View.OnTouchListener {     
    public boolean onTouch(View view, MotionEvent motionEvent) {
    switch(motionEvent.getAction()){            
            case MotionEvent.ACTION_DOWN:
            ((TextView)view).setTextColor(0xFFFFFFFF); //white
                break;          
            case MotionEvent.ACTION_CANCEL:             
            case MotionEvent.ACTION_UP:
            ((TextView)view).setTextColor(0xFF000000); //black
                break;
    } 
        return false;   
    } 
}
