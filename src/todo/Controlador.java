package todo;

import java.util.HashMap;
import java.util.Scanner;

public class Controlador {
	Jugador jugadorActual;
	private HashMap<String,Accion> accionesMapeadas;
	private Scanner sc;
	
	public Controlador(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;

		sc = new Scanner(System.in);
		
		accionesMapeadas = new HashMap<>();
		this.accionesMapeadas.put("/explorar",new AccionExplorar());
		this.accionesMapeadas.put("/tienda", new AccionTienda());
		
	};
	
	
	//Procesar la entrada y devolver si continúa jugando o no.
	public boolean procesarInput(String input) {
		input = input.toLowerCase().replace(" ", ""); //sacar las mayúscula y espacios
		
		//Comandos especiales
		switch (input) {
		case "/leaderboard":
			Leaderboard.update(jugadorActual);
			Leaderboard.mostrarLeaderboard();
			break;
		
		case "/money":
			System.out.println("Agregando 10 de dinero");
			money();
			break;
		
		case "/salir":
			System.out.println("Saliendo...");
			return false;
			
		case "/comandos":
			listarComandos();
			break;
		
		case "/dibujar":
			dibujar();
			break;
			
		default: 
			Accion accion = accionesMapeadas.get(input);
			
			if(accion != null) {
				accion.realizar(jugadorActual, this);
			} else System.out.printf("No se reconoció el comando %s :( \n ",input);
		}


		return true;
	}
	
	//Listar todas las acciones
	private void listarComandos() {
		System.out.println("Comandos disponibles: ");
	
		//Imprimir comandos especiales
		System.out.println("/salir -> Termina el juego.");
		System.out.println("/comandos -> Muestra la lista de comandos disponibles");
		System.out.println("/money -> añade 10 de dinero al jugador (temporal para testeos de leaderboard)");
		System.out.println("/leaderboard -> muestra el top global de jugadores de esta computadora");
		
		for(String c: accionesMapeadas.keySet()) {
			System.out.printf("%s -> *Inserte Descripción*. \n",c);
		}
	}
	
	/**
	 * Simula la limpieza de la consola imprimiendo muchas líneas nuevas.
	 */
	public void limpiarConsola() {
	    for (int i = 0; i < 50; i++) {
	        System.out.println();
	    }
	    System.out.println("---------------------------------------------");
	}
	
	private void dibujar() {
		System.out.println("██████████████████████████████");
         System.out.printf("█%-14s█   _     _   █\n",jugadorActual.nombre);
         System.out.printf("█%-14d█  |*|   |*|  █\n",jugadorActual.vida);
         System.out.printf("█%-14d█      l      █\n",jugadorActual.nivel);
        System.out.println("█              █  |_______|  █");
        System.out.println("█              █             █");
        System.out.println("█              ███████████████");
        System.out.println("█                            █");
        System.out.println("█                            █");
        System.out.println("█                            █");
        System.out.println("█                            █");
        System.out.println("█                            █");
        System.out.println("█                            █");
        System.out.println("█                            █");
        System.out.println("█                            █");
        System.out.println("█                            █");
		System.out.println("██████████████████████████████");
	}
	
	
	private void money() {
		jugadorActual.monedas = jugadorActual.monedas+10;
	}
}
