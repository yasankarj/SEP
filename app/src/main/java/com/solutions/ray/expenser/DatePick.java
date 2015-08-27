package com.solutions.ray.expenser;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DatePick extends ActionBarActivity {

    String pickedDate = "";
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_pick);

        datePicker = (DatePicker)findViewById(R.id.dpResult);
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        pickedDate = df.format(dt);

        Calendar calendar = Calendar.getInstance();
        MyOnDateChangeListener onDateChangeListener = new MyOnDateChangeListener();
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), onDateChangeListener);
    }

    public class MyOnDateChangeListener implements DatePicker.OnDateChangedListener {
        @Override
        public void onDateChanged(DatePicker view, int year, int month, int day) {
            int mon=month+1;
            if(mon<10)
            pickedDate = year+"-0"+mon+"-"+day;

            else
                pickedDate = year+"-"+mon+"-"+day;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_date_pick, menu);
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

    public void onD(View view){

        Intent returnIntent = getIntent();
        returnIntent.putExtra("result",pickedDate);
        setResult(RESULT_OK,returnIntent);
        finish();
    }
}
