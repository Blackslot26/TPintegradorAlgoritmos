package acciones;

import java.util.Random;
import java.util.Scanner;

import todo.Enemigo;
import todo.Jugador;
import todo.TipoEnemigo;

import utiles.MyUtil;
import utiles.Dibujos;

public class AccionCazar implements Accion {
	
	private static final Random ran = new Random();
	public static final int SEG_COOLDOWN= 120;

	@Override
	public void realizar(Jugador jugador, Scanner sc) {
		MyUtil.limpiarConsola();
		// Elegir el enemigo
		TipoEnemigo tipoEnemigo = elegirEnemigoAleatorio();
		Enemigo enemigo = new Enemigo(tipoEnemigo);
		boolean turnoJugador = true;

		boolean cazando = true;
		String input = "";

		try {
			animacionEntrada(enemigo);
			while (cazando) {
				MyUtil.limpiarConsola();
				dibujarEnemigo(enemigo);

				if (turnoJugador) {
					MyUtil.marco("Turno de: " + jugador.getNombre() + " " + jugador.getVidaActual() + "/"
							+ jugador.getVidaMaxima());
					System.out.print("\nQué deseas hacer? : /atacar, /escapar, /proteger> ");

					input = sc.nextLine().toLowerCase().trim();

					boolean cambiarTurno = flujoJugador(input, jugador, enemigo, sc);

					// Si la acción fue escapar, 'cazando' será false y saldrá
					if (input.equals("/escapar")) {
						int monedasPerdidas = (int) (jugador.getMonedas() * -0.25);
						MyUtil.marco("Parece que era mucho para ti..");
						Thread.sleep(2000);
						jugador.modMonedas(monedasPerdidas);
						MyUtil.marco("Mientras escapas se te caen $" + (-monedasPerdidas));
						Thread.sleep(2000);
						cazando = false;
						continue; // Salir del bucle
					}

					// Si el input NO fue válido, no se cambia el turno
					if (!cambiarTurno) {
						continue; // Repite el turno del jugador
					}

				} else {
					MyUtil.marco("Turno de: " + enemigo.getNombre());
					flujoEnemigo(enemigo, jugador, sc);
				}

				if (enemigo.getVidaActual() <= 0) {
					// Ganó el jugador
					MyUtil.marco("¡Has derrotado a " + enemigo.getNombre() + "!");
					// (Aquí va la lógica de recompensas)
					jugador.modMonedas(enemigo.getRecompensa());
					jugador.modExp(enemigo.getRecompensaXp());
					MyUtil.marco(
							"Has conseguido $" + enemigo.getRecompensa() + " y " + enemigo.getRecompensaXp() + "XP");
					System.out.println("[Enter para volver al lobby]");
					sc.nextLine();
					cazando = false; // Termina la batalla

				} else if (jugador.getVidaActual() <= 0) {
					// Perdió el jugador
					MyUtil.marco("Has sido derrotado...");
					MyUtil.marco("[Enter para continuar]");
					sc.nextLine();
					cazando = false; // Termina la batalla
				}

				// Si la batalla no terminó, pasa el turno
				if (cazando) {
					turnoJugador = !turnoJugador; // Invierte el booleano
				}

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jugador.setActionCooldown("/cazar",SEG_COOLDOWN);

	}

	private boolean flujoJugador(String input, Jugador jugador, Enemigo enemigo, Scanner sc)
			throws InterruptedException {

		switch (input) {
		case "/atacar":
			System.out.print("\ndebes lanzar un dado [enter]");
			sc.nextLine();
			
			double multiplicadorDanio = tirarDado();
			int danio = (int) (jugador.getDanio() * multiplicadorDanio);
			if(!enemigo.estaDefendiento()) { //Si el enemigo no se está defendiendo se aplica todo el daño
				enemigo.modVidaActual(danio *-1);
				MyUtil.marco("Le inflijes " + danio + " de daño a " + enemigo.getNombre());
			}
			else {
				int danioTotal = (int) (danio*0.75);
				enemigo.modVidaActual(danioTotal *-1);
				MyUtil.marco(enemigo.getNombre() + " Se protege, le inflijes " + danioTotal + " de daño");
				enemigo.setEstadoDefensa(false);
			}

			Thread.sleep(2000);
			return true;

		case "/proteger":
			jugador.setEstadoDefensa(true);
			MyUtil.marco("Recibirás un "+MyUtil.ANSI_GREEN+"25% "+MyUtil.ANSI_RESET+"menos de daño en el siguiente golpe");
			System.out.println("[Enter para continuar]");
			sc.nextLine();
			return true;
			default:return false;
		}
	}

	private void flujoEnemigo(Enemigo enemigo, Jugador jugador, Scanner sc)
	        throws InterruptedException {

	    //Calcula el daño bruto del enemigo (¡Arreglaste el /10.0!)
	    int danioBruto = (int) (enemigo.getDanio() * (ran.nextInt(15)/10.0)) + 10;

	    //Calcula la mitigación de defensa base del jugador
	    int danioMitigado = (int) (danioBruto * (1.0 - jugador.getDefensa()));

	    if (ran.nextInt(4) < 3) { // 75% de probabilidad de atacar

	        int danioFinal = danioMitigado; // Por defecto, es el daño ya mitigado

	        if (jugador.estaDefendiento()) {
	            // Si defiende, aplica el 25% de reducción adicional
	            danioFinal = (int) (danioMitigado * 0.75); 
	            MyUtil.marco("Te proteges! El golpe se reduce a " +MyUtil.ANSI_RED+ -danioFinal + "HP"+ MyUtil.ANSI_RESET);
	            jugador.setEstadoDefensa(false);
	        } else {
	            MyUtil.marco("Te ataca e inflige "+MyUtil.ANSI_RED + -danioFinal + MyUtil.ANSI_RESET);
	        }

	        jugador.modVida(danioFinal * -1); // Aplica el daño final

	    } else { // 25% de probabilidad de defender
	        enemigo.setEstadoDefensa(true);
	        MyUtil.marco(MyUtil.ANSI_GREEN+enemigo.getNombre()+" se prepara para defenderse"+MyUtil.ANSI_RESET);
	    }

	    System.out.print("[Enter para pasar a tu turno]");
	    sc.nextLine();

	    // El Thread.sleep(1000) que tenías aquí ya no es necesario
	    // porque sc.nextLine() actúa como la pausa.
	}

	private void dibujarEnemigo(Enemigo enemigo) {
		MyUtil.marco(enemigo.getNombre() + "  " + enemigo.getVidaActual() + "/" + enemigo.getVidaMaxima());
		MyUtil.dibujarArrayString(enemigo.getDibujo());
	}

	private TipoEnemigo elegirEnemigoAleatorio() {
		// Obtiene un array de todos los tipos de enemigos
		TipoEnemigo[] tipos = TipoEnemigo.values();

		// Selecciona un índice aleatorio
		int indice = ran.nextInt(tipos.length);

		return tipos[indice];
	}

	private void animacionEntrada(Enemigo enemigo) throws InterruptedException {
		int tiempoEntreTexto = 1500;
		MyUtil.marco("Te adentras en un oscuro bosque");
		Thread.sleep(tiempoEntreTexto);
		MyUtil.limpiarConsola();

		MyUtil.marco("En una noche de luna llena te aventuras a encontrar algo interesante");
		Thread.sleep(tiempoEntreTexto + 500);
		MyUtil.limpiarConsola();

		MyUtil.marco("Cuando de pronto...");
		Thread.sleep(tiempoEntreTexto);
		MyUtil.limpiarConsola();

		MyUtil.marco("Ha aparecido un " + enemigo.getNombre() + "!!!");
		Thread.sleep(tiempoEntreTexto - 500);
	}

	private double tirarDado() throws InterruptedException {
		MyUtil.limpiarConsola();
		int numero = 0;
		double multiplicador = 0;
		int rebotesDado = 25;
		for (int i = 0; i < rebotesDado; i++) {
			MyUtil.limpiarConsola();
			numero = ran.nextInt(21); // Tira un dado de 20, cada iteración es un rebote
			MyUtil.marco(Integer.toString(numero));
			Thread.sleep(100);
		}
		multiplicador = numero / 10.0;
		MyUtil.limpiarConsola();
		MyUtil.marco("Un " + numero + "!");
		Thread.sleep(1000);
		MyUtil.marco("Obtienes un multiplicador de x" + multiplicador);

		if (numero == 20) {
			MyUtil.marco("Golpe Crítico!!!!!!!!");
			MyUtil.dibujarArrayString(Dibujos.DIBUJO_MUSCULOS);
		}
		Thread.sleep(2000);
		return multiplicador; // devuelve un multiplicador entre 0.01 y 2
	}

}
