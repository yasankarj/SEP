package com.solutions.ray.expenser;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Controller.Tracker.TransactionHandler;
import DA.Tracker.TranasctionDA;


public class DailyExpenses extends ActionBarActivity {
    TransactionHandler ths;
    TranasctionDA td;
    ListAdapter listAdapter;
    SimpleDateFormat df;
    Date dt;
    TextView dateTxt;
    ListView listView;
    TextView totTxt;
    ArrayList<String> expList;
    String[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_expenses);

        ths = new TransactionHandler();
        td = new TranasctionDA();

        dateTxt = (TextView)findViewById(R.id.dateTxt);
        totTxt = (TextView)findViewById(R.id.totTxt);

        df = new SimpleDateFormat("yyyy-MM-dd");
        dt = new Date();
        dateTxt.setText(df.format(dt));
        totTxt.setText(""+ths.getDailyExpTot(this,df.format(dt),"daily"));
        expList = td.getExpensesByDate(this, df.format(dt));

        items = expList.toArray(new String[expList.size()]);
//        totTxt.setText(""+expList);

        listAdapter = new CustomAdapter(this,items);

        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String [] subStrs = items[position].split(" ");
                        //Toast.makeText(DailyExpenses.this,"Text "+position,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getBaseContext(), UpdateExpense.class);
                        intent.putExtra("expID",subStrs[0]);
                        startActivity(intent);
                    }
                }
        );

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


    public void setDate(View view){

        Intent intent = new Intent(this, DatePick.class);
        startActivityForResult(intent, 1);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("result");
                dateTxt.setText(result);
                totTxt.setText(""+ths.getDailyExpTot(this,result,"daily"));

                expList = td.getExpensesByDate(this, result);

                items = expList.toArray(new String[expList.size()]);

                listAdapter = new CustomAdapter(this,items);

                listView = (ListView)findViewById(R.id.listView);
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String [] subStrs = items[position].split(" ");
                                //Toast.makeText(DailyExpenses.this,"Text "+position,Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getBaseContext(), UpdateExpense.class);
                                intent.putExtra("expID",subStrs[0]);
                                startActivity(intent);
                            }
                        }
                );
   //             expList = td.getExpensesByDate(this, data.getStringExtra("result"));
             //   totTxt.setText(""+expList);

            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }


    }
}
