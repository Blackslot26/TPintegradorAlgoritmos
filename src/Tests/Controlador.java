package Tests;

import java.util.HashMap;

public class Controlador {
	Jugador jugadorActual;
	private HashMap<String,Accion> accionesMapeadas;
	
	public Controlador(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;

		accionesMapeadas = new HashMap<>();
		this.accionesMapeadas.put("/explorar",new AccionExplorar());
		this.accionesMapeadas.put("/tienda", new AccionTienda());
	};
	
	
	//Procesar la entrada y devolver si continúa jugando o no.
	public boolean procesarInput(String input) {
		input = input.toLowerCase(); //sacar las mayúscula
		
		//Comandos especiales
		switch (input) {
		
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
		System.out.println("████████████████             █");
        System.out.println("               █             █");
        System.out.println("               █             █");
        System.out.println("               █             █");
        System.out.println("               █             █");
        System.out.println("               █             █");
        System.out.println("██████████████████████████████");
        System.out.println("█              █              ");
        System.out.println("█              █              ");
        System.out.println("█              █              ");
        System.out.println("█              █              ");
        System.out.println("█              █              ");
        System.out.println("█              ███████████████");
		
	}
}
