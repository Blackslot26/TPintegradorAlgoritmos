package acciones;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import todo.Controlador;
import todo.Jugador;

public class AccionExplorar implements Accion {
	private Random ran; // Para las recompensas
	private ArrayList<Accion> minijuegos;
	public AccionExplorar() {
		this.ran = new Random();
		this.minijuegos = new ArrayList<>();
		
		minijuegos.add(new AccionAhorcado());
		minijuegos.add(new AccionPreguntado());
	}

	@Override
	public void realizar(Jugador jugadorActual, Controlador controlador, Scanner scExplorar) {
		controlador.limpiarConsola(); // Primero limpia la consola
		int indiceMinijuego = ran.nextInt(minijuegos.size()); //Elige un minijuego al azar
		minijuegos.get(indiceMinijuego).realizar(jugadorActual,controlador,scExplorar);
	}
	
}
