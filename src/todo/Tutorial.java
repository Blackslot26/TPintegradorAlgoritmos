package todo;

import java.util.Scanner;

import trabajos.Aristocrata;
import trabajos.Ladron;
import trabajos.Mercader;
import trabajos.Leñador;
import trabajos.Mercenario;
import trabajos.Minero;
import trabajos.Tahur;

public class Tutorial {
	Jugador jugador;
	Scanner sc;
	Controlador tutorial;
	
	Tutorial(Jugador jugador) {
		this.jugador = jugador;
		this.sc = new Scanner(System.in);
	}

	void iniciarTutorial() throws InterruptedException {
		System.out.println(
				"Bienvenido " + jugador.getNombre() + " a ------." + " \n Aqui es donde comienza tu aventura.");
		Thread.sleep(2000);
		System.out.println("Para comenzar deberas unirte a un gremio");
		System.out.println();
		Thread.sleep(2000);
		System.out.println("Elije por favor un trabajo dentro de los trabajos disponibles."
				+ "\n"
				+ "\nRecuerda que esta accion es unica y no podras modificar tu trabajo en el futuro.");
		Thread.sleep(3000);
		elegirTrabajo();
		System.out.println();
		System.out.println("Muy bien " + jugador.getNombre() + "! Felicidades por seleccionar el trabajo\n"
				+ jugador.getTrabajo().getNombre());
		Thread.sleep(500);
		System.out.println();
		System.out.println(
				"En este juego tu objetivo es conseguir dinero y escalar en rangos hasta volverte el top 1 del servidor.");
		System.out.println();
		System.out.println(
				"Para poder realizar acciones debes utilizar el prefix \"/\" seguido de la accion que deseas realizar");
		System.out.println();
		System.out.println("Empecemos por los comandos mas basicos. Escribe /trabajar\npara trabajar y ganar dinero.");
		while (true) {
			String trabajar = sc.nextLine().toLowerCase();
			if (trabajar.equals("/trabajar") || trabajar.equals("/t") || trabajar.equals("/work")) {
				jugador.getTrabajo().realizar(jugador, tutorial);
				break;
			}
			System.out.println("Comando incorrecto. Escriba /trabajar para realizar un trabajo.");
		}
		System.out.println();
		System.out.println(
				"Muy bien hecho! Pro tip, tambien puedes utilizar abreviaciónes como escribir la primera letra (/t) o escribir el comando en ingles (/work).");
		System.out.println("Puedes ver la lista de abreviaciones utilizando el comando /comandos");
		System.out.println();
		Thread.sleep(500);
		System.out.println(
				"La mayoria de las acciones que realices en este juego tienen un tiempo de espera antes de que las puedas volver a realizar");
		System.out
				.println("Asi que estate atento para poder volver a ejecutar las acciones apenas termine su cooldown!");
		System.out.println();
		Thread.sleep(500);
		System.out.println(
				"Puedes gastar tu dinero en la tienda para obtener mejoras. Escribe /tienda para acceder a la tienda.");
		while (true) {
			String tienda = sc.nextLine().toLowerCase();
			if (tienda.equals("/tienda") || tienda.equals("/t")) {
				/// COMANDO TIENDA //PLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE
				/// HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDER
				break;
			}
			System.out.println("Comando incorrecto. Escriba /tienda para acceder a la tienda.");
		}
		System.out.println(
				"Ahora dentro de la tienda utiliza el comando \"/comprar [nombre del item o numero en la tienda]\" para comprar un item.");
		System.out.println("Por ahora vamos a comprar una pocion de vida menor. Utiliza el comando /comprar Pocion"); // PLACE
																														// HOLDER

		while (true) {
			String compra = sc.nextLine().toLowerCase();
			if (compra.equals("/comprar pocion") || compra.equals("/buy pocion") || compra.equals("/b pocion")) {
				/// COMANDO COMPRA //PLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE
				/// HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDER
				break;
			}
			System.out.println("Comando incorrecto. Por favor escriba /comprar Pocion");
		}

		System.out.println("");
		System.out.println(
				"Muy bien! Acabas de comprar una pocion de vida menor. Los items que compres u obtengas se guardaran en tu inventario");
		System.out.println("");
		System.out.println("Una vez termines de comprar en la tienda utiliza el comando /salir");
		System.out.println("Usa el comando /salir para volver al estado principal");
		while (true) {
			String salir = sc.nextLine().toLowerCase();
			if (salir.equals("/salir") || salir.equals("/s")) {
				/// COMANDO SALIR //PLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE
				/// HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDER
				break;
			}
			System.out.println("Comando incorrecto. Por favor escriba /salir.");
		}
		System.out.println("");
		System.out.println("Ahora vamos a utilizar la pocion que acabas de comprar.");
		System.out.println("Para acceder a tu inventario utiliza el comando /inventario");
		while (true) {
			String inventario = sc.nextLine().toLowerCase();
			if (inventario.equals("/inventario") || inventario.equals("/i")) {
				/// COMANDO COMPRA //PLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE
				
				break;
			}
			System.out.println("Comando incorrecto. Utiliza /inventario para acceder a tu inventario.");
		}
		System.out.println("");
		System.out.println("Ahora que puedes ver los items de tu inventario puedes utilizar \"/usar [prefix o nombre]\" para equipar o utilizar el objeto desesado");
		System.out.println("Utiliza la pocion de vida que acabas de comprar. Para esto escribe \"/usar pocion\" o \"/usar 1\"");
		while (true) {
			String usar = sc.nextLine().toLowerCase();
			if (usar.equals("/usar pocion") || usar.equals("/u pocion") || usar.equals("/u 1") || usar.equals("/usar 1")) {
				/// COMANDO COMPRA //PLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE
				/// HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDER
				break;
			}
			System.out.println("Comando incorrecto. Por favor escriba /usar Pocion");
		}
		System.out.println("");
		System.out.println("Acabas de utilizar la pocion de vida menor y recuperaste tu vida al maximo");
		System.out.println("Para ver tus estadisticas y vida utiliza el comando /estado");
		while (true) {
			String estado = sc.nextLine().toLowerCase();
			if (estado.equals("/estado") || estado.equals("/e")) {
				/// COMANDO ESTADO //PLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE
				/// HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDER
				break;
			}
			System.out.println("Comando incorrecto. Por favor escriba /estado");
		}
		
		jugador.setExperiencia(10);
		System.out.println("TUTORIAL FINALIZADO");
		
		
	}

	
	
	
	void elegirTrabajo() throws InterruptedException {
		Trabajar leñador = new Leñador();
		Trabajar minero = new Minero();
		Trabajar mercader = new Mercader();
		Trabajar mercenario = new Mercenario();
		Trabajar aristocrata = new Aristocrata();
		Trabajar ladron = new Ladron();
		Trabajar tahur = new Tahur();
		
		int longTrabajos = 1000;
		limpiarConsola();
		System.out.println("Los trabajos disponibles son: ");

		Thread.sleep(longTrabajos);
		System.out.println("---------1---------\n" + leñador.getNombre());
		System.out.println();
		
		Thread.sleep(longTrabajos);
		System.out.println("---------2---------\n" + minero.getNombre());
		System.out.println();
		
		Thread.sleep(longTrabajos);
		System.out.println("---------3---------\n" + mercader.getNombre());
		System.out.println();
		
		Thread.sleep(longTrabajos);
		System.out.println("---------4---------\n" + mercenario.getNombre());
		System.out.println();
		
		Thread.sleep(longTrabajos);
		System.out.println("---------5---------\n" + aristocrata.getNombre());
		System.out.println();
		
		Thread.sleep(longTrabajos);
		System.out.println("---------6---------\n" + ladron.getNombre());
		System.out.println();
		
		Thread.sleep(longTrabajos);
		System.out.println("---------7---------\n" + tahur.getNombre());
		System.out.println();
		
		Thread.sleep(longTrabajos);
		
		
		
		while (true) {
			System.out.println("Por favor elija el trabajo que desea realizar escribiendo su prefix (1, 2, 3...)");
			
			String choise = sc.nextLine();

			switch (choise) {

			case "1":
				System.out.println("As seleccionado el trabajo:\n" + leñador.getNombre());
				System.out.println("Descripcion:\n" + leñador.getDescripcion());
				System.out.println();
				System.out.println(
						"¿Esta seguro de elegir este oficio?\n Recuerde que esta accion es unica y no se podra cambiar en el futuro!");
				System.out.println();
				while (true) {
					System.out.println("Escriba YES para confirmar o NO para modificar su eleccion");
					String conf = sc.nextLine().toLowerCase().replace(" ", "");
					if (conf.equals("yes")) {
						jugador.setTrabajo(leñador);
						return;
					}
					if (conf.equals("no")) {
						break;
					}
				}
				break;
				
			case "2":
				System.out.println("As seleccionado el trabajo:\n" + minero.getNombre());
				System.out.println("Descripcion:\n" + minero.getDescripcion());
				System.out.println();
				System.out.println(
						"¿Esta seguro de elegir este oficio?\n Recuerde que esta accion es unica y no se podra cambiar en el futuro!");
				System.out.println();
				while (true) {
					System.out.println("Escriba YES para confirmar o NO para modificar su eleccion");
					String conf = sc.nextLine().toLowerCase().replace(" ", "");
					if (conf.equals("yes")) {
						jugador.setTrabajo(minero);
						return;
					}
					if (conf.equals("no")) {
						break;
					}
				}
				break;
			case "3":
				System.out.println("As seleccionado el trabajo:\n" + mercader.getNombre());
				System.out.println("Descripcion:\n" + mercader.getDescripcion());
				System.out.println();
				System.out.println(
						"¿Esta seguro de elegir este oficio?\n Recuerde que esta accion es unica y no se podra cambiar en el futuro!");
				System.out.println();
				while (true) {
					System.out.println("Escriba YES para confirmar o NO para modificar su eleccion");
					String conf = sc.nextLine().toLowerCase().replace(" ", "");
					if (conf.equals("yes")) {
						jugador.setTrabajo(mercader);
						return;
					}
					if (conf.equals("no")) {
						break;
					}
				}
				break;
			case "4":
				System.out.println("As seleccionado el trabajo:\n" + mercenario.getNombre());
				System.out.println("Descripcion:\n" + mercenario.getDescripcion());
				System.out.println();
				System.out.println(
						"¿Esta seguro de elegir este oficio?\n Recuerde que esta accion es unica y no se podra cambiar en el futuro!");
				System.out.println();
				while (true) {
					System.out.println("Escriba YES para confirmar o NO para modificar su eleccion");
					String conf = sc.nextLine().toLowerCase().replace(" ", "");
					if (conf.equals("yes")) {
						jugador.setTrabajo(mercenario);
						return;
					}
					if (conf.equals("no")) {
						break;
					}
				}
				break;
			case "5":
				System.out.println("As seleccionado el trabajo:\n" + aristocrata.getNombre());
				System.out.println("Descripcion:\n" + aristocrata.getDescripcion());
				System.out.println();
				System.out.println(
						"¿Esta seguro de elegir este oficio?\n Recuerde que esta accion es unica y no se podra cambiar en el futuro!");
				System.out.println();
				while (true) {
					System.out.println("Escriba YES para confirmar o NO para modificar su eleccion");
					String conf = sc.nextLine().toLowerCase().replace(" ", "");
					if (conf.equals("yes")) {
						jugador.setTrabajo(aristocrata);
						return;
					}
					if (conf.equals("no")) {
						break;
					}
				}
				break;
			case "6":
				System.out.println("As seleccionado el \"trabajo\":\n" + ladron.getNombre());
				System.out.println("Descripcion:\n" + ladron.getDescripcion());
				System.out.println();
				System.out.println(
						"¿Esta seguro de elegir este oficio?\n Recuerde que esta accion es unica y no se podra cambiar en el futuro!");
				System.out.println();
				while (true) {
					System.out.println("Escriba YES para confirmar o NO para modificar su eleccion");
					String conf = sc.nextLine().toLowerCase().replace(" ", "");
					if (conf.equals("yes")) {
						jugador.setTrabajo(ladron);
						return;
					}
					if (conf.equals("no")) {
						break;
					}
				}
				break;
			case "7":
				System.out.println("A seleccionado el trabajo:\n" + tahur.getNombre());
				System.out.println("Descripcion:\n" + tahur.getDescripcion());
				System.out.println();
				System.out.println(
						"¿Esta seguro de elegir este oficio?\n Recuerde que esta accion es unica y no se podra cambiar en el futuro!");
				System.out.println();
				while (true) {
					System.out.println("Escriba YES para confirmar o NO para modificar su eleccion");
					String conf = sc.nextLine().toLowerCase().replace(" ", "");
					if (conf.equals("yes")) {
						jugador.setTrabajo(tahur);
						return;
					}
					if (conf.equals("no")) {
						break;
					}
				}
				break;

			case "67": // easter egg.....
				System.out.println("67…mustard BOIII TS SO TUFF diddy mango 67 phonk lord mustard blud: SIX SEVENNN!!\n"
						+ " BOIII DONT MESS WITH THE 67 MANGO MUSTARD GANG OR WE’LL STEAL YOUR MANGOS😂😂😂😂\n"
						+ " mustard is 7 letters… mangos is 6…. 67? TS SO TUFF😂😂😂😂 MANGO MANGO MUSTAAAAAAAARD🥭🥭🥭🥭");
				System.out.println();
				Thread.sleep(2000);
				break;
			default:
				System.out.println("Opcion invalida. Por favor ingrese un numero valido.");
				Thread.sleep(1000);
			}
			limpiarConsola();
			System.out.println("Los trabajos disponibles son: ");

			System.out.println("---------1---------\n" + leñador.getNombre() + "\n---------2---------\n" + minero.getNombre() + "\n---------3---------\n" + mercader.getNombre()
			+ "\n---------4---------\n" + mercenario.getNombre() + "\n---------5---------\n" + aristocrata.getNombre() + "\n---------6---------\n" + ladron.getNombre() + "\n---------7---------\n" + tahur.getNombre());
			System.out.println();

			
			
			
		}
	}
	void limpiarConsola() {
	    for (int i = 0; i < 50; i++) {
	        System.out.println();
	    }
	}

}
