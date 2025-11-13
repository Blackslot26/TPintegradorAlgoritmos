package items;

import todo.Jugador;

public class Equipable extends Item implements IEquipable{
	private static final long serialVersionUID = 1L;
	private ItemEquipable tipo;
	Equipable(ItemEquipable tipo, int cantidad) {
		super(tipo.getNombre(), tipo.getDescripcion(), tipo.getPrecio(), cantidad);
		this.tipo = tipo;
		
		
	}

	@Override
	public void alEquipar(Jugador jugador) {
		System.out.println("Equipaste [ " + this.nombre + " ]"); //daño, vida , resistencia, suerte
		jugador.modDanio(tipo.getDanio());
		jugador.modVidaMaxima(tipo.getVida());
		jugador.modDefensa(tipo.getDefensa());
		jugador.modSuerte(tipo.getSuerte());
		
	}

	@Override
	public void alDesequipar(Jugador jugador) {
		System.out.println("Desequipaste [ " + this.nombre + " ]");
		jugador.modDanio(-tipo.getDanio());
		jugador.modVidaMaxima(-tipo.getVida());
		jugador.modDefensa(-tipo.getDefensa());
		jugador.modSuerte(-tipo.getSuerte());
	}

}
