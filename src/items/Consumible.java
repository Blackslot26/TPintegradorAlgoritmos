package items;
import utiles.MyUtil;
import todo.Jugador;
/**
 * Representa objetos de un solo uso que aplican efectos inmediatos (Pociones, Comida).
 * <p>
 * Implementa {@link IConsumible}. Al usarse, se consume una unidad del inventario
 * y se aplican efectos como curación o buffs temporales/permanentes.
 * </p>
 */
public class Consumible extends Item implements IConsumible{
	private static final long serialVersionUID = 1L;
	
	ItemConsumible tipo;
	
	public Consumible(ItemConsumible tipo, int cantidad) {
		super(tipo.getNombre(), tipo.getDescripcion(), tipo.getPrecio(), cantidad);
		this.tipo = tipo;
	}
	/**
	 * Genera una descripción detallada incluyendo los efectos del consumible.
	 * * @return String con la descripción base + efectos (Cura, Buffs).
	 */
	@Override
	public String getDescripcion() {
		String stats = super.getDescripcion();
		if (tipo.getCuracion() > 0) stats += " | Cura: " + tipo.getCuracion() + "  de vida";
		if (tipo.getSuerte() > 0) stats += " | Suerte: +" + tipo.getSuerte();
		if (tipo.getMultiplicador() > 0) stats += " | Mult: x" + tipo.getMultiplicador();
		return stats;
	}

		/**
		 * Ejecuta el efecto del consumible sobre el jugador.
		 * Aplica curación, suerte o multiplicadores y reduce la cantidad del ítem en 1.
		 */
	@Override
	public void alUsar(Jugador jugador) {
		MyUtil.marco("Usaste " + tipo.getNombre());
		jugador.modVida(tipo.getCuracion());
		jugador.modSuerte(tipo.getSuerte());
		jugador.modMultGanancia(tipo.getMultiplicador());	//pocion de aumento de ganancias (apuestas, etc)
		jugador.modMultVenta(tipo.getMultiplicador());		//pocion de aumento de venta
		this.cantidad--;
	}
	
	public ItemConsumible getTipo() {
		return tipo;
	}

}
