package Model.Tracker;

import java.util.Date;

/**
 * Created by Yasanka on 2015-06-21.
 */
public class Expense extends Transaction {
    private boolean isCredit;
    private Date dueDate;

    public Expense(double amount, String type, boolean isCredit, String desc){
        super(amount,type,desc);
        this.isCredit = isCredit;
    }


    public boolean isCredit() {
        return isCredit;
    }

    public Date getDueDate() {
        return dueDate;
    }
}
