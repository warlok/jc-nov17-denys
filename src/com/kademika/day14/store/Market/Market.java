package com.kademika.day14.store.Market;

import com.kademika.day14.store.Connector;
import com.kademika.day14.store.Market.Objects.*;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Market {
	
	private Store store = new Store();
	private ArrayList<Purchase> purchases = new ArrayList<>();
    public final Connector CONNECTOR = new Connector();

	public Market() {

	}



    public void addAnimal(String name,Type type) {
//		store.addAnimal(a);
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO `store`.`Goods` (`name`,`type`) VALUES (?,?)");
            stmt.setString(1,name);
            stmt.setString(2,type.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	public void removeAnimal(String name) {
//		store.delAnimal(a);
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM `store`.`Goods` WHERE `name`=?");
            stmt.setString(1,name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

    public void addCustomer(String name) {
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO `store`.`Customers` (`name`) VALUES (?)");
            stmt.setString(1,name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeCustomer(String name) {
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM `store`.`Customers` WHERE `name`=?");
            stmt.setString(1,name);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPrice(String good, double price) {
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE `store`.`Goods` SET `price`=? WHERE `name`=?");
            stmt.setDouble(1, price);
            stmt.setString(2, good);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void byToStore(String good, int amount, double price) {
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT `amount` FROM `store`.`Goods` WHERE `name`=?");
            stmt.setString(1,good);
            ResultSet result = stmt.executeQuery();
            result.next();
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE `store`.`Goods` SET `amount`=? WHERE `name`=?");
            pstmt.setDouble(1, result.getDouble(1)+amount);
            pstmt.setString(2, good);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void takeFromStore(String good, int amount) {
        Connection connection = CONNECTOR.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT `amount` FROM `store`.`Goods` WHERE `name`=?");
            stmt.setString(1,good);
            ResultSet result = stmt.executeQuery();
            result.next();
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE `store`.`Goods` SET `amount`=? WHERE `name`=?");
            pstmt.setDouble(1, result.getDouble(1)-amount);
            pstmt.setString(2, good);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

 //------------------------------------------------------------------------------------------

	public void addOnStore(Animal a, int n) {
		store.getOnStore(a, n);
	}

	public void sell(String day, Customer cust, LinkedList<Animal> goods) {
		double price = 0;
		if (goods != null) {
		for (Animal a : goods) {
			store.takeFromStore(a);
			price += a.getPrice();
		}
		purchases.add(new Purchase(day,cust,goods,price));
		cust.spendMoney(price);
		cust.addPurchase();
		cust.clearBucket();
		} else {System.out.println("Bucket is empty");}
	}

	public void addOnStore(Animal a) {
		store.getOnStore(a, 1);
	}
	
	public void printPrices() {
		for (Animal a : store.getAnimals()) {
			System.out.println("Name: " + a.getName() + "\tPrice: " + a.getPrice());
		}
	}
	
	public void printCatalog() {
		
		ArrayList<Animal> anim = (ArrayList<Animal>) store.getAnimals().clone();
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
	
	public void printStore() {
		store.printStore();
	}
	
	public String getGoodsNames(LinkedList<Animal> a) {
		String result = "";
		for (int i=0, j=1; i < a.size(); i++) {
			if (i == 0) {
                result += a.get(i).getName();
            } else if (a.get(i).equals(a.get(i-1)) && i != a.size()-1) {
                j++;
            } else if (!a.get(i).equals(a.get(i-1))){
                result += "(x" + j +  "), " + a.get(i).getName();
                j=1;
            } else {
                j++;
                result += "(x" + j +  ")";
            }
		}
		return result;
	}

	public ArrayList<Animal> getAnimals() {
		return store.getAnimals();
	}
	
	public JScrollPane printTransactions() {
		int i = 1;
        Object[][] data = new Object[100][5];
		for (Purchase pur : purchases) {
            data[i-1][0] = i;
            data[i-1][1] = pur.getDate();
            data[i-1][2] = pur.getCustomer().getName();
            data[i-1][3] = getGoodsNames(pur.getGoods());
            data[i-1][4] = pur.getPrice();
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

        addCustomer("Denys");
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
        byToStore("newt", 60, 0);
	}

}
