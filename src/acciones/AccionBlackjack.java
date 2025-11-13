package acciones;

import java.util.Scanner;

import apuestasResources.Carta;
import apuestasResources.Mazo;
import todo.Jugador;
import utiles.Dibujos;
import utiles.MyUtil;

import java.util.ArrayList;
/**
 * Minijuego de apuestas basado en el Blackjack clásico (21).
 * Permite al jugador apostar monedas y jugar contra la "Casa" (IA simple).
 */
public class AccionBlackjack implements Accion {

	private Mazo mazo;

	public AccionBlackjack() {
		this.mazo = new Mazo();
	}
	/**
	 * Inicia la partida de Blackjack.
	 * <p>
	 * Flujo:
	 * 1. Pedir apuesta.
	 * 2. Repartir cartas iniciales.
	 * 3. Turno del jugador (Pedir/Plantarse).
	 * 4. Turno de la casa (Pide obligatoriamente hasta 17).
	 * 5. Resolución y pago.
	 * </p>
	 */
	@Override
	public void realizar(Jugador jugador, Scanner sc) {
		MyUtil.limpiarConsola();
		MyUtil.dibujarArrayString(Dibujos.DIBUJO_BLACKJACK);
		int apuesta = 0;
		while (true) {
			MyUtil.marco("¿Cuanto deseas apostar?   (Escribe 0 para salir)");
			MyUtil.marco("Monedas: " + jugador.getMonedas());
			try {
				String input = sc.nextLine();
				apuesta = Integer.parseInt(input);

				if (apuesta == 0) {
					return;
				}
				if (apuesta < 0) {
					System.out.println("No se vale apostar aire.");
					return;
				} else if (apuesta > jugador.getMonedas()) {
					System.out.println("No tienes suficiente dinero.");
					return;
				} else {
					break;
				}

			} catch (NumberFormatException e) {
				System.out.println("Numero invalido. Por favor elija un numero valido.");
				return;
			}

		}

		// INICIO LOGICA
		mazo.reiniciarMazo();
		ArrayList<Carta> manoJugador = new ArrayList<>();
		ArrayList<Carta> manoCasa = new ArrayList<>();

		manoJugador.add(mazo.tomarCarta());
		manoCasa.add(mazo.tomarCarta());
		manoJugador.add(mazo.tomarCarta());
		manoCasa.add(mazo.tomarCarta());

		boolean turnoJugador = true;
		boolean jugadorSePaso = false;
		boolean BLACKJACK = false;

		while (turnoJugador) {
			MyUtil.limpiarConsola();
			System.out.println("-------Tu Turno-------");
			System.out.println();
			System.out.println("---MANO DE LA CASA--- ");
			imprimirMano(manoCasa, true);
			System.out.println(">> Puntaje de la casa: " + manoCasa.get(1).getPuntaje());
			System.out.println();
			System.out.println("---TU MANO--- ");
			imprimirMano(manoJugador, false);
			System.out.println();
			int puntajeJugador = calcularPuntaje(manoJugador);
			System.out.println(">> Tu puntaje: " + puntajeJugador);

			if (puntajeJugador > 21) {
				System.out.println("\n ¡TE PASASTE!");
				jugadorSePaso = true;
				turnoJugador = false;
				break;
			}
			if (puntajeJugador == 21) {
				System.out.println("\n ¡21! ¡BLACKJACK!");
				turnoJugador = false;
				BLACKJACK = true;
				break;
			}
			System.out.println("   Pedir una carta o plantarse?");
			System.out.println("/hit (pedir)  -  /stand (plantarse)");
			System.out.print("> ");
			String opcion = sc.nextLine().toLowerCase();

			if (opcion.startsWith("/h")) {
				Carta c = mazo.tomarCarta();
				manoJugador.add(c);
				System.out.println("Recibes una carta");
				esperar(1000);
			} else if (opcion.startsWith("/s")) {
				System.out.println("Te plantas con " + puntajeJugador);
				turnoJugador = false;
			} else {
				System.out.println("Opcion no valida");
				esperar(1000);
			}
		}
		System.out.println("--------------------------------");
		if (jugadorSePaso) {
			System.out.println("La casa gana. Pierdes " + apuesta);
			jugador.modMonedas(-apuesta);
		} else if (BLACKJACK) {
			System.out.println("Obtuviste un Blackjack! Que suerte!!! Ganas: " + apuesta);
			jugador.modMonedas((int) ((apuesta * 1.5) * jugador.getMultGanancia()));
		}else{
		System.out.println("La casa revela su carta...");
		esperar(2000);
		while (calcularPuntaje(manoCasa) < 17) {
			MyUtil.limpiarConsola();
			System.out.println("===TURNO DE LA CASA===");
			System.out.println("---MANO DE LA CASA---");
			imprimirMano(manoCasa, false);
			System.out.println("Puntaje de la casa: " + calcularPuntaje(manoCasa));
			System.out.println("La casa pide una carta...");
			esperar(3000);
			manoCasa.add(mazo.tomarCarta());
		}
		MyUtil.limpiarConsola();

		System.out.println("==RESULTADO FINAL==");
		System.out.println("---CASA---");
		imprimirMano(manoCasa, false);
		int puntosCasa = calcularPuntaje(manoCasa);
		System.out.println("Puntaje de la casa: " + puntosCasa);

		System.out.println("---" + jugador.getNombre() + "---");
		imprimirMano(manoJugador, false);
		int puntosJugador = calcularPuntaje(manoJugador);
		System.out.println("Tus puntos: " + puntosJugador);
		System.out.println("_________________________________________");

		if (puntosCasa > 21) {
			System.out.println("La casa se paso! GANASTE: " + apuesta);
			jugador.modMonedas((int) (apuesta * jugador.getMultGanancia()));
		} else if (puntosJugador > puntosCasa) {
			System.out.println("Tienes mas puntos que la casa! GANASTE: " + apuesta);
			jugador.modMonedas((int)(apuesta* jugador.getMultGanancia()));
		} else if (puntosJugador < puntosCasa) {
			System.out.println("La casa tiene mas puntos! PERDISTE: " + apuesta);
			jugador.modMonedas(-apuesta);
		} else {
			System.out.println("EMPATE! Recuperas la apuesta.");
		}

	}

	pausar(sc);
	}
	/**
	 * Calcula el puntaje total de una mano de cartas.
	 * Maneja automáticamente el valor del As (11 o 1) para evitar pasarse de 21.
	 * * @param mano Lista de cartas a evaluar.
	 * @return La suma total de puntos.
	 */
	int calcularPuntaje(ArrayList<Carta> mano) {
		int puntaje = 0;
		int ases = 0;
		for (int i = 0; i < mano.size(); i++) {
			puntaje += mano.get(i).getPuntaje();
			if (mano.get(i).esAs()) {
				ases++;
			}
		}
		while (puntaje > 21 && ases > 0) {
			puntaje -= 10;
			ases--;
		}
		return puntaje;
	}
	/**
	 * Imprime las cartas en consola una al lado de la otra (arte ASCII).
	 * * @param mano           Lista de cartas a dibujar.
	 * @param ocultarPrimera Si es true, la primera carta se muestra boca abajo (turno de la casa).
	 */
	public void imprimirMano(ArrayList<Carta> mano, boolean ocultarPrimera) {
		if (mano.isEmpty())
			return;
		int alturaCarta = mano.get(0).getArte().length;
		String[] cartaOculta = { " _______ ", "|?//////|", "|///////|", "|///?///|", "|///////|", "|______?|" };

		for (int i = 0; i < alturaCarta; i++) {
			for (int c = 0; c < mano.size(); c++) {
				if (ocultarPrimera && c == 0) {
					System.out.print(cartaOculta[i] + "   ");
				} else {
					System.out.print(mano.get(c).getArte()[i] + "   ");
				}
			}
			System.out.println();
		}
	}

	private void pausar(Scanner sc) {
		System.out.println("[ Presione ENTER para continuar ]");
		sc.nextLine();
	}

	private void esperar(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e) {

		}
	}
}
