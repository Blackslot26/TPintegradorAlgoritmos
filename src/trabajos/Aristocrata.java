package trabajos;

import todo.Jugador;
import todo.Trabajar;

import java.util.Random;

public class Aristocrata extends Trabajar {

	private static final long serialVersionUID = 1L;

	@Override
	public void trabajar(Jugador jugador) {
		Random r = new Random();
		double multiplier = jugador.getNivel() * (1.25 + jugador.getNivel() / 10);
		
		int ganancia = (int) ((1000 + r.nextInt(500)) * multiplier);
		jugador.agregarDinero(ganancia);
		
		int event = r.nextInt(trabajarTextsBase(ganancia).length);
		int xp = 10 + r.nextInt(20);
		jugador.setExperiencia(xp);
		System.out.println(trabajarTexts(event, ganancia, jugador, xp));
	}

	@Override
	public String[] trabajarTextsBase(int ganancia) {
		String[] a = {
			"Firmas un decreto que apenas lees. Ganas " + ganancia + " monedas.",
			"Tu inversión en 'Viñedos Élficos S.A.' da frutos inesperados. Recibes " + ganancia + " monedas.",
			"Amenazas a un gremio menor con 'influencias políticas'. Te pagan " + ganancia + " monedas por tu silencio.",
			"Organizas una fiesta aburrida. Los invitados te pagan " + ganancia + " monedas solo para que termine."
		};
		return a;
	}
	
	

	@Override
	public String getNombreBase() {
		String a = "Aristocrata";
		return a;
	}

	@Override
	public String[] getDescriptionBase() {
		String[] a = new String[] { "Perteneces a una casa noble menor.",
				"Tu trabajo no es sudar, sino mover los hilos del comercio y la política desde salones opulentos.",
				"El oficio del estratega a largo plazo, para quien la paciencia es una inversión.",
				"Tus ganancias iniciales son irrisorias, apenas una limosna.",
				"No obstante, la experiencia hace al maestro,",
				"por lo que iras escalando tus ingresos futuros de forma exponencial,",
				"hasta que el tesoro real parezca un simple monedero." };
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
