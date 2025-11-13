package todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import acciones.Accion;
import acciones.AccionBlackjack;
import acciones.AccionExplorar;
import acciones.AccionInventario;
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
		this.accionesMapeadas.put("/blackjack", new AccionBlackjack());
		this.accionesMapeadas.put("/inventario", new AccionInventario());
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
			System.out.println("[Enter para salir]");
			sc.nextLine();
			break;

		case "/salir":
			System.out.println("Saliendo...");
			return false;

		case "/comandos":
			listarComandos(sc);
			break;
		case "/alias":
			listarAlias(sc);
			break;

		default:
			Accion accion = accionesMapeadas.get(input);

			long tiempoRestante = jugadorActual.getRemainingCooldown(input);

			if (tiempoRestante > 0) {
				MyUtil.marco("Que impaciente! espera " + MyUtil.ANSI_CYAN + tiempoRestante + MyUtil.ANSI_RESET
						+ " Segundos más");
				System.out.println("[Enter para volver]");
				sc.nextLine();
			} else if (accion != null) {
				accion.realizar(jugadorActual, sc);
			} else {
				System.out.printf("No se reconoció el comando %s :( \n ", input);
				System.out.println("[Enter para continuar]");
				sc.nextLine();
			}
		}
		return true;
	}
	// Añade este nuevo método a Controlador.java
	public void setAccionTrabajo(Accion trabajo) {
	    this.accionesMapeadas.put("/trabajar", trabajo);
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
	 * Función para listar los alias de comandos disponibles.
	 */
	private void listarAlias(Scanner sc) {
		// 1. Crear el contenedor para las líneas del marco
		ArrayList<String> lineasAlias = new ArrayList<>();
		lineasAlias.add("--- Lista de Alias ---");
		lineasAlias.add("(Alias -> Comando Principal)");
		lineasAlias.add(""); // Espacio

		// 2. Iterar sobre el HashMap de alias
		for (HashMap.Entry<String, String> entry : DatosJuego.aliasComandos.entrySet()) {
			// Formato: /t -> /trabajar
			lineasAlias.add(MyUtil.ANSI_GREEN + entry.getKey() + MyUtil.ANSI_RESET + "  ->  " + entry.getValue());
		}

		// 3. Dibujar el marco
		MyUtil.marco(lineasAlias.toArray(new String[0]));

		// 4. Esperar al usuario
		System.out.print("[Enter para salir]");
		sc.nextLine();
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
