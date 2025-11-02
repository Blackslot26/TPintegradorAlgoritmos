package todo;

public class AccionExplorar implements Accion {
	String descripcion = "Te encuentras con un evento aleatorio donde puedes conseguir botín y aventuras";
	
	@Override
	public void realizar(Jugador jugadorActual,Controlador controlador) {
		double probabilidad = Math.random();
		
		if(probabilidad <= 0.99999999) {
			System.out.println("Se ha ejecutado la acción 1");
		}
		
	}
	
	/**
	 * Función para obtener la descripción de la tienda.
	 */
	@Override
	public String getDescripcion() {
		return descripcion;
	}
}
