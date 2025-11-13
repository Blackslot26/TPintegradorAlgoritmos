package todo;

import java.util.HashMap;
import java.util.Scanner;

import acciones.Trabajar;
import trabajos.Aristocrata;
import trabajos.Ladron;
import trabajos.Mercader;
import trabajos.Leñador;
import trabajos.Mercenario;
import trabajos.Minero;
import trabajos.Tahur;
import utiles.MyUtil; // Asegúrate de que MyUtil tenga los ANSI_GREEN y ANSI_RESET
import utiles.Titulos;

public class Tutorial {
	private Jugador jugador;
	private Scanner sc;

	Tutorial(Jugador jugador, Scanner sc) {
		this.jugador = jugador;
		this.sc = sc;
	}

	void iniciarTutorial() throws InterruptedException {
		System.out.println("Bienvenido " + jugador.getNombre() + "." + " \nAquí es donde comienza tu aventura.");
		Thread.sleep(2000);

		System.out.println("Para comenzar deberás unirte a un gremio \n");
		Thread.sleep(2000);

		System.out.println("Elije por favor un trabajo: \n");
		Thread.sleep(2000);
		elegirTrabajo();
		MyUtil.limpiarConsola();

		System.out.println("\nMuy bien " + jugador.getNombre() + "! Ahora te dedicas al trabajo de: \n"
				+ jugador.getTrabajo().getNombre());
		Thread.sleep(500);

		System.out.println(
				"En este juego tu objetivo es conseguir dinero y escalar en rangos hasta volverte el top 1 del servidor. \n"
						+ "\nPara poder realizar acciones debes utilizar el prefix " + MyUtil.ANSI_GREEN + "\"/\""
						+ MyUtil.ANSI_RESET + " seguido de la accion que deseas realizar (sin tildes).");

		System.out.print("\nEmpecemos por los comandos mas básicos. Escribe " + MyUtil.ANSI_GREEN + "/trabajar"
				+ MyUtil.ANSI_RESET + " para trabajar y ganar dinero. " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);

		while (true) {
			String input = sc.nextLine().toLowerCase().trim();
			if (input.equals("/trabajar") || input.equals("/t") || input.equals("/work")) {
				MyUtil.limpiarConsola();
				System.out.println();
				jugador.getTrabajo().realizar(jugador, sc);
				break;
			}
			System.out.print("Comando incorrecto. Escriba " + MyUtil.ANSI_GREEN + "/trabajar" + MyUtil.ANSI_RESET
					+ " para realizar un trabajo. " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);
		}

		System.out.println(
				"\nMuy bien hecho!\nPro tip, también puedes utilizar abreviaciónes como escribir la primera letra ("
						+ MyUtil.ANSI_GREEN + "/t" + MyUtil.ANSI_RESET + ")" + " o escribir el comando en inglés ("
						+ MyUtil.ANSI_GREEN + "/work" + MyUtil.ANSI_RESET + ").");

		System.out.println("\nPuedes ver la lista de comandos utilizando " + MyUtil.ANSI_GREEN + "/comandos"
				+ MyUtil.ANSI_RESET + ".");
		Thread.sleep(500);

		System.out.println(
				"\nLa mayoría de las acciones que realices en este juego tienen un tiempo de espera antes de que las puedas realizar nuevamente");
		System.out.println(
				"Así que tienes que estar atento para poder volver a ejecutar las acciones apenas termine su cooldown!");

		Thread.sleep(500);
		System.out.print("\nPuedes gastar tu dinero en la tienda para obtener mejoras. Escribe " + MyUtil.ANSI_GREEN
				+ "/tienda" + MyUtil.ANSI_RESET + " para acceder a la tienda. " + MyUtil.ANSI_GREEN + "> "
				+ MyUtil.ANSI_RESET);

		while (true) {
			String tienda = sc.nextLine().toLowerCase().trim();
			if (tienda.equals("/tienda") || tienda.equals("/shop")) {
				MyUtil.limpiarConsola();
				break;
			}

			System.out.print("\nComando incorrecto. Escriba " + MyUtil.ANSI_GREEN + "/tienda" + MyUtil.ANSI_RESET
					+ " para acceder a la tienda. " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);
		}

		System.out.println("\nAhora dentro de la tienda utiliza el comando \"" + MyUtil.ANSI_GREEN
				+ "/comprar [nombre o numero de ítem]" + MyUtil.ANSI_RESET + "\" para comprar un ítem.");
		System.out.print("\nPor ahora vamos a comprar una poción de vida menor. Utiliza el comando " + MyUtil.ANSI_GREEN
				+ "/comprar pocion" + MyUtil.ANSI_RESET + ". " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);

		while (true) {
			String input = sc.nextLine().toLowerCase().trim();
			if (input.equals("/comprar pocion") || input.equals("/buy pocion") || input.equals("/b pocion")) {
				MyUtil.limpiarConsola();
				break;
			}
			System.out.print("\nComando incorrecto. Por favor escriba " + MyUtil.ANSI_GREEN + "/comprar pocion"
					+ MyUtil.ANSI_RESET + ". " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);
		}

		System.out.println("\nMuy bien! Acabas de comprar una poción de vida menor. "
				+ "Los items que compres u obtengas se guardarán en tu inventario.");

		System.out.print("\nUna vez termines de comprar en la tienda utiliza el comando " + MyUtil.ANSI_GREEN + "/salir"
				+ MyUtil.ANSI_RESET + " para volver al lobby. " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);

		while (true) {
			String input = sc.nextLine().toLowerCase().trim();
			if (input.equals("/salir") || input.equals("/s")) {
				MyUtil.limpiarConsola();
				Titulos.mostrarTituloLobby();
				break;
			}
			System.out.print("\nComando incorrecto. Por favor escriba " + MyUtil.ANSI_GREEN + "/salir"
					+ MyUtil.ANSI_RESET + ". " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);
		}

		System.out.print("\nAhora vamos a utilizar la poción que acabas de comprar. "
				+ "\nPara acceder a tu inventario utiliza el comando " + MyUtil.ANSI_GREEN + "/inventario"
				+ MyUtil.ANSI_RESET + ". " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);

		while (true) {
			String input = sc.nextLine().toLowerCase().trim();
			if (input.equals("/inventario") || input.equals("/i")) {
				MyUtil.limpiarConsola();
				break;
			}
			System.out.print("\nComando incorrecto. Utiliza " + MyUtil.ANSI_GREEN + "/inventario" + MyUtil.ANSI_RESET
					+ " para acceder a tu inventario. " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);
		}

		System.out.print("\nAhora que puedes ver los items de tu inventario puedes utilizar \"" + MyUtil.ANSI_GREEN
				+ "/usar [prefix o nombre]" + MyUtil.ANSI_RESET + "\" para equipar o utilizar el objeto que deseas \n"
				+ "\no Utiliza la pocion de vida que acabas de comprar. Para esto escribe \"" + MyUtil.ANSI_GREEN
				+ "/usar pocion" + MyUtil.ANSI_RESET + "\" o \"" + MyUtil.ANSI_GREEN + "/usar 1" + MyUtil.ANSI_RESET
				+ "\". " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);

		while (true) {
			String input = sc.nextLine().toLowerCase().trim();
			if (input.equals("/usar pocion") || input.equals("/u pocion") || input.equals("/u 1")
					|| input.equals("/usar 1")) {
				MyUtil.limpiarConsola();
				break;
			}
			System.out.print("\nComando incorrecto. Por favor escriba " + MyUtil.ANSI_GREEN + "/usar pocion"
					+ MyUtil.ANSI_RESET + ". " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);
		}

		System.out.println("\nAcabas de utilizar la poción de vida menor y recuperaste tu vida al máximo");
		System.out.print("Para ver tus estadísticas y vida utiliza el comando " + MyUtil.ANSI_GREEN + "/estado"
				+ MyUtil.ANSI_RESET + ". " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);

		while (true) {
			String estado = sc.nextLine().toLowerCase().trim();
			if (estado.equals("/estado") || estado.equals("/e")) {
				MyUtil.limpiarConsola();
				break;
			}
			System.out.print("\nComando incorrecto. Por favor escriba " + MyUtil.ANSI_GREEN + "/estado"
					+ MyUtil.ANSI_RESET + ". " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);
		}
		jugador.mostrarEstadoJugador();
		jugador.modExp(10);
		System.out.print("Muy bien, terminamos por ahora " + MyUtil.ANSI_GREEN + "[Enter para continuar]"
				+ MyUtil.ANSI_RESET + ".");
		sc.nextLine();
	}

