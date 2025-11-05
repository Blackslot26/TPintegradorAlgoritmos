package dibujos;

public class DibujosExplorar {
	public String[] palabrasAhorcado = {
			// Conceptos Positivos y de Bienestar
			"esperanza", "resiliencia", "calma", "serenidad", "alegria", "aceptacion", "gratitud", "animo", "valor",
			"fuerza", "paciencia", "progreso", "recuperar", "autocuidado", "apoyo", "conexion", "empatia", "respirar",
			"enfoque", "claridad", "liberar", "futuro", "bienestar", "optimismo", "proposito", "sanar", "presente",
			"conciencia", "mente", "positivo", "crecer", "meditar", "hablar", "sentir", "vivir", "equilibrio",
			"confianza", "motivacion",
			// Medicamentos
			"terapia", "psicologo", "psiquiatra", "dopamina", "serotonina", "cognitivo", "conductual", "neurologico",
			"isrs", "fluoxetina", "sertralina", "escitalopram", "citalopram", "paroxetina", "venlafaxina", "duloxetina",
			"mirtazapina", "bupropion", "trazodona", "amitriptilina", "nortriptilina", "ansiolitico", "antidepresivo",
			// Otras opciones
			"gambling", "apuestas", "alcohol", "methyl" };

	public void marco(String texto) {
		final int paddingCostados = 2; //espacios de los costados
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

	public void dibujarCalavera() {
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▄▄▄▄░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒▒▒▒▒▒▒▒▄██████▒▒▒▒▒▄▄▄█▄▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒▒▒▒▒▒▄██▀░░▀██▄▒▒▒▒████████▄▒▒▒▒▒▒");
		System.out.println("▒▒▒▒▒▒███░░░░░░██▒▒▒▒▒▒█▀▀▀▀▀██▄▄▒▒▒");
		System.out.println("▒▒▒▒▒▄██▌░░░░░░░██▒▒▒▒▐▌▒▒▒▒▒▒▒▒▀█▄▒");
		System.out.println("▒▒▒▒▒███░░▐█░█▌░██▒▒▒▒█▌▒▒▒▒▒▒▒▒▒▒▀▌");
		System.out.println("▒▒▒▒████░▐█▌░▐█▌██▒▒▒██▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒▒▐████░▐░░░░░▌██▒▒▒█▌▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒▒▒████░░░▄█░░░██▒▒▐█▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒▒▒████░░░██░░██▌▒▒█▌▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒▒▒████▌░▐█░░███▒▒▒█▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒▒▒▐████░░▌░███▒▒▒██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒▒▒▒████░░░███▒▒▒▒█▌▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▒▒██████▌░████▒▒▒██▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒▐████████████▒▒███▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("▒█████████████▄████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("██████████████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("██████████████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("█████████████████▀▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("█████████████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("████████████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println("████████████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
	}
}
