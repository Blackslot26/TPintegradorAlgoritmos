package todo;

import java.io.Serializable;
/**
 * Clase base abstracta para todas las entidades vivas del juego (Jugador y Enemigos).
 * <p>
 * Define atributos fundamentales como vida, nombre, nivel y daño, así como
 * la lógica básica de recibir daño y morir.
 * </p>
 */
public abstract class Personaje implements Serializable {
	private static final long serialVersionUID = 1L;
	private int vidaMaxima;
	private int vidaActual;
	private int nivel;
	private String nombre;
	private int danio;
	private boolean defendiendo; // Para la batalla

	public Personaje(String nombre, int vidaMaxima, int danio) {
		this.nombre = nombre;
		this.vidaMaxima = vidaMaxima;
		this.vidaActual = this.vidaMaxima;
		this.nivel = 1;
		this.danio = danio;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNivel() {
		return nivel;
	}
	public void setNivel(int value) {
		nivel = value;
	}

	public void modVida(int vida) {
		vidaActual += vida;
	}
	
	public void setVidaActual(int vida) {
		vidaActual = vida;
	}

	public int getVidaActual() {
		return vidaActual;
	}

	public int getVidaMaxima() {
		return vidaMaxima;
	}
	
	public void setVidaMaxima(int vida) {
		vidaMaxima = vida;
	}

	public int getDanio() {
		return danio;
	}
	public void modDanio(int modificador) {
		danio += modificador;
	}

	public boolean estaDefendiento() {
		return defendiendo;
	}

	public void setEstadoDefensa(boolean estadoDefensa) {
		defendiendo = estadoDefensa;
	}

	public void modVidaActual(int modificador) {
		vidaActual += modificador;
	}

	public void modVidaMaxima(int modificador) {
		vidaMaxima += modificador;
	}

	public void morir() {
	}
	/**
	 * Verifica y actualiza el estado vital del personaje.
	 * Controla que la vida no supere el máximo ni baje de 0.
	 * Si la vida llega a 0, dispara el método {@link #morir()}.
	 */
	public void actualizar() {
		if (vidaActual > vidaMaxima) {
			vidaActual = vidaMaxima;
		}
		if (vidaActual <= 0) {
			vidaActual = 0;
		}
		if (vidaActual == 0) {
			morir();
		}
	}
}
