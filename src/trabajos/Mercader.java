package trabajos;

import todo.Jugador;
import todo.TrabajarBase;

import java.util.Random;

public class Mercader extends TrabajarBase {

	@Override
	public void trabajar(Jugador jugador) {
		Random r = new Random();
		jugador.agregarDinero((int) (1000 + r.nextInt(1000)) * (1 + jugador.getNivel() / 10));

	}

	@Override
	public String getNombreBase() {
		String a = "Mercader";
		return a;
	}

	@Override
	public String[] getDescriptionBase() {
		String[] a = {
				"Como miembro del ilustre gremio de comerciantes, tu hogar son las rutas y las caravanas.",
				"Sabes el precio de todo y el valor de nada.",
				"Ideal para el aventurero que prefiere la exploración y",
				"la recolección de tesoros por encima del trabajo monótono.",
				"Tus ingresos por \"trabajar\" son mediocres.",
				"Tu verdadera habilidad reside en tu ojo experto,",
				"que te permite vender ítems y botines en la tienda",
				"por un precio muy superior al de cualquier otro." };
		return a;

	}

	@Override
	public double multiplicadorVenta(double precioBase, Jugador jugador) {
		// TODO Auto-generated method stub
		return precioBase * (2 + jugador.getNivel() / 10);
	}

	@Override
	public double multiplicadorGanancias(double gananciaBase, Jugador jugador) {
		// TODO Auto-generated method stub
		return gananciaBase;
	}

}
