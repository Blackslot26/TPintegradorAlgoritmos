package todo;

import java.io.Serializable;

import acciones.Trabajar;

public class Jugador extends Personaje implements Serializable {
	private static final long serialVersionUID = 1L;
	private Trabajar trabajo;

	private int monedas;
	private int experiencia;
	private int experienciaLevel;
	private Inventario inventario;
//	private int bonificadorRenacimiento;
	private int renacimientos;

	// estadisticas EXTRA (principalmente afectan trabajos o eventos)
	private double suerte;
//	private double multiplicadorVenta;
//	private double multiplicadorGanancia;

	public Jugador(String nombre) {
		super(nombre, 100, 30); // Empieza con 100 de vida y nivel 1.
		monedas = 100;
		experiencia = 0;
		inventario = new Inventario();
//		bonificadorRenacimiento = 1;
		renacimientos = 0;
		trabajo = null;
		experienciaLevel = 100;

		// estadisticas extra
		suerte = 0;
//		multiplicadorGanancia = 1;

	}

	// Funciones para acceder a atributos
	public int getRebirth() {
		return renacimientos;
	}

	public int getMonedas() {
		return monedas;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public int getExperienciaLevel() {
		return experienciaLevel;
	}

	public Trabajar getTrabajo() {
		return trabajo;
	}

	public double getSuerte() {
		return suerte;
	}

	// Funciones para modificar Atributos
	public void modMonedas(int cantidad) {
		monedas += cantidad;
	}

	public void modExp(int expGanada) {
		experiencia += expGanada;
	}

	// Funciones para manejar el inventario
	public void addItem(Item item) {
		inventario.addItem(item);

	}

	public void setTrabajo(Trabajar choise) {
		trabajo = choise;
	}

	public void modSuerte(double valor) {
		suerte = +valor;
	}
	// Otras funciones necesarias
	@Override
	public void morir() {
		modMonedas(-((int) (monedas * 0.5))); // Pierde la mitad de sus monedas actuales
		vidaActual = vidaMaxima; // Se resetea la vida
	}

	@Override
	public void actualizar() {
		if (vidaActual <= 0) {
			morir();
		}

	}


	public void mostrarEstadoJugador() {
		// Mostrar el nombre del jugador en mayúsculas
		System.out.println(nombre.toUpperCase());

		// Subrayado para el nombre del jugador de acuerdo a su largo.
		for (int i = 0; i < nombre.length(); i++) {
			System.out.print("=");
		}
		System.out.println(); // salto de línea

		System.out.println("Vida: " + this.getVidaActual() + "/" + this.getVidaMaxima());
		System.out.println("Nivel: " + this.getNivel());
		System.out.println("Modenas: " + monedas);
		System.out.println("Experiencia: " + experiencia);
		System.out.println("Renaciemientos: " + renacimientos);
		System.out.println("Trabajo:\n" + trabajo.getNombre());
		System.out.println();
		inventario.mostrarInventario();

	}

}
