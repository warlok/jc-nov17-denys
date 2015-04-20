package frame16;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Market {
	
	private Store store = new Store();
	private ArrayList<Purchase> purchases = new ArrayList<>();
	
	public Market() {

	}

	public void addAnimal(Animal a) {
		store.addAnimal(a);
	}

	public void removeAnimal(Animal a) {
		store.delAnimal(a);
	}

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

			if (price > 1000) {
				price -= price * 0.1;
			} else if (price > 50) {
				price -= price * 0.05;
			}

		purchases.add(new Purchase(day,cust,goods,price));
		cust.spendMoney(price);
		cust.addPurchase();
		cust.clearBucket();
		} else {System.out.println("Bucket is empty");}
	}

//	public void sell(Animal a) {
//		store.takeFromStore(a);
//	}

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
			System.out.println("Name: " + a.getName() + "\tPrice: " + a.getPrice() + "\tOrder: " + a.getOrder());
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
	
	public JScrollPane printTransactions() {
		int i = 1;
        Object[][] data = new Object[100][5];
		for (Purchase pur : purchases) {
            data[i-1][0] = i;
            data[i-1][1] = "Today";
            data[i-1][2] = pur.getCustomer().getName();
            data[i-1][3] = getGoodsNames(pur.getGoods());
            data[i-1][4] = pur.getPrice();
            i++;
//			if (pur.getDate().equals("Today")) {
//				System.out.println(i + ". " + pur.getCustomer().getName() + " " + getGoodsNames(pur.getGoods()) + "\tPrice: " + pur.getPrice());
//				i++;
//			}
		}
        String[] columsNames = {"Id ", "Date", "Customer name", "Goods", "Price"};
        JTable table = new JTable(data , columsNames);
        TableColumn column = null;
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(25);
        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(200);
        JScrollPane sp = new JScrollPane(table);
        table.setFillsViewportHeight(true);
//        JFrame frame = new JFrame("Transactions");
//        JPanel panel = new JPanel();
//        frame.setContentPane(panel);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        panel.setLayout(new BorderLayout());
//        panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
//        panel.add(table,BorderLayout.CENTER);

//        frame.pack();
//        frame.setVisible(true);
    return sp;
	}

	public ArrayList<Purchase> getPurchases() {
		return purchases;
	}

// makeADiscount(int disc);

}
