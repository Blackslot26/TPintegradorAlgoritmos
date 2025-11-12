package todo;

import java.util.Scanner;

import utiles.Functions;
import utiles.Titulos;

public class Main {
	static Scanner sc;
	static Titulos titulos = new Titulos();
	static Functions myUtil = new Functions();
	public static void main(String[] args) {
		// Intancia de clases y variables necesarias.
		sc = new Scanner(System.in);
		Jugador jugadorActual = logIn(); // logIn retorna el jugador logueado, si este no existe se crea uno nuevo
		Controlador controlador = new Controlador(jugadorActual);
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
		/**/
		// Bucle Principal
		while (enJuego) {
			controlador.limpiarConsola();//Se limpia cualquier cosa anterior
			titulos.mostrarTituloLobby();
			
			System.out.println(); //Separador
			
			jugadorActual.actualizar();
			jugadorActual.feedbackMuerte();
			System.out.print("\nIngrese un comando (/comandos para ayuda) > ");
			input = sc.nextLine(); // Ingresa Imput.
			enJuego = controlador.procesarInput(input, sc); // Verifica si sigue en juego o no.
		}

		GestorPartidas.guardarPartida(jugadorActual);// GUARDAR LA PARTIDA AL ROMPER EL BUCLE WHILE CUANDO enJuego SE
														// VUELVE FALSE

		controlador.limpiarConsola();
		// Fin del Juego.
	}

	public static Jugador logIn() {
		myUtil.dibujarArrayString(titulos.marcoBienvenida,10); //Centrado 10 characters a la derecha
		while (true) {
			String player = sc.nextLine().toLowerCase().trim();
			System.out.println("Esta seguro de que su nombre es: " + player + "?   yes/no");
			String choise = sc.nextLine();
			choise = choise.toLowerCase().trim();
			if (choise.equals("yes")) {
				return GestorPartidas.cargarPartida(player);
			} else {
				System.out.println("Por favor ingrese su nombre: ");
			}
		}
	}

}
