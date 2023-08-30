package controller;

import models.Order;
import models.OrderBook;
import utility.enums.Side;
import utility.enums.OrderStatus;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

public class MatchingEngineMultithread {

    public void addOrder(OrderBook orderBook, Order order) throws Exception {
        if(order.getOrderSide().equals(Side.BUY)) {
            orderBook.getBuyBook().add(order);
            Collections.sort(orderBook.getBuyBook());
            processBuyOrders(orderBook, order);
        } else if(order.getOrderSide().equals(Side.SELL)) {
            orderBook.getSellBook().add(order);
            Collections.sort(orderBook.getSellBook());
            processSellOrders(orderBook, order);
        }
    }

    public void deleteOrder(OrderBook orderBook, Order order) {
        if(order.getOrderSide().equals(Side.BUY)) {
            orderBook.getBuyBook().remove(order);
        } else if(order.getOrderSide().equals(Side.SELL)) {
            orderBook.getSellBook().remove(order);
        }
    }

    public void handleCancel(OrderBook orderBook, Order order) {
        if(order.getOrderSide() == Side.BUY) {

        } else {

        }
    }

    public void handleOrder(OrderBook orderBook, Order order) {
        switch (order.getOrderStatus()) {
            case NEW:
                try {
                    addOrder(orderBook, order);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                break;

            case CANCEL:
                handleCancel(orderBook,order);
                break;
            default:
                break;
        }
    }

    synchronized public void processBuyOrders(OrderBook orderBook, Order order) {
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
                System.out.println("Order Filled...........Return");
                break;
            }
            if(filled + tempOrder.getQuantity() <= order.getQuantity()) {
                filled = filled + tempOrder.getQuantity();
                //create an execution/trade and append to trade array
                consumedSellOrders.add(tempOrder);

            } else if(filled + tempOrder.getQuantity() > order.getQuantity()) {
                int volume = order.getQuantity() - filled;
                filled = filled + volume;
                //create an execution/trade and append to trade array
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
            deleteOrder(orderBook, removeOrder);
        }
        //remove the current order
        deleteOrder(orderBook, order);

    }

    synchronized public void processSellOrders(OrderBook orderBook , Order order) {
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
                System.out.println("Order Filled...........Return");
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
            deleteOrder(orderBook, removeOrder);
        }
        deleteOrder(orderBook, order);

    }



}
