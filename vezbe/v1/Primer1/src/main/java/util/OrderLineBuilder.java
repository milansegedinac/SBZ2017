package util;

import java.util.Optional;

import model.OrderLine;

/**
 *
 * @author esteban
 */
public class OrderLineBuilder {
    
    private final OrderBuilder superBuilder;
    private final OrderLine instance;
    
    private Optional<ItemBuilder> itemBuilder = Optional.empty();
    
    public OrderLineBuilder(OrderBuilder superBuilder) {
        this.superBuilder = superBuilder;
        
        this.instance = new OrderLine();
        this.instance.setQuantity(0);
    }
    
    public OrderLineBuilder withQuantity(int quantity){
        this.instance.setQuantity(quantity);
        return this;
    }
    
    public ItemBuilder withItem(){
        this.itemBuilder = Optional.of(new ItemBuilder(this));
        return this.itemBuilder.get();
    }
    
    public OrderLine build(){
        if (this.itemBuilder.isPresent()){
            this.instance.setItem(this.itemBuilder.get().build());
        }
        return this.instance;
    }
    
    public OrderBuilder end(){
        return superBuilder;
    }
    
}
