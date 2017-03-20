package model;

import java.io.Serializable;

public class Discount implements Serializable {

    private static final long serialVersionUID = 1L;
    
        
    private Double percentage;

    
    public Discount() {
    }

    public Discount(Double percentage) {
        this.percentage = percentage;
    }
        

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((percentage == null) ? 0 : percentage.hashCode());
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
		Discount other = (Discount) obj;
		if (percentage == null) {
			if (other.percentage != null)
				return false;
		} else if (!percentage.equals(other.percentage))
			return false;
		return true;
	}

    
    
    
}
