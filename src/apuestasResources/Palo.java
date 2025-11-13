package apuestasResources;

import utiles.MyUtil;
/**
 * Enumeración que define los cuatro palos de la baraja francesa.
 * <p>
 * Incluye códigos de color ANSI (Rojo para Corazones/Diamantes, Azul/Negro para Tréboles/Picas)
 * para mejorar la visualización en la consola.
 * </p>
 */
public enum Palo {
	
	CORAZONES(MyUtil.ANSI_RED + "♥" + MyUtil.ANSI_RESET),
	DIAMANTES(MyUtil.ANSI_RED + "♦" + MyUtil.ANSI_RESET),
	TREBOLES(MyUtil.ANSI_BLUE + "♣" + MyUtil.ANSI_RESET),
	ESPADAS(MyUtil.ANSI_BLUE + "♠" + MyUtil.ANSI_RESET);
	
	private String simbolo;
	/**
	 * Constructor privado del Enum.
	 * @param simbolo El carácter visual del palo (con color).
	 */
	Palo(String simbolo) {
		this.simbolo = simbolo;
	}
	/**
	 * @return El símbolo del palo coloreado.
	 */
	public String toString() {
		return simbolo;
	}
}
