package acciones;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import todo.Controlador;
import todo.Jugador;
import utiles.DatosJuego;
import utiles.Dibujos;
import utiles.MyUtil;

public class AccionAhorcado implements Accion {
	private static final Random ran = new Random();

	// Atributos de recompensa
	int recompensaBase, recompensaBaseXP;

	public AccionAhorcado() {

		// Asignar las recompensas base
		recompensaBase = 3000;
		recompensaBaseXP = 15;
	}



	@Override
	public void realizar(Jugador jugador, Controlador controlador, Scanner scAhorcado) {
		boolean enAhorcado = true;
		int indicePalabra = ran.nextInt(DatosJuego.palabrasAhorcado.length); // Se elige la pregunta al azar
		String palabra = DatosJuego.palabrasAhorcado[indicePalabra]; // Instancia la palabraf
		String input = "";// Para registrar el input local
		StringBuilder tablero = new StringBuilder(); // crea el tablero
		tablero.append("_ ".repeat(palabra.length()));// Inicializar el tablero
		ArrayList<Character> letrasIntentadas = new ArrayList<>(); // Registro de las letras que se intentó

		try {
			// Pasamos el Scanner a la intro
			introAhorcado(controlador, scAhorcado);
			while (enAhorcado) {
				mostrarTablero(jugador, controlador, tablero, letrasIntentadas);// Muestra la pregunta

				input = scAhorcado.nextLine().toLowerCase().trim(); // Input normalizado

				// Pasamos el Scanner al flujo
				enAhorcado = flujoAhorcado(jugador, controlador, palabra, input, tablero, letrasIntentadas, scAhorcado);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Función para realizar el minijuego del ahorcado * @return Un flujo de acción
	 * para resolver un ahorcado.
	 * 
	 * @throws InterruptedException
	 */
	// Añadimos Scanner scAhorcado como parámetro
	private boolean flujoAhorcado(Jugador jugador, Controlador controlador, String palabra, String input,
			StringBuilder tablero, ArrayList<Character> letrasIntentadas, Scanner scAhorcado)
			throws InterruptedException {

		if (input.equals("/casino") || input.equals("/salir")) { // Siempre puedes salir
			MyUtil.marco("Has recurrido a ténicas prohibidas para salir de tu grave situación");
			int monedasPerdidas = (int) (jugador.getMonedas() * -0.25);
			jugador.modMonedas(monedasPerdidas); // Pierde 1/4 de sus monedas
			MyUtil.marco("Has perdido" + monedasPerdidas);
			Thread.sleep(3000);
			return false;
		}

		if (input.length() == 1) { // Si intentó correctamente una sola letra
			char letraInput = input.charAt(0);

			if (letrasIntentadas.contains(letraInput)) { // Si ya intentó esta letra
//				myUtil.marco("Esa letra ya la intentaste. Prueba otra.");
//				Thread.sleep(1500);
				return true;
			} else {
				letrasIntentadas.add(letraInput);
			}

			if (palabra.indexOf(letraInput) != -1) {// Si la letra está en la palabra
				// Se reemplaza la letra
				for (int i = 0; i < palabra.length(); i++) {
					if (palabra.charAt(i) == letraInput) {
						tablero.setCharAt(i * 2, palabra.charAt(i));// i*2 debido a los espacios
					}
				}

			} else {
				int danio = (int) (jugador.getVidaMaxima() * -0.1);
				jugador.modVida(danio);
//				myUtil.marco("INCORRECTA! PERDISTE " + danio + "HP");
//				Thread.sleep(1500);
			}

		} else if (input.equals(palabra)) {
			// Pasamos el Scanner
			victoriaAhorcado(jugador, palabra, 4000, 30, scAhorcado);
			return false;
		} else if (input.length() == palabra.length()) { // Intentó la palabra y falló
			Thread.sleep(1000);
			jugador.modVida(-jugador.getVidaActual());

			animacionMuerteAhorcado(controlador, scAhorcado);
			return false;
		} else {
			// myUtil.marco("SOLO UNA LETRA!!!");
			// Thread.sleep(2000);
		}

		// Si ya no quedan '_', el jugador gana
		if (tablero.indexOf("_") == -1) { // Si gana intentando todas las letras
			// Pasamos el Scanner
			victoriaAhorcado(jugador, palabra, 3500, 25, scAhorcado);
			return false;
		} else if (jugador.getVidaActual() <= 0) { // Si perdió todas las vidas
			// Pasamos el Scanner
			animacionMuerteAhorcado(controlador, scAhorcado);
			return false;
		}
		return true;
	}

	private void mostrarTablero(Jugador jugador, Controlador controlador, StringBuilder tablero,
			ArrayList<Character> letrasIntentadas) {
		controlador.limpiarConsola();// Borrar lo anterior
		// Mostrar las letras que ya intentó
		MyUtil.dibujarArrayString(Dibujos.DIBUJO_CALAVERA);

		StringBuilder sbIntentadas = new StringBuilder("Probaste: ");
		for (Character letra : letrasIntentadas) {
			if (!tablero.toString().contains(String.valueOf(letra))) {
				sbIntentadas.append(letra).append(" ");
			}

		}
		MyUtil.marco(sbIntentadas.toString());

		MyUtil.marco("Vida: " + jugador.getVidaActual() + "/" + jugador.getVidaMaxima());
		MyUtil.marco(tablero.toString());
		System.out.print("Ingrese una letra o intente adivinar la palabra: ");
	}

	// Añadimos Scanner scAhorcado como parámetro
	private void introAhorcado(Controlador controlador, Scanner scAhorcado) throws InterruptedException {
		MyUtil.marco("Has pasado por muchas dificultades en tu vida un transtorno depresivo te ha invadido...");
		Thread.sleep(2000);
		controlador.limpiarConsola();
		MyUtil.marco("La muerte te busca, está a punto de tomar una decisión muy drástica..");
		Thread.sleep(2000);
		controlador.limpiarConsola();
		MyUtil.marco("Pero hay otra alternativa...");
		Thread.sleep(2000);
		controlador.limpiarConsola();
		MyUtil.marco("JUGUEMOS AL AHORCADO!!! ADIVINA LA PALABRA");
		Thread.sleep(2500);

		// Para evitar que el usuario spamee letras
		// Consume el buffer de spam y espera al usuario
		System.out.print("\n[Presiona Enter para comenzar]");
		scAhorcado.nextLine();
	}

	// Añadimos Scanner scAhorcado como parámetro
	private void animacionMuerteAhorcado(Controlador controlador, Scanner scAhorcado) throws InterruptedException {
		controlador.limpiarConsola();
		MyUtil.marco("No lograste adivinar la palabra...");
		Thread.sleep(2000);
		controlador.limpiarConsola();
		MyUtil.dibujarArrayString(Dibujos.DIBUJO_CALAVERA);
		MyUtil.marco("La muerte te ha alcanzado...");
		Thread.sleep(2000); // Reducido para no esperar tanto

		// Para evitar que el usuario spamee letras
		System.out.print("\n[Presiona Enter para continuar]");
		scAhorcado.nextLine();
	}

	// Añadimos Scanner scAhorcado como parámetro
	private void victoriaAhorcado(Jugador jugador, String palabra, int recMaxima, int xpMaxima, Scanner scAhorcado)
			throws InterruptedException {
		// Calcular las recompensas
		int recompensa = ran.nextInt(recMaxima - recompensaBase + 1) + recompensaBase;
		int expGanada = ran.nextInt(xpMaxima - recompensaBaseXP) + recompensaBaseXP;

		// Textos y añadirle la recompensa
		MyUtil.marco("¡Felicidades! Has adivinadado la palabra: " + palabra);
		MyUtil.marco("Y Has sobrevido a un ataque de depresión");
		Thread.sleep(2500);
		jugador.modMonedas(recompensa);
		jugador.modExp(expGanada);
		MyUtil.marco("Has ganado $" + recompensa + " y " + expGanada + "XP");
		Thread.sleep(2000); // Reducido

		// Para evitar que el usuario spamee letras
		System.out.print("\n[Presiona Enter para continuar]");
		scAhorcado.nextLine();
	}
}