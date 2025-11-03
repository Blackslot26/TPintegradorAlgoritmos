package todo;

import java.io.Serializable;

public abstract class Personaje implements Serializable{
	private static final long serialVersionUID = 1L;
	int vidaMaxima;
	int vidaActual;
	int nivel;
	String nombre;
	
	public Personaje(String nombre, int vidaMaxima, int nivel) {
		this.nombre = nombre;
		this.vidaMaxima = vidaMaxima;
		this.vidaActual = this.vidaMaxima;
		this.nivel = 1;
	}
	
	}
