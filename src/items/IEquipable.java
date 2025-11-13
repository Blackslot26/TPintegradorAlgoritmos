package items;
import todo.Jugador;
/**
 * Interfaz funcional para objetos que pueden equiparse en los slots del jugador.
 * Define los contratos para aplicar y remover estadísticas.
 */
public interface IEquipable {
	/**
	 * Lógica a ejecutar cuando el ítem se coloca en un slot de equipo.
	 */
	void alEquipar(Jugador jugador);
	/**
	 * Lógica a ejecutar cuando el ítem se retira de un slot de equipo.
	 */
	void alDesequipar(Jugador jugador);
}
