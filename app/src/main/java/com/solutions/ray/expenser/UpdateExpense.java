package com.solutions.ray.expenser;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import Controller.Tracker.TransactionHandler;


public class UpdateExpense extends ActionBarActivity {


    TextView amountLbl;
    TransactionHandler transactionHandler;

    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;
    EditText amountTxt;                 //Widgets
    EditText payeeTxt;
    EditText catTxt;
    EditText subCatTxt;
    EditText descTxt;
    TextView testTxt;
    EditText payeeTypeTxt;
    Button addExpBtn;
    Button captureBtn;
    TextView curDateTxt;
    String week;
    String time;
    Button galleryBtn;
    DateFormat df;
    Date dt;
    private static final int SELECT_PHOTO = 100;
    String expID;
    HashMap <String,String> elementMap;

    final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/expenser/";
    File newdir = new File(dir);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_expense);

        amountTxt = (EditText) findViewById(R.id.amountTxt);
        payeeTxt = (EditText) findViewById(R.id.instTxt);
        payeeTypeTxt = (EditText) findViewById(R.id.payeeTypeTxt);
        descTxt = (EditText) findViewById(R.id.descTxt);
        addExpBtn = (Button) findViewById(R.id.updateBtn);
        captureBtn = (Button) findViewById(R.id.captureBtn);
        catTxt = (EditText) findViewById(R.id.categoryTxt);
        subCatTxt = (EditText) findViewById(R.id.subCatTxt);
        testTxt = (TextView) findViewById(R.id.testTxt);
        curDateTxt = (TextView) findViewById(R.id.curDateTxt);
        galleryBtn = (Button) findViewById(R.id.galleryBtn);


        newdir.mkdirs();

        transactionHandler = new TransactionHandler();
        Bundle extras = getIntent().getExtras();

        expID = extras.getString("expID");
        if (extras != null) {

            elementMap = transactionHandler.getExpense(this, expID);

            amountTxt.setText(elementMap.get("amount"));
            curDateTxt.setText(elementMap.get("date"));
            catTxt.setText(elementMap.get("category"));
            subCatTxt.setText(elementMap.get("subCategory"));
            payeeTxt.setText(elementMap.get("payee"));
            payeeTypeTxt.setText(elementMap.get("payType"));
            descTxt.setText(elementMap.get("desc"));
            week = elementMap.get("week");
            time = elementMap.get("time");
        }

    }

    public void setDate(View view) {

        Intent intent = new Intent(this, DatePick.class);
        startActivityForResult(intent, 1);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                curDateTxt.setText(data.getStringExtra("result"));
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
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

    public void updateExpense(View view) throws ParseException {

        String dateStr = curDateTxt.getText().toString();
        Date dt;
        df = new SimpleDateFormat("yyyy-MM-dd");
        dt = df.parse(dateStr);
        df = new SimpleDateFormat("W");
        week = df.format(dt);
        String msg = transactionHandler.updateExpense(this,expID, amountTxt.getText().toString(), descTxt.getText().toString(), catTxt.getText().toString(), subCatTxt.getText().toString(), payeeTxt.getText().toString(), payeeTypeTxt.getText().toString(),curDateTxt.getText().toString(),time,week);
        if(msg.equals("Successfully Updated")){
            Toast.makeText(getApplicationContext(), msg,
                    Toast.LENGTH_LONG).show();
            finish();

        }

        else{
            Toast.makeText(getApplicationContext(), msg,
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void deleteExpense(View view){
        String msg = transactionHandler.deleteExpense(this,expID);
        if(msg.equals("Sucessfully Deleted")){
            Toast.makeText(getApplicationContext(), msg,
                    Toast.LENGTH_LONG).show();
            finish();
        }

        else{
            Toast.makeText(getApplicationContext(), msg,
                    Toast.LENGTH_SHORT).show();
        }
    }
}