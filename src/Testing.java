import models.Order;
import utility.enums.OrderStatus;
import utility.enums.Side;
import utility.enums.Symbols;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Testing extends Thread {

    public void run() {
        for(int i = 0; i < 5; i++) {
            System.out.println("value of i is:- "+ i);
            try {
                Thread.sleep(100);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String args[]) {

        Date d1 = new Date();
        Testing t1 = new Testing();
        //t1.start();
        for(int i = 0; i <= 1000000; i++) {

        }
        Date d2 = new Date();

        System.out.println("start date is: - " + d1);
        System.out.println("end date is: - " + d2);
        long diff = d2.getTime() - d1.getTime();
        System.out.println("difference is: - " + TimeUnit.MICROSECONDS.toMicros(diff));
    }



}
