package trabajos;

import todo.Controlador;
import todo.Jugador;
import todo.Trabajar;

import java.util.Random;
import java.util.Scanner;

public class Tahur extends Trabajar {
	private static final long serialVersionUID = 1L;

	@Override
	public void realizar(Jugador jugador, Controlador c, Scanner sc) {
		Random r = new Random();
		jugador.modificarSuerte(2 + (int) jugador.getNivel() / 10); // tiene 2% mas de probabilidades en todo y gana un
																	// 1% mas cada 10 niveles.
		int ganancia = 10 + r.nextInt(140);
		jugador.agregarDinero(ganancia); // mendigas por un maximo de 140
		int event = r.nextInt(trabajarTextsBase(ganancia).length); //////////
		int xp = 10 + r.nextInt(20);
		jugador.setExperiencia(xp);
		System.out.println(trabajarTexts(event, ganancia, jugador, xp));
	}

	@Override
	public String[] trabajarTextsBase(int ganancia) {
		String[] a = {
			"Te arrodillas en una esquina y pones tu mejor cara de pena. Un noble te tira " + ganancia + " monedas.",
			"Finges una cojera muy convincente durante una hora. Ganas " + ganancia + " monedas por lástima.",
			"Cuentas una historia triste sobre tu dragón mascota perdido. Te dan " + ganancia + " monedas.",
			"Te confundieron con un mendigo real. ¡Funciona! Ganas " + ganancia + " monedas."
		};
		return a;
	}

	@Override
	public String getNombreBase() {
		String a = "Tahúr";
		return a;
	}

	@Override
	public String[] getDescriptionBase() {
		String[] a = { "La Dama de la Fortuna es tu única diosa y maestra.",
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

	@Override
	public String getDescripcion() {
		// TODO Auto-generated method stub
		return null;
	}




}
