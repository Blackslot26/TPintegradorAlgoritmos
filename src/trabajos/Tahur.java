package trabajos;

import todo.Jugador;
import todo.Trabajar;
import java.util.Random;

public class Tahur implements Trabajar {

	@Override
	public void trabajar(Jugador jugador) {
		Random r = new Random();
		Jugador.modificarSuerte(2 + (int)Jugador.getNivel()/10);  //tiene 2% mas de probabilidades en todo y gana un 1% mas cada 10 niveles.
		Jugador.agregarDinero(10 + r.nextInt(140));  // mendigas por un maximo de 140
	}

	@Override
	public String getNombre() {
		return "Tahúr";
	}

	@Override
	public String getDescription() {
		return "La Dama de la Fortuna es tu única diosa y maestra."
			+ " Desprecias el trabajo honesto y vives por el sonido de los dados en la taberna."
			+ " \\n El camino del adicto a la adrenalina. Todo o nada."
			+ " \\n Tienes prohibido realizar un trabajo normal y no generas ingresos fijos."
			+ " A cambio, los dioses de la suerte te sonríen: posees mayor fortuna y recibes ganancias multiplicadas en todas las formas de apuesta.";
	}

	@Override
	public double multiplicadorVenta(double precioBase) {
		// TODO Auto-generated method stub
		return precioBase * 0.5;
	}

	@Override
	public double multiplicadorGanancias(double gananciaBase) {
		// TODO Auto-generated method stub
		return gananciaBase * ( 1.10 + (int)Jugador.getNivel()/100);
	}

}
