package items;
import utiles.MyUtil;
import todo.Jugador;

public class Equipable extends Item implements IEquipable{
	
	private ItemEquipable tipo;
	private static final long serialVersionUID = 1L;
	Equipable(ItemEquipable tipo, int cantidad) {
		super(tipo.getNombre(), tipo.getDescripcion(), tipo.getPrecio(), cantidad);
		this.tipo = tipo;
		
		
	}
	
	@Override
	public void alEquipar(Jugador jugador) {
		MyUtil.marco("Equipaste [ " + tipo.getNombre() + " ]"); //daño, vida , resistencia, suerte
		jugador.modDanio(tipo.getDanio());
		jugador.modVidaMaxima(tipo.getVida());
		jugador.modDefensa(tipo.getDefensa());
		jugador.modSuerte(tipo.getSuerte());
		
	}

	@Override
	public void alDesequipar(Jugador jugador) {
		MyUtil.marco("Desequipaste [ " + tipo.getNombre() + " ]");
		jugador.modDanio(-tipo.getDanio());
		jugador.modVidaMaxima(-tipo.getVida());
		jugador.modDefensa(-tipo.getDefensa());
		jugador.modSuerte(-tipo.getSuerte());
	}

}
