package com.solutions.ray.expenser;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Controller.Tracker.TransactionHandler;


public class AddExpenseActivity extends ActionBarActivity {

    TransactionHandler transHandler;    //Controller Object

    int TAKE_PHOTO_CODE = 0;
    public static int count=0;
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
    Button galleryBtn;
    DateFormat df;
    Date dt;
    Calendar myCalendar;
    DatePicker dpd;
    private static final int SELECT_PHOTO = 100;

    final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/expenser/";
    File newdir = new File(dir);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        transHandler = new TransactionHandler();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        /*widget initialization*/
        amountTxt = (EditText)findViewById(R.id.amountTxt);
        payeeTxt = (EditText)findViewById(R.id.instTxt);
        payeeTypeTxt = (EditText)findViewById(R.id.payeeTypeTxt);
        descTxt = (EditText)findViewById(R.id.descTxt);
        addExpBtn = (Button)findViewById(R.id.addExpBtn);
        captureBtn = (Button)findViewById(R.id.captureBtn);
        catTxt = (EditText)findViewById(R.id.categoryTxt);
        subCatTxt = (EditText)findViewById(R.id.subCatTxt);
        testTxt = (TextView)findViewById(R.id.testTxt);
        curDateTxt = (TextView)findViewById(R.id.curDateTxt);
        df = new SimpleDateFormat("yyyy-MM-dd");
        dt = new Date();
        galleryBtn = (Button)findViewById(R.id.galleryBtn);



        curDateTxt.setText(df.format(dt));
        newdir.mkdirs();




    }
    public void setDate(View view){

        Intent intent = new Intent(this, DatePick.class);
        startActivityForResult(intent, 1);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
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

    public void addExpense(View view){
        /*Button Action performance*/
        /*Controller takes the action to his hand*/
        String dateStr = curDateTxt.getText().toString();
        df = new SimpleDateFormat("hh:mm W");
        dateStr = dateStr+" "+df.format(dt);
        String msg = transHandler.addNewExpense(this, amountTxt.getText().toString(), descTxt.getText().toString(), catTxt.getText().toString(), subCatTxt.getText().toString(), payeeTxt.getText().toString(), payeeTypeTxt.getText().toString(),dateStr);
        long val = 0;

        if(msg.equals("Successfully Added")){
            Toast.makeText(getApplicationContext(), msg,
                    Toast.LENGTH_LONG).show();
            finish();

        }

        else{
            Toast.makeText(getApplicationContext(), msg,
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void addPhoto(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    public void capturePhoto(View view){
        // here,counter will be incremented each time,and the picture taken by camera will be stored as 1.jpg,2.jpg and likewise.
        count++;
        String file = dir+count+".jpg";

        testTxt.setText(file);
        File newfile = new File(file);
        try {
            newfile.createNewFile();
        } catch (IOException e) {}

        Uri outputFileUri = Uri.fromFile(newfile);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
    }


}

