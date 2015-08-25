package Controller.Tracker;
/**
 * Created by Yasanka on 2015-06-20.
 */

import android.content.Context;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

import DA.Tracker.TranasctionDA;
import Model.Tracker.Expense;
import Model.Tracker.Income;

public class TransactionHandler {
    /*Controller class which maps model objects with the data base*/

    public double getDailyExpTot(Context context,String value){
        TranasctionDA transDA = new TranasctionDA();
        return transDA.sumExpenses(context,value);
    }

    public double getDailyIncTot(Context context){

        TranasctionDA transDA = new TranasctionDA();
        return transDA.sumIncome(context);
    }

    public String addNewExpense(Context contxt, String amountStr, String desc, String type, String subType, String payee, String payType, String dateStr){
        //(double amount, String type,String subType,String desc, String payee, String payType)
        String msg = "";
        String [] dateVals = dateStr.split(" ");
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

                    Expense exp = new Expense(amount, type, subType, desc, payee, payType, dateVals[2], dateVals[1], dateVals[0]);    //Expense object is created with the passed data
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
