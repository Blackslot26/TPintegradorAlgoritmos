package apuestasResources;
/**
 * Representa una carta individual de la baraja.
 * <p>
 * Combina un {@link Palo} y un {@link Valores}. Es responsable de proporcionar
 * su puntaje para el juego y de generar su propia representación gráfica en arte ASCII.
 * </p>
 */
public class Carta {

	private Palo palo;
	private Valores valor;
	/**
	 * Crea una nueva carta.
	 * @param palo El palo de la carta (Corazones, etc.).
	 * @param valor El rango de la carta (As, Rey, 2, etc.).
	 */
	public Carta(Palo palo, Valores valor) {
		this.palo = palo;
		this.valor = valor;
	}
	/**
	 * Obtiene el valor numérico de la carta para el Blackjack.
	 * @return 10 para figuras, 11 para As, o el valor nominal.
	 */
	public int getPuntaje() {
		return valor.getValorNumerico();
	}
	/**
	 * Verifica si la carta es un As.
	 * Útil para la lógica de puntaje flexible (1 u 11).
	 * @return true si es un As.
	 */
	public boolean esAs() {
		return valor == Valores.AS;
	}
	/**
	 * Genera el arte ASCII de la carta línea por línea.
	 * <p>
	 * Retorna un array de Strings para permitir la impresión de múltiples
	 * cartas una al lado de la otra en la consola.
	 * </p>
	 * @return Array de Strings con las líneas del dibujo.
	 */
	public String[] getArte() {
		int largoCarta = 7;
		String[] arteCarta = {
		" _______ ",
		"|"+valor+ " ".repeat(largoCarta - (valor+"").length()) + "|",
		"|       |",
		"|   "+palo+"   |",
		"|       |",
		"|"+"_".repeat(largoCarta-(valor+"").length())+valor+"|"};
		return arteCarta;
	}
}
