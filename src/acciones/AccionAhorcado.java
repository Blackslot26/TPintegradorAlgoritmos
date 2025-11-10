package acciones;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import todo.Controlador;
import todo.Jugador;
import utiles.Dibujos;
import utiles.Functions;

public class AccionAhorcado implements Accion {
	private String descripcion = "Minijuego de trivia utilizado exclusivamente en la accion de \"explorar\"";
	private Functions myUtil;
	private Dibujos misDibujos;
	private Random ran;

	public AccionAhorcado() {
		myUtil = new Functions();
		misDibujos = new Dibujos();
		ran = new Random();
	}

	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public void realizar(Jugador jugador, Controlador controlador, Scanner scAhorcado) {
		boolean enAhorcado = true;
		int indicePalabra = ran.nextInt(myUtil.palabrasAhorcado.length); // Se elige la pregunta al azar
		String palabra = myUtil.palabrasAhorcado[indicePalabra]; // Instancia la palabraf
		String input = "";// Para registrar el input local
		StringBuilder tablero = new StringBuilder(); // crea el tablero
		tablero.append("_ ".repeat(palabra.length()));// Inicializar el tablero
		ArrayList<Character> letrasIntentadas = new ArrayList<>(); // Registro de las letras que se intentó
		
		
		try {
			introAhorcado(controlador);// Primero se realiza la introducción
			while (enAhorcado) {
				mostrarTablero(jugador, controlador, tablero, letrasIntentadas);//Muestra la pregunta
				
				input = scAhorcado.nextLine().toLowerCase().trim(); // Input normalizado
				
				enAhorcado = flujoAhorcado(jugador, controlador, palabra, input, tablero, letrasIntentadas);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Función para realizar el minijuego del ahorcado
	 * 
	 * @return Un flujo de acción para resolver un ahorcado.
	 * @throws InterruptedException
	 */
	private boolean flujoAhorcado(Jugador jugador, Controlador controlador, String palabra, String input,
			StringBuilder tablero, ArrayList<Character> letrasIntentadas)
			throws InterruptedException {

		if (input.equals("/casino") || input.equals("/salir")) { // Siempre puedes salir
			myUtil.marco("Has recurrido a ténicas prohibidas para salir de tu grave situación");
			jugador.perderMonedas((int) (jugador.getMonedas() * 0.25)); // Pierde 1/4 de sus monedas
			Thread.sleep(3000);
			return false;
		}

		if (input.length() == 1) { // Si intentó correctamente una sola letra
			char letraInput = input.charAt(0);

			if (letrasIntentadas.contains(letraInput)) { // Si ya intentó esta letra
				myUtil.marco("Esa letra ya la intentaste. Prueba otra.");
				Thread.sleep(1500);
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
				int danio = (int) (jugador.getVidaMaxima() * 0.1);
				jugador.recibirDanio(danio);
				myUtil.marco("Esta letra no se encuentra en la palabra. Perdiste " + danio + "HP");
				Thread.sleep(2500);
			}

		} else if (input.equals(palabra)) {
			victoriaAhorcado(jugador, palabra, 30, 5);
			return false;
		} else if (input.length() == palabra.length()) { // Intentó la palabra y falló
			jugador.recibirDanio(jugador.getVidaActual());
			animacionMuerteAhorcado(controlador);
			return false;
		} else {
			controlador.limpiarConsola();
			myUtil.marco("SOLO UNA LETRA TE DIJE, intenta de nuevo");
			Thread.sleep(2000);
		}

		// Si ya no quedan '_', el jugador gana
		if (tablero.indexOf("_") == -1) { // Si gana intentando todas las letras
			victoriaAhorcado(jugador, palabra, 20, 4);
			return false;
		} else if (jugador.getVidaActual() <= 0) { // Si perdió todas las vidas
			animacionMuerteAhorcado(controlador);
			return false;
		}
		return true;
	}

	private void mostrarTablero(Jugador jugador, Controlador controlador, StringBuilder tablero,
			ArrayList<Character> letrasIntentadas) {
		controlador.limpiarConsola();// Borrar lo anterior
		// Mostrar las letras que ya intentó
		misDibujos.dibujarCalavera();

		StringBuilder sbIntentadas = new StringBuilder("Probaste: ");
	    for (Character letra : letrasIntentadas) {
	    	if(!tablero.toString().contains(String.valueOf(letra))) {
	        sbIntentadas.append(letra).append(" ");	
	    	}

	    }
	    myUtil.marco(sbIntentadas.toString());

		myUtil.marco("Vida: " + jugador.getVidaActual() + "/" + jugador.getVidaMaxima());
		myUtil.marco(tablero.toString());
		System.out.print("Ingrese una letra o intente adivinar la palabra: ");
	}

	private void introAhorcado(Controlador controlador) throws InterruptedException {
		myUtil.marco("Has pasado por muchas dificultades en tu vida un transtorno depresivo te ha invadido...");
		Thread.sleep(2000);
		controlador.limpiarConsola();
		myUtil.marco("La muerte te busca, está a punto de tomar una decisión muy drástica..");
		Thread.sleep(2000);
		controlador.limpiarConsola();
		myUtil.marco("Pero hay otra alternativa...");
		Thread.sleep(2000);
		controlador.limpiarConsola();
		myUtil.marco("JUGUEMOS AL AHORCADO!!! ADIVINA LA PALABRA");
		Thread.sleep(2500);
	}

	private void animacionMuerteAhorcado(Controlador controlador) throws InterruptedException {
		controlador.limpiarConsola();
		myUtil.marco("Intentaste adivinar la palabra y fallaste");
		Thread.sleep(2000);
		controlador.limpiarConsola();
		misDibujos.dibujarCalavera();
		myUtil.marco("La muerte te ha alcanzado...");
		Thread.sleep(5000);
	}

	private void victoriaAhorcado(Jugador jugador, String palabra, int recompensaBase, int multiplicadorEXP)
			throws InterruptedException {
		int recompensa = ran.nextInt(41) + recompensaBase; // Si se intentó la palabra completa se gana más recompensa
		int expGanada = ran.nextInt(palabra.length() * multiplicadorEXP); // Gana experiencia según el largo de la
																			// palabra
		myUtil.marco("¡Felicidades! Has adivinadado la palabra: " + palabra);
		myUtil.marco("Y Has sobrevido a un ataque de depresión");
		Thread.sleep(2500);
		jugador.addMonedas(recompensa);
		jugador.ganarExperiencia(expGanada);
		Thread.sleep(5000);
	}
}
