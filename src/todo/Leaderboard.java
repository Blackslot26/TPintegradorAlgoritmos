package todo;

import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Leaderboard {

	private static final String ARCHIVO = "leaderboard.txt";

	public static void actualizarPuntaje(String nombre, int nivel, int rebirths, int monedas) {

		HashMap<String, String> puntajes = new HashMap<>();

		try (Scanner sc = new Scanner(new File(ARCHIVO))) {
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				String[] datos = linea.split(",");
				if (datos.length == 4) {
					String nombreJugador = datos[0];
					puntajes.put(nombreJugador, linea);
				}
			}
		} catch (FileNotFoundException e) {
		}

		String nuevoPuntaje = nombre + "," + nivel + "," + rebirths + "," + monedas;
		puntajes.put(nombre, nuevoPuntaje);
		
		try (FileWriter fw = new FileWriter(ARCHIVO, false);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			
			for (String lineaActualizada : puntajes.values()) {
                out.println(lineaActualizada);
            }
		} catch (IOException e) {
			System.err.println("Error al guardar puntaje: " + e.getMessage());
		}
	}
	
	public static void update(Jugador jugador) {
		Leaderboard.actualizarPuntaje(
				jugador.getNombre(),
				jugador.getNivel(),
				jugador.getRebirth(),
				jugador.getMonedas()
				);
	}
	

	public static void mostrarLeaderboard() {
		ArrayList<String> lineas = new ArrayList<>();

		try (Scanner sc = new Scanner(new File(ARCHIVO))) {
			while (sc.hasNextLine()) {
				lineas.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println("-- No hay puntajes registrados --");
			return;
		}

		ArrayList<String[]> datosJugadores = convertirLeaderboard(lineas);
		ordenarLeaderboard(datosJugadores);

		System.out.println("--- LEADERBOARD ---");
		int pos = 1;
		for (String[] datos : datosJugadores) {
			System.out.println(pos + ". " + datos[0] + " - Nivel: " + datos[1] + " - Monedas: " + datos[3]);
			pos++;
		}
		System.out.println("---------------------------------------------");

	}

	public static ArrayList<String[]> convertirLeaderboard(ArrayList<String> a) {
		ArrayList<String[]> playerData = new ArrayList<>();
		for (int i = 0; i < a.size(); i++) {
			String[] datos = a.get(i).split(","); // 0 - Nombre. 1 - nivel. 2 - rebirths. 3- monedas
			if (datos.length == 4) {
				playerData.add(datos);
			}
		}
		return playerData;
	}

	public static void ordenarLeaderboard(ArrayList<String[]> cLdb) { // funcion para ordenar la leaderboard mediante
																		// Insertion sort
		for (int i = 1; i < cLdb.size(); i++) { // el ordenaminto es al revez por lo que queda de mayor a menor
			int actual = Integer.parseInt(cLdb.get(i)[3]);
			String[] key = cLdb.get(i);
			int j = i - 1;
			while (j >= 0 && Integer.parseInt(cLdb.get(j)[3]) < actual) {
				cLdb.set(j + 1, cLdb.get(j));
				j--;
			}
			cLdb.set(j + 1, key);
		}

	}
}
