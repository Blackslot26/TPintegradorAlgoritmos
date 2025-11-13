package acciones;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import items.Consumible;
import items.Desechable;
import items.Equipable;
import items.Item;
import items.ItemConsumible;
import items.ItemDesechable;
import items.ItemEquipable;
import todo.Enemigo;
import todo.Jugador;
import todo.TipoEnemigo;

import utiles.MyUtil;
import utiles.Dibujos;
/**
 * Gestiona la mecánica de combate contra enemigos (PvE).
 * <p>
 * Incluye el bucle de turnos, cálculo de daño basado en dados, 
 * lógica de defensa/ataque y generación de botín (loot) al ganar.
 * </p>
 */
public class AccionCazar implements Accion {

	private static final Random ran = new Random();
	public static final int SEG_COOLDOWN = 120;
	/**
	 * Inicia el encuentro de combate.
	 * Selecciona un enemigo aleatorio y comienza el bucle de turnos hasta que
	 * uno de los dos (jugador o enemigo) pierda toda su vida o el jugador escape.
	 * * @param jugador El jugador que inicia la caza.
	 * @param sc      Scanner para los comandos de combate (/atacar, /proteger, /escapar).
	 */
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
					MyUtil.marco("Turno de: " + jugador.getNombre() + " " + MyUtil.ANSI_GREEN + jugador.getVidaActual()
							+ MyUtil.ANSI_RESET + "/" + jugador.getVidaMaxima());
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
					enemigo.setVidaActual(0);
					// Ganó el jugador
					dibujarEnemigo(enemigo);
					MyUtil.marco(
							MyUtil.ANSI_GREEN + "¡Has derrotado a " + enemigo.getNombre() + "!" + MyUtil.ANSI_RESET);

					// (Aquí va la lógica de recompensas)
					jugador.modMonedas(enemigo.getRecompensa());
					jugador.modExp(enemigo.getRecompensaXp());

					MyUtil.marco(
							"Has conseguido " + MyUtil.ANSI_YELLOW + "$" + enemigo.getRecompensa() + MyUtil.ANSI_RESET
									+ " y " + MyUtil.ANSI_CYAN + enemigo.getRecompensaXp() + "XP" + MyUtil.ANSI_RESET);
					generarLoot(jugador,enemigo);
					System.out.println("[Enter para volver al lobby]");
					sc.nextLine();
					cazando = false; // Termina la batalla

				} else if (jugador.getVidaActual() <= 0) {
					// Perdió el jugador
					MyUtil.marco(MyUtil.ANSI_RED + "Has sido derrotado..." + MyUtil.ANSI_RESET);
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
		jugador.setActionCooldown("/cazar", SEG_COOLDOWN);

	}
	/**
     * Procesa el turno del jugador.
     * * @param input   Comando ingresado por el usuario.
     * @param jugador El jugador.
     * @param enemigo El enemigo actual.
     * @param sc      Scanner para pausas.
     * @return true si la acción consumió el turno, false si fue un comando inválido.
     * @throws InterruptedException Si falla el Thread.sleep en las animaciones.
     */
	private boolean flujoJugador(String input, Jugador jugador, Enemigo enemigo, Scanner sc)
			throws InterruptedException {

		switch (input) {
		case "/atacar":
			System.out.print("\ndebes lanzar un dado [enter]");
			sc.nextLine();

			double multiplicadorDanio = tirarDado(jugador);
			int danio = (int) (jugador.getDanio() * multiplicadorDanio);
			if (!enemigo.estaDefendiento()) { // Si el enemigo no se está defendiendo se aplica todo el daño
				enemigo.modVidaActual(danio * -1);
				MyUtil.marco("Le inflijes " + MyUtil.ANSI_GREEN + danio + " de daño" + MyUtil.ANSI_RESET + " a "
						+ enemigo.getNombre());
			} else {
				int danioTotal = (int) (danio * 0.75);
				enemigo.modVidaActual(danioTotal * -1);
				MyUtil.marco(enemigo.getNombre() + " Se protege, le inflijes " + MyUtil.ANSI_YELLOW + danioTotal
						+ " de daño" + MyUtil.ANSI_RESET);
				enemigo.setEstadoDefensa(false);
			}
			System.out.println("[Enter para pasar de turno]");
			sc.nextLine();
			return true;

		case "/proteger":
			jugador.setEstadoDefensa(true);
			MyUtil.marco("Recibirás un " + MyUtil.ANSI_GREEN + "25% " + MyUtil.ANSI_RESET
					+ "menos de daño en el siguiente golpe");
			System.out.println("[Enter para continuar]");
			sc.nextLine();
			return true;
		default:
			return false;
		}
	}

