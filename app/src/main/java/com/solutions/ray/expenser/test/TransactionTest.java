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
        String msg = ths.addNewExpense(getContext(),1000.0,"Test 1","Food","Lunch","Com bank","Com bank","2015-08-25 06:36 4");
        assertEquals("Successfully Added",msg);

        msg = ths.addNewExpense(getContext(),-1000.0,"Test 1","Food","Lunch","Com bank","Com bank","2015-08-25 06:36 4");
        assertEquals("Amount should not be negative",msg);

        msg = ths.addNewExpense(getContext(),1000.0,"Test 1","","Lunch","Com bank","Com bank","2015-08-25 06:36 4");
        assertEquals("Type cannot be empty",msg);

        double valuePrev = ths.getDailyExpTot(getContext(),"2015-08-25");
        ths.addNewExpense(getContext(),1500.0,"Test 1","Food","Lunch","Com bank","Com bank","2015-08-25 06:36 4");
        double valueNext = ths.getDailyExpTot(getContext(),"2015-08-25");
        assertEquals(valuePrev+1500,valueNext);

    }
}
