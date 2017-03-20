package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderId;
    private Date date;
    private Customer customer;
    private List<OrderLine> orderLines = new ArrayList<>();
    private Discount discount;

    public Order() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setItems(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

  
    public double getTotal() {
        return this.getOrderLines().stream()
                .mapToDouble(item -> item.getItem().getSalePrice() * item.getQuantity())
                .sum();
    }
    
    public int getTotalItems() {
        return this.getOrderLines().stream()
                .mapToInt(item -> item.getQuantity())
                .sum();
    }
    
    public void increaseDiscount(double increase) {
        if (discount == null) {
            discount = new Discount(0.0);
        }
        discount.setPercentage(discount.getPercentage() + increase);
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((orderLines == null) ? 0 : orderLines.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderLines == null) {
			if (other.orderLines != null)
				return false;
		} else if (!orderLines.equals(other.orderLines))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", date=" + date + ", customer=" + customer + ", orderLines=" + orderLines
				+ ", discount=" + discount + "]";
	}
    
    

  
}
