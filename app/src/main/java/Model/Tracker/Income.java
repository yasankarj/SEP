package Model.Tracker;

/**
 * Created by Yasanka on 2015-07-21.
 */
public class Income extends Transaction{
    private String institute;
    Income(double amount, String type, String institute){
        super(amount, type);
        this.institute = institute;
    }

}
