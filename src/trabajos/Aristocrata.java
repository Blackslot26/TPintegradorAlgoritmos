package trabajos;

import todo.Jugador;
import todo.TrabajarBase;

import java.util.Random;

public class Aristocrata extends TrabajarBase {

	@Override
	public void trabajar(Jugador jugador) {
		Random r = new Random();
		double multiplier = jugador.getNivel() * (0.25 + jugador.getNivel() / 10);
		jugador.agregarDinero((int) ((1000 + r.nextInt(500)) * multiplier));
	}

	@Override
	public String getNombreBase() {
		String a = "Aristocrata";
		return a;
	}

	@Override
	public String[] getDescriptionBase() {
		String[] a = new String[]{"Perteneces a una casa noble menor.",
				"Tu trabajo no es sudar, sino mover los hilos del comercio y la política desde salones opulentos.",
				"El oficio del estratega a largo plazo, para quien la paciencia es una inversión.",
				"Tus ganancias iniciales son irrisorias, apenas una limosna.",
				"No obstante, la experiencia hace al maestro,",
				"por lo que iras escalando tus ingresos futuros de forma exponencial,",
				"hasta que el tesoro real parezca un simple monedero."};
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
