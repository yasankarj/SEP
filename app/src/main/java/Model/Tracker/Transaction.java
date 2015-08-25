package Model.Tracker;

import android.os.SystemClock;

import java.util.Date;

/**
 * Created by Yasanka on 2015-06-21.
 */
public class Transaction {
    private int transID;
    private double amount;
    private String type;
    private String subType;
    private String imageLoc;
    private String date;
    private String time;
    private String week;

    private String desc;

    Transaction(int transID, double amount, String type,String subType, String desc,String date, String time, String week){
        this.transID = transID;
        this.amount = amount;
        this.type = type;
        this.subType = subType;
        this.desc = desc;
        this.date = date;
        this.week = week;
        this.time = time;
    }

    Transaction(double amount, String type,String subType, String desc,String date, String time, String week){
        this.amount = amount;
        this.type = type;
        this.subType = subType;
        this.desc = desc;
        this.date = date;
        this.week = week;
        this.time = time;
    }

    public int getTransID() {
        return transID;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public String getImageLoc() {
        return imageLoc;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getWeek() {
        return week;
    }
}
