package Model.Tracker;

import android.os.SystemClock;

import java.util.Date;

/**
 * Created by Yasanka on 2015-07-21.
 */
public class Transaction {
    private int transID;
    private double amount;
    private String type;
    private String imageLoc;
    private Date timeStamp;

    Transaction(double amount, String type){
        this.amount = amount;
        this.type = type;
    }
}
