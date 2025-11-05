package todo;

import java.util.Scanner;

public class FlujoPrincipal {
	static Scanner sc;
	static GestorPartidas gm;

	public static void main(String[] args) {
		//Intancia de clases y variables necesarias.
		sc = new Scanner(System.in);
		Jugador jugadorActual = logIn();   //logIn retorna el jugador logueado, si este no existe se crea uno nuevo
		Controlador controlador = new Controlador(jugadorActual);
		boolean enJuego = true;
		String input = "";

		
		//Bucle Principal
		while(enJuego) {
			jugadorActual.actualizar();;
			controlador.limpiarConsola();
			jugadorActual.mostrarEstadoJugador();
			input = sc.nextLine(); //Ingresa Imput.
			enJuego = controlador.procesarInput(input); //Verifica si sigue en juego o no.
		}
		
		
		GestorPartidas.guardarPartida(jugadorActual);//GUARDAR LA PARTIDA AL ROMPER EL BUCLE WHILE CUANDO enJuego SE VUELVE FALSE
		
		System.out.println("Se ha cerrado el juego");
		//Fin del Juego.
	}

	public static Jugador logIn() {
		System.out.println("Bienvenid@!, por favor escribe tu nombre de jugador para loguearte \n");
		while (true) {	
			String player = sc.nextLine();
			System.out.println("Esta seguro de que su nombre es: " + player + "?   yes/no");
			String choise = sc.nextLine();
			choise = choise.toLowerCase().trim();
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
