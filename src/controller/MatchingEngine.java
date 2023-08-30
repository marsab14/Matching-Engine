package controller;

import models.Order;
import models.OrderBook;
import utility.enums.OrderStatus;

import java.util.LinkedList;
import java.util.Random;

public class MatchingEngine {

    MatchingEngineSingleThread obc = new MatchingEngineSingleThread();
    public void processBuyOrders(OrderBook orderBook, Order order) {
        LinkedList<Order> sellOrderQueue = orderBook.getSellBook();
        if(sellOrderQueue.size() == 0) {
            System.out.println("Currently No Sell Orders Available");
            return;
        }
        int filled = 0;
        LinkedList<Order> consumedSellOrders = new LinkedList<>();
        for(Order tempOrder: sellOrderQueue) {
            if(tempOrder.getPrice() > order.getPrice()) {
                //sell price is too high
                break;
            } else if(filled == order.getQuantity()) {
                //order filled
                break;
            }
            if(filled + tempOrder.getQuantity() <= order.getQuantity()) {
                filled = filled + tempOrder.getQuantity();
                //create an execution/trade and append to trade array
                System.out.println("Trade Generated");
                consumedSellOrders.add(tempOrder);

            } else if(filled + tempOrder.getQuantity() > order.getQuantity()) {
                int volume = order.getQuantity() - filled;
                filled = filled + volume;
                //create an execution/trade and append to trade array
                System.out.println("Trade Generated");
                tempOrder.setQuantity(tempOrder.getQuantity() - volume);
            }
        }
        //if even after iterating over the loop any order quantity remains, create a new order for it
        if(filled < order.getQuantity()) {
            //creaete new sell Order for this left over quantity
            Order od = new Order(100 + new Random().nextInt(1000), order.getProductId(), order.getPrice(), order.getQuantity() - filled, OrderStatus.NEW, order.getOrderSide(), order.getSymbol(), order.getOrderType()  );
            orderBook.getSellBook().add(od);
        }
        //remove all the consumed sell orders from the sellOrderQueue
        for(Order removeOrder: consumedSellOrders) {
            obc.deleteOrder(orderBook, removeOrder);
        }
        //remove the current order
        obc.deleteOrder(orderBook, order);

    }

    public void processSellOrders(OrderBook orderBook , Order order) {
        LinkedList<Order> buyOrderQueue = orderBook.getBuyBook();
        if(buyOrderQueue.size() == 0) {
            System.out.println("Currently No Buy Orders Availalbe");
            return;
        }
        int filled = 0;
        LinkedList<Order> consumedBuyOrders = new LinkedList<>();
        for(Order tempOrder: buyOrderQueue) {
            if(tempOrder.getPrice() < order.getPrice()) {
                //price too low
                break;
            } else if(filled == tempOrder.getQuantity()) {
                //order filled

                break;
            }
            if(filled + tempOrder.getQuantity() <= order.getQuantity()) {
                filled = filled + tempOrder.getQuantity();
                //create an execution/trade and append to trade array
                System.out.println("Trade Generated");
                consumedBuyOrders.add(tempOrder);
            } else if (filled + tempOrder.getQuantity() > order.getQuantity()) {
                int volume = order.getQuantity() - filled;
                filled = filled + volume;
                //create an execution/trade and append to trade array
                System.out.println("Trade Generated");
                tempOrder.setQuantity(tempOrder.getQuantity() - volume);
            }
        }

        //if even after iterating over the loop any order quantity remains, create a new order for it
        if(filled < order.getQuantity()) {
            //creaete new sell Order for this left over quantity
            Order od = new Order(100 + new Random().nextInt(1000), order.getProductId(), order.getPrice(), order.getQuantity() - filled, OrderStatus.NEW, order.getOrderSide(), order.getSymbol(), order.getOrderType()  );
            orderBook.getBuyBook().add(od);
        }
        //remove all the consumed sell orders from the sellOrderQueue
        for(Order removeOrder: consumedBuyOrders) {
            obc.deleteOrder(orderBook, removeOrder);
        }
        obc.deleteOrder(orderBook, order);

    }

}
