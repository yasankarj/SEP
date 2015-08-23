package com.solutions.ray.expenser;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class DailyExpenses extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_expenses);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        TextView tvx = (TextView)findViewById(R.id.textView3);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            String value = extras.getString("range");
            tvx.setText(value);
        }

        for (int i = 0; i < 5; i++)
        {
            TextView tv = new TextView(this);
            tv.setClickable(true);
            tv.setId(i + 5);
            tv.setText("Dynamic TextView" + i+"\n");
            linearLayout.addView(tv);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daily_expenses, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
