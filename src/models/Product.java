package models;

import utility.enums.Symbols;

import java.util.Date;

public class Product {

    private int productId;
    private Symbols symbol;
    private int lotSize;
    private String description;
    private Date creationDate = new Date();

    public Product(int productId, Symbols symbol, int lotSize, String description) {
        this.productId = productId;
        this.symbol = symbol;
        this.lotSize = lotSize;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", symbol=" + symbol +
                ", lotSize=" + lotSize +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
