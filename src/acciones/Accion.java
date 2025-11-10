package acciones;

import java.util.Scanner;

import todo.Controlador;
import todo.Jugador;

public interface Accion {
	public String getDescripcion();
	public void realizar(Jugador jugadorActual,Controlador controlador,Scanner sc);
}
