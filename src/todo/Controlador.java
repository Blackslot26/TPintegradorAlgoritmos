package todo;

import java.util.HashMap;


public class Controlador {


	Jugador jugadorActual; // Para conocer el jugador que estará jugando.
	private HashMap<String, Accion> accionesMapeadas; // Lista de acciones.

	public Controlador(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
		accionesMapeadas = new HashMap<>();

		// Lista de acciones.
		this.accionesMapeadas.put("/explorar", new AccionExplorar());
		this.accionesMapeadas.put("/tienda", new AccionTienda());

	};

	/**
	 * Función que procesa el imput.
	 * 
	 * @return Devuelve false si el jugador decide salir del juego y true si elije
	 *         una acción.
	 */
	public boolean procesarInput(String input) {
		input = input.toLowerCase().trim(); // sacar las mayúscula y espacios
		input = aliasToAccion(input); //pasar de alias a comando
		
		// Comandos especiales
		switch (input) {

		case "/leaderboard":
			Leaderboard.update(jugadorActual);
			Leaderboard.mostrarLeaderboard();
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

			if (accion != null) {
				accion.realizar(jugadorActual, this);
			} else
				System.out.printf("No se reconoció el comando %s :( \n ", input);
		}

		return true;
	}

	/**
	 * Función para tranformar alias de acciones en el comando original
	 * 
	 * @return Si el input es un alias de comando, devulve el comando original,
	 *         sino, el mismo input.
	 */
	private String aliasToAccion(String input) {
		switch (input) {
		case "/t":
			input = "/tienda";
			break;
		case "/s":
			input = "/salir";
			break;
		case "/c":
			input = "/comandos";
			break;
		case "/jugadores":
			input = "/leaderboard";
			break;
		case "/e":
			input = "/explorar";
			break;

		}
		return input;
	}

	/**
	 * Función para listar los comandos disponibles.
	 * 
	 * @return Imprime todos los comandos disponibles.
	 */
	private void listarComandos() {
		System.out.println("Comandos disponibles: ");

		// Imprimir comandos especiales
		System.out.println("/salir -> Termina el juego.");
		System.out.println("/comandos -> Muestra la lista de comandos disponibles");
		System.out.println("/leaderboard -> muestra el top global de jugadores de esta computadora");
		System.out.println("/estado -> Muestra las características actuales del jugador");

		for (String c : accionesMapeadas.keySet()) {
			System.out.printf("%s -> %s \n", c, accionesMapeadas.get(c).getDescripcion());
		}
	}

	/**
	 * Simula la limpieza de la consola imprimiendo muchas líneas nuevas.
	 */
	public void limpiarConsola() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}/**/
		
		//Para cuando se exporte como archivo jar
		/*try {
	        // Obtenemos el nombre del sistema operativo
	        String os = System.getProperty("os.name");

	        // Creamos una lista de comandos
	        ProcessBuilder pb;
	        if (os.contains("Windows")) {
	            // Si es Windows, usamos "cls"
	            pb = new ProcessBuilder("cmd", "/c", "cls");
	        } else {
	            // Si no es Windows, usamos "clear"
	            pb = new ProcessBuilder("clear");
	        }
	        
	        // Ejecutamos el comando
	        pb.inheritIO().start().waitFor();

	    } catch (final Exception e) {
	        System.out.println("Error al limpiar la pantalla: " + e.getMessage());
	    }/**/
	}

}
