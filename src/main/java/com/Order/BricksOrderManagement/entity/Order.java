package com.Order.BricksOrderManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER")
public class Order {

	@Id
	@Column(name = "orderref")
	private Long orderRef;

	@Column(name = "bricks")
	private Integer bricks;

	@Column(name = "dispatched")
	private boolean dispatched;

	public Long getOrderRef() {
		return orderRef;
	}

	public void setOrderRef(Long orderRef) {
		this.orderRef = orderRef;
	}

	public Integer getBricks() {
		return bricks;
	}

	public void setBricks(Integer bricks) {
		this.bricks = bricks;
	}

	public boolean isDispatched() {
		return dispatched;
	}

	public void setDispatched(boolean dispatched) {
		this.dispatched = dispatched;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bricks == null) ? 0 : bricks.hashCode());
		result = prime * result + (dispatched ? 1231 : 1237);
		result = prime * result + ((orderRef == null) ? 0 : orderRef.hashCode());
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
		if (bricks == null) {
			if (other.bricks != null)
				return false;
		} else if (!bricks.equals(other.bricks))
			return false;
		if (dispatched != other.dispatched)
			return false;
		if (orderRef == null) {
			if (other.orderRef != null)
				return false;
		} else if (!orderRef.equals(other.orderRef))
			return false;
		return true;
	}

	public Order(Long orderRef, Integer bricks, boolean dispatched) {
		super();
		this.orderRef = orderRef;
		this.bricks = bricks;
		this.dispatched = dispatched;
	}

	@Override
	public String toString() {
		return "Order [orderRef=" + orderRef + ", bricks=" + bricks + ", dispatched=" + dispatched + "]";
	}

}
