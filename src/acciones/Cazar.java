package acciones;

import java.util.Scanner;

import todo.Controlador;
import todo.Jugador;

public class Cazar implements Accion {
	String descripcion = "Te encuentras con un enemigo aleatorio, deberás derrotarlo para obtener recompensas";

	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public void realizar(Jugador jugadorActual, Controlador controlador, Scanner sc) {
		boolean cazando = true;
		String input="";
		
		
		while(cazando) {
			
			input = sc.nextLine().toLowerCase().trim();
			
		}
	}

}
