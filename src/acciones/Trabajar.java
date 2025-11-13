package acciones;
import java.io.Serializable;

import todo.Jugador;
/**
 * Clase abstracta que sirve de plantilla para todos los trabajos del juego.
 * <p>
 * Maneja la lógica de presentación visual (cajas ASCII) y define los métodos
 * que cada trabajo concreto debe implementar (estrategia de ganancias, textos, etc.).
 * </p>
 */
public abstract class Trabajar implements Serializable, Accion{
	private static final long serialVersionUID = 1L;
	/**
	 * Genera y formatea una caja de texto ASCII con el resultado del trabajo realizado.
	 * Incluye información sobre el jugador, la ganancia obtenida y la experiencia.
	 * * @param event    El índice del evento de texto aleatorio seleccionado.
	 * @param ganancia La cantidad de monedas ganadas.
	 * @param jugador  El jugador que realizó el trabajo.
	 * @param xp       La experiencia ganada.
	 * @return Un String formateado listo para imprimir en consola.
	 */
	
	public String trabajarTexts(int event, int ganancia, Jugador jugador, int xp) {
		
		String[] texts = trabajarTextsBase(ganancia);
		StringBuilder sb = new StringBuilder();
		int total = texts[event].length();
		int blanksL = (total - texts[event].length()) / 2;
		int blanksR = total - texts[event].length();
		
		String ganancias = "Ganancia: " + ganancia + "       " + "Dinero Total: " + jugador.getMonedas();
		String nivel = "Xp: + " + xp + "       " + "Nivel: " + jugador.getExperiencia() + "/" + jugador.getExperienciaLevel();
		
		top(sb, total);  // parte superior de la caja
	
		sb.append("║" + "Player: " + jugador.getNombre() + " ".repeat(total-jugador.getNombre().length()-8) + "║\n");  //nombre jugador el -8 es por los caracteres de "Player: "
		sb.append("║" + "Trabajo: " + jugador.getTrabajo().getNombreBase() + " ".repeat(total-jugador.getTrabajo().getNombreBase().length()-9) + "║\n"); //trabajo del jugador el -9 es por los caracteres de "trabajo: "
		line(sb, total);//lineas vacias en la caja
		line(sb, total);
		
		sb.append("║" + " ".repeat(blanksL) + texts[event] + " ".repeat(blanksR - blanksL) + "║\n"); //Texto del trabajo
		
		line(sb, total);
		
		sb.append("║" + " ".repeat((total - ganancias.length()) / 2) + ganancias + " ".repeat((total - ganancias.length()) - (total - ganancias.length()) / 2) + "║\n"); //muestra de ganancias y dinero total
		
		sb.append("║" + " ".repeat((total - nivel.length()) / 2) + nivel + " ".repeat((total - nivel.length()) - (total - nivel.length()) / 2) + "║\n");
		
		line(sb, total);
		line(sb, total);
		bot(sb, total);

		return sb.toString();
	}
	
	/**
	 * Genera el arte ASCII del nombre del trabajo encerrado en una caja.
	 * * @return String con el nombre formateado.
	 */
	public String getNombre() {
		String nombreSimple = getNombreBase();
		StringBuilder sb = new StringBuilder();

		sb.append("╔");
		sb.append("═".repeat(17));
		sb.append("╗\n");
		sb.append("║");
		sb.append(" ".repeat((17 - nombreSimple.length()) / 2));
		sb.append(nombreSimple);
		sb.append(" ".repeat((17 - nombreSimple.length()) - ((17 - nombreSimple.length()) / 2)));
		sb.append("║\n");
		sb.append("╚");
		sb.append("═".repeat(17));
		sb.append("╝");

		return sb.toString();

	}

	public String getDescriptionT() {
		String[] descripcionSimple = getDescriptionBase();
		int total = 100; // 100 + 2
		
		StringBuilder sb = new StringBuilder();
		sb.append("╔");
		sb.append("═".repeat(total));
		sb.append("╗\n");
		
		sb.append("║");
		sb.append(" ".repeat(total));
		sb.append("║\n");
		
		for (int i = 0; i < descripcionSimple.length; i++) {
			int blanksL = (total - descripcionSimple[i].length()) / 2;
			int blanksR = total - descripcionSimple[i].length();
			
			sb.append("║");
			sb.append(" ".repeat(blanksL));
			sb.append(descripcionSimple[i]);
			sb.append(" ".repeat(blanksR - blanksL));
			sb.append("║\n");
		}
		
		sb.append("║");
		sb.append(" ".repeat(total));
		sb.append("║\n");
		
		sb.append("╚");
		sb.append("═".repeat(total));
		sb.append("╝\n");
		

		return sb.toString();

	}
	
	
	void top(StringBuilder sb, int total) {
		sb.append("╔");
		sb.append("═".repeat(total));
		sb.append("╗\n");
	}
	
	void line(StringBuilder sb, int total) {
		sb.append("║");
		sb.append(" ".repeat(total));
		sb.append("║\n");
	}
	
	void bot(StringBuilder sb, int total) {
		sb.append("╚");
		sb.append("═".repeat(total));
		sb.append("╝\n");
	}

	public abstract String[] getDescriptionBase();

	public abstract String getNombreBase();
	
	public abstract String[] trabajarTextsBase(int ganancia);

	public double multiplicadorVenta(double precioBase, Jugador jugador) {
		return precioBase;

	}

	public double multiplicadorGanancias(double gananciaBase, Jugador jugador) {
		return gananciaBase;

	}
}
