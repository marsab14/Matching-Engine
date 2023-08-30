package models;

import utility.enums.OrderStatus;
import utility.enums.Side;
import utility.enums.Symbols;

import java.util.Date;
import java.util.Objects;

public class Order implements Comparable<Order>{

    private int orderId;
    private int productId;
    private int price;
    private int quantity;
    private OrderStatus orderStatus;
    private Side orderSide;
    private Symbols symbol;
    private String orderType;
    private Date creationTime = new Date();
   // private Date entryTime;

    @Override
    public int compareTo(Order order) {
        if(this.price > order.price ) {
            return 1;
        } else if(this.price < order.price) {
            return -1;
        } else {
            return 0;
        }
    }
    public Order(int orderId, int productId, int price, int quantity, OrderStatus orderStatus, Side orderSide, Symbols symbol, String orderType) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
        this.orderSide = orderSide;
        this.symbol = symbol;
        this.orderType = orderType;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Side getOrderSide() {
        return orderSide;
    }

    public void setOrderSide(Side orderSide) {
        this.orderSide = orderSide;
    }

    public Symbols getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbols symbol) {
        this.symbol = symbol;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", orderStatus=" + orderStatus +
                ", orderSide=" + orderSide +
                ", symbol=" + symbol +
                ", orderType='" + orderType + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }


}
