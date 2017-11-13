package com.springmvc.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer orderId;
	
	@Column(name="total_sum")
	private float totalSum;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> items;
	
	public Order() {
		items = new ArrayList<OrderItem>();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public float getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(float totalSum) {
		this.totalSum = totalSum;
	}
	
	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	
}
