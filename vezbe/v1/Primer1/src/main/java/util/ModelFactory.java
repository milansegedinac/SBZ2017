package util;

import model.Customer;
import model.Order;

public class ModelFactory {
public static Order getOrderWithFiveHighRangeItems() {
        
        return new OrderBuilder(new Customer())
            .newLine()
                .withItem()
                    .withName("A")
                    .withCost(700.0)
                    .withSalePrice(800.0)
                .end()
                .withQuantity(1)
                .end()
            .end()
            .newLine()
                .withItem()
                    .withName("B")
                    .withCost(800.0)
                    .withSalePrice(850.0)
                .end()
                .withQuantity(2)
                .end()
            .end()
            .newLine()
                .withItem()
                    .withName("C")
                    .withCost(800.0)
                    .withSalePrice(850.0)
                .end()
                .withQuantity(3)
                .end()
            .end()
            .newLine()
                .withItem()
                    .withName("D")
                    .withCost(800.0)
                    .withSalePrice(850.0)
                .end()
                .withQuantity(4)
                .end()
            .end()    
            .newLine()
                .withItem()
                    .withName("E")
                    .withCost(800.0)
                    .withSalePrice(850.0)
                .end()
                .withQuantity(5)
                .end()
            .end()
        .build();
    }
    
    

}
