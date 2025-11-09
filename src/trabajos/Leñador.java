package trabajos;

import java.util.Random;

import todo.Controlador;
import todo.Jugador;
import todo.Trabajar;

public class Leñador extends Trabajar {
	private static final long serialVersionUID = 1L;

	@Override
	public void realizar(Jugador jugador, Controlador c) {
		Random r = new Random();
		int ganancia = (int) (3000 + r.nextInt(2000) * (1 + jugador.getNivel() / 5)); ////////////
		jugador.agregarDinero(ganancia); // gana entre 3000 y 5000	//////////////
		int event = r.nextInt(trabajarTextsBase(ganancia).length); //////////
		int xp = 10 + r.nextInt(20);
		jugador.setExperiencia(xp);
		System.out.println(trabajarTexts(event, ganancia, jugador, xp));			////////////							// de dinero
	}
	
	@Override
	public String[] trabajarTextsBase(int ganancia) {
		String[] a = {
			"Talas un roble robusto. La madera se vende bien por " + ganancia + " monedas.",
			"Un día sudoroso, pero la pila de leña es grande. Ganas " + ganancia + " monedas.",
			"Un Ent te mira mal todo el día, pero no te detiene. Vendes la leña por " + ganancia + " monedas.",
			"Esquivas una ardilla furiosa y terminas el trabajo. Recibes " + ganancia + " monedas."
		};
		return a;
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

	@Override
	public String getDescripcion() {
		// TODO Auto-generated method stub
		return null;
	}




}
