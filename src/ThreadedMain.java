import controller.MatchingEngine;
import controller.MatchingEngineMultithread;
import controller.MatchingEngineSingleThread;
import models.Order;
import models.OrderBook;
import utility.enums.OrderStatus;
import utility.enums.Side;
import utility.enums.Symbols;

import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThreadedMain extends Thread{
    public void run(OrderBook ob, LinkedList<Order> orders) {
        MatchingEngineMultithread mtm = new MatchingEngineMultithread();
        for(Order order: orders) {
            mtm.handleOrder(ob,order);
        }
    }
    public static void main(String args[]) {
        Random rand = new Random();
        //considering all the orders are for same SYMBOL
        OrderBook ob = new OrderBook(Symbols.TATA);
        //generating initial random buy orders
        for(int i = 1; i <= 100; i++) {
            Order o = new Order(rand.nextInt(10000), 111, rand.nextInt(100), i * 10, OrderStatus.NEW, Side.BUY, Symbols.TATA, "LIMIT" );
            ob.getBuyBook().add(o);
        }
        //generating initial random sell orders
        for(int i = 1; i <= 100; i++) {
            Order o = new Order(rand.nextInt(10000), 111, rand.nextInt(100), i * 10, OrderStatus.NEW, Side.SELL, Symbols.TATA, "LIMIT" );
            ob.getSellBook().add(o);
        }

        LinkedList<Order> forFirst = new LinkedList<>();
        LinkedList<Order> forSecond = new LinkedList<>();
        LinkedList<Order> forThird = new LinkedList<>();
        LinkedList<Order> forFourth = new LinkedList<>();
        for(int i = 1; i <= 100000; i++) {
            Order odr = new Order(rand.nextInt(10000), 111, rand.nextInt(1000), 30, OrderStatus.NEW, Side.SELL, Symbols.TATA, "LIMIT" );
            forFirst.add(odr);
        }
        for(int i = 1; i <= 100000; i++) {
            Order odr = new Order(rand.nextInt(10000), 111, rand.nextInt(1000), 30, OrderStatus.NEW, Side.SELL, Symbols.TATA, "LIMIT" );
            forSecond.add(odr);
        }
        for(int i = 1; i <= 100000; i++) {
            Order odr = new Order(rand.nextInt(10000), 111, rand.nextInt(1000), 30, OrderStatus.NEW, Side.SELL, Symbols.TATA, "LIMIT" );
            forThird.add(odr);
        }
        for(int i = 1; i <= 100000; i++) {
            Order odr = new Order(rand.nextInt(10000), 111, rand.nextInt(1000), 30, OrderStatus.NEW, Side.SELL, Symbols.TATA, "LIMIT" );
            forFourth.add(odr);
        }
        Date startDate = new Date();
        ThreadedMain tm1 = new ThreadedMain();
        ThreadedMain tm2 = new ThreadedMain();
        ThreadedMain tm3 = new ThreadedMain();
        ThreadedMain tm4 = new ThreadedMain();
        tm1.run(ob, forFirst);
        tm2.run(ob, forSecond);
        tm3.run(ob, forThird);
        tm4.run(ob, forFourth);
        Date endDate = new Date();
        long timeDiff = endDate.getTime() - startDate.getTime();
        System.out.println("Total Time Taken (Multithreaded) in Micro Seconds is: -" + TimeUnit.MICROSECONDS.toMicros(timeDiff));
        System.out.println("Total Time taken (Multithreaded) in mili second is: -" + TimeUnit.MICROSECONDS.toMillis(timeDiff));
        System.out.println("Total Time taken (Multithreaded) in second is: -" + TimeUnit.MICROSECONDS.toSeconds(timeDiff));
    }
}
