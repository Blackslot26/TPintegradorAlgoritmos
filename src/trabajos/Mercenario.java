package trabajos;

import acciones.Accion;
import acciones.Trabajar;
import todo.Controlador;
import todo.Jugador;

import java.util.Random;
import java.util.Scanner;

public class Mercenario extends Trabajar implements Accion{
	private static final long serialVersionUID = 1L;

	@Override
	public void realizar(Jugador jugador, Controlador c, Scanner sc) {
		Random r = new Random();
		if (jugador.getNivel() < 15) {
			int ganancia = (10000 + r.nextInt(5000)) - (jugador.getNivel() / 3) * 1500;
			jugador.modMonedas(ganancia);
			int event = r.nextInt(trabajarTextsBase(ganancia).length); //////////
			int xp = 10 + r.nextInt(20);
			jugador.modExp(xp);
			System.out.println(trabajarTexts(event, ganancia, jugador, xp));
		}
		// gana mucho y sin
		// riesgo pero cada 3
		// niveles gana 1500
		// menos.
		if (jugador.getNivel() > 15) {
			int ganancia = 1000 + r.nextInt(1000);
			jugador.modMonedas(ganancia); // a partir del nivel 15 se alcanzan los menores ingresos.
			int event = r.nextInt(trabajarTextsBase(ganancia).length); //////////
			int xp = 10 + r.nextInt(20);
			jugador.modExp(xp);
			System.out.println(trabajarTexts(event, ganancia, jugador, xp));
		}

	}

	@Override
	public String[] trabajarTextsBase(int ganancia) {
		String[] a = {
			"Un Lord te paga generosamente por escoltar su carruaje. Ganas " + ganancia + " monedas.",
			"¡Limpia fácil! Ahuyentas a unos goblins de una bodega. Recibes " + ganancia + " monedas.",
			"Tu fama te precede. Un noble te paga " + ganancia + " monedas solo por intimidar a un rival.",
			"Aceptas un trabajo simple de guardaespaldas por " + ganancia + " monedas."
		};
		return a;
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
		String[] a = { "Eres una espada de alquiler, un prodigio del combate cuya fama te precede. ",
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
