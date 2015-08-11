package com.kademika.day14.store.Market;

import com.kademika.day14.store.Connector;
import com.kademika.day14.store.Market.Objects.*;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.sql.*;
import java.util.*;

public class Market {

	private ArrayList<Transaction> transactions = new ArrayList<>();
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    public final Connector CONNECTOR = new Connector();

	public Market() {

	}

    public void addAnimal(Animal animal) {
        animals.add(animal);
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO `store`.`Goods` (`name`,`type`) VALUES (?,?)");
            stmt.setString(1,animal.getName());
            stmt.setString(2,animal.getType().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

	public void removeAnimal(Animal animal) {
        animals.remove(animal);
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM `store`.`Goods` WHERE `name`=?");
            stmt.setString(1,animal.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
	}

    public void addCustomer(Customer cust) {
        customers.add(cust);
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO `store`.`Customers` (`name`) VALUES (?)");
            stmt.setString(1,cust.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCustomer(Customer cust) {
        customers.remove(cust);
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM `store`.`Customers` WHERE `name`=?");
            stmt.setString(1,cust.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void setPrice(Animal animal, double price) {
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE `store`.`Goods` SET `price`=? WHERE `name`=?");
            stmt.setDouble(1, price);
            stmt.setString(2, animal.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void buyToStore(Animal animal, int amount, double price) {
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT `amount` FROM `store`.`Goods` WHERE `name`=?");
            stmt.setString(1,animal.getName());
            ResultSet result = stmt.executeQuery();
            result.next();
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE `store`.`Goods` SET `amount`=? WHERE `name`=?");
            pstmt.setDouble(1, result.getDouble(1)+amount);
            pstmt.setString(2, animal.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void takeFromStore(Animal animal, int amount) {
        animal.setAmount(animal.getAmount()-amount);
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT `amount` FROM `store`.`Goods` WHERE `name`=?");
            stmt.setString(1,animal.getName());
            ResultSet result = stmt.executeQuery();
            result.next();
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE `store`.`Goods` SET `amount`=? WHERE `name`=?");
            pstmt.setDouble(1, result.getDouble(1)-amount);
            pstmt.setString(2, animal.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void takeFromStore(Animal animal) {
        animal.setAmount(animal.getAmount()-1);
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT `amount` FROM `store`.`Goods` WHERE `name`=?");
            stmt.setString(1,animal.getName());
            ResultSet result = stmt.executeQuery();
            result.next();
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE `store`.`Goods` SET `amount`=? WHERE `name`=?");
            pstmt.setDouble(1, result.getDouble(1)-1);
            pstmt.setString(2, animal.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

	public void addOnStore(Animal animal, int amount, double price) {
        buyToStore(animal, amount,price);
	}

	public void sell(String day, Customer cust, HashMap<Animal,Integer> goods) {
		double price = 0;
		if (!goods.isEmpty()) {
		for (Animal animal : goods.keySet()) {
            int amount = goods.get(animal);
			takeFromStore(animal,amount);
			price += animal.getPrice()*amount;
		}
            transactions.add(new Transaction(day,cust,goods,price));
		cust.spendMoney(price);
		cust.addPurchase();
		cust.clearBucket();
		} else {System.out.println("Bucket is empty");}
	}

	public void addOnStore(Animal a, double price) {
        buyToStore(a, 1, price);
	}
	
	public void printPrices() {
		for (Animal a : animals) {
			System.out.println("Name: " + a.getName() + "\tPrice: " + a.getPrice());
		}
	}
	
	public void printCatalog() {
		
		ArrayList<Animal> anim = (ArrayList<Animal>) animals.clone();
		Collections.sort(anim);
		Type category = null;
		for (Animal a : anim) {
			
			if (a.getType() != category) {
				category = a.getType();
				System.out.println();
				System.out.println("Class: " + category);
			}
			System.out.println("Name: " + a.getName() + "\tPrice: " + a.getPrice());
		}
		}

    public boolean checkAmount(Animal a) {
        if (a.getAmount() >= 3) {
            return false;
        }
        System.out.println("There is only" + a.getAmount() + " items" );
        return true;
    }

    public void printStore() {
        for (Animal a : animals) {
            System.out.println("Name: " + a.getName() + "\tAmount: " + a.getAmount());
        }
    }
	
	public String getGoodsNames(HashMap<Animal,Integer> bucket) {
		String result = "";
		for (Animal animal : bucket.keySet()) {
                result += animal.getName() + "(x" + bucket.get(animal) + "); ";
		}
		return result;
	}

	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
	public JScrollPane printTransactions() {
		int i = 1;
        Object[][] data = new Object[100][5];
		for (Transaction tr : transactions) {
            data[i-1][0] = i;
            data[i-1][1] = tr.getDate();
            data[i-1][2] = tr.getCustomer().getName();
            data[i-1][3] = getGoodsNames(tr.getGoods());
            data[i-1][4] = tr.getPrice();
            i++;
		}
        String[] columsNames = {"Id ", "Date", "Customer name", "Goods", "Price"};
        JTable table = new JTable(data , columsNames);
        TableColumn column = null;
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(25);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(150);
        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(200);
        JScrollPane sp = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        return sp;
	}

	public void init() {

        /*addCustomer("Denys");
        addCustomer("Nina");
        addCustomer("Oleh");
        addCustomer("Vasya");

		addAnimal("tiger",Type.Mammal);
        setPrice("tiger",30);
        byToStore("tiger",10,0);

		addAnimal("rabbit", Type.Mammal);
        setPrice("rabbit",5);
        byToStore("rabbit",5,0);

		addAnimal("raccoon", Type.Mammal);
        setPrice("raccoon",2);
        byToStore("raccoon",3,0);

		addAnimal("penguin", Type.Bird);
        setPrice("penguin",100);
        byToStore("penguin",12,0);

		addAnimal("chameleon", Type.Reptile);
        setPrice("chameleon",1);
        byToStore("chameleon",40,0);

		addAnimal("iguana", Type.Reptile);
        setPrice("iguana",3);
        byToStore("iguana",11,0);

		addAnimal("owl", Type.Bird);
        setPrice("owl",12);
        byToStore("owl",2,0);

		addAnimal("parrot", Type.Bird);
        setPrice("parrot",7);
        byToStore("parrot",50,0);

		addAnimal("sunfish", Type.Fish);
        setPrice("sunfish",1000);
        byToStore("sunfish",6,0);

		addAnimal("boa", Type.Reptile);
        setPrice("boa",100);
        byToStore("boa",24,0);

		addAnimal("frog", Type.Amphibian);
        setPrice("frog",5);
        byToStore("frog",33,0);

		addAnimal("aligator", Type.Reptile);
        setPrice("aligator",500);
        byToStore("aligator",15,0);

		addAnimal("newt", Type.Amphibian);
        setPrice("newt", 10);
        byToStore("newt", 60, 0);*/
	}

}
