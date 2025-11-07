package todo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AccionExplorar implements Accion {
	private String descripcion = "Te encuentras con un evento aleatorio donde puedes conseguir botín y aventuras";
	private Random ran; // Para las recompensas
	private ArrayList<Accion> minijuegos;
	public AccionExplorar() {
		this.ran = new Random();
		this.minijuegos = new ArrayList<>();
		
		minijuegos.add(new AccionAhorcado());
		minijuegos.add(new AccionPreguntado());
	}
	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public void realizar(Jugador jugadorActual, Controlador controlador, Scanner scExplorar) {
		controlador.limpiarConsola(); // Primero limpia la consola
		int indiceMinijuego = ran.nextInt(minijuegos.size()); //Elige un minijuego al azar
		minijuegos.get(indiceMinijuego).realizar(jugadorActual,controlador,scExplorar);
	}
	
}
