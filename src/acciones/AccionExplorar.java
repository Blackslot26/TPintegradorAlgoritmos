package acciones;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import todo.Jugador;
import utiles.MyUtil;

public class AccionExplorar implements Accion {
	public static final int SEG_COOLDOWN= 120;
	private Random ran; // Para las recompensas
	private ArrayList<Accion> minijuegos;
	public AccionExplorar() {
		this.ran = new Random();
		this.minijuegos = new ArrayList<>();
		
		minijuegos.add(new AccionAhorcado());
		minijuegos.add(new AccionPreguntado());
		minijuegos.add(new AccionBuscaminas());
	}

	@Override
	public void realizar(Jugador jugadorActual, Scanner scExplorar) {
		MyUtil.limpiarConsola(); // Primero limpia la consola
		int indiceMinijuego = ran.nextInt(minijuegos.size()); //Elige un minijuego al azar
		minijuegos.get(indiceMinijuego).realizar(jugadorActual,scExplorar);
		jugadorActual.setActionCooldown("/explorar", SEG_COOLDOWN);
	}
	
}
