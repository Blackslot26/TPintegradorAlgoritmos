package trabajos;

import java.util.Random;

import todo.Jugador;
import todo.TrabajarBase;

public class Leñador extends TrabajarBase {

	@Override
	public void trabajar(Jugador jugador) {
		Random r = new Random();
		jugador.agregarDinero((int) (3000 + r.nextInt(2000) * (1 + jugador.getNivel() / 5))); // gana entre 3000 y 5000
																								// de dinero
	}

	@Override
	public String getNombreBase() {
		String a = "Leñador";
		return a;
	}

	@Override
	public String[] getDescriptionBase() {
		String[] a = {
				"Trabajas como un leñador en los bosques cercanos al feudo real.",
				"Eres la columna vertebral de la industria del reino. ",
				"La clase ideal para el jugador casual o el alma paciente. ",
				"Cuenta con ganancias promedio, estables y no posee riesgos significativos.",
				"Un trabajo honesto, con una paga honesta." };
		return a;
	}

	@Override
	public double multiplicadorVenta(double precioBase, Jugador jugador) {

		return precioBase * 0.5;
	}

	@Override
	public double multiplicadorGanancias(double gananciaBase, Jugador jugador) {
		// TODO Auto-generated method stub
		return gananciaBase;
	}

}
