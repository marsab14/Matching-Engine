package models;

import utility.enums.Symbols;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class OrderBook {

    private LinkedList<Order> buyBook;
    private LinkedList<Order> sellBook;
    private Symbols symbol;
    private Date creatioDate = new Date();

    public OrderBook(Symbols symbol) {
        this.buyBook = new LinkedList<Order>();
        this.sellBook = new LinkedList<>();
        this.symbol = symbol;
    }

    public LinkedList<Order> getBuyBook() {
        return buyBook;
    }

    public void setBuyBook(LinkedList<Order> buyBook) {
        this.buyBook = buyBook;
    }

    public LinkedList<Order> getSellBook() {
        return sellBook;
    }

    public void setSellBook(LinkedList<Order> sellBook) {
        this.sellBook = sellBook;
    }

    public Symbols getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbols symbol) {
        this.symbol = symbol;
    }

    public Date getCreatioDate() {
        return creatioDate;
    }

    public void setCreatioDate(Date creatioDate) {
        this.creatioDate = creatioDate;
    }

    @Override
    public String toString() {
        return "OrderBook{" +
                "buyBook=" + buyBook +
                ", sellBook=" + sellBook +
                ", symbol=" + symbol +
                ", creatioDate=" + creatioDate +
                '}';
    }
}
