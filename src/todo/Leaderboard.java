package todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Leaderboard {

	private static final String ARCHIVO = "leaderboard.txt";

	public static void registrarPuntaje(String nombre, int nivel, int rebirths, int monedas) {

		String nuevoPuntaje = nombre + "," + nivel + "," + rebirths + "," + monedas;

		try (FileWriter fw = new FileWriter(ARCHIVO, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			out.println(nuevoPuntaje);

		} catch (IOException e) {
			System.err.println("Error al guardar puntaje: " + e.getMessage());
		}
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
            System.out.println(pos + ". " + datos[0] + 
                               " - Nivel: " + datos[1] + 
                               " - Monedas: " + datos[3]);
            pos++;
        }
        System.out.println("---------------------------------");
		

	}

	public static ArrayList<String[]> convertirLeaderboard(ArrayList<String> a) {
		ArrayList<String[]> playerData = new ArrayList<>();
		for (int i = 0; i < a.size(); i++) {
			String[] datos = a.get(i).split(","); // 0 - Nombre. 1 - nivel. 2 - rebirths. 3- monedas
			playerData.add(datos);
		}
		return playerData;
	}

	public static void ordenarLeaderboard(ArrayList<String[]> cLdb) {			//funcion para ordenar la leaderboard mediante Insertion sort
		for (int i = 1; i < cLdb.size(); i++) {									//el ordenaminto es al revez por lo que queda de mayor a menor
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
