package com.solutions.ray.expenser;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Controller.Tracker.TransactionHandler;


public class DailyChart extends ActionBarActivity {

    SimpleDateFormat df;
    Date dt;
    TextView dateTxt;
    TextView totTxt;
    LinearLayout linearLayout;
    TransactionHandler transactionHandler;
    ArrayList <String> elementList;
    String [][] expenses;
    private static int[] COLORS = new int[] { Color.GREEN, Color.BLUE,Color.MAGENTA, Color.CYAN };
    private static double[] VALUES;
    private static String[] NAME_LIST;
    private CategorySeries mSeries = new CategorySeries("");
    private DefaultRenderer mRenderer = new DefaultRenderer();
    private GraphicalView mChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_chart);

        transactionHandler = new TransactionHandler();

      //  ArrayList <String> elementList = transactionHandler.getExpensesByDateCharts(this,"");
        dateTxt = (TextView)findViewById(R.id.dateTxt);
        totTxt = (TextView)findViewById(R.id.amountTxt);
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        dateTxt = (TextView)findViewById(R.id.dateTxt);
        totTxt = (TextView)findViewById(R.id.totTxt);

        df = new SimpleDateFormat("yyyy-MM-dd");
        dt = new Date();
        dateTxt.setText(df.format(dt));
        totTxt.setText(""+transactionHandler.getDailyExpTot(this,df.format(dt),"daily"));
        elementList = transactionHandler.getExpensesByDate(this, df.format(dt));

        VALUES = new double[elementList.size()];
        NAME_LIST = new String[elementList.size()];
        int j = 0;

        for(String str:elementList){
            String [] subs = str.split(" ");
            VALUES[j] = Double.parseDouble(subs[0]);
            NAME_LIST[j] = subs[1];
        }

        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.WHITE);
        mRenderer.setChartTitleTextSize(20);
        mRenderer.setChartTitle("Expenses By Type");
        mRenderer.setLabelsTextSize(15);
        mRenderer.setLabelsColor(Color.BLACK);
        mRenderer.setLegendTextSize(15);
        mRenderer.setMargins(new int[]{20, 30, 15, 0});
        mRenderer.setZoomButtonsVisible(true);
        mRenderer.setStartAngle(-90);

        for (int i = 0; i < VALUES.length; i++) {
            mSeries.add(NAME_LIST[i] + " " + VALUES[i], VALUES[i]);
            SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
            renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
            mRenderer.addSeriesRenderer(renderer);
        }

        mChartView = ChartFactory.getPieChartView(this, mSeries, mRenderer);
        linearLayout.addView(mChartView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        if (mChartView != null) {
            mChartView.repaint();
        }




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daily_chart, menu);
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
