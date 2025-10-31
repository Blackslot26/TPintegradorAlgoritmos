package todo;
import java.io.Serializable;

public class Jugador extends Personaje implements Serializable{
	private static final long serialVersionUID = 1L;
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
	
	public String getNombre() {
		return nombre;
	}
	public int getNivel() {
		return nivel;
	}
	
	public int getRebirth() {
		return renacimientos;
	}
	
	public int getMonedas() {
		return monedas;
	}
	public int getExperiencia() {
		return experiencia;
	}

}
