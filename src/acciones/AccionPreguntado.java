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
			introPreguntado(controlador);// Primero se realiza la introducción
			while (enPreguntado) {
				mostrarPregunta(jugador, controlador, indicePregunta);// Dibujar lo necesario
				input = scPreguntado.nextLine().toLowerCase().trim(); // Input normalizado
				enPreguntado = flujoPreguntado(jugador, controlador, input, indicePregunta);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private boolean flujoPreguntado(Jugador jugador, Controlador controlador, String input, int i)
			throws InterruptedException {
		if (input.equals("/escapar") || input.equals("/esc") || input.equals("/salir") || input.equals("/s")) {

			myUtil.marco("Has salido corriendo como una niñita");
			int monedasPerdidas = (int) (jugador.getMonedas() * -0.25);
			jugador.modMonedas(monedasPerdidas); // Pierde 1/4 de sus monedas
			myUtil.marco("Perdiste $" + monedasPerdidas);
			Thread.sleep(3000);
			return false; // Termina el juego
		}

		try {
			if (Integer.parseInt(input) - 1 == myUtil.preguntas.get(i).getRespuesta()) {
				victoriaPreguntado(jugador, i);
				return false;
			} else if (Integer.parseInt(input) - 1 >= myUtil.preguntas.get(i).getOpciones().length) {
				myUtil.marco("No ves que no existe la opción " + input + "?");
				Thread.sleep(2500);
			} else {
				return derrotaPreguntado(jugador);
			}

		} catch (NumberFormatException e) {
			myUtil.marco("ELIGE UN NÚMERO!!!");
			Thread.sleep(2000);
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

	private void victoriaPreguntado(Jugador jugador, int indicePregunta) throws InterruptedException {
		int recompensa = myUtil.preguntas.get(indicePregunta).getRecompensa();
		int expGanada = ran.nextInt(20) + 10;// Puede ganar hasta 30 de EXP y minimo 10
		myUtil.marco("Muy bien, has logrado sobrevir");
		myUtil.marco("Puedes irte");
		Thread.sleep(2500);
		jugador.modMonedas(recompensa);
		jugador.modExp(expGanada);
		myUtil.marco("Has ganado $" + recompensa + " y "+ expGanada);
		Thread.sleep(2500);
	}

	private boolean derrotaPreguntado(Jugador jugador) throws InterruptedException {
		int danio = (int) (jugador.getVidaMaxima() / -3);// Pierde un tercio de su vida máxima
		jugador.modVida(danio);
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
	}

}
