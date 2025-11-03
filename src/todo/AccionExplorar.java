package todo;

import java.util.ArrayList;
import java.util.Scanner;

public class AccionExplorar implements Accion {
	String descripcion = "Te encuentras con un evento aleatorio donde puedes conseguir botín y aventuras";
	
	Scanner scExplorar = new Scanner(System.in);
	//Lista de palabras para el ahorcado
	private ArrayList<String> palabrasAhorcado = new ArrayList<>();
	
	AccionExplorar(){
		//Inicialización del ArrayDePalabras
		palabrasAhorcado.add("niggle");
	}
	
	
	@Override
	public void realizar(Jugador jugadorActual,Controlador controlador) {
		double probabilidad = Math.random();
		
		if(probabilidad <= 0.99999999) {
			realizarAhorcado(jugadorActual);
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
	 * @return Un flujo de acción para resolver un ahorcado.
	 */
	private void realizarAhorcado(Jugador jugador) {
		System.out.println("Juguemos al ahorcado");	
		//Calcula la probabilidad para elegir la palabra en la lista.
		String input ="";
		int probabilidadPalabra = (int) (Math.random() *palabrasAhorcado.size());
		//Obtiene la palabra correspondiente.
		String palabra = palabrasAhorcado.get(probabilidadPalabra);

		StringBuilder palabraRespuesta = new StringBuilder();

	    for (int i = 0; i < palabra.length(); i++) {
	        palabraRespuesta.append("_ ");
	    }
		
		boolean enAhorcado = true;
		
		while(enAhorcado) {
			System.out.println("Vida: " + jugador.vidaActual + "/" + jugador.vidaMaxima);
	        System.out.println(palabraRespuesta.toString());
	        System.out.print("Ingrese su letra elegida (SOLO UNA LETRA): ");
	        
	        // Convertimos el input a minúscula y quitamos espacios
	        input = scExplorar.nextLine().toLowerCase().trim(); 
	        
	        if(input.length() == 1) {
	            char letraInput = input.charAt(0);
	            
	            //Comprobar si la letra está en la palabra
	            if(palabra.indexOf(letraInput) != -1) {

	                //Se reemplaza la letra en 
	                for (int i = 0; i < palabra.length(); i++) {
	                    // Si la letra en la palabra (minúscula) coincide con el input
	                    if (palabra.charAt(i) == letraInput) {
	                        // Reemplazamos el '_' en palabraRespuesta
	                        // Usamos (i * 2) para saltar los espacios
	                        // Usamos palabra.charAt(i) (la original) para respetar mayúsculas (ej: 'N' en "Niggle")
	                        palabraRespuesta.setCharAt(i * 2, palabra.charAt(i));
	                    }
	                }
	                
	            } else {
	                jugador.vidaActual -= (jugador.vidaMaxima * 0.2);
	            }
	        } else {
	            //Se igresó más de una letra, inválido.
	        }
	        
	        // --- Lógica de Victoria/Derrota ---
	        // Si ya no quedan '_', el jugador gana
	        if (palabraRespuesta.indexOf("_") == -1) {
	            System.out.println("¡Felicidades! Has adivinado la palabra: " + palabra);
	            // Aquí puedes dar recompensas
	            enAhorcado = false;
	        }
	}
}
}
