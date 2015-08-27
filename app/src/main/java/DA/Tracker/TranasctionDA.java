package DA.Tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Utils.DBHandler;
import Model.Tracker.Expense;
import Model.Tracker.Income;

/**
 * Created by Yasanka on 2015-07-21.
 */
public class TranasctionDA {
    /*Data access class which concerns Transactions*/
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

    /*this method will add a new expense*/
    public long addExpense(Expense expn, Context contxt){
        //(Context contxt, Double amount, String desc, String type, String subType, String payee, String payType)
        DBHandler dbHandler = DBHandler.getDBHandler(contxt, null, null, 1);    //Database connection estabished using Singleton
        SQLiteDatabase db = dbHandler.getDataBase();                            //Writable database is fetched
        ContentValues contentValues = new ContentValues();                      //

        contentValues.put(EXPN_COLUMN_AMOUNT,expn.getAmount());
        contentValues.put(EXPN_COLUMN_DESC,expn.getDesc());
        contentValues.put(EXPN_COLUMN_CAT,expn.getType());
        contentValues.put(EXPN_COLUMN_SUB_CAT,expn.getSubType());
        contentValues.put(EXPN_PAYEE,expn.getPayee());
        contentValues.put(EXPN_PAY_TYPE,expn.getPayType());
        contentValues.put(EXPN_DATE,expn.getDate());
        contentValues.put(EXPN_TIME,expn.getTime());
        contentValues.put(EXPN_WEEK,expn.getWeek());
        long val = db.insert(TABLE_EXPENSE,null,contentValues);
        db.close();

        return val;
    }

    /*this method will give all the expenses which are in the database as an concatenated string*/
    public String allExpenses(Context contxt){
        DBHandler dbh = DBHandler.getDBHandler(contxt,null,null,1);
       // return dbh.getAllExpenses();
        //concatanated expenses
        String dbStr = "";
        SQLiteDatabase db = dbh.getDataBase();
        String query = "SELECT amount,category,date FROM"+ TABLE_EXPENSE+";";

        Cursor cur = db.rawQuery(query,null);


        /*retrieve the data set using a Cursor*/
        if (cur.moveToFirst()) {
            do {
                dbStr += cur.getString(cur.getColumnIndex(EXPN_COLUMN_AMOUNT)) +" "+ cur.getString(cur.getColumnIndex("category"))+" "+cur.getString(cur.getColumnIndex("date"));
                dbStr += "\n";
            } while (cur.moveToNext());
        }
        db.close();


        return dbStr;
    }

    public Map<String,String> getExpense(Context contxt, int expID){
        DBHandler dbh = DBHandler.getDBHandler(contxt,null,null,1);
        // return dbh.getAllExpenses();
        //concatanated expenses
        HashMap<String,String> elementMap= new HashMap<String,String>();
        String dbStr = "";
        SQLiteDatabase db = dbh.getDataBase();
        String query = "SELECT * FROM "+ TABLE_EXPENSE+" WHERE expID = "+expID+";";

        Cursor cur = db.rawQuery(query,null);

        elementMap.put(EXPN_COLUMN_ID,cur.getString(cur.getColumnIndex(EXPN_COLUMN_ID)));
        elementMap.put(EXPN_COLUMN_AMOUNT,cur.getString(cur.getColumnIndex(EXPN_COLUMN_AMOUNT)));
        elementMap.put(EXPN_PAY_TYPE,cur.getString(cur.getColumnIndex(EXPN_PAY_TYPE)));
        elementMap.put(EXPN_PAYEE,cur.getString(cur.getColumnIndex(EXPN_PAYEE)));
        elementMap.put(EXPN_COLUMN_CAT,cur.getString(cur.getColumnIndex(EXPN_COLUMN_CAT)));
        elementMap.put(EXPN_COLUMN_SUB_CAT,cur.getString(cur.getColumnIndex(EXPN_COLUMN_SUB_CAT)));
        elementMap.put(EXPN_COLUMN_DESC,cur.getString(cur.getColumnIndex(EXPN_COLUMN_DESC)));
        elementMap.put(EXPN_DATE,cur.getString(cur.getColumnIndex(EXPN_PAY_TYPE)));
        elementMap.put(EXPN_TIME,cur.getString(cur.getColumnIndex(EXPN_PAY_TYPE)));
        elementMap.put(EXPN_WEEK,cur.getString(cur.getColumnIndex(EXPN_PAY_TYPE)));

        return elementMap;

    }
    public ArrayList <String> getExpensesByDate(Context context, String date){
        ArrayList <String> expenseList = new ArrayList<>();
        DBHandler dbh = DBHandler.getDBHandler(context,null,null,1);
        String dbStr = "";
        SQLiteDatabase db = dbh.getDataBase();
        int i = 0;

        String query = "SELECT expID,amount,category,subCategory,paytype FROM "+ TABLE_EXPENSE+" WHERE "+EXPN_DATE+" = '"+date+"';";

        Cursor cur = db.rawQuery(query,null);


        /*retrieve the data set using a Cursor*/
        if (cur.moveToFirst()) {
            do {
                dbStr =  cur.getString(cur.getColumnIndex("expID")) +" "+cur.getString(cur.getColumnIndex("amount")) +" "+ cur.getString(cur.getColumnIndex("category"))+" |"+cur.getString(cur.getColumnIndex("subCategory"))+" &"+cur.getString(cur.getColumnIndex("paytype"));
                expenseList.add(dbStr);
                i++;
            } while (cur.moveToNext());
        }
        db.close();

        return expenseList;
    }

