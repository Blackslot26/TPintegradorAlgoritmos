package todo;

import java.util.HashMap;

public class Controlador {

	Jugador jugadorActual;	//Para conocer el jugador que estará jugando.
	private HashMap<String,Accion> accionesMapeadas; //Lista de acciones.
	private String estadoActual;//Variable que señala el estado en el que está el jugador
	
	public Controlador(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
		this.estadoActual = "MENU_PRINCIPAL";
		accionesMapeadas = new HashMap<>();
		
		//Lista de acciones.
		this.accionesMapeadas.put("/explorar",new AccionExplorar());
		this.accionesMapeadas.put("/tienda", new AccionTienda());
	};
	
	
	/**
	*Función que procesa el imput.
	@return Devuelve false si el jugador decide salir del juego y true si elije una acción.
	*/
	public boolean procesarInput(String input) {
		input = input.toLowerCase(); //sacar las mayúscula
		
		//Comandos especiales
		switch (input) {
		
		case "/salir":
			System.out.println("Saliendo...");
			return false;
			
		case "/comandos":
			listarComandos();
			break;
		
			
		case "/estado":
			jugadorActual.mostrarEstadoJugador();
			break;
			
		default: 
			Accion accion = accionesMapeadas.get(input);
			
			if(accion != null) {
				accion.realizar(jugadorActual, this);
			} else System.out.printf("No se reconoció el comando %s :( \n ",input);
		}


		return true;
	}
	
	private void cambiarEstado(String nuevoEstado) {
		this.estadoActual = nuevoEstado;
		
		limpiarConsola();
		
		
	}
	
	/**
	*Función para listar los comandos disponibles.
	@return Imprime todos los comandos disponibles.
	*/
	private void listarComandos() {
		System.out.println("Comandos disponibles: ");
	
		//Imprimir comandos especiales
		System.out.println("/salir -> Termina el juego.");
		System.out.println("/comandos -> Muestra la lista de comandos disponibles");
		System.out.println("/estado -> Muestra las características actuales del jugador");
		
		for(String c: accionesMapeadas.keySet()) {
			System.out.printf("%s -> *Inserte Descripción*. \n",c);
		}
	}
	
	/**
	 * Simula la limpieza de la consola imprimiendo muchas líneas nuevas.
	 */
	public void limpiarConsola() {
	    for (int i = 0; i < 50; i++) {
	        System.out.println();
	    }
	}
	

}
