package todo;

public interface Accion {
	public String getDescripcion();
	public void realizar(Jugador jugadorActual,Controlador controlador);
}
