package Tests;

import java.util.Scanner;

public class FlujoPrincipal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Jugador jugadorActual = new Jugador("Paralelepípedo");
		Controlador controlador = new Controlador(jugadorActual);
		boolean enJuego = true;
		String input = " ";
		System.out.println("Bienvenid@!, el juego empieza aquí \n");
		System.out.println("Puedes escribir \"/explorar\" para hacer tu primera exploración \n o puedes escribir \"/comandos\" para guiarte");
		
		while(enJuego) {
			input = sc.nextLine();
			controlador.limpiarConsola();
			enJuego = controlador.procesarInput(input);
		}
			
		sc.close();
		System.out.println("Se ha cerrado el juego");
	}

}
