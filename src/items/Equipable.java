package items;

import todo.Jugador;

public class Equipable extends Item implements IEquipable{
	private static final long serialVersionUID = 1L;
	private ItemEquipable tipo;
	Equipable(ItemEquipable tipo, int cantidad) {
		super(tipo.getNombre(), tipo.getPrecio(), cantidad);
		this.tipo = tipo;
		
		
	}

	@Override
	public void alEquipar(Jugador jugador) {
		System.out.println("Equipaste [ " + this.nombre + " ]");
	}

	@Override
	public void alDesequipar(Jugador jugador) {
		System.out.println("Desequipaste [ " + this.nombre + " ]");
		
	}

}
