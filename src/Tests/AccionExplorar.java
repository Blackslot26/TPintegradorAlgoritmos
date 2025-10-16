package Tests;

public class AccionExplorar implements Accion {
	@Override
	public void realizar(Jugador jugadorActual,Controlador controlador) {
		double probabilidad = Math.random();
		
		if(probabilidad <= 0.99999999) {
			System.out.println("Se ha ejecutado la acción 1");
		}
		
	}
}
