package models;

public class Trade {
    private int tradeId;
    private int orderId;
    private int price;
    private int quantity;

    public Trade(int tradeId, int orderId, int price, int quantity) {
        this.tradeId = tradeId;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
    }
}
