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
		monedas = 100;
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

	public void mostrarEstadoJugador(){
		//Mostrar el nombre del jugador en mayúsculas
		System.out.println(nombre.toUpperCase());
		
		//Subrayado para el nombre del jugador de acuerdo a su largo.
		for(int i=0; i< nombre.length();i++) {
			System.out.print("=");
		}
		System.out.println(); //salto de línea
		
		System.out.println("Vida: " + vidaActual + "/" + vidaMaxima);
		System.out.println("Nivel: " + nivel);
		System.out.println("Modenas: " + monedas);
		System.out.println("Experiencia: " + experiencia);
		System.out.println("Renaciemientos: " + renacimientos);
		System.out.println();
		inventario.mostrarInventario();
		
		
	}
	
}
