package com.solutions.ray.expenser;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import Controller.Tracker.TransactionHandler;


public class MainActivity extends ActionBarActivity {
    TransactionHandler transHandler;
    TextView testTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        TextView txt1 = (TextView)findViewById(R.id.txt1);
        TextView txt2 = (TextView)findViewById(R.id.txt2);
        testTxt = (TextView)findViewById(R.id.testTxt);
        TextView dailyTotExpenseTxt = (TextView)findViewById(R.id.dailyTotExpenseTxt);
        TextView dailyTotIncomeTxt = (TextView)findViewById(R.id.dailyTotIncomeTxt);

        transHandler = new TransactionHandler();
        dailyTotExpenseTxt.setText(""+transHandler.getDailyExpTot(this));
        dailyTotIncomeTxt.setText(""+transHandler.getDailyIncTot(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void addExpense(View view){
        Intent intent = new Intent(this, AddExpenseActivity.class);
        startActivity(intent);
    }

    public void addIncome(View view){
        Intent intent = new Intent(this, AddIncomeActivity.class);
        startActivity(intent);
    }

    public void runTest(View view){
        Intent intent = new Intent(this, Test.class);
        startActivity(intent);
    }
}
