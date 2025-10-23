package todo;

import java.util.Scanner;

public class FlujoPrincipal {

	public static void main(String[] args) {
		//Scanner para input de juagador
		Scanner sc = new Scanner(System.in);
		
		//Generar instancia del jugador
		Jugador jugadorActual = new Jugador("Paralelepípedo");
		
		//Instancia de ontrolador de acciones.
		Controlador controlador = new Controlador(jugadorActual);
		
		boolean enJuego = true; //Para seguir o salir del juego.
		
		String input = " ";//Variable de la entrada del jugador.
		
		//*Insert Tutorial*
		System.out.println("Bienvenid@!, el juego empieza aquí \n");
		System.out.println("Puedes escribir \"/explorar\" para hacer tu primera exploración \n o puedes escribir \"/comandos\" para guiarte");
		
		//Bucle Principal
		while(enJuego) {
			input = sc.nextLine(); //Ingresa Imput
			controlador.limpiarConsola(); //Redirecciona a la acción.
			enJuego = controlador.procesarInput(input); //Verifica si sigue en juego o no.
		}
			
		sc.close(); //Cierra Scanner 
		System.out.println("Se ha cerrado el juego");
		//Fin del Juego.
	}

}
