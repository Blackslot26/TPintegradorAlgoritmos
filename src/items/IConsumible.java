package items;

import todo.Jugador;
/**
 * Interfaz funcional para objetos que pueden ser consumidos desde el inventario.
 * Define el contrato para aplicar efectos inmediatos.
 */
public interface IConsumible {
	/**
	 * Lógica a ejecutar cuando el jugador decide usar el ítem.
	 */
	void alUsar(Jugador jugador);
}
