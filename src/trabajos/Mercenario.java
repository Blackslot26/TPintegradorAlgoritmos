package trabajos;

import todo.Jugador;
import todo.TrabajarBase;

import java.util.Random;

public class Mercenario extends TrabajarBase {

	@Override
	public void trabajar(Jugador jugador) {
		Random r = new Random();
		if (jugador.getNivel() < 15)
			jugador.agregarDinero((10000 + r.nextInt(5000)) - (jugador.getNivel() / 3) * 1500); // gana mucho y sin
																								// riesgo pero cada 3
																								// niveles gana 1500
																								// menos.
		if (jugador.getNivel() > 15)
			jugador.agregarDinero(1000 + r.nextInt(1000)); // a partir del nivel 15 se alcanzan los menores ingresos.

	}

	@Override
	public String getNombreBase() {
		// TODO Auto-generated method stub
		String a = "Mercenario";
		return a;
	}

	@Override
	public String[] getDescriptionBase() {
		// TODO Auto-generated method stub
		String[] a = {
				"Eres una espada de alquiler, un prodigio del combate cuya fama te precede. ",
				"Los lores y reyes pagan sumas exorbitantes por tus servicios... por ahora.",
				"La clase perfecta para un arranque explosivo o para dominar el juego temprano.",
				"Tus contratos iniciales te otorgan ganancias inmensas, permitiéndote amasar una fortuna rápidamente.",
				"Sin embargo, tu fama se estanca y tus habilidades se vuelven comunes;",
				"tus contratos serán cada vez menos lucrativos a medida que el mundo avanza y tu leyenda decae." };
		return a;

	}

	@Override
	public double multiplicadorVenta(double precioBase, Jugador jugador) {
		// TODO Auto-generated method stub
		return precioBase * 0.5;
	}

	@Override
	public double multiplicadorGanancias(double gananciaBase, Jugador jugador) {
		// TODO Auto-generated method stub
		return gananciaBase;
	}

}
