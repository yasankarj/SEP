package Model.Tracker;

/**
 * Created by Yasanka on 2015-06-21.
 */
public class Income extends Transaction{
    private String institute;
    public Income(double amount, String type, String institute, String desc){
        super(amount, type,desc);
        this.institute = institute;
    }

}
