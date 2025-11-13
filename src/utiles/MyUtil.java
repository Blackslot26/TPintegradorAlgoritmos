package utiles;

public class MyUtil {

	
	//Para evitar instancias
	private MyUtil() {}
	
	//Comandos de colores para usar en los prints
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static void marco(String texto) {
		final int paddingCostados = 2; // espacios de los costados
		final int largoTexto = texto.length() + (2 * paddingCostados);

		if (texto == null || texto.isEmpty()) {
			texto = " ";
		}

		StringBuilder sb = new StringBuilder();

		// 1. Línea superior: ╔════════...════════╗
		sb.append("╔");
		sb.append("═".repeat(largoTexto));
		sb.append("╗\n");

		// 2. Línea central con texto y padding
		sb.append("║");
		// Añadimos el padding (espacio) a la izquierda
		sb.append(" ".repeat(paddingCostados));
		sb.append(texto);
		// Añadimos el padding (espacio) a la derecha
		sb.append(" ".repeat(paddingCostados));
		sb.append("║\n");

		// 3. Línea inferior: ╚════════...════════╝
		sb.append("╚");
		sb.append("═".repeat(largoTexto));
		sb.append("╝");

		// Imprimimos el marco final
		System.out.println(sb.toString());
	}

	public static void marco(String[] textos) {
		final int paddingCostados = 2; // espacios de los costados

		if (textos == null || textos.length == 0) {
			textos = new String[] { " " };
		}

		// Determinar el largo máximo de los textos
		int largoMaximo = 0;
		for (String texto : textos) {
			if (texto != null && texto.length() > largoMaximo) {
				largoMaximo = texto.length();
			}
		}

		final int largoMarco = largoMaximo + (2 * paddingCostados);
		StringBuilder sb = new StringBuilder();

		// 1. Línea superior
		sb.append("╔").append("═".repeat(largoMarco)).append("╗\n");

		// 2. Líneas centrales
		for (String texto : textos) {
			if (texto == null)
				texto = "";
			int espaciosRestantes = largoMaximo - texto.length();
			sb.append("║").append(" ".repeat(paddingCostados)).append(texto).append(" ".repeat(espaciosRestantes))
					.append(" ".repeat(paddingCostados)).append("║\n");
		}

		// 3. Línea inferior
		sb.append("╚").append("═".repeat(largoMarco)).append("╝");

		// Imprimir el marco
		System.out.println(sb.toString());
	}

	public static void dibujarArrayString(String[] array) {
		for (String linea : array) {
			System.out.println(linea);
		}
	}

	public static void dibujarArrayString(String[] array, int rightChars) {
		for (String linea : array) {
			System.out.print(" ".repeat(rightChars));
			System.out.print(linea + "\n");
		}
	}
	
	/**
	 * Simula la limpieza de la consola imprimiendo muchas líneas nuevas.
	 * o hace 50 prints.
	 */
	public static void limpiarConsola() {
		 for (int i = 0; i < 50; i++) { System.out.println(); }/**/

		// Para cuando se exporte como archivo jar
		
		try {
			// Obtenemos el nombre del sistema operativo
			String os = System.getProperty("os.name");

			// Creamos una lista de comandos
			ProcessBuilder pb;
			if (os.contains("Windows")) {
				// Si es Windows, usamos "cls"
				pb = new ProcessBuilder("cmd", "/c", "cls");
			} else {
				// Si no es Windows, usamos "clear"
				pb = new ProcessBuilder("clear");
			}

			// Ejecutamos el comando
			pb.inheritIO().start().waitFor();

		} catch (final Exception e) {
			System.out.print("Error al limpiar la pantalla: " + e.getMessage());
		} /**/
	}



}
