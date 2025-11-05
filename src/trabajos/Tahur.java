package trabajos;

import todo.Jugador;
import todo.TrabajarBase;

import java.util.Random;

public class Tahur extends TrabajarBase {

	@Override
	public void trabajar(Jugador jugador) {
		Random r = new Random();
		jugador.modificarSuerte(2 + (int) jugador.getNivel() / 10); // tiene 2% mas de probabilidades en todo y gana un
																	// 1% mas cada 10 niveles.
		jugador.agregarDinero(10 + r.nextInt(140)); // mendigas por un maximo de 140
	}

	@Override
	public String getNombreBase() {
		String a = "Tahúr";
		return a;
	}

	@Override
	public String[] getDescriptionBase() {
		String[] a = {
				"La Dama de la Fortuna es tu única diosa y maestra.",
				"Desprecias el trabajo honesto y vives por el sonido de los dados en la taberna.",
				"El camino del adicto a la adrenalina. Todo o nada.",
				"Tienes prohibido realizar un trabajo normal y no generas ingresos fijos.",
				"A cambio, los dioses de la suerte te sonríen:",
				"posees mayor fortuna y recibes ganancias multiplicadas en todas las formas de apuesta." };
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
		return gananciaBase * (1.10 + (int) jugador.getNivel() / 100);
	}

}
