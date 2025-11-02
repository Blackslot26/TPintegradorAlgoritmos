package todo;

import java.io.*;

public class GestorPartidas {

	public static void guardarPartida(Jugador jugador) { // Metodo para guardar partidas. Se ejecuta al salir del juego.
		String nombreArchivo = jugador.getNombre() + ".save";

		try (FileOutputStream fos = new FileOutputStream(nombreArchivo); // este tipo de try-catch es algo nuevo de java
																			// llamado try with resourses
				ObjectOutputStream oos = new ObjectOutputStream(fos)) { // y evita problemas al abrir y cerrar archivos.
			/*
			 * Se crean dos objetos de tipo FOS y OOS que son usados para guardar los datos
			 * de la clase jugador y todas las clases que implementen serializable
			 */
			oos.writeObject(jugador);
			System.out.println("< Partida Guardada >");
			Leaderboard.update(jugador);
			
		} catch (IOException e) {
			System.out.println("Error al guardar la partida: " + e.getMessage());
		}

	}
	

	public static Jugador cargarPartida(String nombre) {
		String nombreArchivo = nombre + ".save";	//nombre de la partida
		File archivo = new File(nombreArchivo);     //variable que actua como puntero al archivo de guardado
		
		if(!archivo.exists()) {
				return new Jugador(nombre);
		}

	try(

	FileInputStream fis = new FileInputStream(archivo);
	ObjectInputStream ois = new ObjectInputStream(fis))
	{

		Jugador jugadorActual = (Jugador) ois.readObject(); // si el nombre del jugador existe se inicializa una clase
		System.out.println("Bienvenido devuelta: " + nombre); // de jugador con sus datos
		return jugadorActual;

	}catch(IOException|
	ClassNotFoundException e)
	{
		System.out.println("Eror al cargar la partida: " + e.getMessage());
		System.out.println("Creando nuevo jugador...");
		return new Jugador(nombre);
	}
}

}
