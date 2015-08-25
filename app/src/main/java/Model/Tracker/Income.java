package Model.Tracker;

/**
 * Created by Yasanka on 2015-06-21.
 */
public class Income extends Transaction{
    private String institute;
    public Income(double amount, String type,String subType, String institute, String desc,String timeStamp){
        super(amount, type,subType,desc,timeStamp,"","");
        //(double amount, String type,String subType, String desc,String timeStamp)
        this.institute = institute;
    }

}
