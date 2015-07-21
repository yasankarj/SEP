package Model.Tracker;

import java.util.Date;

/**
 * Created by Yasanka on 2015-07-21.
 */
public class Expense extends Transaction {
    private boolean isCredit;
    private String desc;
    private Date dueDate;

    Expense(double amount, String type, boolean isCredit, String desc){
        super(amount,type);
        this.desc = desc;
        this.isCredit = isCredit;
    }
}
