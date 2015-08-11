package com.kademika.day14.store.Market.Objects;

import java.util.HashMap;

public class Customer {

    private static int current_id = 0;
    private int id;
	private String name;
	private int amountPurchases;
	private double spendMoney;
	private HashMap<Animal,Integer> bucket = new HashMap<>();

	public Customer() {
        current_id++;
        this.id = current_id;
	}

    public Customer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmountPurchases(int amountPurchases) {
        this.amountPurchases = amountPurchases;
    }

    public void setSpendMoney(double spendMoney) {
        this.spendMoney = spendMoney;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmountPurchases() {
		return amountPurchases;
	}

	public double getSpendMoney() {
		return spendMoney;
	}

	public void addPurchase() {
		amountPurchases++;
	}

	public void spendMoney(double SpendMoney) {
		this.spendMoney += SpendMoney;
	}

	public void addOnBucket(Animal a, int n) {
        if (bucket.containsKey(a)) {
            int exist = bucket.get(a);
            bucket.put(a, exist+n);
        } else {
            bucket.put(a, n);
        }
	}

	public void addOnBucket(Animal a) {
		if (bucket.containsKey(a)) {
            int exist = bucket.get(a);
            bucket.put(a, exist+1);
        } else {
            bucket.put(a, 1);
        }
	}

	public void delFromBucket(Animal a, int n) {

			if (bucket.containsKey(a)) {
                int exist = bucket.get(a);
                if ((exist-n) <= 0) {
                    bucket.remove(a);
                } else {
                    bucket.put(a, exist - n);
                }
			}
	}

	public void delFromBucket(Animal a) {
        if (bucket.containsKey(a)) {
            int exist = bucket.get(a);
            if ((exist-1) <= 0) {
                bucket.remove(a);
            } else {
                bucket.put(a, exist - 1);
            }
        }
	}

	public HashMap<Animal,Integer> getBucket() {
		return bucket;
	}
	
	public void clearBucket() {
		bucket = new HashMap<Animal,Integer>();
	}

}
