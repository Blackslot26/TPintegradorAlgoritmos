package items;
import utiles.MyUtil;
import todo.Jugador;
/**
 * Representa objetos que el jugador puede equipar para mejorar sus estadísticas (Armas, Armaduras, Accesorios).
 * <p>
 * Implementa {@link IEquipable} para definir el comportamiento al equipar/desequipar.
 * Se basa en el enum {@link ItemEquipable} para definir sus propiedades.
 * </p>
 */
public class Equipable extends Item implements IEquipable{
	
	private ItemEquipable tipo;
	private static final long serialVersionUID = 1L;
	public Equipable(ItemEquipable tipo, int cantidad) {
		super(tipo.getNombre(), tipo.getDescripcion(), tipo.getPrecio(), cantidad);
		this.tipo = tipo;
		
		
	}
	/**
	 * Genera una descripción detallada incluyendo las estadísticas que modifica.
	 * * @return String con la descripción base + stats (Daño, Vida, Def, Suerte).
	 */
	@Override
	public String getDescripcion() {
		// Construimos una descripción detallada usando los datos del ENUM
		return super.getDescripcion() + 
			   " | Daño: " + tipo.getDanio() + 
			   " | Vida: " + tipo.getVida() + 
			   " | Def: " + (int)(tipo.getDefensa() * 100) + "%" +
			   " | Suerte: " + tipo.getSuerte();
	}
	/**
	 * Aplica las estadísticas del ítem al jugador.
	 * Suma daño, vida máxima, defensa y suerte.
	 */
	@Override
	public void alEquipar(Jugador jugador) {
		MyUtil.marco("Equipaste [ " + tipo.getNombre() + " ]"); //daño, vida , resistencia, suerte
		jugador.modDanio(tipo.getDanio());
		jugador.modVidaMaxima(tipo.getVida());
		jugador.modDefensa(tipo.getDefensa());
		jugador.modSuerte(tipo.getSuerte());
		
	}
	/**
	 * Remueve las estadísticas del ítem del jugador.
	 * Resta los valores que fueron sumados al equipar.
	 */
	@Override
	public void alDesequipar(Jugador jugador) {
		MyUtil.marco("Desequipaste [ " + tipo.getNombre() + " ]");
		jugador.modDanio(-tipo.getDanio());
		jugador.modVidaMaxima(-tipo.getVida());
		jugador.modDefensa(-tipo.getDefensa());
		jugador.modSuerte(-tipo.getSuerte());
	}
	
	public ItemEquipable getTipo() {
		return tipo;
	}

}
