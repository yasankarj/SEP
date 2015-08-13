package Model.Tracker;

import java.util.Date;

/**
 * Created by Yasanka on 2015-06-21.
 */
public class Expense extends Transaction {

    private String payee;
    private String payType;
    private Date dueDate;

    public Expense(double amount, String type,String subType,String desc, String payee, String payType){
        super(amount,type,subType,desc);
        this.payee = payee;
        this.payType = payType;
    }


    public String getPayee() {
        return payee;
    }

    public String getPayType(){return payType;};

    public Date getDueDate() {
        return dueDate;
    }
}
