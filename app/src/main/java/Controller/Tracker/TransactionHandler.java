package Controller.Tracker;
/**
 * Created by Yasanka on 2015-06-20.
 */

import android.content.Context;

import DA.Tracker.TranasctionDA;
import Model.Tracker.Expense;
import Model.Tracker.Income;

public class TransactionHandler {
    /*Controller class which maps model objects with the data base*/

    public double getDailyExpTot(Context context){
        TranasctionDA transDA = new TranasctionDA();
        return transDA.sumExpenses(context);
    }

    public double getDailyIncTot(Context context){

        TranasctionDA transDA = new TranasctionDA();
        return transDA.sumIncome(context);
    }

    public void addNewExpense(Context contxt, Double amount, String desc, String cat){
        Expense exp = new Expense(amount,cat,true,desc);    //Expense object is created with the passed data
        TranasctionDA transDA = new TranasctionDA();
        transDA.addExpense(exp,contxt);                                //Model object is passed to the data access layer
    }

    public void addNewIncome(Context contxt, Double amount, String desc, String cat, String inst){
        Income income = new Income(amount,cat,inst,desc);    //Income object is created with the passed data
        TranasctionDA transDA = new TranasctionDA();
        transDA.addIncome(income,contxt);                                //Model object is passed to the data access layer
    }
}