private void flujoEnemigo(Enemigo enemigo, Jugador jugador, Scanner sc) throws InterruptedException {
		
		int danioBruto = (int) (enemigo.getDanio() * (ran.nextInt(15) / 10.0)) + 10;
		// Aplicar la defensa base del jugador (ej. 0.15 para 15%)
		int danioMitigado = (int) (danioBruto * (1.0 - jugador.getDefensa()));

		if (ran.nextInt(4) < 3) { // 75% de probabilidad de atacar
			
			int danioFinal = danioMitigado; // Por defecto

			if (jugador.estaDefendiento()) {
				danioFinal = (int) (danioMitigado * 0.75); // Reducción adicional de /proteger
				MyUtil.marco("Te proteges! El golpe se reduce a " + MyUtil.ANSI_YELLOW + "-" + danioFinal + "HP"
						+ MyUtil.ANSI_RESET);
				jugador.setEstadoDefensa(false);
			} else {
				MyUtil.marco(MyUtil.ANSI_RED + "Te ataca e inflige -" + danioFinal + "HP" + MyUtil.ANSI_RESET);
			}

			jugador.modVida(danioFinal * -1); // Aplica el daño final

		} else { // 25% de probabilidad de defender
			enemigo.setEstadoDefensa(true);
			MyUtil.marco(MyUtil.ANSI_GREEN + enemigo.getNombre() + " se prepara para defenderse" + MyUtil.ANSI_RESET);
		}

		System.out.print("[Enter para pasar a tu turno]");
		sc.nextLine();
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
	/**
     * Simula el lanzamiento de un dado D20 para determinar el multiplicador de daño.
     * Incluye una animación visual en consola.
     * * @param jugador Para obtener su estadística de suerte.
     * @return Un multiplicador de daño (base + suerte + resultado del dado).
     * @throws InterruptedException
     */
	private double tirarDado(Jugador jugador) throws InterruptedException {
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
		
		// El multiplicador base (0.0 a 2.0) se incrementa con la suerte del jugador
		multiplicador = (numero / 10.0) + jugador.getSuerte(); 
		
		MyUtil.limpiarConsola();
		MyUtil.marco("Un " + numero + "!");
		Thread.sleep(1000);
		MyUtil.marco("Obtienes un multiplicador de x" + MyUtil.ANSI_CYAN + multiplicador + MyUtil.ANSI_RESET + " (Base: "
				+ (numero / 10.0) + " + Suerte: " + jugador.getSuerte() + ")");

		if (numero == 20) {
			MyUtil.marco(MyUtil.ANSI_GREEN + "Golpe Crítico!!!!!!!!" + MyUtil.ANSI_RESET);
			MyUtil.dibujarArrayString(Dibujos.DIBUJO_MUSCULOS);
		}
		Thread.sleep(2000);
		return multiplicador; 
	}

	/**
	 * Procesa la tabla de loot del enemigo y añade los items al inventario del
	 * jugador.
	 */
	/**
	 * Procesa la tabla de loot del enemigo y añade los items al inventario del
	 * jugador basándose en la probabilidad de drop.
	 */
	/**
     * Genera las recompensas (ítems) tras derrotar a un enemigo.
     * Utiliza la probabilidad de drop del enemigo y la suerte del jugador.
     */
	private void generarLoot(Jugador jugador, Enemigo enemigo) {
		Enum<?>[] plantillas = enemigo.getPlantillaLoot();
		double[] chances = enemigo.getLootChances();

		if (plantillas == null || plantillas.length == 0) {
			return; 
		}

		ArrayList<String> feedbackLoot = new ArrayList<>();
		
		// --- APLICACIÓN DE ESTADÍSTICA 'SUERTE' ---
		double suerteJugador = jugador.getSuerte(); 

		for (int i = 0; i < plantillas.length; i++) {
			
			// La chance base (ej. 0.1) se incrementa con la suerte (ej. 0.1 + 0.15 = 0.25)
			double chanceFinal = chances[i] + suerteJugador; 
			
			if (ran.nextDouble() < chanceFinal) { 

				Enum<?> template = plantillas[i];
				Item itemDroppedInstance = null;
				int cantidad = 1;

				if (template instanceof ItemDesechable) {
					itemDroppedInstance = new Desechable((ItemDesechable) template, cantidad);
				} else if (template instanceof ItemConsumible) {
					itemDroppedInstance = new Consumible((ItemConsumible) template, cantidad);
				} else if (template instanceof ItemEquipable) {
					itemDroppedInstance = new Equipable((ItemEquipable) template, cantidad);
				}

				if (itemDroppedInstance != null) {
					jugador.addItem(itemDroppedInstance);
					// Añadido color al item dropeado
					feedbackLoot.add("[+] " + MyUtil.ANSI_CYAN + itemDroppedInstance.getNombre() + MyUtil.ANSI_RESET);
				}
			}
		}

		if (!feedbackLoot.isEmpty()) {
			// Añadido color al título del botín
			feedbackLoot.add(0, MyUtil.ANSI_YELLOW + "Botín Obtenido:" + MyUtil.ANSI_RESET);
			MyUtil.marco(feedbackLoot.toArray(new String[0]));
		}
	}
}
