package com.kademika.day14.store.Market;

import com.kademika.day14.store.Connector;
import com.kademika.day14.store.Market.Objects.*;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.sql.*;
import java.util.*;

public class Market {

	private ArrayList<Transaction> transactions = new ArrayList<>();
    private HashMap<Integer,Animal> animals = new HashMap<>();
    private HashMap<Integer,Customer> customers = new HashMap<>();
    public final Connector CONNECTOR = new Connector();

	public Market() {

	}

    public void addAnimal(Animal animal) {
        animals.put(animal.getId(), animal);
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO `market`.`Goods` (`id_good`,`name`,`type`) VALUES (?,?,?)");
            stmt.setInt(1, animal.getId());
            stmt.setString(2,animal.getName());
            stmt.setString(3,animal.getType().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

	public void removeAnimal(Animal animal) {
        animals.remove(animal.getId());
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM `market`.`Goods` WHERE `id_good`=?");
            stmt.setInt(1, animal.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
	}

    public void addCustomer(Customer cust) {
        customers.put(cust.getId(), cust);
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO `market`.`Customers` (`id`,`name`) VALUES (?,?)");
            stmt.setInt(1,cust.getId());
            stmt.setString(2,cust.getName());
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
        customers.remove(cust.getId());
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM `market`.`Customers` WHERE `id_cust`=?");
            stmt.setInt(1,cust.getId());
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
                    "UPDATE `market`.`Goods` SET `price`=? WHERE `id_good`=?");
            stmt.setDouble(1, price);
            stmt.setInt(2, animal.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void buyToStore(Animal animal, int amount, double price) {
        int newAmount = animal.getAmount()+amount;
        animal.setAmount(newAmount);
        Connection connection = CONNECTOR.getConnection();
        try {
//            PreparedStatement stmt = connection.prepareStatement(
//                    "SELECT `amount` FROM `market`.`Goods` WHERE `id_good`=?");
//            stmt.setInt(1,animal.getId());
//            ResultSet result = stmt.executeQuery();
//            result.next();
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE `market`.`Goods` SET `amount`=? WHERE `id_good`=?");
            pstmt.setDouble(1, newAmount);
            pstmt.setInt(2, animal.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void takeFromStore(Animal animal, int amount) {
        int newAmount = animal.getAmount()-amount;
        animal.setAmount(newAmount);
        Connection connection = CONNECTOR.getConnection();
        try {
//            PreparedStatement stmt = connection.prepareStatement(
//                    "SELECT `amount` FROM `market`.`Goods` WHERE `name`=?");
//            stmt.setString(1,animal.getName());
//            ResultSet result = stmt.executeQuery();
//            result.next();
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE `market`.`Goods` SET `amount`=? WHERE `id_good`=?");
            pstmt.setDouble(1, newAmount);
            pstmt.setInt(2, animal.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void takeFromStore(Animal animal) {
        int newAmount = animal.getAmount()-1;
        animal.setAmount(newAmount);
        Connection connection = CONNECTOR.getConnection();
        try {
//            PreparedStatement stmt = connection.prepareStatement(
//                    "SELECT `amount` FROM `market`.`Goods` WHERE `name`=?");
//            stmt.setString(1,animal.getName());
//            ResultSet result = stmt.executeQuery();
//            result.next();
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE `market`.`Goods` SET `amount`=? WHERE `id_good`=?");
            pstmt.setDouble(1, newAmount);
            pstmt.setInt(2, animal.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
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
	
	public void printPrices() {
		for (Animal a : animals.values()) {
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
        for (Animal a : animals.values()) {
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

	public HashMap<Integer,Animal> getAnimals() {
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
        Connection connection = CONNECTOR.getConnection();
        try {
            ResultSet result = null;
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM `market`.`Goods`");
            result = stmt.executeQuery();
            while (result.next()) {
                Animal animal = new Animal(result.getInt("id_good"));
                animal.setName(result.getString("name"));
                animal.setPrice(result.getDouble("price"));
                animal.setAmount(result.getInt("amount"));
                animal.setType(Type.valueOf(result.getString("type")));
                animals.put(animal.getId(),animal);
            }

            stmt = connection.prepareStatement(
                    "SELECT * FROM `market`.`Customers`");
            result = stmt.executeQuery();
            while (result.next()) {
                Customer cust = new Customer(result.getInt("id_cust"));
                cust.setName(result.getString("name"));
                cust.setAmountPurchases(result.getInt("purchases"));
                cust.setSpendMoney(result.getDouble("spent"));
                customers.put(cust.getId(), cust);
            }

            stmt = connection.prepareStatement(
                    "SELECT * FROM `market`.`Transactions`");
            result = stmt.executeQuery();
            while (result.next()) {
                Transaction transacrion = new Transaction(result.getInt("id"));
                transacrion.setPrice(result.getDouble("price"));
                transacrion.setCustomer(customers.get(result.getInt("id_cust")));
                transacrion.setDate(result.getString("date"));

                HashMap<Animal,Integer> goods = new HashMap<>();
                int purchaseId = result.getInt("id_purch");
                PreparedStatement stmtInner = connection.prepareStatement(
                        "SELECT * FROM `market`.`Purchases` WHERE `id_purch`=? ");
                stmtInner.setInt(1,purchaseId);
                ResultSet resultInner = stmt.executeQuery();
                while (resultInner.next()) {
                    goods.put(animals.get(resultInner.getInt("id_good")),resultInner.getInt("amount"));
                }
                transacrion.setGoods(goods);
                transactions.add(transacrion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

}
