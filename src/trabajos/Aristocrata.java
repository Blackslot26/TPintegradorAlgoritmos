package trabajos;

import todo.Jugador;
import todo.Trabajar;
import java.util.Random;

public class Aristocrata implements Trabajar{
	
	@Override
	public void trabajar(Jugador jugador) {
		Random r = new Random();
		double multiplier = Jugador.getNivel() * ( 0.25 + Jugador.getNivel() / 10);
		Jugador.agregarDinero((int)((1000 + r.nextInt(500)) * multiplier));
	}

	@Override
	public String getNombre() {
		return "Aristocrata";
	}

	@Override
	public String getDescription() {
		return "Perteneces a una casa noble menor. Tu trabajo no es sudar, sino mover los hilos del comercio y la política desde salones opulentos."
			+ " \\n El oficio del estratega a largo plazo, para quien la paciencia es una inversión."
			+ " \\n Tus ganancias iniciales son irrisorias, apenas una limosna."
			+ " No obstante, la experiencia hace al maestro, por lo que iras escalando tus ingresos futuros de forma exponencial hasta que el tesoro real parezca un simple monedero.";
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
