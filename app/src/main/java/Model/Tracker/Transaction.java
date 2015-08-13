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
    private Date timeStamp;

    private String desc;

    Transaction(double amount, String type,String subType, String desc){
        this.amount = amount;
        this.type = type;
        this.subType = subType;
        this.desc = desc;
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

    public String getImageLoc() {
        return imageLoc;
    }

    public String getDesc() {
        return desc;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
}
