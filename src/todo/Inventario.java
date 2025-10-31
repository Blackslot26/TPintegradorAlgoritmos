package todo;

import java.util.ArrayList;

public class Inventario {
	ArrayList<Item> slots;
	
	public Inventario() {
		slots = new ArrayList<>();
	}
	
	public void mostrarInventario() {
		for(int i = 0; i<slots.size();i++) {
			System.out.printf(" | %s | ", slots.get(i).nombre);
		}
		System.out.println();
	}
	
	public void addItem(Item item) {
		slots.add(item);
	}
}
