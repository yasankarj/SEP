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
public class TransactionDA {
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
        SQLiteDatabase db = dbHandler.getWritableDatabase();                            //Writable database is fetched
        ContentValues contentValues = new ContentValues();                      //

        /*setting values in a content*/
        contentValues.put(EXPN_COLUMN_AMOUNT,expn.getAmount());
        contentValues.put(EXPN_COLUMN_DESC,expn.getDesc());
        contentValues.put(EXPN_COLUMN_CAT,expn.getType());
        contentValues.put(EXPN_COLUMN_SUB_CAT,expn.getSubType());
        contentValues.put(EXPN_PAYEE,expn.getPayee());
        contentValues.put(EXPN_PAY_TYPE,expn.getPayType());
        contentValues.put(EXPN_DATE,expn.getDate());
        contentValues.put(EXPN_TIME,expn.getTime());
        contentValues.put(EXPN_WEEK,expn.getWeek());

        /*insert data to the database*/
        long val = db.insert(TABLE_EXPENSE,null,contentValues);
        db.close();

        //return the status
        return val;
    }

    public long updateExpense(Context context, Expense expense){
        /*method to update given expense*/
        DBHandler dbHandler = DBHandler.getDBHandler(context, null, null, 1);    //Database connection estabished using Singleton
        SQLiteDatabase db = dbHandler.getWritableDatabase();                            //Writable database is fetched
        ContentValues contentValues = new ContentValues();                      //Containser to keep values

        /*Insertion the values to the container*/
        contentValues.put(EXPN_COLUMN_AMOUNT, expense.getAmount());
        contentValues.put(EXPN_COLUMN_DESC, expense.getDesc());
        contentValues.put(EXPN_COLUMN_CAT, expense.getType());
        contentValues.put(EXPN_COLUMN_SUB_CAT,expense.getSubType());
        contentValues.put(EXPN_PAYEE, expense.getPayee());
        contentValues.put(EXPN_PAY_TYPE,expense.getPayType());
        contentValues.put(EXPN_DATE, expense.getDate());
        contentValues.put(EXPN_TIME,expense.getTime());
        contentValues.put(EXPN_WEEK, expense.getWeek());

        //run update
        long val = db.update(TABLE_EXPENSE, contentValues, EXPN_COLUMN_ID + " = " + expense.getTransID(), null);
        db.close();

        //return the status
        return val;
    }

    public Expense getExpense(Context contxt, int expID){
        /*this method returns a Expense when the expID is given*/
        DBHandler dbh = DBHandler.getDBHandler(contxt,null,null,1);     //database handler 'singleton'
        SQLiteDatabase db = dbh.getDataBase();                          //database connection established
        String query = "SELECT * FROM "+ TABLE_EXPENSE+" WHERE expID = "+expID+";";     //query to get Expense
        Expense expense = null;                                         //set Expense object to null
        /*to prevent null values enterd to the Expense object*/
        int id = 0;
        double amount = 0.0;
        String category = "";
        String subCategory = "";
        String desc = "";
        String payee = "";
        String payType = "";
        String date = "";
        String time = "";
        String week = "";

        /*data fetched to the */
        Cursor cur = db.rawQuery(query,null);

        /*mapping data to variables*/
        if (cur.moveToFirst()) {
            id = cur.getInt(cur.getColumnIndex(EXPN_COLUMN_ID));
            amount = cur.getDouble(cur.getColumnIndex(EXPN_COLUMN_AMOUNT));
            payType = cur.getString(cur.getColumnIndex(EXPN_PAY_TYPE));
            payee = cur.getString(cur.getColumnIndex(EXPN_PAYEE));
            category = cur.getString(cur.getColumnIndex(EXPN_COLUMN_CAT));
            subCategory = cur.getString(cur.getColumnIndex(EXPN_COLUMN_SUB_CAT));
            desc = cur.getString(cur.getColumnIndex(EXPN_COLUMN_DESC));
            date = cur.getString(cur.getColumnIndex(EXPN_DATE));
            time = cur.getString(cur.getColumnIndex(EXPN_TIME));
            week = cur.getString(cur.getColumnIndex(EXPN_WEEK));

            //expense object initialized
            //Expense(int id,double amount, String type,String subType,String desc, String payee, String payType, String dateStr, String timeStr, String weekStr)
            expense = new Expense(id,amount,category,subCategory,desc,payee,payType,date,time,week);

        }
        db.close(); //database connection closed

        //return the expense object
        return expense;

    }
    public ArrayList <Expense> getExpensesByDate(Context context, String date){
        ArrayList <Expense> expenseList = new ArrayList<>();
        DBHandler dbh = DBHandler.getDBHandler(context,null,null,1);
        String dbStr = "";
        SQLiteDatabase db = dbh.getDataBase();
        int id = 0;
        double amount = 0.0;
        String category = "";
        String subCategory = "";
        String desc = "";
        String payee = "";
        String payType = "";
        String time = "";
        String week = "";

        String query = "SELECT * FROM "+ TABLE_EXPENSE+" WHERE "+EXPN_DATE+" = '"+date+"';";

        Cursor cur = db.rawQuery(query,null);


        /*retrieve the data set using a Cursor*/
        if (cur.moveToFirst()) {
            do {
                id = 0+cur.getInt(cur.getColumnIndex(EXPN_COLUMN_ID));
                amount = 0.0+cur.getDouble(cur.getColumnIndex(EXPN_COLUMN_AMOUNT));
                payType = ""+cur.getString(cur.getColumnIndex(EXPN_PAY_TYPE));
                payee = ""+cur.getString(cur.getColumnIndex(EXPN_PAYEE));
                category = ""+cur.getString(cur.getColumnIndex(EXPN_COLUMN_CAT));
                subCategory = ""+cur.getString(cur.getColumnIndex(EXPN_COLUMN_SUB_CAT));
                desc = ""+cur.getString(cur.getColumnIndex(EXPN_COLUMN_DESC));
                date = ""+cur.getString(cur.getColumnIndex(EXPN_DATE));
                time = ""+cur.getString(cur.getColumnIndex(EXPN_TIME));
                week = ""+cur.getString(cur.getColumnIndex(EXPN_WEEK));

                //expense object initialized
                //Expense(int id,double amount, String type,String subType,String desc, String payee, String payType, String dateStr, String timeStr, String weekStr)
                Expense expense = new Expense(id,amount,category,subCategory,desc,payee,payType,date,time,week);



                expenseList.add(expense);
            } while (cur.moveToNext());
        }
        db.close();

        return expenseList;
    }

    /*this metod will give the total of all the expenses for a given time period*/
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

    public long deleteExpense(Context context, String expID){
        /*method to delete given expense*/
        long val = 0;
        int expenseID = Integer.parseInt(expID);
        DBHandler dbHandler = DBHandler.getDBHandler(context, null, null, 1);    //Database connection estabished using Singleton
        SQLiteDatabase db = dbHandler.getWritableDatabase();                     //Writable database is fetched

        //run delete
        val = db.delete(TABLE_EXPENSE, EXPN_COLUMN_ID + " = " + expenseID, null);
        db.close();         //database connection closed

        return val; //return status
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
