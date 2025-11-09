package todo;

import java.util.HashMap;


public class Controlador {

	Jugador jugadorActual;	//Para conocer el jugador que estará jugando.
	private HashMap<String,Accion> accionesMapeadas; //Lista de acciones.

	
	public Controlador(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
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
		input = input.toLowerCase().replace(" ", ""); //sacar las mayúscula y espacios
		
		//Comandos especiales
		switch (input) {
		case "/trabajar":
			jugadorActual.getTrabajo().trabajar(jugadorActual);
			break;
		
		case "/leaderboard":
			Leaderboard.update(jugadorActual);
			Leaderboard.mostrarLeaderboard();
			break;
		
		case "/money":
			System.out.println("Agregando 10 de dinero");
			break;
		
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
	
	/**
	*Función para listar los comandos disponibles.
	@return Imprime todos los comandos disponibles.
	*/
	private void listarComandos() {
		System.out.println("Comandos disponibles: ");
	
		//Imprimir comandos especiales
		System.out.println("/salir -> Termina el juego.");
		System.out.println("/comandos -> Muestra la lista de comandos disponibles");
		System.out.println("/leaderboard -> muestra el top global de jugadores de esta computadora");
		System.out.println("/estado -> Muestra las características actuales del jugador");
		
		for(String c: accionesMapeadas.keySet()) {
			System.out.printf("%s -> %s \n",c,accionesMapeadas.get(c).getDescripcion());
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
