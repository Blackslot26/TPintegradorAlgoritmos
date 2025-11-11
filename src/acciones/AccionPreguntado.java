package acciones;

import java.util.Random;
import java.util.Scanner;

import todo.Controlador;
import todo.Jugador;
import utiles.Functions;
import utiles.Dibujos;

public class AccionPreguntado implements Accion {

	private String descripcion = "Minijuego de trivia utilizado exclusivamente en la accion de \"explorar\"";
	private Functions myUtil;
	private Dibujos misDibujos;
	private Random ran;

	public AccionPreguntado() {
		myUtil = new Functions();
		misDibujos = new Dibujos();
		ran = new Random();
	}

	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public void realizar(Jugador jugador, Controlador controlador, Scanner scPreguntado) {
		boolean enPreguntado = true;
		int indicePregunta = ran.nextInt(myUtil.preguntas.size()); // Se elige la pregunta al azar
		String input = "";// Para registrar el input local

		try {
			// Pasamos el Scanner a la intro
			introPreguntado(controlador, scPreguntado);
			while (enPreguntado) {
				mostrarPregunta(jugador, controlador, indicePregunta);// Dibujar lo necesario
				input = scPreguntado.nextLine().toLowerCase().trim(); // Input normalizado
				// Pasamos el Scanner al flujo
				enPreguntado = flujoPreguntado(jugador, controlador, input, indicePregunta, scPreguntado);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// Añadimos Scanner scPreguntado
	private boolean flujoPreguntado(Jugador jugador, Controlador controlador, String input, int i, Scanner scPreguntado)
			throws InterruptedException {
		if (input.equals("/escapar") || input.equals("/esc") || input.equals("/salir") || input.equals("/s")) {

			myUtil.marco("Has salido corriendo como una niñita");
			int monedasPerdidas = (int) (jugador.getMonedas() * -0.25);
			jugador.modMonedas(monedasPerdidas); // Pierde 1/4 de sus monedas
			myUtil.marco("Se te caen -$" + -monedasPerdidas);
			Thread.sleep(3000);
			
			//Para evitar que el usuario spamee letras
			System.out.print("\n[Presiona Enter para continuar]...");
			scPreguntado.nextLine();
			return false; // Termina el juego
		}

		try {
			if (Integer.parseInt(input) - 1 == myUtil.preguntas.get(i).getRespuesta()) {
				// Pasamos el Scanner
				victoriaPreguntado(jugador, i, scPreguntado);
				return false;
			} else if (Integer.parseInt(input) - 1 >= myUtil.preguntas.get(i).getOpciones().length) {
//				myUtil.marco("No ves que no existe la opción " + input + "?");
//				Thread.sleep(2500);
			} else {
				// Pasamos el Scanner
				return derrotaPreguntado(jugador, scPreguntado);
			}

		} catch (NumberFormatException e) {
//			myUtil.marco("ELIGE UN NÚMERO!!!");
//			Thread.sleep(2000);
		}

		return true;
	}

	private void mostrarPregunta(Jugador jugador,Controlador controlador,int i) {
		controlador.limpiarConsola();
		misDibujos.dibujarEsfinge();
		myUtil.marco("Vida: " + jugador.getVidaActual() + "/" + jugador.getVidaMaxima()); // Muestra la vida
		myUtil.marco(myUtil.preguntas.get(i).getPregunta());// Muestra la pregunta
		myUtil.marco(myUtil.preguntas.get(i).opcionesToString()); // Muestra las opciones
		System.out.print("\nElige con cuidado (1,2,3 o 4): "); // Para ingresar el input
	}

	// Añadimos Scanner scPreguntado
	private void victoriaPreguntado(Jugador jugador, int indicePregunta, Scanner scPreguntado) throws InterruptedException {
		int recompensa = myUtil.preguntas.get(indicePregunta).getRecompensa();
		int expGanada = ran.nextInt(20) + 10;// Puede ganar hasta 30 de EXP y minimo 10
		myUtil.marco("Muy bien, has logrado sobrevir");
		myUtil.marco("Puedes irte");
		Thread.sleep(2500);
		jugador.modMonedas(recompensa);
		jugador.modExp(expGanada);
		myUtil.marco("Has ganado $" + recompensa + " y "+ expGanada + "XP");
		Thread.sleep(2500);
		
		//Para evitar que el usuario spamee letras
		System.out.print("\n[Presiona Enter para continuar]...");
		scPreguntado.nextLine();
	}

	// Añadimos Scanner scPreguntado
	private boolean derrotaPreguntado(Jugador jugador, Scanner scPreguntado) throws InterruptedException {
		int danio = (int) (jugador.getVidaMaxima() / -3);// Pierde un tercio de su vida máxima
		jugador.modVida(danio);
		myUtil.marco("Esa no es la opción correcta. *Recibe un fuerte golpe* " + danio + "HP");
		Thread.sleep(2500); // Esto es feedback, no una cinemática final, no requiere sc.nextLine()
		
		if (jugador.getVidaActual() <= 0) {
			myUtil.marco("Has muerto en el intento");
			Thread.sleep(2500);
			
			//Para evitar que el usuario spamee letras
			System.out.print("\n[Presiona Enter para continuar]...");
			scPreguntado.nextLine();
			return false;
		}
		return true;
	}

	// Añadimos Scanner scPreguntado
	private void introPreguntado(Controlador controlador, Scanner scPreguntado) throws InterruptedException {
		myUtil.marco("Te has aventurado a tierras inexploradas");
		misDibujos.dibujarCamello();
		Thread.sleep(3000);
		controlador.limpiarConsola();
		myUtil.marco("En medio de una gran tormenta de arena caes en un lugar extraño");
		misDibujos.dibujarVentisca();
		Thread.sleep(2500);
		controlador.limpiarConsola();
		myUtil.marco("Te encuentras en completa oscuridad....");
		Thread.sleep(2000);
		controlador.limpiarConsola();
		myUtil.marco("Hasta que...");
		Thread.sleep(3000);
		controlador.limpiarConsola();
		misDibujos.dibujarEsfinge();
		myUtil.marco("RESPONDE O MUERE AQUÍ MISMO!!!");
		Thread.sleep(2500);
		
		//Para evitar que el usuario spamee letras
		System.out.print("\n[Presiona Enter para comenzar]...");
		scPreguntado.nextLine();
	}

}