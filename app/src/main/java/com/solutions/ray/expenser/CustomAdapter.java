package com.solutions.ray.expenser;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import Utils.SPHandler;

/**
 * Created by Yasanka on 2015-08-27.
 */
public class CustomAdapter extends ArrayAdapter<String>{

    public CustomAdapter(Context context, String [] content) {
        super(context, R.layout.custom_row,content);
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        SPHandler sph = new SPHandler();
        sph.setSettingsValue(getContext(),"currency","USSD");
        String cur = sph.getSettingsValue(getContext(),"currency");
        View customView = layoutInflater.inflate(R.layout.cust_row,parent,false);
        TextView typeTxt = (TextView)customView.findViewById(R.id.typeTxt);
        TextView payTypeTxt = (TextView)customView.findViewById(R.id.payTypeTxt);
        TextView subTypeTxt = (TextView)customView.findViewById(R.id.subTypeTxt);
        TextView amountTxt = (TextView)customView.findViewById(R.id.amountTxt);
        TextView curTxt = (TextView)customView.findViewById(R.id.currencyTxt);

        curTxt.setText(cur);

        typeTxt.setText("");
        amountTxt.setText("");
        subTypeTxt.setText("");
        payTypeTxt.setText("");

        String str = getItem(position);
        String [] subStr = str.split(" ");
        Log.d("@Cust",""+subStr.length+" |:"+str);
        if(subStr.length == 3){
            typeTxt.setText(subStr[2]);
            amountTxt.setText(subStr[1]);
        }

        else if(subStr.length == 4){
            typeTxt.setText(subStr[2]);
            amountTxt.setText(subStr[1]);
            if(subStr[2].startsWith("|")){
                subTypeTxt.setText(subStr[3].substring(1));
                payTypeTxt.setText("");
            }
            else {
                payTypeTxt.setText(subStr[3].substring(1));
                subTypeTxt.setText("");
            }
        }

        else if(subStr.length == 5){
            typeTxt.setText(subStr[2]);
            amountTxt.setText(subStr[1]);
            subTypeTxt.setText(subStr[3].substring(1));
            payTypeTxt.setText(subStr[4].substring(1));
        }

        return customView;
    }
}
