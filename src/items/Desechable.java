package items;

import java.util.Random;
/**
 * Representa objetos de "botín" o "chatarra" que no tienen uso práctico pero tienen valor monetario.
 * <p>
 * Su precio se calcula aleatoriamente dentro de un rango definido en {@link ItemDesechable}
 * al momento de instanciarse.
 * </p>
 */
public class Desechable extends Item{
	private static final long serialVersionUID = 1L;
	ItemDesechable tipo;
	/**
	 * Constructor que inicializa el ítem con un valor aleatorio.
	 * * @param tipo     El tipo de ítem desechable (enum).
	 * @param cantidad Cantidad inicial.
	 */
	public Desechable(ItemDesechable tipo, int cantidad) {
		super(tipo.getNombre(), tipo.getDescripcion(), valorAleatorio(tipo), cantidad);
		this.tipo = tipo;
	}
	/**
	 * Calcula un precio aleatorio dentro del rango definido por el tipo de ítem.
	 * * @param tipo El enum con los valores min y max.
	 * @return Un valor entero entre min y max (inclusive).
	 */
	private static int valorAleatorio(ItemDesechable tipo) {
		Random r = new Random();
        int min = tipo.getValorMin();
        int max = tipo.getValorMax();
        return r.nextInt(max - min + 1) + min;
	}
	
	public ItemDesechable getTipo() {
		return tipo;
	}

}
