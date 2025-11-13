package acciones;

import java.util.Random;
import java.util.Scanner;
import todo.Jugador;
import utiles.MyUtil;

public class AccionBuscaminas implements Accion {
	
	private static final Random ran = new Random();
	public static final int SEG_COOLDOWN = 180; // 3 minutos de cooldown

	// --- Configuración del Juego ---
	private static final int TAMANIO_TABLERO = 8; // Un tablero de 8x8
	private static final int CANTIDAD_MINAS = 10; // 10 minas
	private static final char CELDA_OCULTA = '■';
	private static final char CELDA_MINA = '*';
	private static final char CELDA_BANDERA = 'P';
	private static final char CELDA_VACIA = ' ';

	// --- Atributos de Recompensa ---
	private int recompensaBase = 5000;
	private int recompensaBaseXP = 30;

	@Override
	public void realizar(Jugador jugador, Scanner scBuscaminas) {
		
		// 1. Inicializar los tableros
		// El tablero lógico (la solución)
		char[][] tableroSolucion = new char[TAMANIO_TABLERO][TAMANIO_TABLERO];
		// El tablero que ve el jugador
		char[][] tableroVisible = new char[TAMANIO_TABLERO][TAMANIO_TABLERO];

		// Prepara el juego: Pone minas y calcula los números
		inicializarTablero(tableroSolucion, tableroVisible);
		
		boolean enBuscaminas = true;
		String input = "";

		try {
			introBuscaminas(scBuscaminas);
			
			while (enBuscaminas) {
				mostrarTablero(jugador, tableroVisible);
				
				System.out.print("\nComando (ej: " + MyUtil.ANSI_GREEN + "r 3 4" + MyUtil.ANSI_RESET + " o " + MyUtil.ANSI_YELLOW + "f 3 4" + MyUtil.ANSI_RESET + ", /salir) > ");
				input = scBuscaminas.nextLine().toLowerCase().trim();
				
				// El flujo devuelve un int: 0=continuar, 1=victoria, -1=derrota
				int resultadoTurno = flujoBuscaminas(jugador, input, tableroSolucion, tableroVisible, scBuscaminas);
				
				if (resultadoTurno == 1) { // Victoria
					victoriaBuscaminas(jugador, scBuscaminas);
					enBuscaminas = false;
				} else if (resultadoTurno == -1) { // Derrota
					derrotaBuscaminas(jugador, tableroSolucion, tableroVisible, scBuscaminas);
					enBuscaminas = false;
				} else if (resultadoTurno == -2) { // Escapar
					enBuscaminas = false;
				}
				// Si es 0, el bucle continúa
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		jugador.setActionCooldown("/explorar", SEG_COOLDOWN); // Comparte cooldown con explorar
	}

	/**
	 * Procesa la entrada del jugador.
	 * Devuelve 0 (continuar), 1 (victoria), -1 (derrota), -2 (escapar)
	 */
	private int flujoBuscaminas(Jugador jugador, String input, char[][] solucion, char[][] visible, Scanner sc)
			throws InterruptedException {

		if (input.equals("/salir") || input.equals("/escapar")) {
			MyUtil.marco("Decides dejar el puzzle para otro momento...");
			Thread.sleep(1500);
			return -2;
		}

		String[] partes = input.split(" ");
		
		// Validar formato de entrada (ej: "r 3 4")
		if (partes.length != 3) {
			MyUtil.marco(MyUtil.ANSI_RED + "Comando inválido. Formato: [r/f] [fila] [columna]" + MyUtil.ANSI_RESET);
			Thread.sleep(1500);
			return 0; // Continuar
		}
		
		try {
			String accion = partes[0];
			int fila = Integer.parseInt(partes[1]);
			int col = Integer.parseInt(partes[2]);

			// Validar coordenadas
			if (fila < 0 || fila >= TAMANIO_TABLERO || col < 0 || col >= TAMANIO_TABLERO) {
				MyUtil.marco(MyUtil.ANSI_RED + "Coordenadas fuera del tablero (0-" + (TAMANIO_TABLERO - 1) + ")" + MyUtil.ANSI_RESET);
				Thread.sleep(1500);
				return 0;
			}
			
			// --- Lógica de Acción ---
			
			if (accion.equals("r")) { // REVELAR
				if (visible[fila][col] != CELDA_OCULTA) {
					MyUtil.marco("Esa celda ya está revelada o marcada.");
					Thread.sleep(1000);
					return 0;
				}
				
				// ¡BOOM!
				if (solucion[fila][col] == CELDA_MINA) {
					return -1; // Derrota
				}
				
				// Si es un '0', se expande. Si es un número, solo se revela.
				revelarRecursivo(solucion, visible, fila, col);

			} else if (accion.equals("f")) { // MARCAR (FLAG)
				if (visible[fila][col] == CELDA_OCULTA) {
					visible[fila][col] = CELDA_BANDERA;
				} else if (visible[fila][col] == CELDA_BANDERA) {
					visible[fila][col] = CELDA_OCULTA;
				}
				
			} else {
				MyUtil.marco(MyUtil.ANSI_RED + "Acción desconocida. Usa 'r' (revelar) o 'f' (marcar)." + MyUtil.ANSI_RESET);
				Thread.sleep(1500);
				return 0;
			}
			
			// --- Chequear Victoria ---
			if (chequearVictoria(solucion, visible)) {
				return 1; // Victoria
			}

		} catch (NumberFormatException e) {
			MyUtil.marco(MyUtil.ANSI_RED + "Comando inválido. Fila y Columna deben ser números." + MyUtil.ANSI_RESET);
			Thread.sleep(1500);
		}
		
		return 0; // Continuar
	}

	// --- MÉTODOS DE LÓGICA INTERNA ---

	/**
	 * Prepara los tableros, llenándolos con celdas ocultas, minas y números.
	 */
	private void inicializarTablero(char[][] solucion, char[][] visible) {
		// 1. Llenar ambos tableros
		for (int i = 0; i < TAMANIO_TABLERO; i++) {
			for (int j = 0; j < TAMANIO_TABLERO; j++) {
				solucion[i][j] = CELDA_VACIA; // La solución se llena con vacías
				visible[i][j] = CELDA_OCULTA; // El visible se llena con ocultas
			}
		}

		// 2. Plantar Minas
		int minasPlantadas = 0;
		while (minasPlantadas < CANTIDAD_MINAS) {
			int r = ran.nextInt(TAMANIO_TABLERO);
			int c = ran.nextInt(TAMANIO_TABLERO);
			if (solucion[r][c] != CELDA_MINA) {
				solucion[r][c] = CELDA_MINA;
				minasPlantadas++;
			}
		}
		
		// 3. Calcular Números Adyacentes
		calcularNumeros(solucion);
	}
	
	/**
	 * Calcula los números (pistas) basado en las minas adyacentes.
	 */
	private void calcularNumeros(char[][] solucion) {
		for (int r = 0; r < TAMANIO_TABLERO; r++) {
			for (int c = 0; c < TAMANIO_TABLERO; c++) {
				// Si la celda actual es una mina, no necesita número
				if (solucion[r][c] == CELDA_MINA) {
					continue;
				}
				
				int minasAdyacentes = 0;
				
				// Revisar los 8 vecinos (de -1 a +1 en fila y columna)
				for (int dr = -1; dr <= 1; dr++) {
					for (int dc = -1; dc <= 1; dc++) {
						if (dr == 0 && dc == 0) continue; // No contarse a sí mismo
						
						int nr = r + dr;
						int nc = c + dc;
						
						// Comprobar que el vecino esté dentro del tablero
						if (nr >= 0 && nr < TAMANIO_TABLERO && nc >= 0 && nc < TAMANIO_TABLERO) {
							if (solucion[nr][nc] == CELDA_MINA) {
								minasAdyacentes++;
							}
						}
					}
				}
				
				if (minasAdyacentes > 0) {
					// Convertir el número (ej. 3) al carácter ('3')
					solucion[r][c] = (char) (minasAdyacentes + '0');
				}
			}
		}
	}

	/**
	 * Revela celdas usando "Flood Fill". Si una celda es ' ', revela a sus vecinos.
	 */
	private void revelarRecursivo(char[][] solucion, char[][] visible, int r, int c) {
		// 1. Límites del tablero
		if (r < 0 || r >= TAMANIO_TABLERO || c < 0 || c >= TAMANIO_TABLERO) {
			return;
		}
		// 2. Si ya está visible, no hacer nada
		if (visible[r][c] != CELDA_OCULTA) {
			return;
		}
		
		// 3. Revelar la celda actual
		char celdaSolucion = solucion[r][c];
		visible[r][c] = (celdaSolucion == CELDA_VACIA) ? ' ' : celdaSolucion;

		// 4. Si la celda es un número (1-8), parar aquí.
		if (celdaSolucion != CELDA_VACIA) {
			return;
		}

		// 5. Si es VACÍA (' '), continuar la recursión en los 8 vecinos
		for (int dr = -1; dr <= 1; dr++) {
			for (int dc = -1; dc <= 1; dc++) {
				if (dr == 0 && dc == 0) continue;
				revelarRecursivo(solucion, visible, r + dr, c + dc);
			}
		}
	}

	/**
	 * Comprueba si el jugador ha ganado.
	 * Gana si todas las celdas NO-MINA han sido reveladas.
	 */
	private boolean chequearVictoria(char[][] solucion, char[][] visible) {
		for (int r = 0; r < TAMANIO_TABLERO; r++) {
			for (int c = 0; c < TAMANIO_TABLERO; c++) {
				// Si encontramos una celda que NO es mina Y todavía está oculta/marcada...
				if (solucion[r][c] != CELDA_MINA && (visible[r][c] == CELDA_OCULTA || visible[r][c] == CELDA_BANDERA)) {
					return false; // El juego no ha terminado
				}
			}
		}
		return true; // Todas las celdas no-mina están visibles
	}

	// --- MÉTODOS DE CINEMÁTICA ---

	private void mostrarTablero(Jugador jugador, char[][] tableroVisible) {
		MyUtil.limpiarConsola();
		MyUtil.marco("Vida: " + MyUtil.ANSI_GREEN + jugador.getVidaActual() + MyUtil.ANSI_RESET + "/" + jugador.getVidaMaxima());
		
		// Encabezado de Columnas (0 1 2 3...)
		System.out.print("   "); // Espacio para el índice de filas
		for (int c = 0; c < TAMANIO_TABLERO; c++) {
			System.out.print(MyUtil.ANSI_CYAN + c + " " + MyUtil.ANSI_RESET);
		}
		System.out.println();
		
		// Dibujar el tablero
		for (int r = 0; r < TAMANIO_TABLERO; r++) {
			System.out.print(MyUtil.ANSI_CYAN + r + MyUtil.ANSI_RESET + "  "); // Índice de Fila
			for (int c = 0; c < TAMANIO_TABLERO; c++) {
				char celda = tableroVisible[r][c];
				System.out.print(colorearCelda(celda) + " ");
			}
			System.out.println();
		}
	}
	
	private String colorearCelda(char c) {
		switch (c) {
			case CELDA_MINA:
				return MyUtil.ANSI_RED + c + MyUtil.ANSI_RESET;
			case CELDA_BANDERA:
				return MyUtil.ANSI_YELLOW + c + MyUtil.ANSI_RESET;
			case '1':
				return MyUtil.ANSI_BLUE + c + MyUtil.ANSI_RESET;
			case '2':
				return MyUtil.ANSI_GREEN + c + MyUtil.ANSI_RESET;
			case '3':
			case '4':
			case '5':
				return MyUtil.ANSI_RED + c + MyUtil.ANSI_RESET;
			default:
				return String.valueOf(c);
		}
	}

	private void introBuscaminas(Scanner sc) throws InterruptedException {
		MyUtil.limpiarConsola();
		MyUtil.marco("Encuentras un viejo tablero de madera sobre un pedestal.");
		Thread.sleep(2000);
		MyUtil.marco("Las reglas son simples: Revela todas las celdas seguras.");
		Thread.sleep(2000);
		MyUtil.marco(MyUtil.ANSI_RED + "Pero no toques las minas." + MyUtil.ANSI_RESET);
		Thread.sleep(1500);
		System.out.print("\n[Presiona Enter para comenzar]...");
		sc.nextLine();
	}

	private void derrotaBuscaminas(Jugador jugador, char[][] solucion,char[][] visible, Scanner sc) throws InterruptedException {
		// Mostrar todas las minas
		for(int r=0; r<TAMANIO_TABLERO; r++) {
			for(int c=0; c<TAMANIO_TABLERO; c++) {
				if(solucion[r][c] == CELDA_MINA) {
					visible[r][c] = CELDA_MINA;
				}
			}
		}
		mostrarTablero(jugador, visible); // Mostrar el tablero final
		
		MyUtil.marco(MyUtil.ANSI_RED + "¡BOOOOOOM! Has pisado una mina." + MyUtil.ANSI_RESET);
		
		// Penalización: Perder 1/3 de la vida
		int danio = (int) (jugador.getVidaMaxima() * -0.33);
		jugador.modVida(danio);
		MyUtil.marco("Pierdes " + MyUtil.ANSI_RED + (-danio) + "HP" + MyUtil.ANSI_RESET + " por la explosión.");
		
		System.out.print("\n[Presiona Enter para continuar]...");
		sc.nextLine();
	}

	private void victoriaBuscaminas(Jugador jugador, Scanner sc) throws InterruptedException {
		
		double multiplicadorSuerte = 1.0 + jugador.getSuerte();
		
		int recompensaBruta = recompensaBase;
		int expBruta = recompensaBaseXP;
		
		int recompensaFinal = (int) (recompensaBruta * multiplicadorSuerte);
		int expFinal = (int) (expBruta * multiplicadorSuerte);
		
		MyUtil.marco(MyUtil.ANSI_GREEN + "¡Felicidades! Has despejado el tablero de forma segura." + MyUtil.ANSI_RESET);
		Thread.sleep(1500);
		
		jugador.modMonedas(recompensaFinal);
		jugador.modExp(expFinal);
		
		MyUtil.marco("Has ganado " + MyUtil.ANSI_YELLOW + "$" + recompensaFinal + MyUtil.ANSI_RESET + " y " + MyUtil.ANSI_CYAN + expFinal + "XP" + MyUtil.ANSI_RESET);
		
		if (jugador.getSuerte() > 0) {
			MyUtil.marco("(Bonificación por suerte: $" + (recompensaFinal - recompensaBruta) + " y " 
					+ (expFinal - expBruta) + "XP)");
		}
		
		System.out.print("\n[Presiona Enter para continuar]...");
		sc.nextLine();
	}
}