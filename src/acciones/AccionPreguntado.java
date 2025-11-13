package acciones;

import java.util.Random;
import java.util.Scanner;

import todo.Jugador;
import utiles.MyUtil;
import utiles.DatosJuego;
import utiles.Dibujos;
/**
 * Minijuego de preguntas y respuestas (Trivia).
 * Presenta un desafío intelectual donde fallar conlleva penalización de vida.
 */
public class AccionPreguntado implements Accion {
	private Random ran;

	public AccionPreguntado() {
		ran = new Random();
	}


	@Override
	public void realizar(Jugador jugador, Scanner scPreguntado) {
		boolean enPreguntado = true;
		int indicePregunta = ran.nextInt(DatosJuego.preguntas.size()); // Se elige la pregunta al azar
		String input = "";// Para registrar el input local

		try {
			// Pasamos el Scanner a la intro
			introPreguntado(scPreguntado);
			while (enPreguntado) {
				mostrarPregunta(jugador, indicePregunta);// Dibujar lo necesario
				input = scPreguntado.nextLine().toLowerCase().trim(); // Input normalizado
				// Pasamos el Scanner al flujo
				enPreguntado = flujoPreguntado(jugador, input, indicePregunta, scPreguntado);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// Añadimos Scanner scPreguntado
	/**
	 * Verifica si la respuesta elegida es correcta.
	 * * @param i Índice de la pregunta actual en la base de datos.
	 * @return true si el juego continúa (aunque en este diseño suele ser una sola pregunta), false si termina.
	 */
	private boolean flujoPreguntado(Jugador jugador, String input, int i, Scanner scPreguntado)
			throws InterruptedException {
		if (input.equals("/escapar") || input.equals("/esc") || input.equals("/salir") || input.equals("/s")) {

			MyUtil.marco("Has salido corriendo como una niñita");
			int monedasPerdidas = (int) (jugador.getMonedas() * -0.25);
			jugador.modMonedas(monedasPerdidas); // Pierde 1/4 de sus monedas
			MyUtil.marco("Se te caen -$" + -monedasPerdidas);

			// Para evitar que el usuario spamee letras
			System.out.print("\n[Presiona Enter para continuar]...");
			scPreguntado.nextLine();
			return false; // Termina el juego
		}

		try {
			if (Integer.parseInt(input) - 1 == DatosJuego.preguntas.get(i).getRespuesta()) {
				// Pasamos el Scanner
				victoriaPreguntado(jugador, i, scPreguntado);
				return false;
			} else if (Integer.parseInt(input) - 1 >= DatosJuego.preguntas.get(i).getOpciones().length) {
				MyUtil.marco("No ves que no existe la opción " + input + "?");
				Thread.sleep(1000);
			} else {
				// Pasamos el Scanner
				return derrotaPreguntado(jugador, scPreguntado);
			}

		} catch (NumberFormatException e) {
//			MyUtil.marco("ELIGE UN NÚMERO!!!");
//			Thread.sleep(2000);
		}

		return true;
	}

	private void mostrarPregunta(Jugador jugador, int i) {
		MyUtil.limpiarConsola();
		MyUtil.dibujarArrayString(Dibujos.DIBUJO_ESFINGE);
		MyUtil.marco("Vida: " + MyUtil.ANSI_GREEN + jugador.getVidaActual() + MyUtil.ANSI_RESET + "/" + jugador.getVidaMaxima());
		MyUtil.marco(DatosJuego.preguntas.get(i).getPregunta());// Muestra la pregunta
		MyUtil.marco(DatosJuego.preguntas.get(i).opcionesToString()); // Muestra las opciones
		System.out.print("\nElige con cuidado (1,2,3 o 4): "); // Para ingresar el input
	}

	// Añadimos Scanner scPreguntado
	private void victoriaPreguntado(Jugador jugador, int indicePregunta, Scanner scPreguntado)
			throws InterruptedException {
		double multiplicadorSuerte = 1.0 + jugador.getSuerte();
		
		int recompensaBruta = DatosJuego.preguntas.get(indicePregunta).getRecompensa();
		int expBruta = ran.nextInt(20) + 10; // Puede ganar hasta 30 de EXP y sin la suerte
		
		int recompensaFinal = (int) (recompensaBruta * multiplicadorSuerte);
		int expFinal = (int) (expBruta * multiplicadorSuerte);
		
		MyUtil.marco(MyUtil.ANSI_GREEN + "Muy bien, has logrado sobrevir" + MyUtil.ANSI_RESET);
		MyUtil.marco("Puedes irte");
		Thread.sleep(2500);
		jugador.modMonedas(recompensaFinal);
		jugador.modExp(expFinal);
		
		MyUtil.marco("Has ganado " + MyUtil.ANSI_YELLOW + "$" + recompensaFinal + MyUtil.ANSI_RESET + " y " + MyUtil.ANSI_CYAN + expFinal + "XP" + MyUtil.ANSI_RESET);
		
		// Muestra el desglose de la suerte si hubo bonificación
		if (jugador.getSuerte() > 0) {
			MyUtil.marco("(Bonificación por suerte: $" + (recompensaFinal - recompensaBruta) + " y " 
					+ (expFinal - expBruta) + "XP)");
		}
		

		// Para evitar que el usuario spamee letras
		System.out.print("\n[Presiona Enter para continuar]...");
		scPreguntado.nextLine();
	}

	// Añadimos Scanner scPreguntado
	private boolean derrotaPreguntado(Jugador jugador, Scanner scPreguntado) throws InterruptedException {
		int danio = (int) (jugador.getVidaMaxima() / -3);// Pierde un tercio de su vida máxima
		jugador.modVida(danio);
		MyUtil.marco("Esa no es la opción correcta. *Recibe un fuerte golpe* " + MyUtil.ANSI_RED + danio + "HP" + MyUtil.ANSI_RESET);
		Thread.sleep(2500);

		if (jugador.getVidaActual() <= 0) {
			MyUtil.marco(MyUtil.ANSI_RED + "Has muerto en el intento" + MyUtil.ANSI_RESET);

			// Para evitar que el usuario spamee letras
			System.out.print("\n[Presiona Enter para continuar]...");
			scPreguntado.nextLine();
			return false;
		}
		return true;
	}

	// Añadimos Scanner scPreguntado
	private void introPreguntado(Scanner scPreguntado) throws InterruptedException {
		MyUtil.marco("Te has aventurado a tierras inexploradas");
		MyUtil.dibujarArrayString(Dibujos.DIBUJO_CAMELLO);
		Thread.sleep(3000);
		MyUtil.limpiarConsola();
		MyUtil.marco("En medio de una gran tormenta de arena caes en un lugar extraño");
		MyUtil.dibujarArrayString(Dibujos.DIBUJO_VENTISCA);
		Thread.sleep(2500);
		MyUtil.limpiarConsola();
		MyUtil.marco("Te encuentras en completa oscuridad....");
		Thread.sleep(2000);
		MyUtil.limpiarConsola();
		MyUtil.marco("Hasta que...");
		Thread.sleep(3000);
		MyUtil.limpiarConsola();
		MyUtil.dibujarArrayString(Dibujos.DIBUJO_ESFINGE);
		MyUtil.marco(MyUtil.ANSI_RED + "RESPONDE O MUERE AQUÍ MISMO!!!" + MyUtil.ANSI_RESET);
		
		
		// Para evitar que el usuario spamee letras
		System.out.print("\n[Presiona Enter para comenzar]...");
		scPreguntado.nextLine();
	}

}
