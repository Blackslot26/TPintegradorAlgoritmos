package trabajos;

import todo.Jugador;
import todo.TrabajarBase;

import java.util.Random;

public class Ladron extends TrabajarBase {

	@Override
	public void trabajar(Jugador jugador) {
		Random r = new Random();
		double success = r.nextInt(100) + jugador.getSuerte();
		if (success >= 5) {
			jugador.agregarDinero((int) (8000 + r.nextInt(5000)) * (1 + jugador.getNivel() / 10)); // puede ganar un
																									// maximo de 13.000,
																									// la clase que mas
																									// gana.
		} else if (success < 5) {
			jugador.agregarDinero(-100000 - r.nextInt(50000) - (int) (0.50 * jugador.getMonedas())); // pierde 100000 de
																										// dinero mas un
																										// numero al
																										// azar hasta
																										// 50000 mas un
																										// 50% de su
																										// dinero
																										// actual.
		}

	}

	@Override
	public String getNombreBase() {
		String a = "Ladron";
		return a;
	}

	@Override
	public String[] getDescriptionBase() {
		String[] a = {
				"Operas desde las sombras, un fantasma en los callejones del bajo mundo.",
				"Tu sustento no proviene del trabajo, sino de la \"redistribución\" de la riqueza ajena.",
				"Solo para los audaces (o insensatos) que arriesgan todo por la gloria instantánea.",
				"Te permite sustraer grandes sumas de oro de otros aventureros.",
				"Pero si la guardia te atrapa, la penalización es catastrófica:",
				"perderás un porcentaje masivo de tu fortuna y todo tu honor."};
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
