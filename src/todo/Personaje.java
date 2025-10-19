package todo;

public abstract class Personaje {
	int vida;
	int nivel;
	String nombre;
	
	public Personaje(String nombre, int vida, int nivel) {
		this.nombre = nombre;
		this.vida = 100;
		this.nivel = 1;
	}
	
	}
