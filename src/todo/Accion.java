package todo;

import java.util.Scanner;

public interface Accion {
	public String getDescripcion();
	public void realizar(Jugador jugadorActual,Controlador controlador,Scanner sc);
}
