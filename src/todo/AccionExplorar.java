package todo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import utiles.Functions;
import utiles.Dibujos;

public class AccionExplorar implements Accion {
	String descripcion = "Te encuentras con un evento aleatorio donde puedes conseguir botín y aventuras";
	Functions myUtil; // Instancia para usar funciones utiles
	Dibujos misDibujos;
	Random ran; // Para las recompensas

	AccionExplorar() {
		myUtil = new Functions();
		misDibujos = new Dibujos();
		ran = new Random();
	}

	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public void realizar(Jugador jugadorActual, Controlador controlador, Scanner scExplorar) {
		controlador.limpiarConsola(); // Primero limpia la consola
		int probabilidad = ran.nextInt(2); // Calcula la probabilidad para elegir el miniJuego

		try { // Manjar errores de interrupción de los sleep de las funciones.
			switch (probabilidad) {
			case 0:
				realizarAhorcado(jugadorActual, controlador, scExplorar);
				break;

			case 1:
				realizarPreguntado(jugadorActual, controlador, scExplorar);
				break;

			default:
				myUtil.marco("No se encontró nada interesante");
				Thread.sleep(1000);
				break;
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void realizarPreguntado(Jugador jugador, Controlador controlador, Scanner scExplorar)
			throws InterruptedException {
		introPreguntado(controlador); // Primero se realiza la introducción
		boolean enPreguntado = true;
		int i = ran.nextInt(myUtil.preguntas.size()); // Se elige la pregunta al azar
		String input = "";// Para registrar el input local

		while (enPreguntado) {
			controlador.limpiarConsola();
			// misDibujos.dibujarEsfinge();
			myUtil.marco("Vida: " + jugador.getVidaActual() + "/" + jugador.getVidaMaxima()); // Muestra la vida del
																								// jugador
			myUtil.marco(myUtil.preguntas.get(i).getPregunta());// Muestra la pregunta

			for (int o = 0; o < myUtil.preguntas.get(i).getOpciones().length; o++) { // Recorre el array de las opciones
				// muestra las preguntas con un indice mas
				System.out.printf("[%d. %s]  ", o + 1, myUtil.preguntas.get(i).getOpciones()[o]);
			}

			System.out.print("\nElige con cuidado (1,2,3 o 4): ");
			input = scExplorar.nextLine().toLowerCase().trim();

			if (input.length() > 1) { // Si quiere salir o si escribio cualquier cosa
				if (input.equals("/escapar") || input.equals("/esc") || input.equals("/salir") || input.equals("/s")) {
					enPreguntado = false;
					myUtil.marco("Has salido corriendo como una niñita");
					jugador.perderMonedas((int) (jugador.getMonedas() * 0.25)); // Pierde 1/4 de sus monedas
					Thread.sleep(3000);
					continue;
				} else {
					myUtil.marco("SOLO ELIGE UN NÚMERO ENTRE 1 y "+ myUtil.preguntas.get(i).getOpciones().length+"!!!");
					Thread.sleep(2000);
					continue;
				}
			}
			
			try {
				if (Integer.parseInt(input) - 1 == myUtil.preguntas.get(i).getRespuesta()) {
					victoriaPreguntado(jugador, i);
					enPreguntado = false;
				} else if(Integer.parseInt(input) - 1 > myUtil.preguntas.get(i).getOpciones().length){
					myUtil.marco("No ves que hay solo 4 opciones?");
					Thread.sleep(2500);
				}else {
						enPreguntado = derrotaPreguntado(jugador);
				}

			}catch(NumberFormatException e) {
				myUtil.marco("ELIGE UN NÚMERO!!!");
				Thread.sleep(2000);
			}

		}
	}

	private void victoriaPreguntado(Jugador jugador, int indicePregunta) throws InterruptedException {
		int recompensa = myUtil.preguntas.get(indicePregunta).getRecompensa();
		int expGanada = ran.nextInt(50)+10;// Puede ganar hasta 50 de EXP
		myUtil.marco("Muy bien, has logrado sobrevir");
		myUtil.marco("Puedes irte");
		Thread.sleep(2500);
		jugador.addMonedas(recompensa);
		jugador.ganarExperiencia(expGanada);
		Thread.sleep(2500);
	}
	
	private boolean derrotaPreguntado(Jugador jugador) throws InterruptedException {
		int danio = (int) (jugador.getVidaMaxima() / 3);// Pierde un tercio de su vida máxima
		jugador.recibirDanio(danio);
		myUtil.marco("Esa no es la opción correcta. *Recibe un fuerte golpe* -" + danio + "HP");
		Thread.sleep(2500);
		if (jugador.getVidaActual() <= 0) {
			myUtil.marco("Has muerto en el intento");
			Thread.sleep(2500);
			return false;
		}
		return true;
	}

	private void introPreguntado(Controlador controlador) throws InterruptedException {
		myUtil.marco("Te has aventurado a tierras inexploradas");
		misDibujos.dibujarCamello();
		Thread.sleep(2500);
		controlador.limpiarConsola();
		myUtil.marco("En medio de una gran tormenta de arena caes en un lugar extraño");
		misDibujos.dibujarVentisca();
		Thread.sleep(2000);
		controlador.limpiarConsola();
		myUtil.marco("Te encuentras en completa oscuridad....");
		Thread.sleep(2000);
		controlador.limpiarConsola();
		myUtil.marco("Hasta que...");
		Thread.sleep(2500);
		controlador.limpiarConsola();
		misDibujos.dibujarEsfinge();
		myUtil.marco("RESPONDE O MUERE AQUÍ MISMO!!!");
		Thread.sleep(2500);

	}

	/**
	 * Función para realizar el minijuego del ahorcado
	 * 
	 * @return Un flujo de acción para resolver un ahorcado.
	 * @throws InterruptedException
	 */
	private void realizarAhorcado(Jugador jugador, Controlador controlador, Scanner scExplorar)
			throws InterruptedException {
		introAhorcado(controlador);// Realizar la introducción
		String input = ""; // Para registrar el input del usuario
		int probabilidadPalabra = ran.nextInt(myUtil.palabrasAhorcado.length); // Calcula la probabilidad de
																				// la palabra
		String palabra = myUtil.palabrasAhorcado[probabilidadPalabra]; // Instancia la palabraf
		StringBuilder tablero = new StringBuilder(); // crea el tablero
		boolean enAhorcado = true; // Para el subMain del minijuego
		boolean entradaInvalida = false; // Para indicar que ingresó más de una letra
		ArrayList<Character> letrasIntentadas = new ArrayList<>(); // Registro de las letras que se intentó
		String cadenaLetrasIntentas = "Probaste:";
		tablero.append("_ ".repeat(palabra.length()));// Inicializar el tablero

		while (enAhorcado) {
			if (entradaInvalida) {
				controlador.limpiarConsola();
				myUtil.marco("SOLO UNA LETRA TE DIJE, intenta de nuevo");
				Thread.sleep(2000);
				entradaInvalida = false;
			}

			controlador.limpiarConsola();// Borrar lo anterior
			// Mostrar las letras que ya intentó
			misDibujos.dibujarCalavera();

			myUtil.marco(cadenaLetrasIntentas);

			myUtil.marco("Vida: " + jugador.getVidaActual() + "/" + jugador.getVidaMaxima());
			myUtil.marco(tablero.toString());
			System.out.print("Ingrese una letra o intente adivinar la palabra: ");

			input = scExplorar.nextLine().toLowerCase().trim();// Entrada del usuario normalizada

			if (input.equals("/casino") || input.equals("/salir")) { // Siempre puedes salir
				enAhorcado = false;
				myUtil.marco("Has recurrido a ténicas prohibidas para salir de tu grave situación");
				jugador.perderMonedas((int) (jugador.getMonedas() * 0.25)); // Pierde 1/4 de sus monedas
				Thread.sleep(3000);
				continue;
			}

			if (input.length() == 1) { // Si intentó correctamente una sola letra
				char letraInput = input.charAt(0);

				if (letrasIntentadas.contains(letraInput)) { // Si ya intentó esta letra
					myUtil.marco("Esa letra ya la intentaste. Prueba otra.");
					Thread.sleep(1500);
					continue;
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
					letrasIntentadas.add(letraInput);
					cadenaLetrasIntentas = cadenaLetrasIntentas + " " + letraInput;
				}
			} else if (input.equals(palabra)) {
				victoriaAhorcado(jugador, palabra, 30, 5);
				enAhorcado = false;
				continue;
			} else if (input.length() == palabra.length()) { // Intentó la palabra y falló
				animacionMuerteAhorcado(controlador, jugador);
				enAhorcado = false;
				continue;
			} else
				entradaInvalida = true;

			// Si ya no quedan '_', el jugador gana
			if (tablero.indexOf("_") == -1) { // Si gana intentando todas las letras
				victoriaAhorcado(jugador, palabra, 20, 4);
				enAhorcado = false;
			} else if (jugador.getVidaActual() <= 0) { // Si perdió todas las vidas
				animacionMuerteAhorcado(controlador, jugador);
				enAhorcado = false;
				continue;
			}
		}
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

	private void animacionMuerteAhorcado(Controlador controlador, Jugador jugador) throws InterruptedException {
		controlador.limpiarConsola();
		myUtil.marco("Intentaste adivinar la palabra y fallaste");
		Thread.sleep(2000);
		controlador.limpiarConsola();
		misDibujos.dibujarCalavera();
		myUtil.marco("La muerte te ha alcanzado... -" + jugador.getVidaActual() + "HP");
		jugador.recibirDanio(jugador.getVidaActual());
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
