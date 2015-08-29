package com.solutions.ray.expenser;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import Controller.Tracker.TransactionHandler;

public class MainActivity extends ActionBarActivity {
    TransactionHandler transHandler;
    TextView testTxt;
    TextView dailyTotExpenseTxt;
    TextView dailyTotIncomeTxt;

    TextView weeklyTotExpenseTxt;
    TextView monthlyTotExpenseTxt;
    TextView annualExpTxt;

    SimpleDateFormat df;
    SimpleDateFormat weekF ;
    Date dt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        dailyTotExpenseTxt = (TextView)findViewById(R.id.dailyExpTxt);
        dailyTotIncomeTxt = (TextView)findViewById(R.id.dailyIncTxt);

        weeklyTotExpenseTxt = (TextView)findViewById(R.id.weeklyExpTxt);
        monthlyTotExpenseTxt = (TextView) findViewById(R.id.monthlyExpTxt);
        annualExpTxt = (TextView)findViewById(R.id.annualExpTxt);
        transHandler = new TransactionHandler();


        weekF = new SimpleDateFormat("W");
        dt = new Date();
        df= new SimpleDateFormat("yyyy-MM-dd"); //Date is stored as a text in format yyyy-MM-dd hh:mm WW
        dailyTotExpenseTxt.setText(""+transHandler.getDailyExpTot(this,df.format(dt),"daily"));
        dailyTotIncomeTxt.setText(""+transHandler.getDailyIncTot(this));

        df= new SimpleDateFormat("yyyy-MM");
        monthlyTotExpenseTxt.setText(""+transHandler.getDailyExpTot(this,df.format(dt),"monthly"));

        weeklyTotExpenseTxt.setText("" + transHandler.getDailyExpTot(this, weekF.format(dt), "week"));
        df= new SimpleDateFormat("yyyy");
        annualExpTxt.setText(""+transHandler.getDailyExpTot(this,df.format(dt),"annually"));

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
        Intent intent = new Intent(this, Test2.class);
        startActivity(intent);
    }

    public void showDailyExp(View view){
        Intent intent = new Intent(this, DailyExpenses.class);
        startActivity(intent);
    }

    public void showSideBar(View view){
        Intent intent = new Intent(this,DailyChart.class);
        startActivity(intent);
    }
}
