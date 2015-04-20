package frame16;

import java.util.ArrayList;

public class Store {

	private ArrayList<Animal> animals = new ArrayList<>();
	private int[] amount = new int[30];

	public Store() {

	}

	public void getOnStore(Animal a, int n) {
		amount[animals.indexOf(a)] += n;
	}

	public void takeFromStore(Animal a, int n) {
		if (amount[animals.indexOf(a)] >= n) {
			amount[animals.indexOf(a)] += n;
		} else {
			System.out.println("There is only" + amount[animals.indexOf(a)] + " items");
		}
	}

	public void takeFromStore(Animal a) {
		if (amount[animals.indexOf(a)] > 0) {
		amount[animals.indexOf(a)]--;
		} else {
			System.out.println("There is 0 items on Store");
		}
	}

	public boolean checkAmount(Animal a) {
		if (amount[animals.indexOf(a)] >= 3) {
			return false;
		}
		System.out.println("There is only" + amount[animals.indexOf(a)] + " items" );
		return true;
	}
	
	public void addAnimal(Animal a) {
		animals.add(a);
	}

	public void delAnimal(Animal a) {
		animals.remove(a);
	}

	public void printStore() {
			for (Animal a : animals) {
				System.out.println("Name: " + a.getName() + "\tAmount: " + amount[animals.indexOf(a)]);
			}
	}

	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	
}
