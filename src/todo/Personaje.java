package todo;

import java.io.Serializable;

public abstract class Personaje implements Serializable{
	private static final long serialVersionUID = 1L;
	protected int vidaMaxima;
	protected int vidaActual;
	protected int nivel;
	protected String nombre;
	
	public Personaje(String nombre, int vidaMaxima, int nivel) {
		this.nombre = nombre;
		this.vidaMaxima = vidaMaxima;
		this.vidaActual = this.vidaMaxima;
		this.nivel = 1;
	}
	
	public String getNombre() {
		return nombre;
	}
	public int getNivel() {
		return nivel;
	}
	
	public void recibirDanio(int danio) {
		vidaActual -= danio;
	}
	public int getVidaActual() {
		return vidaActual;
	}
	public int getVidaMaxima() {
		return vidaMaxima;
	}
	public void curar(int curacion) {
		vidaActual+= curacion;
	}
	
	public void morir() {
	}
	
	public void actualizar() {
		if(vidaActual <=0) {
			morir();
		}
	}
	}
