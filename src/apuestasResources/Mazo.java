package apuestasResources;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Gestiona la baraja de cartas.
 * <p>
 * Se encarga de crear las 52 cartas, barajarlas y repartirlas.
 * Incluye lógica para reiniciarse automáticamente si se queda sin cartas.
 * </p>
 */
public class Mazo {

	private ArrayList<Carta> mazo;
	/**
	 * Constructor. Inicializa y baraja un mazo nuevo.
	 */
	public Mazo() {
		mazo = new ArrayList<>();
		reiniciarMazo();
	}
	/**
	 * Limpia el mazo actual y genera uno nuevo completo de 52 cartas (4 palos x 13 valores).
	 * Invoca a {@link #mezclar()} al terminar.
	 */
	public void reiniciarMazo() {
		mazo.clear();					//se borra el mazo
		for (Palo p : Palo.values()) {			//doble for loop para añadir una carta de cada palo y cada numero
			for (Valores v : Valores.values()) {		//el for loop es un for each porque estoy usando el metodo values() de enum
				mazo.add(new Carta(p, v));	
			}
		}
		mezclar(); //desordenar el arraylist
	}
	/**
	 * Desordena aleatoriamente las cartas del mazo usando {@link Collections#shuffle}.
	 */
	public void mezclar() {
		Collections.shuffle(mazo);
	}
	/**
	 * Extrae la carta superior del mazo.
	 * <p>
	 * Si el mazo está vacío, genera uno nuevo automáticamente antes de repartir.
	 * </p>
	 * @return La carta extraída.
	 */
	public Carta tomarCarta() {
		if (mazo.isEmpty()) { //si el mazo esta vacio se reinicia
			System.out.println("Se acabaron las cartas. Reiniciando mazo...");
			reiniciarMazo();
		}
		// retorna la primera y la elimina de la lista
		return mazo.remove(0);
	}
}
