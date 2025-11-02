package trabajos;

import todo.Jugador;
import todo.Trabajar;
import java.util.Random;

public class Ladron implements Trabajar {

	@Override
	public void trabajar(Jugador jugador) {
		Random r = new Random();
		double success = r.nextInt(100) + Jugador.getSuerte();
		if (success >= 5) {
			Jugador.agregarDinero((int)(8000 + r.nextInt(5000)) * ( 1 + Jugador.getNivel()/10)); // puede ganar un maximo de 13.000, la clase que mas gana.
		} else if (success < 5) {
			Jugador.agregarDinero(-100000 - r.nextInt(50000) - (int) (0.50 * Jugador.getMonedas())); // pierde 100000 de
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
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Ladrón";
	}

	@Override
	public String getDescription() {
		return "Operas desde las sombras, un fantasma en los callejones del bajo mundo."
				+ " Tu sustento no proviene del trabajo, sino de la \"redistribución\" de la riqueza ajena."
				+ " \\n Solo para los audaces (o insensatos) que arriesgan todo por la gloria instantánea."
				+ " \\n Te permite sustraer grandes sumas de oro de otros aventureros."
				+ " Pero si la guardia te atrapa, la penalización es catastrófica: perderás un porcentaje masivo de tu fortuna y todo tu honor.";
	}

	@Override
	public double multiplicadorVenta(double precioBase) {
		// TODO Auto-generated method stub
		return precioBase * 0.5;
	}

	@Override
	public double multiplicadorGanancias(double gananciaBase) {
		// TODO Auto-generated method stub
		return gananciaBase;
	}

}
