package acciones;

import java.util.Random;
import java.util.Scanner;

import todo.Controlador;
import todo.Enemigo;
import todo.Jugador;
import todo.TipoEnemigo;

import utiles.Functions;
import utiles.Dibujos;

public class AccionCazar implements Accion {
	String descripcion = "Te encuentras con un enemigo aleatorio, deberás derrotarlo para obtener recompensas";
	private static final Random ran = new Random();
	private static final Functions myUtil = new Functions();
	private static final Dibujos misDibujos = new Dibujos();
	private static boolean entradaValida = false;

	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public void realizar(Jugador jugador, Controlador controlador, Scanner sc) {
		controlador.limpiarConsola();
		// Elegir el enemigo
		TipoEnemigo tipoEnemigo = elegirEnemigoAleatorio();
		Enemigo enemigo = new Enemigo(tipoEnemigo);
		boolean turnoJugador = true;

		boolean cazando = true;
		String input = "";

		try {
			animacionEntrada(controlador, enemigo);
			while (cazando) {
				controlador.limpiarConsola();
				dibujarEnemigo(enemigo);

				if (turnoJugador) {
					myUtil.marco("Turno de: " + jugador.getNombre() + jugador.getVidaActual() + "/" + jugador.getVidaMaxima());
					System.out.print("\nQué deseas hacer? : /atacar, /escapar, /proteger> ");

					input = sc.nextLine().toLowerCase().trim();
					cazando = flujoJugador(input, jugador, controlador, enemigo, sc);

					// Validar que la entrada sea valida
					if (!entradaValida) {
						continue;
					}

				} else {
					myUtil.marco("Turno de: " + enemigo.getNombre());
					flujoEnemigo(enemigo, jugador, controlador, sc);
					Thread.sleep(2000);
				}

				if (enemigo.getVidaActual() <= 0) {
					// Ganó el jugador
					myUtil.marco("¡Has derrotado a " + enemigo.getNombre() + "!");
					// (Aquí va la lógica de recompensas)
					jugador.modMonedas(enemigo.getRecompensa());
					jugador.modExp(enemigo.getRecompensaXp());
					myUtil.marco(
							"Has conseguido $" + enemigo.getRecompensa() + " y " + enemigo.getRecompensaXp() + "XP");
					Thread.sleep(2000);
					cazando = false; // Termina la batalla

				} else if (jugador.getVidaActual() <= 0) {
					// Perdió el jugador
					myUtil.marco("Has sido derrotado...");
					myUtil.marco("[Enter para continuar]");
					cazando = false; // Termina la batalla
				}

				// --- CAMBIO DE TURNO ---
				// Si la batalla no terminó, pasa el turno
				if (cazando) {
					turnoJugador = !turnoJugador; // Invierte el booleano
				}

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private boolean flujoJugador(String input, Jugador jugador, Controlador controlador, Enemigo enemigo, Scanner sc)
			throws InterruptedException {

		switch (input) {
		case "/escapar":
			int monedasPerdidas = (int) (jugador.getMonedas() * -0.25);
			myUtil.marco("Parece que era mucho para ti..");
			Thread.sleep(2000);
			jugador.modMonedas(monedasPerdidas);
			myUtil.marco("Mientras escapas se te caen $" + (-monedasPerdidas));
			Thread.sleep(2000);
			return false;

		case "/atacar":
			myUtil.marco("[Presiona enter para tirar un dado]");

			sc.nextLine();
			double multiplicadorDanio = tirarDado(controlador);
			int danio = (int) (jugador.getDanio() * multiplicadorDanio);
			int danioTotal = (int) (danio * -multiplicadorDanio);
			enemigo.modVidaActual(danioTotal);
			myUtil.marco("Le inflijes " + danioTotal + " de daño a " + enemigo.getNombre());
			Thread.sleep(2000);
			return true;
		case "/proteger":
			myUtil.marco("Recibirás un 25% menos de daño en el siguiente golpe");
			myUtil.marco("[Enter para continuar]");
			sc.nextLine();
			return true;

		default:
			entradaValida = false;
			return true;
		}
	}

	private void flujoEnemigo(Enemigo enemigo, Jugador jugador, Controlador controlador, Scanner sc)
			throws InterruptedException {
		int danioTotal = enemigo.getDanio() + ran.nextInt(10);

		myUtil.marco("Te ataca e inflige -" + danioTotal);
		jugador.modVida(-danioTotal);
		Thread.sleep(1000);

	}

	private void dibujarEnemigo(Enemigo enemigo) {
		myUtil.marco(enemigo.getNombre() + "  " + enemigo.getVidaActual() + "/" + enemigo.getVidaMaxima());
		myUtil.dibujarArrayString(enemigo.getDibujo());
	}

	private TipoEnemigo elegirEnemigoAleatorio() {
		// Obtiene un array de todos los tipos de enemigos
		TipoEnemigo[] tipos = TipoEnemigo.values();

		// Selecciona un índice aleatorio
		int indice = ran.nextInt(tipos.length);

		return tipos[indice];
	}

	private void animacionEntrada(Controlador controlador, Enemigo enemigo) throws InterruptedException {
		int tiempoEntreTexto = 1500;
		myUtil.marco("Te adentras en un oscuro bosque");
		Thread.sleep(tiempoEntreTexto);
		controlador.limpiarConsola();

		myUtil.marco("En una noche de luna llena te aventuras a encontrar algo interesante");
		Thread.sleep(tiempoEntreTexto + 500);
		controlador.limpiarConsola();

		myUtil.marco("Cuando de pronto...");
		Thread.sleep(tiempoEntreTexto);
		controlador.limpiarConsola();

		myUtil.marco("Ha aparecido un " + enemigo.getNombre() + "!!!");
		Thread.sleep(tiempoEntreTexto - 500);
	}

	private double tirarDado(Controlador controlador) throws InterruptedException {
		controlador.limpiarConsola();
		int numero = 0;
		double multiplicador = 0;
		for (int i = 0; i < 25; i++) {
			controlador.limpiarConsola();
			numero = ran.nextInt(21); // Tira un dado de 20, cada iteración es un rebote
			myUtil.marco(Integer.toString(numero));
			Thread.sleep(100);
		}
		multiplicador = numero / 10.0;
		controlador.limpiarConsola();
		myUtil.marco("Un " + numero + "!");
		Thread.sleep(1000);
		myUtil.marco("Obtienes un multiplicador de x" + multiplicador);

		if (numero == 20) {
			myUtil.marco("Golpe Crítico!!!!!!!!");
			myUtil.dibujarArrayString(misDibujos.musculos);
		}
		Thread.sleep(2000);
		return multiplicador; // devuelve un multiplicador entre 0.01 y 2
	}

}
