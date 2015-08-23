package Utils;

import android.content.Context;
import android.widget.Toast;

import java.util.TimerTask;

/**
 * Created by Yasanka on 2015-08-23.
 */
public class TimeBasedActivity extends TimerTask {
    Context context;
    public TimeBasedActivity(Context context){
        this.context = context;
    }
    public void run(){
        Toast.makeText(context.getApplicationContext(), "Booted, Time is 11.00 !!!",
                Toast.LENGTH_LONG).show();
    }
}
