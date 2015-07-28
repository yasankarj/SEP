package DA.Tracker;

import android.content.Context;

import DA.DBHandler;
import Model.Tracker.Expense;
import Model.Tracker.Income;

/**
 * Created by Yasanka on 2015-07-21.
 */
public class TranasctionDA {
    /*Data access class which concerns Transactions*/

    public void addExpense(Expense exp, Context contxt){
        DBHandler dbh = DBHandler.getDBHandler(contxt, null, null, 1);
        dbh.addExpense(exp);
    }

    public String allExpenses(Context contxt){
        DBHandler dbh = DBHandler.getDBHandler(contxt,null,null,1);
        return dbh.getAllExpenses();
    }

    public double sumExpenses(Context contxt){
        DBHandler dbh = DBHandler.getDBHandler(contxt,null,null,1);
        return dbh.sumExpenses();
    }

    public void addIncome(Income income, Context contxt){
        DBHandler dbh = DBHandler.getDBHandler(contxt, null, null, 1);
        dbh.addIncome(income);
    }

    public String allIncome(Context contxt){
        DBHandler dbh = DBHandler.getDBHandler(contxt,null,null,1);
        return dbh.getAllIncome();
    }

    public double sumIncome(Context contxt){
        DBHandler dbh = DBHandler.getDBHandler(contxt,null,null,1);
        return dbh.sumIncome();
    }

}
