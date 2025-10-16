package Tests;

public class Jugador extends Personaje{
	int monedas;
	int experiencia;
	Inventario inventario;
	int bonificadorRenacimiento;
	int renacimientos;
	
	public Jugador(String nombre) {
		super(nombre,100,1); //Empieza con 100 de vida y nivel 1.
		monedas = 0;
		experiencia = 0;
		inventario = new Inventario();
		bonificadorRenacimiento = 1;
		renacimientos = 0;
	}
	
	
}
