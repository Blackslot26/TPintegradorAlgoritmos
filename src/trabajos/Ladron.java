package trabajos;

import todo.Controlador;
import todo.Jugador;

import java.util.Random;
import java.util.Scanner;

import acciones.Trabajar;

public class Ladron extends Trabajar {
	private static final long serialVersionUID = 1L;
	double success;

	@Override
	public void realizar(Jugador jugador, Controlador c, Scanner sc) {
		if (jugador.getExperiencia() == 0) {	//esta garantizado de ganar en el tutorial para que no sea injusto
			Random r = new Random();
			int ganancia = (int) (8000 + r.nextInt(5000)) * (1 + jugador.getNivel() / 10);
			int event = r.nextInt(trabajarTextsBase(ganancia).length);
			jugador.modMonedas(ganancia);
			this.success = 100;
			int xp = 10 + r.nextInt(20);
			jugador.modExp(xp);
			System.out.println(trabajarTexts(event, ganancia, jugador, xp));
		} else {
			Random r = new Random();
			double success = r.nextInt(100) + jugador.getSuerte();
			this.success = success;
			int ganancia = (int) (8000 + r.nextInt(5000)) * (1 + jugador.getNivel() / 10);
			int perdida = -100000 - r.nextInt(50000) - (int) (0.50 * jugador.getMonedas());
			if (success >= 5) {
				int event = r.nextInt(trabajarTextsBase(ganancia).length);
				jugador.modMonedas(ganancia); // puede ganar un
				int xp = 10 + r.nextInt(20);
				jugador.modMonedas(xp);
				System.out.println(trabajarTexts(event, ganancia, jugador, xp)); // maximo de 13.000,
				// la clase que mas
				// gana.
			} else if (success < 5) {
				int event = r.nextInt(trabajarTextsBase(perdida).length);
				jugador.modMonedas(perdida); // pierde 100000 de
				int xp = 10 + r.nextInt(20);
				jugador.modExp(xp);
				System.out.println(trabajarTexts(event, ganancia, jugador, xp)); // dinero mas un
				// numero al
				// azar hasta
				// 50000 mas un
				// 50% de su
				// dinero
				// actual.
			}
		}

	}

	@Override
	public String[] trabajarTextsBase(int ganancia) {
		String[] suceso = {
				"El bolsillo del noble era más pesado de lo que parecía. ¡Te llevas " + ganancia + " monedas!",
				"Como una sombra, entras y sales de la tesorería del alcalde. Botín: " + ganancia + " monedas.",
				"Le 'vendes' un puente a un ogro bastante tonto. Ganas " + ganancia + " monedas." };

		String[] perdida = {
				"¡TORPE! Intentaste robarle a una anciana... que resultó ser una Archimaga. Pierdes " + ganancia
						+ " monedas en ungüentos.",
				"Un guardia te vio y te dio una paliza que no olvidarás. La fianza te cuesta " + ganancia + " monedas.",
				"Te quedaste atascado en una chimenea. Tuvieron que demoler la pared. Pierdes " + ganancia
						+ " monedas en reparaciones." };

		if (this.success >= 5) {
			return suceso;
		}
		if (this.success < 5) {
			return perdida;
		}
		return suceso; // Por si acaso
	}

	@Override
	public String getNombreBase() {
		String a = "Ladron";
		return a;
	}

	@Override
	public String[] getDescriptionBase() {
		String[] a = { "Operas desde las sombras, un fantasma en los callejones del bajo mundo.",
				"Tu sustento no proviene del trabajo, sino de la \"redistribución\" de la riqueza ajena.",
				"Solo para los audaces (o insensatos) que arriesgan todo por la gloria instantánea.",
				"Te permite sustraer grandes sumas de oro de otros aventureros.",
				"Pero si la guardia te atrapa, la penalización es catastrófica:",
				"perderás un porcentaje masivo de tu fortuna y todo tu honor." };
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
