package Controller.Tracker;
/**
 * Created by Yasanka on 2015-06-20.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DA.Tracker.TranasctionDA;
import Model.Tracker.Expense;
import Model.Tracker.Income;

public class TransactionHandler {
    /*Controller class which maps model objects with the data base*/
    private static final String TABLE_EXPENSE = "expense";
    private static final String EXPN_COLUMN_ID = "expID";
    private static final String EXPN_COLUMN_AMOUNT = "amount";
    private static final String EXPN_COLUMN_CAT = "category";
    private static final String EXPN_COLUMN_SUB_CAT = "subCategory";
    private static final String EXPN_COLUMN_DESC = "desc";
    private static final String EXPN_PAYEE = "payee";
    private static final String EXPN_PAY_TYPE = "paytype";
    private static final String EXPN_DATE = "date";
    private static final String EXPN_TIME = "time";
    private static final String EXPN_WEEK = "week";

    private static final String TABLE_INCOME = "income";
    private static final String INC_COLUMN_ID = "incID";
    private static final String INC_COLUMN_AMOUNT = "amount";
    private static final String INC_COLUMN_CAT = "category";
    private static final String INC_COLUMN_DESC = "desc";

    public Expense getExpense(Context context, String expID){

        int e_id = Integer.parseInt(expID);
        TranasctionDA transDA = new TranasctionDA();
        Map<String,String> elementMap= transDA.getExpense(context,e_id);



        //Expense(double amount, String type,String subType,String desc, String payee, String payType, String dateStr, String timeStr, String weekStr)

        int id = Integer.parseInt(elementMap.get(EXPN_COLUMN_ID));
        double amount = Double.parseDouble(elementMap.get(EXPN_COLUMN_AMOUNT));
        String payee = elementMap.get(EXPN_PAY_TYPE);
        String payType = elementMap.get(EXPN_PAYEE);
        String type = elementMap.get(EXPN_COLUMN_CAT);
        String subType = elementMap.get(EXPN_COLUMN_SUB_CAT);
        String desc = elementMap.get(EXPN_COLUMN_DESC);
        String date = elementMap.get(EXPN_DATE);
        String time = elementMap.get(EXPN_TIME);
        String week = elementMap.get(EXPN_WEEK);

        Expense exp = new Expense(id,amount,type,subType,desc,payee, payType, date, time, week);
        return exp;
    }

    public double getDailyExpTot(Context context,String value, String range){
        TranasctionDA transDA = new TranasctionDA();
        return transDA.sumExpenses(context,value,range);
    }

    public double getDailyIncTot(Context context){

        TranasctionDA transDA = new TranasctionDA();
        return transDA.sumIncome(context);
    }

    public String addNewExpense(Context contxt, String amountStr, String desc, String type, String subType, String payee, String payType, String dateStr){
        //(double amount, String type,String subType,String desc, String payee, String payType)
        String msg = "";
        String [] dateVals = dateStr.split(" ");
        Log.d("Test Date : ",dateVals[0]+"|"+dateVals[1]+"|"+dateVals[2]);
        if(amountStr.equals("")){
          msg = "Amount cannot be empty";
        }

        else if(type.equals("")){
            msg = "Type cannot be empty";
        }

        else{

            try{
                double amount = Double.parseDouble(amountStr);
                if(amount<=0){
                    msg = "Amount cannot be negative";
                }

                else {

                    Expense exp = new Expense(amount, type, subType, desc, payee, payType, dateVals[0], dateVals[1], dateVals[2]);    //Expense object is created with the passed data
                    TranasctionDA transDA = new TranasctionDA();
                    transDA.addExpense(exp, contxt);                                //Model object is passed to the data access layer
                    msg = "Successfully Added";
                }
            }

            catch(Exception ex) {
                msg = "Unable to enter data";
            }
        }

        return msg;
    }

    public void addNewIncome(Context contxt, Double amount, String desc, String cat, String inst){
        Income income = new Income(amount,cat,"",inst,desc,"");    //Income object is created with the passed data
        TranasctionDA transDA = new TranasctionDA();
        transDA.addIncome(income,contxt);                                //Model object is passed to the data access layer
    }
}
