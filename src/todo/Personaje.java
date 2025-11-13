package todo;

import java.io.Serializable;

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
