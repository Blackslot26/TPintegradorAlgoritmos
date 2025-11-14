package todo;

import java.util.Scanner;

import utiles.MyUtil;
import utiles.Titulos;

/**
 * Punto de entrada principal de la aplicación.
 * <p>
 * Gestiona el ciclo de vida del juego:
 * <ul>
 * <li>Inicio de sesión / Carga de partida.</li>
 * <li>Bucle principal del juego (Game Loop).</li>
 * <li>Guardado automático al salir.</li>
 * </ul>
 * </p>
 * * @author [Tu Nombre/Grupo]
 * 
 * @version 1.0
 */
public class Main {
	static Scanner sc = new Scanner(System.in);
	static Controlador controlador;
	static Jugador jugadorActual;

	public static void main(String[] args) {
		// Intancia de clases y variables necesarias.
		jugadorActual = logIn(); // logIn retorna el jugador logueado, si este no existe se crea uno nuevo
		controlador = new Controlador(jugadorActual);
		boolean enJuego = true;
		String input = "";

		if (jugadorActual.getExperiencia() == 0) { // SI LA EXPERIENCIA DEL JUGADOR ES 0 SIGNIFICA QUE ES UN JUGADOR
													// NUEVO Y ESTA FORZADO A REALIZAR EL TUTORIAL.
			Tutorial tutorial = new Tutorial(jugadorActual, sc);
			try {
				tutorial.iniciarTutorial();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		controlador.setAccionTrabajo(jugadorActual.getTrabajo());
		/**/
		// Bucle Principal
		while (enJuego) {
			MyUtil.limpiarConsola();// Se limpia cualquier cosa anterior
			MyUtil.dibujarArrayString(Titulos.TITULO_LOBBY, 10);

			System.out.println(); // Separador

			jugadorActual.actualizar();
			jugadorActual.feedbackMuerte();
			System.out.print("\nIngrese un comando (/comandos para ayuda) > ");
			input = sc.nextLine(); // Ingresa Imput.
			enJuego = controlador.procesarInput(input, sc); // Verifica si sigue en juego o no.
		}
		MyUtil.limpiarConsola();
		GestorPartidas.guardarPartida(jugadorActual);// GUARDAR LA PARTIDA AL ROMPER EL BUCLE WHILE CUANDO enJuego SE
														// VUELVE FALSE
		// Fin del Juego.
	}

	/**
	 * Gestiona el proceso de login. Pregunta el nombre de usuario e intenta cargar
	 * un archivo de guardado existente mediante el {@link GestorPartidas}. Si no
	 * existe, crea uno nuevo. * @return Un objeto Jugador cargado o nuevo.
	 */
	public static Jugador logIn() {
		MyUtil.limpiarConsola();
		MyUtil.dibujarArrayString(Titulos.marcoBienvenida, 10); // Centrado 10 characters a la derecha
		while (true) {
			System.out.print("\nIngrese su nombre (3-15 caracteres) > ");
			String player = sc.nextLine().trim();
			if (player.length() > 15) {
				System.out.println(MyUtil.ANSI_RED + "ERROR: ¡Nombre demasiado largo!" + MyUtil.ANSI_RESET + " Maximo 15 caracteres");
				continue;
			}else if(player.length() < 3) {
				System.out.println(MyUtil.ANSI_RED + "ERROR: ¡Nombre demasiado corto!" + MyUtil.ANSI_RESET + " Minimo 3 caracteres");
				continue;
			}
				MyUtil.marco("Estas seguro tu nombre es: " + MyUtil.ANSI_YELLOW + player + MyUtil.ANSI_RESET +"? si/no. > ");
				String choise = sc.nextLine();
				choise = choise.toLowerCase().trim();
				if (choise.equals("si") || choise.equals("yes") || choise.equals("y")) {
					return GestorPartidas.cargarPartida(player.toLowerCase());
				} else if (choise.equals("no") || choise.equals("n")) {
					System.out.print("Por favor ingrese su nombre > ");
				} else {
					System.out.print("No se reconoció \"" + choise + "\" ingrese si/no. > ");
				}
		}
	}
}
