package todo;

import java.util.HashMap;
import java.util.Scanner;

import acciones.Accion;
import acciones.AccionExplorar;
import acciones.AccionTienda;
import acciones.AccionCazar;
import utiles.DatosJuego;
import utiles.MyUtil;

public class Controlador {

	Jugador jugadorActual; // Para conocer el jugador que estará jugando.
	private HashMap<String, Accion> accionesMapeadas; // Lista de acciones.

	public Controlador(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
		accionesMapeadas = new HashMap<>();

		// Lista de acciones.
		this.accionesMapeadas.put("/explorar", new AccionExplorar());
		this.accionesMapeadas.put("/tienda", new AccionTienda());
		this.accionesMapeadas.put("/cazar", new AccionCazar());
		this.accionesMapeadas.put("/trabajar", jugadorActual.getTrabajo());
	};

	/**
	 * Función que procesa el imput.
	 * 
	 * @return Devuelve false si el jugador decide salir del juego y true si elije
	 *         una acción.
	 */
	public boolean procesarInput(String input, Scanner sc) {
		input = input.toLowerCase().trim(); // sacar las mayúscula y espacios
		input = aliasToAccion(input); // pasar de alias a comando

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
			listarComandos(sc);
			break;

		case "/estado":
			jugadorActual.mostrarEstadoJugador();
			System.out.println("[Enter para continuar]");
			sc.nextLine();
			break;

		case "/inventario":
			System.out.println("Si te aparece esto es porque solo se implemento en el estado del jugador,usa /estado");
			break;
		default:
			Accion accion = accionesMapeadas.get(input);

			long tiempoRestante = jugadorActual.getRemainingCooldown(input);

			if(tiempoRestante>0) {
				MyUtil.marco("\nAún no puedes acceder a esta acción, espera "+MyUtil.ANSI_CYAN + tiempoRestante + MyUtil.ANSI_RESET+" Segundo más");
				System.out.println("[Enter para volver]");
				sc.nextLine();
			}else if (accion != null) {
				accion.realizar(jugadorActual, sc);
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
		return DatosJuego.aliasComandos.getOrDefault(input, input);
	}

	/**
	 * Función para listar los comandos disponibles.
	 * 
	 * @return Imprime todos los comandos disponibles.
	 */
	private void listarComandos(Scanner sc) {
		MyUtil.marco(DatosJuego.comandos);
		System.out.print("[Enter para salir]");
		sc.nextLine();
	}

}
