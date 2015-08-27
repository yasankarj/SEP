package com.solutions.ray.expenser.test;

import android.test.AndroidTestCase;
import Controller.Tracker.TransactionHandler;

/**
 * Created by Yasanka on 2015-08-23.
 */
public class TransactionTest extends AndroidTestCase {

    public void testAddExpense() throws Exception{
        TransactionHandler ths = new TransactionHandler();
        //yyyy-MM-dd hh:mm WW

        String msg = ths.addNewExpense(getContext(),"1000.0","Test 1","Food","Lunch","Com bank","Com bank","2015-08-25 06:36 4");
        assertEquals("Successfully Added",msg);

        msg = ths.addNewExpense(getContext(),"-1000.0","Test 1","Food","Lunch","Com bank","Com bank","2015-08-25 06:36 4");
        assertEquals("Amount cannot be negative",msg);

        msg = ths.addNewExpense(getContext(),"1000.","Test 1","","Lunch","Com bank","Com bank","2015-08-25 06:36 4");
        assertEquals("Type cannot be empty",msg);
        getSum();
        addData();


    }

    public void getSum() throws Exception{
        TransactionHandler ths = new TransactionHandler();

        double valuePrev = ths.getDailyExpTot(getContext(),"5","daily");
        ths.addNewExpense(getContext(),"1500.0","Test 1","Food","Lunch","Com bank","Com bank","2015-08-25 06:36 5");
        double valueNext = ths.getDailyExpTot(getContext(),"5","daily");
        assertEquals(valuePrev+1500,valueNext);

    }
    public void addData() throws Exception{
        TransactionHandler ths = new TransactionHandler();
        ths.addNewExpense(getContext(),"100.0","Test 1","Food","Lunch","Com bank","Com bank","2014-08-25 06:36 5");
        ths.addNewExpense(getContext(),"150.0","Test 1","Food","Lunch","Com bank","Com bank","2015-07-25 06:36 4");
        ths.addNewExpense(getContext(),"200.0","Test 1","Food","Lunch","Com bank","Com bank","2015-08-23 06:36 4");
        ths.addNewExpense(getContext(),"120.0","Test 1","Food","Lunch","Com bank","Com bank","2015-08-24 06:36 5");
        ths.addNewExpense(getContext(),"100.0","Test 1","Food","Lunch","Com bank","Com bank","2015-08-25 06:36 5");
        ths.addNewExpense(getContext(),"100.0","Test 1","Food1","Lunch","Com bank","Com bank","2015-08-25 06:36 5");
        ths.addNewExpense(getContext(),"100.0","Test 1","Food2","Lunch","Com bank","Com bank","2015-08-25 06:36 5");

    }
}
