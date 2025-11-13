package acciones;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
	public void realizar(Jugador jugador, Scanner scAhorcado) {
		boolean enAhorcado = true;
		int indicePalabra = ran.nextInt(DatosJuego.palabrasAhorcado.length); // Se elige la pregunta al azar
		String palabra = DatosJuego.palabrasAhorcado[indicePalabra]; // Instancia la palabraf
		String input = "";// Para registrar el input local
		StringBuilder tablero = new StringBuilder(); // crea el tablero
		tablero.append("_ ".repeat(palabra.length()));// Inicializar el tablero
		ArrayList<Character> letrasIntentadas = new ArrayList<>(); // Registro de las letras que se intentó

		try {
			// Pasamos el Scanner a la intro
			introAhorcado(scAhorcado);
			while (enAhorcado) {
				mostrarTablero(jugador, tablero, letrasIntentadas);// Muestra la pregunta

				input = scAhorcado.nextLine().toLowerCase().trim(); // Input normalizado

				// Pasamos el Scanner al flujo
				enAhorcado = flujoAhorcado(jugador, palabra, input, tablero, letrasIntentadas, scAhorcado);
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
	private boolean flujoAhorcado(Jugador jugador, String palabra, String input,
			StringBuilder tablero, ArrayList<Character> letrasIntentadas, Scanner scAhorcado)
			throws InterruptedException {

		if (input.equals("/casino") || input.equals("/salir")) { // Siempre puedes salir
			MyUtil.marco("Has recurrido a ténicas prohibidas para salir de tu grave situación");
			int monedasPerdidas = (int) (jugador.getMonedas() * -0.25);
			jugador.modMonedas(monedasPerdidas); // Pierde 1/4 de sus monedas
			MyUtil.marco("Has perdido " + MyUtil.ANSI_RED + monedasPerdidas + MyUtil.ANSI_RESET);
			Thread.sleep(3000);
			return false;
		}

		if (input.length() == 1) { // Si intentó correctamente una sola letra
			char letraInput = input.charAt(0);

			if (letrasIntentadas.contains(letraInput)) { // Si ya intentó esta letra
				MyUtil.marco(MyUtil.ANSI_YELLOW + "Esa letra ya la intentaste. Prueba otra." + MyUtil.ANSI_RESET);
				return true;
			} else {
				letrasIntentadas.add(letraInput);
			}

			if (palabra.indexOf(letraInput) != -1) {// Si la letra está en la palabra
				MyUtil.marco(MyUtil.ANSI_GREEN + "¡Correcto!" + MyUtil.ANSI_RESET);
				// Se reemplaza la letra
				for (int i = 0; i < palabra.length(); i++) {
					if (palabra.charAt(i) == letraInput) {
						tablero.setCharAt(i * 2, palabra.charAt(i));// i*2 debido a los espacios
					}
				}

			} else {
				int danio = (int) (jugador.getVidaMaxima() * -0.1);
				jugador.modVida(danio);
				MyUtil.marco(MyUtil.ANSI_RED + "INCORRECTA! PERDISTE " + (-danio) + "HP" + MyUtil.ANSI_RESET);
			}

		} else if (input.equals(palabra)) {
			// Pasamos el Scanner
			victoriaAhorcado(jugador, palabra, 4000, 30, scAhorcado);
			return false;
		} else if (input.length() == palabra.length()) { // Intentó la palabra y falló
			Thread.sleep(1000);
			jugador.modVida(-jugador.getVidaActual()); // Pierde toda la vida

			animacionMuerteAhorcado(scAhorcado);
			return false;
		} else {
			// Feedback para input inválido
			MyUtil.marco(MyUtil.ANSI_RED + "Entrada inválida. Intenta una sola letra o la palabra completa." + MyUtil.ANSI_RESET);
		}

		// Si ya no quedan '_', el jugador gana
		if (tablero.indexOf("_") == -1) { // Si gana intentando todas las letras
			// Pasamos el Scanner
			victoriaAhorcado(jugador, palabra, 3500, 25, scAhorcado);
			return false;
		} else if (jugador.getVidaActual() <= 0) { // Si perdió todas las vidas
			// Pasamos el Scanner
			animacionMuerteAhorcado(scAhorcado);
			return false;
		}
		return true;
	}

	private void mostrarTablero(Jugador jugador, StringBuilder tablero,
			ArrayList<Character> letrasIntentadas) {
		MyUtil.limpiarConsola();// Borrar lo anterior
		// Mostrar las letras que ya intentó
		MyUtil.dibujarArrayString(Dibujos.DIBUJO_CALAVERA);

		StringBuilder sbIntentadas = new StringBuilder("Probaste: ");
		for (Character letra : letrasIntentadas) {
			if (!tablero.toString().contains(String.valueOf(letra))) {
				sbIntentadas.append(letra).append(" ");
			}

		}
		MyUtil.marco(sbIntentadas.toString());
		
		MyUtil.marco("Vida: " + MyUtil.ANSI_GREEN + jugador.getVidaActual() + MyUtil.ANSI_RESET + "/"
				+ jugador.getVidaMaxima());
		MyUtil.marco(tablero.toString());
		System.out.print("Ingrese una letra o intente adivinar la palabra: ");
	}

	// Añadimos Scanner scAhorcado como parámetro
	private void introAhorcado(Scanner scAhorcado) throws InterruptedException {
		MyUtil.marco("Has pasado por muchas dificultades en tu vida un transtorno depresivo te ha invadido...");
		Thread.sleep(2000);
		
		MyUtil.limpiarConsola();
		MyUtil.marco("La muerte te busca, está a punto de tomar una decisión muy drástica..");
		Thread.sleep(2000);
		
		MyUtil.limpiarConsola();
		MyUtil.marco("Pero hay otra alternativa...");
		Thread.sleep(2000);
		
		MyUtil.limpiarConsola();
		MyUtil.marco(MyUtil.ANSI_PURPLE + "JUGUEMOS AL AHORCADO!!! ADIVINA LA PALABRA" + MyUtil.ANSI_RESET);
		Thread.sleep(2500);

		// Para evitar que el usuario spamee letras
		// Consume el buffer de spam y espera al usuario
		System.out.print("\n[Presiona Enter para comenzar]");
		scAhorcado.nextLine();
	}

	// Añadimos Scanner scAhorcado como parámetro
	private void animacionMuerteAhorcado(Scanner scAhorcado) throws InterruptedException {
		MyUtil.limpiarConsola();
		MyUtil.marco("No lograste adivinar la palabra...");
		Thread.sleep(2000);
		MyUtil.limpiarConsola();
		MyUtil.dibujarArrayString(Dibujos.DIBUJO_CALAVERA);
		MyUtil.marco(MyUtil.ANSI_RED + "La muerte te ha alcanzado..." + MyUtil.ANSI_RESET);
		Thread.sleep(2000); // Reducido para no esperar tanto

		// Para evitar que el usuario spamee letras
		System.out.print("\n[Presiona Enter para continuar]");
		scAhorcado.nextLine();
	}

	private void victoriaAhorcado(Jugador jugador, String palabra, int recMaxima, int xpMaxima, Scanner scAhorcado)
			throws InterruptedException {
		
		double multiplicadorSuerte = 1.0 + jugador.getSuerte(); // Ej: 1.0 + 0.15 = 1.15 (15% más)
		
		// Calcular las recompensas
		int recompensaBruta = ran.nextInt(recMaxima - recompensaBase + 1) + recompensaBase;
		int expBruta = ran.nextInt(xpMaxima - recompensaBaseXP) + recompensaBaseXP;
		
		// Aplicar bonificación de suerte
		int recompensaFinal = (int) (recompensaBruta * multiplicadorSuerte);
		int expFinal = (int) (expBruta * multiplicadorSuerte);

		// Textos y añadirle la recompensa
		MyUtil.marco(MyUtil.ANSI_GREEN + "¡Felicidades! Has adivinadado la palabra: " + palabra + MyUtil.ANSI_RESET);
		MyUtil.marco("Y Has sobrevido a un ataque de depresión");
		Thread.sleep(2500);
		jugador.modMonedas(recompensaFinal);
		jugador.modExp(expFinal);
		
		// --- AÑADIDO COLOR A LAS RECOMPENSAS ---
		MyUtil.marco("Has ganado " + MyUtil.ANSI_YELLOW + "$" + recompensaFinal + MyUtil.ANSI_RESET + " y " 
				+ MyUtil.ANSI_CYAN + expFinal + "XP" + MyUtil.ANSI_RESET);
		
		// Muestra el desglose de la suerte si hubo bonificación
		if (jugador.getSuerte() > 0) {
			MyUtil.marco("(Bonificación por suerte: $" + (recompensaFinal - recompensaBruta) + " y " 
					+ (expFinal - expBruta) + "XP)");
		}
		
		Thread.sleep(4000); 

		// Para evitar que el usuario spamee letras
		System.out.print("\n[Presiona Enter para continuar]");
		scAhorcado.nextLine();
	}
}