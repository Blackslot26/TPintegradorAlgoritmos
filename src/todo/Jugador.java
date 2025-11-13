package todo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import acciones.Trabajar;
import items.Item;
import utiles.MyUtil;

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
	// private int bonificadorRenacimiento;

	// estadisticas EXTRA (principalmente afectan trabajos o eventos)
	public double suerte;
	public double multiplicadorVenta;
	public double multiplicadorGanancia;
	Item[] itemsEquipados;
	private HashMap<String, Long> cooldownsAcciones = new HashMap<>();

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

	public Item[] getItemsEquipados() {
		return itemsEquipados;
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
		murio = true;
		ultimasMonedasPerdidas = (int) (monedas * -0.5);
		modMonedas((ultimasMonedasPerdidas)); // Pierde la mitad de sus monedas actuales
		this.setVidaActual(this.getVidaMaxima()); // Se resetea la vida
	}

	public void feedbackMuerte() {
		if (murio) {
			MyUtil.marco("Has muerto y perdiste la mitad de tus monedas [" +MyUtil.ANSI_RED+ ultimasMonedasPerdidas + MyUtil.ANSI_RESET+"]");
		}
		murio = false;
	}

	/**
	 * Establece el cooldown para una acción.
	 * 
	 * @param nombreAccion El nombre del comando (ej: "/cazar") y seconds
	 * @param seconds La cantidad de segundos que debe esperar
	 */
	public void setActionCooldown(String nombreAccion, int seconds) {
		long ahora = System.currentTimeMillis();
		long readyTime = ahora + (seconds * 1000); // Convierte segundos a milisegundos
		cooldownsAcciones.put(nombreAccion, readyTime);
	}

	/**
	 * Devuelve el tiempo restante (en segundos) para que una acción esté lista.
	 * 
	 * @param actionName El nombre del comando
	 * @return Segundos restantes, o 0 si está lista.
	 */
	public long getRemainingCooldown(String actionName) {
		//Obtener el tiempo en que estará lista
		Long readyTime = cooldownsAcciones.get(actionName);
		
		//Si nunca se ha usado, está lista (retorna 0)
		if (readyTime == null) {
			return 0;
		}

		//Calcular el tiempo restante
		long millisRestantes = readyTime - System.currentTimeMillis();

		// Si el tiempo restante es negativo (ya pasó), retorna 0
		if (millisRestantes <= 0) {
			return 0;
		}

		//Convertir milisegundos a segundos (redondeando hacia arriba)
		return (millisRestantes / 1000) + 1;
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
		String pName = " Player Name: " + getNombre().toUpperCase();
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
		sb.append("\n╔" + "═".repeat(boxLong) + "╗\n");
		sb.append("║" + " ".repeat(boxLong - pfp1.length()) + pfp1 + "║\n");
		sb.append("║" + pName + " ".repeat(boxLong - pName.length() - pfp2.length()) + pfp2 + "║\n");
		sb.append("║" + " ".repeat(boxLong - pfp3.length()) + pfp3 + "║\n");
		sb.append("║" + stats + " ".repeat(boxLong - stats.length() - pfp4.length()) + pfp4 + "║\n");
		sb.append("║" + monedas + " ".repeat(boxLong - monedas.length() - pfp5.length()) + pfp5 + "║\n");
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
