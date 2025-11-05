package todo;

public abstract class TrabajarBase implements Trabajar {
	@Override
	public void trabajar(Jugador jugador) {

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

	public abstract String[] getDescriptionBase();

	public abstract String getNombreBase();

	@Override
	public double multiplicadorVenta(double precioBase, Jugador jugador) {
		return precioBase;

	}

	@Override
	public double multiplicadorGanancias(double gananciaBase, Jugador jugador) {
		return gananciaBase;

	}
}
