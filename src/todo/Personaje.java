package todo;

import java.io.Serializable;

public abstract class Personaje implements Serializable{
	private static final long serialVersionUID = 1L;
	int vida;
	static int nivel;
	String nombre;
	
	public Personaje(String nombre, int vida, int nivel) {
		this.nombre = nombre;
		this.vida = 100;
		Personaje.nivel = 1;
	}
	
	}
