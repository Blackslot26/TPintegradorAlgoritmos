package todo;
import java.io.Serializable;
import java.util.ArrayList;

import items.Item;
/**
 * Contenedor lógico para los objetos del jugador.
 * <p>
 * Gestiona una lista de {@link Item}. Permite agregar objetos apilables
 * (aumentando su cantidad si ya existen) o nuevos.
 * </p>
 */
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
	
	
	public ArrayList<Item> getInventario(){
		return slots;
	}
	
	/**
	 * Añade un ítem al inventario.
	 * Si el ítem ya existe (mismo nombre), suma la cantidad.
	 * Si no existe, lo agrega como una entrada nueva a la lista.
	 * * @param item El objeto a añadir.
	 */
	public void addItem(Item item) {
		boolean added = false;
		for(int i = 0; i < slots.size(); i++) {
			if(slots.get(i).getNombre().equals(item.getNombre())) {
				int nuevaCantidad = slots.get(i).getCantidad() + item.getCantidad();
				slots.get(i).setCantidad(nuevaCantidad);
				added = true;
				break;
			}
		}
		if(added == false) {
			slots.add(item);
		}
	}
}
