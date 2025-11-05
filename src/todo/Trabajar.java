package todo;

public interface Trabajar {
	
	void trabajar(Jugador jugador);
	
	String getNombre();
	
	String getDescription();
	
	double multiplicadorVenta(double precioBase, Jugador jugador);
	
	double multiplicadorGanancias(double gananciaBase, Jugador jugador);
	
	
	
}
