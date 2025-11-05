package todo;

import java.util.ArrayList;
import java.util.Scanner;

import dibujos.DibujosExplorar;

public class AccionExplorar implements Accion {
	String descripcion = "Te encuentras con un evento aleatorio donde puedes conseguir botín y aventuras";
	Scanner scExplorar = new Scanner(System.in);
	DibujosExplorar graficos;	// Instancia para usar los dibujos
	

	AccionExplorar() {
		graficos = new DibujosExplorar();
	}

	@Override
	public void realizar(Jugador jugadorActual, Controlador controlador) {
		controlador.limpiarConsola();
		double probabilidad = Math.random();
		if (probabilidad <= 0.99999999) {

			try {
				realizarAhorcado(jugadorActual, controlador);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Función para obtener la descripción de la tienda.
	 */
	@Override
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Función para realizar el minijuego del ahorcado
	 * 
	 * @return Un flujo de acción para resolver un ahorcado.
	 * @throws InterruptedException
	 */
	private void realizarAhorcado(Jugador jugador, Controlador controlador) throws InterruptedException {
		IntroAhorcado(controlador);// Realizar la introducción
		String input = ""; // Para registrar el input del usuario
		int probabilidadPalabra = (int) (Math.random() * graficos.palabrasAhorcado.length); // Calcula la probabilidad de la palabra
		String palabra = graficos.palabrasAhorcado[probabilidadPalabra]; // Instancia la palabraf
		StringBuilder tablero = new StringBuilder(); // crea el tablero
		boolean enAhorcado = true; // Para el subMain del minijuego
		boolean entradaInvalida = false; // Para indicar que ingresó más de una letra
		ArrayList<Character> letrasIntentadas = new ArrayList<>(); // Registro de las letras que se intentó
		String mostrarLetrasIntentadas= "Probaste:";
		tablero.append("_ ".repeat(palabra.length()));// Inicializar el tablero
		
		while (enAhorcado) {
			
			if (entradaInvalida) {
				graficos.marco("SOLO UNA LETRA TE DIJE, intenta de nuevo");
				Thread.sleep(2000);
			}
			
			controlador.limpiarConsola();// Borrar lo anterior
			// Mostrar las letras que ya intentó
			graficos.dibujarCalavera();
			
			graficos.marco(mostrarLetrasIntentadas);
			
			graficos.marco("Vida: " + jugador.getVidaActual() + "/" + jugador.getVidaMaxima());
			graficos.marco(tablero.toString()); //Muestra el marco
			System.out.print("Ingrese su letra elegida (SOLO UNA LETRA): ");

			input = scExplorar.nextLine().toLowerCase().trim();//Entrada del usuario formalizada
			
			if(input.equals("/casino")) { //Siempre puedes salir
				enAhorcado = false;

				graficos.marco("Has recurrido a ténicas prohibidas para salir de tu grave situación");
				jugador.perderMonedas((int)(jugador.getMonedas()*0.25));
				Thread.sleep(3000);
			}

			if (input.length() == 1) {
				entradaInvalida = false;
				char letraInput = input.charAt(0);

				if (letrasIntentadas.contains(letraInput)) {
					graficos.marco("Esa letra ya la intentaste. Prueba otra.");
					Thread.sleep(1500);
					continue;
				}
				
				// Comprobar si la letra está en la palabra
				if (palabra.indexOf(letraInput) != -1) {

					// Se reemplaza la letra en
					for (int i = 0; i < palabra.length(); i++) {
						// Si la letra en la palabra coincide con el input
						if (palabra.charAt(i) == letraInput) {
							// Reemplazamos el '_' en palabraRespuesta
							// Usamos (i * 2) para saltar los espacios
							tablero.setCharAt(i * 2, palabra.charAt(i));
						}
					}

				} else {
					int danio = (int) (jugador.getVidaMaxima() * 0.1);
					jugador.recibirDanio(danio);
					graficos.marco("Esta letra no se encuentra en la palabra [-" + danio + "HP]");
					Thread.sleep(2500);
					letrasIntentadas.add(letraInput);
					mostrarLetrasIntentadas = mostrarLetrasIntentadas + " " + letraInput;
				}
			} else {
				entradaInvalida = true;
			}

			// --- Lógica de Victoria/Derrota ---
			// Si ya no quedan '_', el jugador gana
			if (tablero.indexOf("_") == -1) {
				int recompensa = (int) (Math.random() * 50);
				graficos.marco("¡Felicidades! Has adivinadado la palabra: " + palabra);
				graficos.marco("Y Has sobrevido a un ataque de depresión");
				Thread.sleep(2500);
				jugador.addMonedas(recompensa);
				Thread.sleep(5000);
				enAhorcado = false;
			}
			else if(jugador.getVidaActual()<=0) {
				enAhorcado = false;
			}
		}
	}

	private void IntroAhorcado(Controlador controlador) throws InterruptedException {
		graficos.marco("Has pasado por muchas dificultades en tu vida un transtorno depresivo te ha invadido...");
		Thread.sleep(2000);
		controlador.limpiarConsola();
		graficos.marco("La muerte te busca, está a punto de tomar una decisión muy drástica..");
		Thread.sleep(2000);
		controlador.limpiarConsola();
		graficos.marco("Pero hay otra alternativa...");
		Thread.sleep(2000);
		controlador.limpiarConsola();
		graficos.marco("JUGUEMOS AL AHORCADO!!! ADIVINA LA PALABRA");
		Thread.sleep(2500);

	}
}
