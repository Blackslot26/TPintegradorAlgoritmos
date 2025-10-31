package todo;

public class Jugador extends Personaje{
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
	
	
	public void mostrarEstadoJugador(){
		//Mostrar el nombre del jugador en mayúsculas
		System.out.println(nombre.toUpperCase());
		
		//Subrayado para el nombre del jugador de acuerdo a su largo.
		for(int i=0; i< nombre.length();i++) {
			System.out.print("=");
		}
		System.out.println(); //salto de línea
		
		System.out.println("Vida: " + vida);
		System.out.println("Nivel: " + nivel);
		System.out.println("Modenas: " + monedas);
		System.out.println("Experiencia: " + experiencia);
		System.out.println("Renaciemientos: " + renacimientos);
		System.out.println();
		inventario.mostrarInventario();
		
		
	}
	
}
