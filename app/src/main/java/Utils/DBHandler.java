package Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yasanka on 2015-06-25.
 */
public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "expenser.db";

    private static final String TABLE_EXPENSE = "expense";
    private static final String EXPN_COLUMN_ID = "expID";
    private static final String EXPN_COLUMN_AMOUNT = "amount";
    private static final String EXPN_COLUMN_CAT = "category";
    private static final String EXPN_COLUMN_DESC = "desc";
    private static final String EXPN_DATE_TIME = "date_time";

    private static final String TABLE_INCOME = "income";
    private static final String INC_COLUMN_ID = "incID";
    private static final String INC_COLUMN_AMOUNT = "amount";
    private static final String INC_COLUMN_CAT = "category";
    private static final String INC_COLUMN_DESC = "desc";


    /*Singleton Pattern implementation*/
    private static DBHandler DBH = null;

    private DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public static DBHandler getDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        if(DBH == null){
            DBH =  new DBHandler(context, name,factory, version);
        }

        return DBH;
    }
    /*Singleton Pattern implementation*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*create expense table*/
        String queryExp = "CREATE TABLE " + TABLE_EXPENSE + "(" +
                EXPN_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EXPN_COLUMN_AMOUNT + " REAL, " +
                EXPN_DATE_TIME + " TEXT, " +
                EXPN_COLUMN_CAT + " TEXT, " +
                EXPN_COLUMN_DESC + " TEXT " +");";

        /*create income table*/
        String queryInc = "CREATE TABLE " + TABLE_INCOME + "(" +
                INC_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                INC_COLUMN_AMOUNT + " REAL, " +
                INC_COLUMN_CAT + " TEXT, " +
                INC_COLUMN_DESC + " TEXT " +");";
        db.execSQL(queryExp);
        db.execSQL(queryInc);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_EXPENSE);
        db.execSQL("DROP TABLE IF EXIST "+TABLE_INCOME);
        onCreate(db);
    }

    public SQLiteDatabase getDataBase(){
        return getWritableDatabase();
    }

}
