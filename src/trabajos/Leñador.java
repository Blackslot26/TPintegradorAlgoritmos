package trabajos;

import java.util.Random;

import todo.Jugador;
import todo.Trabajar;

public class Leñador implements Trabajar {

	@Override
	public void trabajar(Jugador jugador) {
		Random r = new Random();
		Jugador.agregarDinero((int) (3000 + r.nextInt(2000) * (1 + Jugador.getNivel() / 5))); // gana entre 3000 y 5000
																								// de dinero
	}

	@Override
	public String getNombre() {
		return "Leñador";
	}

	@Override
	public String getDescription() {
		return "Trabajas como un leñador en los bosques cercanos al feudo real. Eres la columna vertebral de la industria del reino. "
				+ "\\n La clase ideal para el jugador casual o el alma paciente. "
				+ "\\n Cuenta con ganancias promedio, estables y no posee riesgos significativos."
				+ " Un trabajo honesto, con una paga honesta.";
	}

	@Override
	public double multiplicadorVenta(double precioBase) {

		return precioBase * 0.5;
	}

	@Override
	public double multiplicadorGanancias(double gananciaBase) {
		// TODO Auto-generated method stub
		return gananciaBase;
	}

}
