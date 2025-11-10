package todo;
import java.io.Serializable;
import java.util.ArrayList;

public class Inventario implements Serializable{
	private static final long serialVersionUID = 1L;
	ArrayList<Item> slots;
	
	public Inventario() {
		slots = new ArrayList<>();
	}
	
	public void mostrarInventario() {
		for(int i = 0; i<slots.size();i++) {
			System.out.printf(" | %s | ", slots.get(i).getNombre());
		}
		System.out.println();
	}
	
	public void addItem(Item item) {
		slots.add(item);
	}
}