    /*this metod will give the total of all the transactions*/
    public double sumExpenses(Context contxt,String value,String range){
        DBHandler dbh = DBHandler.getDBHandler(contxt,null,null,1);
        double sum = 0;
        SQLiteDatabase db = dbh.getDataBase();
        String query;
        if(range.equals("week")){
            query = "SELECT amount FROM "+ TABLE_EXPENSE+" WHERE "+EXPN_WEEK+" = '"+value+"';";
        }
        else {
            query = "SELECT amount FROM " + TABLE_EXPENSE + " WHERE " + EXPN_DATE + " LIKE '" + value + "%';";
           }
      //  }
       // String query = "SELECT * FROM "+ TABLE_EXPENSE+";";
        Cursor cur = db.rawQuery(query,null);


        /*retrieve the data set using a Cursor*/
        if (cur.moveToFirst()) {
            do {
                sum += Double.parseDouble(cur.getString(cur.getColumnIndex("amount")));
            } while (cur.moveToNext());
        }
        db.close();


        return sum;
    }

    /*this method will add a new income*/
    public void addIncome(Income income, Context contxt){
        DBHandler dbh = DBHandler.getDBHandler(contxt, null, null, 1);
        ContentValues contentValues = new ContentValues();
        contentValues.put(INC_COLUMN_AMOUNT,income.getAmount());
        contentValues.put(INC_COLUMN_DESC,income.getDesc());
        SQLiteDatabase db = dbh.getDataBase();
        db.insert(TABLE_INCOME,null,contentValues);
        db.close();
    }

    /*this method will give all the income transactons as a concatenated string*/
    public String allIncome(Context contxt){
        DBHandler dbh = DBHandler.getDBHandler(contxt,null,null,1);
        String dbStr = "";
        SQLiteDatabase db = dbh.getDataBase();
        String query = "SELECT * FROM "+ TABLE_INCOME+";";

        Cursor cur = db.rawQuery(query,null);


        /*retrieve the data set using a Cursor*/
        if (cur.moveToFirst()) {
            do {
                dbStr += cur.getString(cur.getColumnIndex("amount")) +" "+ cur.getString(cur.getColumnIndex("desc"));
                dbStr += "\n";
            } while (cur.moveToNext());
        }
        db.close();


        return dbStr;
    }

    /*this method will calculate the summation of the incomes*/
    public double sumIncome(Context contxt){
        DBHandler dbh = DBHandler.getDBHandler(contxt,null,null,1);
        double sum = 0;
        SQLiteDatabase db = dbh.getDataBase();
        String query = "SELECT * FROM "+ TABLE_INCOME+";";

        Cursor cur = db.rawQuery(query,null);



        if (cur.moveToFirst()) {
            do {
                sum += Double.parseDouble(cur.getString(cur.getColumnIndex("amount")));
            } while (cur.moveToNext());
        }
        db.close();


        return sum;
    }

}