	void elegirTrabajo() throws InterruptedException {

		// Instanciar los trabajos
		Trabajar leñador = new Leñador();
		Trabajar minero = new Minero();
		Trabajar mercader = new Mercader();
		Trabajar mercenario = new Mercenario();
		Trabajar aristocrata = new Aristocrata();
		Trabajar ladron = new Ladron();
		Trabajar tahur = new Tahur();

		// mapear los trabajos añadiendoles un numero
		HashMap<String, Trabajar> trabajosDisponibles = new HashMap<>();
		trabajosDisponibles.put("1", leñador);
		trabajosDisponibles.put("2", minero);
		trabajosDisponibles.put("3", mercader);
		trabajosDisponibles.put("4", mercenario);
		trabajosDisponibles.put("5", aristocrata);
		trabajosDisponibles.put("6", ladron);
		trabajosDisponibles.put("7", tahur);

		// Mostrar los trabajos con animación
		MyUtil.limpiarConsola();
		System.out.println("Los trabajos disponibles son: ");
		for (String key : trabajosDisponibles.keySet()) {
			System.out.println("---------" + key + "---------\n" + trabajosDisponibles.get(key).getNombre());
			Thread.sleep(1000); // Pausa entre cada trabajo
		}

		while (true) {
			System.out.print("\nPor favor elija el trabajo que desea realizar escribiendo su prefix (1, 2, 3...) "
					+ MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);
			String choise = sc.nextLine().trim();

			if (choise.equals("67")) { // Easter egg
				System.out.println("67…mustard BOIII TS SO TUFF diddy mango 67 phonk lord mustard blud: SIX SEVENNN!!\n"
						+ " BOIII DONT MESS WITH THE 67 MANGO MUSTARD GANG OR WE’LL STEAL YOUR MANGOS😂😂😂😂\n"
						+ " mustard is 7 letters… mangos is 6…. 67? TS SO TUFF😂😂😂😂 MANGO MANGO MUSTAAAAAAAARD🥭🥭🥭🥭\n");
				Thread.sleep(2000);

			} else if (trabajosDisponibles.containsKey(choise)) {// Si es valida la eleccion se elige el trabajo
				Trabajar trabajoSeleccionado = trabajosDisponibles.get(choise);

				// llama a la funcion de confirmación
				if (confirmarTrabajo(trabajoSeleccionado, jugador, sc)) {
					return;
				}
				// Si devuelve false, el jugador dijo "no" y se repite el bucle

			} else {
				System.out.println("Opcion inválida. Por favor ingrese un número válido.");
				Thread.sleep(1000);
			}

			// Volver a mostrar la lista (solo si el jugador dijo "no" o se equivocó)
			MyUtil.limpiarConsola();
			System.out.println("Los trabajos disponibles son: ");
			for (String key : trabajosDisponibles.keySet()) {
				System.out.println("---------" + key + "---------\n" + trabajosDisponibles.get(key).getNombre());
				System.out.println();
			}
		}
	}

	private boolean confirmarTrabajo(Trabajar trabajo, Jugador jugador, Scanner sc) {
		MyUtil.limpiarConsola();
		System.out.println("Has seleccionado el trabajo:\n" + trabajo.getNombre());
		System.out.println("Descripción:\n" + trabajo.getDescriptionT());
		System.out.println();
		System.out.println(
				"¿Está seguro de elegir este oficio?\n \nRecuerde que esta accion es única y no se podrá cambiar en el futuro!");
		System.out.println();

		while (true) {
			System.out.print("Escriba " + MyUtil.ANSI_GREEN + "si" + MyUtil.ANSI_RESET + " para confirmar o "
					+ MyUtil.ANSI_GREEN + "no" + MyUtil.ANSI_RESET + " para modificar su elección. " + MyUtil.ANSI_GREEN
					+ "> " + MyUtil.ANSI_RESET);
			String conf = sc.nextLine().toLowerCase().trim(); // Input normalizado
			if (conf.equals("si") || conf.equals("yes") || conf.equals("y")) {
				jugador.setTrabajo(trabajo);
				return true;
			} else if (conf.equals("no") || conf.equals("n")) {
				return false;
			}
		}
	}

}