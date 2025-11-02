package trabajos;

import todo.Jugador;
import todo.Trabajar;
import java.util.Random;

public class Mercader implements Trabajar {

	@Override
	public void trabajar(Jugador jugador) {
		Random r = new Random();
		Jugador.agregarDinero((int) (1000 + r.nextInt(1000)) * (1 + Jugador.getNivel() / 10));

	}

	@Override
	public String getNombre() {
		return "Mercader";
	}

	@Override
	public String getDescription() {
		return "Como miembro del ilustre gremio de comerciantes, tu hogar son las rutas y las caravanas."
				+ " Sabes el precio de todo y el valor de nada."
				+ " \\n Ideal para el aventurero que prefiere la exploración y la recolección de tesoros por encima del trabajo monótono."
				+ " \\n Tus ingresos por \"trabajar\" son mediocres."
				+ " Tu verdadera habilidad reside en tu ojo experto, que te permite vender ítems y botines en la tienda por un precio muy superior al de cualquier otro.";
	}

	@Override
	public double multiplicadorVenta(double precioBase) {
		// TODO Auto-generated method stub
		return precioBase * ( 2 + Jugador.getNivel() / 10);
	}

	@Override
	public double multiplicadorGanancias(double gananciaBase) {
		// TODO Auto-generated method stub
		return gananciaBase;
	}

}
