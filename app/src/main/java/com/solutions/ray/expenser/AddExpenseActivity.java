package com.solutions.ray.expenser;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Controller.Tracker.TransactionHandler;


public class AddExpenseActivity extends ActionBarActivity {

    TransactionHandler transHandler;    //Controller Object
    EditText amountTxt;                 //Widgets
    EditText payeeTxt;
    EditText descTxt;
    Button addExpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        transHandler = new TransactionHandler();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        /*widget initialization*/
        amountTxt = (EditText)findViewById(R.id.amountTxt);
        payeeTxt = (EditText)findViewById(R.id.instTxt);
        descTxt = (EditText)findViewById(R.id.descTxt);
        addExpBtn = (Button)findViewById(R.id.addExpBtn);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_expense, menu);
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
        /*Button Action performance*/
        /*Controller takes the action to his hand*/
       transHandler.addNewExpense(this,Double.parseDouble(amountTxt.getText().toString()),descTxt.getText().toString(),"lunch");

    }
}
