package acciones;

import java.util.Scanner;
import todo.Jugador;
/**
 * Interfaz funcional que define el comportamiento base para cualquier actividad
 * interactiva dentro del juego (minijuegos, tiendas, combate, etc.).
 * <p>
 * Implementa el patrón de diseño <b>Command/Strategy</b>.
 * </p>
 * * @author [Tu Nombre o Grupo]
 * @version 1.0
 */
public interface Accion {
	/**
	 * Ejecuta la lógica principal de la acción.
	 * * @param jugadorActual El objeto del jugador que realiza la acción.
	 * @param sc            El Scanner compartido para la entrada de datos por consola.
	 */
	public void realizar(Jugador jugadorActual,Scanner sc);
}
