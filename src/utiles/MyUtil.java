package utiles;
/**
 * Clase de utilidades generales para la manipulación de la consola y textos.
 * <p>
 * Provee métodos estáticos para:
 * <ul>
 * <li>Limpiar la pantalla (compatible con Windows/Linux).</li>
 * <li>Dibujar marcos ASCII alrededor de textos.</li>
 * <li>Manejar colores ANSI.</li>
 * <li>Imprimir arrays de arte ASCII.</li>
 * </ul>
 * </p>
 */
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
	 * Elimina los códigos de escape ANSI de una cadena de texto.
	 * Útil para calcular la longitud visible real de un String coloreado
	 * al momento de alinear textos o dibujar marcos.
	 * * @param texto El texto con colores ANSI.
	 * @return El texto limpio.
	 */

	public static String stripAnsi(String texto) {
		if (texto == null) {
			return "";
		}
		// Esta expresión regular busca y reemplaza los códigos de escape ANSI
		return texto.replaceAll("\u001B\\[[;\\d]*m", "");
	}
	/**
	 * Imprime un texto encerrado en un marco doble ASCII (╔══╗).
	 * Calcula automáticamente el ancho necesario basándose en el texto visible.
	 * * @param texto El contenido a enmarcar.
	 */
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
	/**
	 * Versión sobrecargada de {@link #marco(String)} para múltiples líneas.
	 * Ajusta el ancho del marco a la línea más larga y rellena con espacios las demás.
	 * * @param textos Array de líneas a mostrar dentro del marco.
	 */
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

	    int anchoVisibleInterior = largo - (paddingCostados * 2);
	    if (anchoVisibleInterior < 10) anchoVisibleInterior = 10; // mínimo razonable

	    StringBuilder sb = new StringBuilder();

	    for (String textoOriginal : textos) {
	        String texto = textoOriginal == null ? "" : textoOriginal;

	        // Dividir en trozos que quepan en anchoVisibleInterior, usando la longitud visible (sin ANSI)
	        String restante = texto;
	        while (!restante.isEmpty()) {
	            // Construir una subcadena que no exceda anchoVisibleInterior en longitud visible
	            StringBuilder lineaVisibleBuilder = new StringBuilder();
	            StringBuilder lineaRawBuilder = new StringBuilder(); // mantendrá códigos ANSI + texto en paralelo
	            int visibleCount = 0;
	            int pos = 0;

	            // Recorremos el string char a char para respetar códigos ANSI
	            while (pos < restante.length() && visibleCount < anchoVisibleInterior) {
	                char c = restante.charAt(pos);
	                if (c == '\u001B') {
	                    // comienzo de secuencia ANSI: copiar hasta la m incluida sin contar en visibleCount
	                    int seqEnd = pos + 1;
	                    while (seqEnd < restante.length() && restante.charAt(seqEnd) != 'm') seqEnd++;
	                    if (seqEnd < restante.length()) {
	                        // incluir la secuencia completa
	                        String seq = restante.substring(pos, seqEnd + 1);
	                        lineaRawBuilder.append(seq);
	                        pos = seqEnd + 1;
	                        continue;
	                    } else {
	                        // secuencia no terminada: copiar resto y romper
	                        lineaRawBuilder.append(restante.substring(pos));
	                        pos = restante.length();
	                        break;
	                    }
	                } else {
	                    lineaRawBuilder.append(c);
	                    lineaVisibleBuilder.append(c);
	                    pos++;
	                    visibleCount++;
	                }
	            }

	            String lineaRaw = lineaRawBuilder.toString();
	            String lineaVisible = lineaVisibleBuilder.toString();

	            // Si quedamos en medio de una palabra y hay más texto, intentar retroceder hasta el último espacio visible
	            if (pos < restante.length() && !lineaVisible.isEmpty()) {
	                int ultimoEspacio = lineaVisible.lastIndexOf(' ');
	                if (ultimoEspacio > Math.max(0, lineaVisible.length() / 2)) {
	                    // reconstruir lineaRaw hasta la posición del último espacio visible
	                    int visibleCut = ultimoEspacio;
	                    // necesitamos mapear visibleCut a índice en lineaRaw que respeta códigos ANSI
	                    int visibleSeen = 0;
	                    int rawCutIndex = 0;
	                    while (rawCutIndex < lineaRaw.length() && visibleSeen < visibleCut) {
	                        if (lineaRaw.charAt(rawCutIndex) == '\u001B') {
	                            // saltar secuencia ANSI
	                            rawCutIndex++;
	                            while (rawCutIndex < lineaRaw.length() && lineaRaw.charAt(rawCutIndex) != 'm') rawCutIndex++;
	                            if (rawCutIndex < lineaRaw.length()) rawCutIndex++;
	                        } else {
	                            rawCutIndex++;
	                            visibleSeen++;
	                        }
	                    }
	                    // cortar lineaRaw y ajustar pos en restante acorde
	                    String lineaRawCortada = lineaRaw.substring(0, rawCutIndex);
	                    sb.append("║").append(" ".repeat(paddingCostados)).append(lineaRawCortada);
	                    int relleno = anchoVisibleInterior - MyUtil.stripAnsi(lineaRawCortada).length();
	                    sb.append(" ".repeat(Math.max(0, relleno))).append(" ".repeat(paddingCostados)).append("║\n");

	                    // calcular cuánto avanzamos en 'restante' en términos de caracteres reales (no visibles)
	                    int avanzados = rawCutIndex;
	                    restante = restante.substring(avanzados).trim();
	                    continue;
	                }
	            }

	            // Añadir la línea tal cual (pos caracteres consumidos de 'restante')
	            int consumidos = pos;
	            String lineaParaImprimir = lineaRaw;
	            sb.append("║").append(" ".repeat(paddingCostados)).append(lineaParaImprimir);
	            int espaciosRestantes = anchoVisibleInterior - MyUtil.stripAnsi(lineaParaImprimir).length();
	            sb.append(" ".repeat(Math.max(0, espaciosRestantes))).append(" ".repeat(paddingCostados)).append("║\n");

	            // Actualizar 'restante'
	            if (consumidos >= restante.length()) {
	                restante = "";
	            } else {
	                restante = restante.substring(consumidos).trim();
	            }
	        }
	    }

	    // Línea inferior del bloque
	    sb.append("╠").append("═".repeat(largo)).append("╣");

	    System.out.println(sb.toString());
	}
	/**
	 * Imprime un array de Strings línea por línea.
	 * Utilizado para renderizar arte ASCII complejo.
	 * * @param array El dibujo a imprimir.
	 */
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
	 * Limpia la consola de comandos.
	 * Intenta ejecutar el comando del sistema operativo ("cls" en Windows, "clear" en Unix).
	 * Si falla, imprime múltiples líneas en blanco como fallback.
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
