package Controller.Tracker;
/**
 * Created by Yasanka on 2015-06-20.
 */

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DA.Tracker.TransactionDA;
import Model.Tracker.Expense;
import Model.Tracker.Income;

public class TransactionHandler {
    /*Controller class which maps model objects with the data base*/

    public String addNewExpense(Context contxt, String amountStr, String desc, String type, String subType, String payee, String payType, String dateStr){
        //(double amount, String type,String subType,String desc, String payee, String payType)
        String msg = "";
        String [] dateVals = dateStr.split(" ");
        if(amountStr.equals("")){
            msg = "Amount cannot be empty";
        }

        else if(type.equals("")){
            msg = "Type cannot be empty";
        }

        else{

            try{
                double amount = Double.parseDouble(amountStr);
                if(amount<=0){
                    msg = "Amount cannot be negative";
                }

                else {

                    Expense exp = new Expense(amount, type, subType, desc, payee, payType, dateVals[0], dateVals[1], dateVals[2]);    //Expense object is created with the passed data
                    TransactionDA transDA = new TransactionDA();
                    transDA.addExpense(exp, contxt);                                //Model object is passed to the data access layer
                    msg = "Successfully Added";
                }
            }

            catch(Exception ex) {
                msg = "Unable to enter data";
            }
        }

        return msg;
    }


    public String updateExpense(Context contxt,String expID, String amountStr, String desc, String type, String subType, String payee, String payType, String date, String time, String week){
        //(double amount, String type,String subType,String desc, String payee, String payType)
        String msg = "";
        int id = Integer.parseInt(expID);
        if(amountStr.equals("")){
            msg = "Amount cannot be empty";
        }

        else if(type.equals("")){
            msg = "Type cannot be empty";
        }

        else{

            try{
                double amount = Double.parseDouble(amountStr);
                if(amount<=0){
                    msg = "Amount cannot be negative";
                }

                else {
                    //Expense(int id,double amount, String type,String subType,String desc, String payee, String payType, String dateStr, String timeStr, String weekStr)
                    Expense expense = new Expense(id,amount, type, subType, desc, payee, payType, date, time, week);    //Expense object is created with the passed data
                    TransactionDA transDA = new TransactionDA();
                    transDA.updateExpense(contxt,expense);                                //Model object is passed to the data access layer
                    msg = "Successfully Updated";
                }
            }

            catch(Exception ex) {
                msg = "Unable to update data";
            }
        }

        return msg;
    }

    public String deleteExpense(Context context, String expID){
        String msg = "";
        TransactionDA transactionDA = new TransactionDA();
        try{
            transactionDA.deleteExpense(context,expID);
            msg = "Succesfully Deleted";
        }
        catch (Exception ex){
            msg = "Failed to delete";
        }
        return msg;
    }

    public HashMap<String,String> getExpense(Context context, String expID){

        HashMap<String,String> elementMap = new HashMap<>();    // hashmap to store retreived data
        int e_id = Integer.parseInt(expID);                     // expense ID which is needed to be retireved
        TransactionDA transDA = new TransactionDA();            //DA object initialized

        try{
            Expense expense = transDA.getExpense(context,e_id);     //expense object is retrieved

            /*data is stored in a HashMap*/
            elementMap.put("expense","A");
            elementMap.put("expID",""+expense.getTransID());
            elementMap.put("amount",""+expense.getAmount());
            elementMap.put("payee",""+expense.getPayee());
            elementMap.put("payType",""+expense.getPayType());
            elementMap.put("category",""+expense.getType());
            elementMap.put("subCategory",""+expense.getSubType());
            elementMap.put("desc",""+expense.getDesc());
            elementMap.put("date",""+expense.getDate());
            elementMap.put("time",""+expense.getTime());
            elementMap.put("week",""+expense.getWeek());

        }

        catch (NullPointerException ex ){
            /*if received null expense*/
            elementMap.put("expense","NA");
        }
         return elementMap;
    }


    public double getDailyExpTot(Context context,String value, String range){
        TransactionDA transDA = new TransactionDA();
        return transDA.sumExpenses(context,value,range);
    }

    public ArrayList<String> getExpensesByDate(Context context, String date){
        ArrayList <String> elementList = new ArrayList<>();
        TransactionDA transDA = new TransactionDA();
        ArrayList <Expense> expensesList = transDA.getExpensesByDate(context,date);

        for(Expense expense : expensesList){
            String str = expense.getTransID()+" "+expense.getAmount()+" "+expense.getType()+" |"+expense.getSubType()+" $"+expense.getPayType();
            elementList.add(str);
        }
        return elementList;
    }

    public ArrayList<String> getExpensesByDateCharts(Context context, String date){
        ArrayList <String> elementList = new ArrayList<>();
        TransactionDA transDA = new TransactionDA();
        ArrayList <Expense> expensesList = transDA.getExpensesByDate(context,date);

        for(Expense expense : expensesList){
            String str = expense.getAmount()+" "+expense.getType();
            elementList.add(str);
        }
        return elementList;
    }
    public double getDailyIncTot(Context context){

        TransactionDA transDA = new TransactionDA();
        return transDA.sumIncome(context);
    }





    public void addNewIncome(Context contxt, Double amount, String desc, String cat, String inst){
        Income income = new Income(amount,cat,"",inst,desc,"");    //Income object is created with the passed data
        TransactionDA transDA = new TransactionDA();
        transDA.addIncome(income,contxt);                                //Model object is passed to the data access layer
    }
}
