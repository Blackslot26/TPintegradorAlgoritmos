package todo;

import java.util.Scanner;

public class FlujoPrincipal {
	static Scanner sc;
	static GestorPartidas gm;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		Jugador jugadorActual = logIn();   //logIn retorna el jugador logueado, si este no existe se crea uno nuevo
											//luego se crea el controlador e inicia el bucle while principal
		Controlador controlador = new Controlador(jugadorActual);
		boolean enJuego = true;
		
		
		String input = " ";
		System.out.println("Bienvenid@!, el juego empieza aquí \n");
		System.out.println(
				"Puedes escribir \"/explorar\" para hacer tu primera exploración \n o puedes escribir \"/comandos\" para guiarte");
		
		while (enJuego) {
			input = sc.nextLine();
			controlador.limpiarConsola();
			enJuego = controlador.procesarInput(input);
		}
		
		
		GestorPartidas.guardarPartida(jugadorActual);			//GUARDAR LA PARTIDA AL ROMPER EL BUCLE WHILE CUANDO enJuego SE VUELVE FALSE
		
		System.out.println("Se ha cerrado el juego");
	}

	public static Jugador logIn() {
		System.out.println("Bienvenid@!, por favor escribe tu nombre de jugador para loguearte \n");
		while (true) {	
			String player = sc.nextLine();
			System.out.println("Esta seguro de que su nombre es: " + player + "?   yes/no");
			String choise = sc.nextLine();
			choise = choise.toLowerCase().replace(" ", "");
			if(choise.equals("yes")) {
				return GestorPartidas.cargarPartida(player);
			}else { 
				System.out.println("Por favor ingrese su nombre: ");
			}
		}
	}

	public static void tutorial() {

	}

}
