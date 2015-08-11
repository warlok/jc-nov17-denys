package com.kademika.day14.store.Market.Objects;

import java.util.HashMap;

public class Transaction {

    private static int current_id = 0;
    private int id;
	private String date;
	private Customer customer;
	private HashMap<Animal,Integer> goods;
	private double price;

	public Transaction(String date, Customer customer, HashMap<Animal,Integer> goods, double price) {
        current_id++;
        this.id = current_id;
        this.date = date;
		this.customer = customer;
		this.goods = goods;
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public HashMap<Animal,Integer> getGoods() {
		return goods;
	}

	public double getPrice() {
		return price;
	}
	
}