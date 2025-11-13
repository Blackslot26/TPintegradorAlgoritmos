package todo;

import java.io.Serializable;
import java.util.ArrayList;

import acciones.Trabajar;
import items.Item;

public class Jugador extends Personaje implements Serializable {
	private static final long serialVersionUID = 1L;
	// Atributos base del jugador
	private Trabajar trabajo;
	private int monedas;
	private int experiencia;
	private int experienciaLevel; // Cantidad de xp necesaria para subir de nivel
	private int renacimientos;
	public double defensa;
	private Inventario inventario;

	// Variables auxiliares para actualizar el jugador
	private int ultimasMonedasPerdidas;
	private boolean murio;
	//private int bonificadorRenacimiento;

	// estadisticas EXTRA (principalmente afectan trabajos o eventos)
	public double suerte;
	public double multiplicadorVenta;
	public double multiplicadorGanancia;
	Item[] itemsEquipados;

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
		defensa = 0;
		suerte = 0;
		multiplicadorGanancia = 1;

		itemsEquipados = new Item[4];
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
		suerte += valor;
	}
	public double getDefensa() {
		return defensa;
	}
	public void modDefensa(double d) {  //LA DEFENSA DEBE DE SER MODIFICADA DE FORMA INVERSA, ES DECIR NORMALMENTE ES 1, PARA QUE AUMENTE SE LE TIENE QUE RESTAR YA QUE POR ESTA SE MULTIPLICA EL DAÑO RECIBIDO
		defensa += d;  // defensa = 0.75 es igual a que el jugador recibe un 25% menos de daño.
	}
	public void modMultVenta(double value) {
		multiplicadorVenta += value;
	}
	public double getMultVenta() {
		return multiplicadorVenta;	
	}
	public void modMultGanancia(double value) {
		multiplicadorGanancia += value;
	}
	public double getMultGanancia() {
		return multiplicadorGanancia;	
	}
	
	

	// Otras funciones necesarias
	@Override
	public void morir() {
		ultimasMonedasPerdidas = (int) (monedas * -0.5);
		modMonedas((ultimasMonedasPerdidas)); // Pierde la mitad de sus monedas actuales
		vidaActual = vidaMaxima; // Se resetea la vida
	}

	@Override
	public void actualizar() {
		if (vidaActual > vidaMaxima) {	//cambio de 100 --> vidaMaxima por las dudas
			vidaActual = vidaMaxima;
		}
		if (vidaActual < 0) {
			vidaActual = 0;
		}
		if (vidaActual <= 0) {
			murio = true;
			morir();
		}
		
		//Resetear auxiliares
		setEstadoDefensa(false);
	}

	public void feedbackMuerte() {
		if (murio) {
			System.out.println("Has muerto y perdiste la mitad de tus monedas [" + ultimasMonedasPerdidas + "]");
		}
		murio = false;
	}

	public Item[] getItemsEquipados() {
		return itemsEquipados;
	}
	public void mostrarEstadoJugador() {
		String pfp1 = "|¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|";
		String pfp2 = "|     -------     |";
		String pfp3 = "|    |  | |  |    |";
		String pfp4 = "|    |   -   |    |";
		String pfp5 = "|      -----      |";
		String pfp6 = "|     ---|---     |";
		String pfp7 = "|     |  |  |     |";
		String pfp8 = "|________|________|";
		
		
		int boxLong = 80;
		String pName = " Player Name: " + getNombre();
		String stats = "     --Estadisticas--";
		String monedas = " Monedas: " + getMonedas();
		String vida = " Vida: " + getVidaActual() + "/" + getVidaMaxima();
		String nivel = " Nivel: " + getNivel();
		String experiencia = " Experiencia: " + getExperiencia() + "/" + getExperienciaLevel();
		String rebirths = " Rebirths: " + getRebirth();
		String danio = " Daño: " + getDanio();
		String defensa = " Defensa: " + getDefensa() * 10 + "%";
		String suerte = " Suerte: " + getSuerte();
		String trabajo = " Trabajo: " + getTrabajo().getNombreBase();

		String inventario = "---Inventario---";

		String iEquipados = "---Items Equipados---";
		StringBuilder slotsBuild = new StringBuilder();
		for (int i = 0; i < itemsEquipados.length; i++) {
			if (itemsEquipados[i] == null) {
				slotsBuild.append(i + 1 + "- " + "[ Vacio ]");
			} else {
				slotsBuild.append(i + 1 + "- " + "[ " + itemsEquipados[i].getNombre() + " ]");
			}
			if (i < itemsEquipados.length - 1) {
				slotsBuild.append(" ║ ");
			}
		}
		String slots = slotsBuild.toString();

		StringBuilder sb = new StringBuilder();
		sb.append("\n╔" + "═".repeat(boxLong)+ "╗\n");
		sb.append("║" + " ".repeat(boxLong-pfp1.length()) + pfp1 + "║\n");
		sb.append("║" + pName + " ".repeat(boxLong - pName.length() - pfp2.length()) + pfp2 + "║\n");
		sb.append("║" + " ".repeat(boxLong - pfp3.length()) + pfp3 + "║\n");
		sb.append("║" + stats + " ".repeat(boxLong - stats.length()-pfp4.length()) + pfp4 + "║\n");
		sb.append("║" + monedas + " ".repeat(boxLong - monedas.length() - pfp5.length())  + pfp5 + "║\n");
		sb.append("║" + vida + " ".repeat(boxLong - vida.length() - pfp6.length()) + pfp6 + "║\n");
		sb.append("║" + nivel + " ".repeat(boxLong - nivel.length() - pfp7.length()) + pfp7 + "║\n");
		sb.append("║" + experiencia + " ".repeat(boxLong - experiencia.length() - pfp8.length()) + pfp8 + "║\n");
		sb.append("║" + rebirths + " ".repeat(boxLong - rebirths.length()) + "║\n");
		sb.append("║" + danio + " ".repeat(boxLong - danio.length()) + "║\n");
		sb.append("║" + defensa + " ".repeat(boxLong - defensa.length()) + "║\n");
		sb.append("║" + suerte + " ".repeat(boxLong - suerte.length()) + "║\n");
		sb.append("║" + trabajo + " ".repeat(boxLong - trabajo.length()) + "║\n");
		sb.append("║" + " ".repeat(boxLong) + "║\n");
		sb.append("║" + " ".repeat((boxLong - inventario.length()) / 2) + inventario
				+ " ".repeat(boxLong - inventario.length() - (boxLong - inventario.length()) / 2) + "║\n"); // ---inventario---

		ArrayList<Item> items = this.inventario.getInventario();
		int inventarioSize = items.size();

		if (inventarioSize == 0) {
			String msgVacio = "[ Inventario Vacio ]";
			int padVacio = (boxLong - msgVacio.length()) / 2;
			sb.append(
					"║" + " ".repeat(padVacio) + msgVacio + " ".repeat(boxLong - padVacio - msgVacio.length()) + "║\n");
		} else {
			for (int i = 0; i < inventarioSize; i += 3) {

				StringBuilder rowBuilder = new StringBuilder();
				for (int j = 0; j < 3; j++) {
					int itemIndex = i + j;
					if (itemIndex < inventarioSize) {
						Item item = items.get(itemIndex);
						rowBuilder.append(
								itemIndex + "- " + "[ " + item.getNombre() + " (" + item.getCantidad() + ")" + " ]");
					} else {
						rowBuilder.append("[ Vacio ]");
					}
					if (j < 2) {
						rowBuilder.append(" ║ ");
					}
				}
				String filaCompleta = rowBuilder.toString();
				int padding = boxLong - filaCompleta.length();
				int padIzquierdo = padding / 2;
				int padDerecho = padding - padIzquierdo;
				sb.append("║" + " ".repeat(padIzquierdo) + filaCompleta + " ".repeat(padDerecho) + "║\n");
			}
		}

		sb.append("║" + " ".repeat(boxLong) + "║\n");
		sb.append("║" + " ".repeat((boxLong - iEquipados.length()) / 2) + iEquipados
				+ " ".repeat(boxLong - iEquipados.length() - (boxLong - iEquipados.length()) / 2) + "║\n"); // items
																											// equipados
																											// (solo
																											// texto)
		sb.append("║" + " ".repeat((boxLong - slots.length()) / 2) + slots
				+ " ".repeat(boxLong - slots.length() - (boxLong - slots.length()) / 2) + "║\n"); // slots de items
																									// equipados
		sb.append("║" + " ".repeat(boxLong) + "║\n");
		sb.append("╚" + "═".repeat(boxLong) + "╝\n");

		System.out.println(sb);
		// ╔═╗║╚╝

	}


}
