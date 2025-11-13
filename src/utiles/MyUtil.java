package utiles;

public class MyUtil {

	// Para evitar instancias
	private MyUtil() {
	}

	// Comandos de colores para usar en los prints
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	/**
	 * Función de ayuda para eliminar códigos de color ANSI de un String. Esto nos
	 * permite medir el ANCHO VISIBLE del texto.
	 * 
	 * @param texto El texto con códigos de color (ej. "\u001B[31mHola")
	 * @return El texto sin códigos (ej. "Hola")
	 */

	private static String stripAnsi(String texto) {
		if (texto == null) {
			return "";
		}
		// Esta expresión regular busca y reemplaza los códigos de escape ANSI
		return texto.replaceAll("\u001B\\[[;\\d]*m", "");
	}

	public static void marco(String texto) {
		final int paddingCostados = 2; // espacios de los costados

		if (texto == null || texto.isEmpty()) {
			texto = " ";
		}

		// 1. Medir el largo VISIBLE, sin códigos de color
		int largoVisible = stripAnsi(texto).length();

		// 2. Calcular el marco basándose en el largo VISIBLE
		final int largoMarco = largoVisible + (2 * paddingCostados);

		StringBuilder sb = new StringBuilder();

		// 1. Línea superior
		sb.append("╔");
		sb.append("═".repeat(largoMarco));
		sb.append("╗\n");

		// 2. Línea central
		sb.append("║");
		sb.append(" ".repeat(paddingCostados));
		sb.append(texto); // Se imprime el texto ORIGINAL (con color)

		// 3. Añadir el relleno derecho
		sb.append(" ".repeat(paddingCostados));
		sb.append("║\n");

		// 4. Línea inferior
		sb.append("╚");
		sb.append("═".repeat(largoMarco));
		sb.append("╝");

		// Imprimimos el marco final
		System.out.println(sb.toString());
	}

	public static void marco(String[] textos) {
		final int paddingCostados = 2; // espacios de los costados

		if (textos == null || textos.length == 0) {
			textos = new String[] { " " };
		}

		// 1. Determinar el largo máximo VISIBLE
		int largoMaximo = 0;
		for (String texto : textos) {
			if (texto != null) {
				// Medir el texto "limpio"
				int largoVisible = stripAnsi(texto).length();
				if (largoVisible > largoMaximo) {
					largoMaximo = largoVisible;
				}
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

			// Calcular los espacios restantes basándose en el largo VISIBLE
			int largoVisibleActual = stripAnsi(texto).length();
			int espaciosRestantes = largoMaximo - largoVisibleActual;

			sb.append("║").append(" ".repeat(paddingCostados)).append(texto) // Imprime el texto ORIGINAL (con color)
					.append(" ".repeat(espaciosRestantes)) // Añade el relleno correcto
					.append(" ".repeat(paddingCostados)).append("║\n");
		}

		// 3. Línea inferior
		sb.append("╚").append("═".repeat(largoMarco)).append("╝");

		// Imprimir el marco
		System.out.println(sb.toString());
	}

	public static void marcoTienda(String[] textos, int largo) {
		final int paddingCostados = 2; // espacios de los costados

		if (textos == null || textos.length == 0) {
			textos = new String[] { " " };
		}

		// El largo total del marco (el ancho de la línea '═')
		int largoMarco = largo;
		StringBuilder sb = new StringBuilder();

		// 2. Líneas centrales
		for (String texto : textos) {
			if (texto == null)
				texto = "";

			// --- SOLUCIÓN AQUÍ ---
			// Calcular los espacios restantes basándose en el largo VISIBLE (sin ANSI)
			int largoVisibleActual = stripAnsi(texto).length();
			int espaciosRestantes = largoMarco - largoVisibleActual - (paddingCostados * 2);

			// Asegurarse de que los espacios no sean negativos si el texto es muy largo
			if (espaciosRestantes < 0) {
				espaciosRestantes = 0;
			}
			// --- FIN DE LA SOLUCICIÓN ---

			sb.append("║").append(" ".repeat(paddingCostados)).append(texto) // Imprime el texto ORIGINAL (con color)
					.append(" ".repeat(espaciosRestantes)) // Añade el relleno correcto
					.append(" ".repeat(paddingCostados)).append("║\n");
		}

		// 3. Línea inferior
		sb.append("╠" + "═".repeat(largoMarco) + "╣");

		// Imprimir el marco
		System.out.println(sb.toString());
	}
	public static void dibujarArrayString(String[] array) {
		for (String linea : array) {
			System.out.print(linea + "\n");
		}
	}

	public static void dibujarArrayString(String[] array, int rightChars) {
		for (String linea : array) {
			System.out.print(" ".repeat(rightChars));
			System.out.print(linea + "\n");
		}
	}

	/**
	 * Simula la limpieza de la consola imprimiendo muchas líneas nuevas. o hace 50
	 * prints.
	 */
	public static void limpiarConsola() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		} /**/

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
