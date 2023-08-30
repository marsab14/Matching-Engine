import controller.MatchingEngineSingleThread;
import models.Order;
import models.OrderBook;
import utility.enums.OrderStatus;
import utility.enums.Side;
import utility.enums.Symbols;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

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
        MatchingEngineSingleThread obc = new MatchingEngineSingleThread();
        Date startDate = new Date();
        for(int i = 1; i <= 400000; i++) {
            Order odr = new Order(rand.nextInt(10000), 111, rand.nextInt(1000), 30, OrderStatus.NEW, Side.SELL, Symbols.TATA, "LIMIT" );
            obc.handleOrder(ob, odr);
        }
        Date endDate = new Date();
        long timeDiff = endDate.getTime() - startDate.getTime();
        System.out.println("Total Time Taken in Micro Seconds is: -" + TimeUnit.MICROSECONDS.toMicros(timeDiff));
        System.out.println("Total Time taken in mili second is: -" + TimeUnit.MICROSECONDS.toMillis(timeDiff));
        System.out.println("Total Time taken in second is: -" + TimeUnit.MICROSECONDS.toSeconds(timeDiff));

    }
}