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

public class Tutorial {
	private Jugador jugador;
	private Scanner sc;
	private Controlador controlador;
	
	Tutorial(Jugador jugador, Scanner sc, Controlador controlador) {
		this.controlador = controlador;
		this.jugador = jugador;
		this.sc = sc;
	}

	void iniciarTutorial() throws InterruptedException {
		System.out.println(
				"Bienvenido " + jugador.getNombre() + "." + " \n Aquí es donde comienza tu aventura.");
		Thread.sleep(2000);
		
		System.out.println("Para comenzar deberás unirte a un gremio \n");
		Thread.sleep(2000);
		
		System.out.println("Elije por favor un trabajo: \n");
		Thread.sleep(3000);
		elegirTrabajo();
		controlador.limpiarConsola();

		System.out.println("\nMuy bien " + jugador.getNombre() + "! Ahora te dedicas al trabajo de: /n "
				+ jugador.getTrabajo().getNombre());
		Thread.sleep(500);
		
		System.out.println(
				"En este juego tu objetivo es conseguir dinero y escalar en rangos hasta volverte el top 1 del servidor. \n"
				+ "Para poder realizar acciones debes utilizar el prefix \"/\" seguido de la accion que deseas realizar (sin tildes)");

		System.out.println("\nEmpecemos por los comandos mas básicos. Escribe /trabajar para trabajar y ganar dinero.");
		
		while (true) {
			String input = sc.nextLine().toLowerCase().trim();
			if (input.equals("/trabajar") || input.equals("/t") || input.equals("/work")) {
				jugador.getTrabajo().realizar(jugador, controlador, sc);
				break;
			}
			System.out.println("Comando incorrecto. Escriba /trabajar para realizar un trabajo.");
		}
		
		System.out.println("\nMuy bien hecho! Pro tip, tambien puedes utilizar abreviaciónes como escribir la primera letra (/t)"
				+ " o escribir el comando en inglés (/work).");
		
		System.out.println("\nPuedes ver la lista de comandos utilizando /comandos");
		Thread.sleep(500);
		
		System.out.println(
				"La mayoría de las acciones que realices en este juego tienen un tiempo de espera antes de que las puedas volver a realizar");
		System.out
				.println("Asi que tienes que estar atento para poder volver a ejecutar las acciones apenas termine su cooldown!");

		Thread.sleep(500);
		System.out.print(
				"\nPuedes gastar tu dinero en la tienda para obtener mejoras. Escribe /tienda para acceder a la tienda. > ");
		
		while (true) {
			String tienda = sc.nextLine().toLowerCase().trim();
			if (tienda.equals("/tienda") || tienda.equals("/shop")) {
				break;
			}
			System.out.print("\nComando incorrecto. Escriba /tienda para acceder a la tienda. > ");
		}
		System.out.println(
				"\nAhora dentro de la tienda utiliza el comando \"/comprar [nombre o numero de ítem]\" para comprar un ítem.");
		System.out.print("\n\nPor ahora vamos a comprar una poción de vida menor. Utiliza el comando /comprar pocion. > ");

		while (true) {
			String input = sc.nextLine().toLowerCase();
			if (input.equals("/comprar pocion") || input.equals("/buy pocion") || input.equals("/b pocion")) {
				/// COMANDO COMPRA //PLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE
				/// HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDER
				break;
			}
			System.out.print("\nComando incorrecto. Por favor escriba /comprar pocion. > ");
		}

		System.out.println(
				"\nMuy bien! Acabas de comprar una poción de vida menor. "
				+ "Los items que compres u obtengas se guardarán en tu inventario");
		
		System.out.print("\nUna vez termines de comprar en la tienda utiliza el comando /salir para volver al lobby. >");

		while (true) {
			String input = sc.nextLine().toLowerCase();
			if (input.equals("/salir") || input.equals("/s")) {
				/// COMANDO SALIR //PLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE
				/// HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDER
				break;
			}
			System.out.print("\nComando incorrecto. Por favor escriba /salir. > ");
		}
		
		System.out.println("\nAhora vamos a utilizar la poción que acabas de comprar. "
				+ "\nPara acceder a tu inventario utiliza el comando /inventario. > ");

		while (true) {
			String input = sc.nextLine().toLowerCase();
			if (input.equals("/inventario") || input.equals("/i")) {
				/// COMANDO COMPRA //PLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE
				break;
			}
			System.out.print("\nComando incorrecto. Utiliza /inventario para acceder a tu inventario. >");
		}
		
		System.out.print("\nAhora que puedes ver los items de tu inventario puedes utilizar \"/usar [prefix o nombre]\" para equipar o utilizar el objeto que deseas \n"
				+ "\no Utiliza la pocion de vida que acabas de comprar. Para esto escribe \"/usar pocion\" o \"/usar 1\". > ");

		while (true) {
			String input = sc.nextLine().toLowerCase();
			if (input.equals("/usar pocion") || input.equals("/u pocion") || input.equals("/u 1") || input.equals("/usar 1")) {
				/// COMANDO COMPRA //PLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE
				/// HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDER
				break;
			}
			System.out.print("\nComando incorrecto. Por favor escriba /usar pocion. > ");
		}

		System.out.println("\nAcabas de utilizar la poción de vida menor y recuperaste tu vida al máximo");
		System.out.println("Para ver tus estadísticas y vida utiliza el comando /estado");
		
		while (true) {
			String estado = sc.nextLine().toLowerCase();
			if (estado.equals("/estado") || estado.equals("/e")) {
				/// COMANDO ESTADO //PLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE
				/// HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDERPLACE HOLDER
				break;
			}
			System.out.println("Comando incorrecto. Por favor escriba /estado");
		}
		
		jugador.modExp(10);
		System.out.println("TUTORIAL FINALIZADO");
		
		
	}

	
	void elegirTrabajo() throws InterruptedException {
	    
	    //Instanciar los trabajos
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
	    controlador.limpiarConsola();
	    System.out.println("Los trabajos disponibles son: ");
	    for (String key : trabajosDisponibles.keySet()) {
	        System.out.println("---------"+key+"---------\n" + trabajosDisponibles.get(key).getNombre());
	        Thread.sleep(1000); // Pausa entre cada trabajo
	    }

	    
	    while (true) {
	        System.out.print("\nPor favor elija el trabajo que desea realizar escribiendo su prefix (1, 2, 3...) > ");
	        String choise = sc.nextLine().trim();

	        if (choise.equals("67")) { // Easter egg
	        	System.out.println("67…mustard BOIII TS SO TUFF diddy mango 67 phonk lord mustard blud: SIX SEVENNN!!\n"
	        	+ " BOIII DONT MESS WITH THE 67 MANGO MUSTARD GANG OR WE’LL STEAL YOUR MANGOS😂😂😂😂\n"
	        	+ " mustard is 7 letters… mangos is 6…. 67? TS SO TUFF😂😂😂😂 MANGO MANGO MUSTAAAAAAAARD🥭🥭🥭🥭\n");
	            Thread.sleep(2000);
	            
	        } else if (trabajosDisponibles.containsKey(choise)) {//Si es valida la eleccion se elige el trabajo
	            Trabajar trabajoSeleccionado = trabajosDisponibles.get(choise);
	            
	            //llama a la funcion de confirmación
	            if (confirmarTrabajo(trabajoSeleccionado, jugador, sc)) {
	                return;
	            }
	            // Si devuelve false, el jugador dijo "no" y se repite el bucle
	            
	        } else {
	            System.out.println("Opcion inválida. Por favor ingrese un número válido.");
	            Thread.sleep(1000);
	        }

	        // Volver a mostrar la lista (solo si el jugador dijo "no" o se equivocó)
	        controlador.limpiarConsola();
	        System.out.println("Los trabajos disponibles son: ");
	        for (String key : trabajosDisponibles.keySet()) {
	            System.out.println("---------"+key+"---------\n" + trabajosDisponibles.get(key).getNombre());
	            System.out.println();
	        }
	    }
	}
	private boolean confirmarTrabajo(Trabajar trabajo, Jugador jugador, Scanner sc) {
	    System.out.println("Has seleccionado el trabajo:\n" + trabajo.getNombre());
	    System.out.println("Descripción:\n" + trabajo.getDescriptionT());
	    System.out.println();
	    System.out.println(
	            "¿Está seguro de elegir este oficio?\n \nRecuerde que esta accion es única y no se podra cambiar en el futuro!");
	    System.out.println();

	    while (true) {
	        System.out.println("Escriba YES para confirmar o NO para modificar su elección");
	        String conf = sc.nextLine().toLowerCase().trim(); //Input normalizado
	        if (conf.equals("yes") || conf.equals("y")) {
	            jugador.setTrabajo(trabajo);
	            return true; 
	        }
	        if (conf.equals("no")) {
	            return false;
	        }
	    }
	}


}
