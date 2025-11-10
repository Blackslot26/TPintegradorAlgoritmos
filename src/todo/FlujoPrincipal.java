package todo;

import java.util.Scanner;

public class FlujoPrincipal {
	static Scanner sc;
	
	public static void main(String[] args) {
		//Intancia de clases y variables necesarias.
		sc = new Scanner(System.in);
		Jugador jugadorActual = logIn();   //logIn retorna el jugador logueado, si este no existe se crea uno nuevo
		Controlador controlador = new Controlador(jugadorActual);
		boolean enJuego = true;
		String input = "";

		
		
		

		if(jugadorActual.getExperiencia() == 0) {					//SI LA EXPERIENCIA DEL JUGADOR ES 0 SIGNIFICA QUE ES UN JUGADOR NUEVO Y ESTA FORZADO A REALIZAR EL TUTORIAL.
			Tutorial tutorial = new Tutorial(jugadorActual);
			try {
				tutorial.iniciarTutorial();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	/**/
		//Bucle Principal
		while(enJuego) {
			jugadorActual.actualizar();
			controlador.limpiarConsola();
			jugadorActual.mostrarEstadoJugador();
			input = sc.nextLine(); //Ingresa Imput.
			enJuego = controlador.procesarInput(input,sc); //Verifica si sigue en juego o no.
		}
		
		
		GestorPartidas.guardarPartida(jugadorActual);//GUARDAR LA PARTIDA AL ROMPER EL BUCLE WHILE CUANDO enJuego SE VUELVE FALSE
		
		System.out.println("Se ha cerrado el juego");
		//Fin del Juego.
	}

	public static Jugador logIn() {
		System.out.println("			╔════════════════════════════════════════════════════════════════════════╗");
		System.out.println("			║                                                                        ║");
		System.out.println("			║                                                                        ║");
		System.out.println("			║   Bienvenid@!, por favor escribe tu nombre de jugador para loguearte   ║");
		System.out.println("			║                                                                        ║");
		System.out.println("			║                                                                        ║");
		System.out.println("			╚════════════════════════════════════════════════════════════════════════╝");
		while (true) {	
			String player = sc.nextLine().toLowerCase().trim();
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


}
