package apuestasResources;
/**
 * Enumeración que define los rangos (números y figuras) de las cartas.
 * <p>
 * Asocia cada rango con su valor numérico específico para las reglas del Blackjack
 * (ej: J, Q, K valen 10).
 * </p>
 */
public enum Valores {

		DOS("2", 2),
		TRES("3", 3),
		CUATRO("4", 4),
		CINCO("5", 5),
		SEIS("6", 6),
		SIETE("7", 7),
		OCHO("8",8),
		NUEVE("9",9),
		DIEZ("10",10),
		JOTA("J", 10),
		REYNA("Q", 10),
		REY("K", 10),
		AS("A", 11);

		private String nombre;
		private int valorNumerico;
		/**
		 * Constructor del Enum.
		 * @param nombre Representación visual corta (ej: "K", "10").
		 * @param valorNumerico Valor en puntos para el Blackjack.
		 */
	    Valores(String nombre, int valorNumerico) {
	        this.nombre = nombre;
	        this.valorNumerico = valorNumerico;
	    }
	    /**
	     * @return La representación visual del valor (ej: "A", "10").
	     */
	    public String toString() {
	        return nombre;
	    }
	    /**
	     * Obtiene el valor en puntos de la carta.
	     * @return Entero con el valor (2-11).
	     */
	    public int getValorNumerico() {
	        return valorNumerico;
	    }
	}

