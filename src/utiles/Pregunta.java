package utiles;

public class Pregunta {
	private String pregunta;
	private int respuesta;
	private String[] opciones = new String[4];
	private int recompensa;
	
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
}
