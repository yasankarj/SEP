package com.solutions.ray.expenser;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Controller.Tracker.TransactionHandler;


public class AddIncomeActivity extends ActionBarActivity {

    TransactionHandler transHandler;    //Controller Object
    EditText amountTxt;                 //Widgets
    EditText instTxt;
    EditText descTxt;
    Button addIncBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        transHandler = new TransactionHandler();
        amountTxt = (EditText)findViewById(R.id.amountTxt);
        instTxt = (EditText)findViewById(R.id.instTxt);
        descTxt = (EditText)findViewById(R.id.descTxt);
        addIncBtn = (Button)findViewById(R.id.addIncBtn);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_income, menu);
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

    public void addIncome(View view){
        transHandler.addNewIncome(this, Double.parseDouble(amountTxt.getText().toString()), descTxt.getText().toString(), "Cheque", instTxt.getText().toString());
    }
}
