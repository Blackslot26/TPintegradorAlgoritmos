package trabajos;

import todo.Accion;
import todo.Controlador;
import todo.Jugador;

import java.util.Random;
import java.util.Scanner;

public class Mercader extends Trabajar implements Accion{
	private static final long serialVersionUID = 1L;

	@Override
	public void realizar(Jugador jugador, Controlador c, Scanner sc) {
		Random r = new Random();
		int ganancia = (int) (1000 + r.nextInt(1000)) * (1 + jugador.getNivel() / 10);
		jugador.modMonedas(ganancia);
		int event = r.nextInt(trabajarTextsBase(ganancia).length); //////////
		int xp = 10 + r.nextInt(20);
		jugador.modExp(xp);
		System.out.println(trabajarTexts(event, ganancia, jugador, xp));

	}
	@Override
	public String[] trabajarTextsBase(int ganancia) {
		String[] a = {
			"¡Trueque exitoso! Convences a un granjero de que sus gallinas valen poco. Ganas " + ganancia + " monedas.",
			"Vendes 'especias exóticas' (básicamente, sal con pimienta) por " + ganancia + " monedas.",
			"Tu caravana llega a salvo. Las sedas se venden por " + ganancia + " monedas.",
			"Logras evadir al recaudador de impuestos del rey. Te quedas con " + ganancia + " monedas extra."
		};
		return a;
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
