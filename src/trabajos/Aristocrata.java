package trabajos;

import acciones.Accion;
import todo.Jugador;
import utiles.MyUtil;

import java.util.Random;
import java.util.Scanner;

import acciones.Trabajar;
/**
 * Trabajo de "Late Game" (Juego Tardío) o Inversión.
 * <p>
 * Comienza con ganancias muy bajas, pero escala exponencialmente con el nivel del jugador.
 * Ideal para partidas largas o después de varios renacimientos.
 * </p>
 */
public class Aristocrata extends Trabajar implements Accion{

	private static final long serialVersionUID = 1L;
	
	private static final int SEG_COOLDOWN= 60;
	/**
	 * Ejecuta las labores administrativas del aristócrata.
	 * Aplica un multiplicador de ganancia que crece con el nivel más rápido que otras clases.
	 */
	@Override
	public void realizar(Jugador jugador, Scanner sc) {
		Random r = new Random();
		double multiplier = jugador.getNivel() * (1.25 + jugador.getNivel() / 10);
		
		int ganancia = (int) ((1000 + r.nextInt(500)) * multiplier);
		jugador.modMonedas(ganancia);
		
		int event = r.nextInt(trabajarTextsBase(ganancia).length);
		int xp = 10 + r.nextInt(20);
		jugador.modMonedas(xp);
		System.out.println(trabajarTexts(event, ganancia, jugador, xp));
		
		MyUtil.marco("Enter para continuar");
		sc.nextLine();
		
		jugador.setActionCooldown("/trabajar", SEG_COOLDOWN);
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
		String a = "Aristócrata";
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
