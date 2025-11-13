package utiles;
/**
 * Modelo de datos para una pregunta de trivia (Minijuego Preguntado).
 * <p>
 * Almacena el enunciado, las opciones de respuesta, el índice de la respuesta correcta
 * y la recompensa asociada por acertar.
 * </p>
 */
public class Pregunta {
	private String pregunta;
	private int respuesta;
	private String[] opciones = new String[4];
	private int recompensa;
	/**
	 * Crea una nueva pregunta.
	 * * @param pregunta   El texto del enunciado.
	 * @param opciones   Array de 4 posibles respuestas.
	 * @param respuesta  Índice (0-3) de la opción correcta en el array.
	 * @param recompensa Cantidad base de monedas que otorga.
	 */
	public Pregunta(String pregunta,String[] opciones,int respuesta,int recompensa) {
			this.pregunta= pregunta;
			this.respuesta = respuesta;
			this.opciones = opciones;
			this.recompensa = recompensa;
	}
	
	public String getPregunta() {
		return pregunta;
	}
	public int getRespuesta() {
		return respuesta;
	}
	public String[] getOpciones() {
		return opciones;
	}
	public int getRecompensa() {
		return recompensa;
	}
	/**
	 * Formatea las opciones de respuesta en un solo String legible.
	 * * @return String con formato "[1. Opción A]  [2. Opción B] ..."
	 */
	public String opcionesToString() {
		StringBuilder sb = new StringBuilder();
		
		for (int o = 0; o < opciones.length; o++) {
	        sb.append("[");
	        sb.append(o + 1);
	        sb.append(". ");
	        sb.append(opciones[o]);
	        sb.append("]  "); // Espacio entre opciones
	    }
		return sb.toString();
	}
}
