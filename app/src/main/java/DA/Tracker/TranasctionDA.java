package DA.Tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private static final String EXPN_DATE_TIME = "date_time";

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
        contentValues.put(EXPN_DATE_TIME,expn.getTimeStamp());
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
        String query = "SELECT amount,desc,date_time FROM "+ TABLE_EXPENSE+";";

        Cursor cur = db.rawQuery(query,null);


        /*retrieve the data set using a Cursor*/
        if (cur.moveToFirst()) {
            do {
                dbStr += cur.getString(cur.getColumnIndex("amount")) +" "+ cur.getString(cur.getColumnIndex("desc"))+" "+cur.getString(cur.getColumnIndex("date_time"));//+" "+ cur.getString(cur.getColumnIndex("date_time"));
                dbStr += "\n";
            } while (cur.moveToNext());
        }
        db.close();


        return dbStr;
    }

    /*this metod will give the total of all the transactions*/
    public double sumExpenses(Context contxt,String value){
        DBHandler dbh = DBHandler.getDBHandler(contxt,null,null,1);
        double sum = 0;
        SQLiteDatabase db = dbh.getDataBase();


            String query = "SELECT * FROM "+ TABLE_EXPENSE+" WHERE "+EXPN_DATE_TIME+" LIKE '"+value+"%' ;";
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
