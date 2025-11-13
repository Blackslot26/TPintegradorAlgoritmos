package apuestasResources;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {

	private ArrayList<Carta> mazo;

	public Mazo() {
		mazo = new ArrayList<>();
		reiniciarMazo();
	}

	public void reiniciarMazo() {
		mazo.clear();					//se borra el mazo
		for (Palo p : Palo.values()) {			//doble for loop para añadir una carta de cada palo y cada numero
			for (Valores v : Valores.values()) {		//el for loop es un for each porque estoy usando el metodo values() de enum
				mazo.add(new Carta(p, v));	
			}
		}
		mezclar(); //desordenar el arraylist
	}

	public void mezclar() {
		Collections.shuffle(mazo);
	}

	public Carta tomarCarta() {
		if (mazo.isEmpty()) { //si el mazo esta vacio se reinicia
			System.out.println("Se acabaron las cartas. Reiniciando mazo...");
			reiniciarMazo();
		}
		// retorna la primera y la elimina de la lista
		return mazo.remove(0);
	}
}
