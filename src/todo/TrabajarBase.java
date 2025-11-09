package todo;
import java.io.Serializable;

public abstract class TrabajarBase implements Trabajar, Serializable{
	private static final long serialVersionUID = 1L;

	@Override
	public void trabajar(Jugador jugador) {

	}
	
	public String trabajarTexts(int event, int ganancia, Jugador jugador) {
		
		String[] texts = trabajarTextsBase(ganancia);
		StringBuilder sb = new StringBuilder();
		int total = texts[event].length();
		int blanksL = (total - texts[event].length()) / 2;
		int blanksR = total - texts[event].length();
		
		String ganancias = "Ganancia: " + ganancia + "       " + "Dinero Total: " + jugador.getMonedas();
		
		top(sb, total);
	
		sb.append("║");
		sb.append("Player: " + jugador.getNombre());
		sb.append(" ".repeat(total-jugador.getNombre().length()-8));
		sb.append("║\n");
		
		line(sb, total);
		line(sb, total);
		
		sb.append("║");
		sb.append(" ".repeat(blanksL));
		sb.append(texts[event]);
		sb.append(" ".repeat(blanksR - blanksL));
		sb.append("║\n");
		
		line(sb, total);
		
		sb.append("║");
		sb.append(" ".repeat((total - ganancias.length()) / 2));
		sb.append(ganancias);
		sb.append(" ".repeat((total - ganancias.length()) - (total - ganancias.length()) / 2));
		sb.append("║\n");
		
		
		line(sb, total);
		line(sb, total);
		bot(sb, total);

		return sb.toString();
	}
	

	@Override
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

	@Override
	public String getDescription() {
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

	@Override
	public double multiplicadorVenta(double precioBase, Jugador jugador) {
		return precioBase;

	}

	@Override
	public double multiplicadorGanancias(double gananciaBase, Jugador jugador) {
		return gananciaBase;

	}
}
