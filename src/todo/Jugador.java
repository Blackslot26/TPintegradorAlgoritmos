package todo;

import java.io.Serializable;
import java.util.ArrayList;

import items.Item;

public class Jugador extends Personaje implements Serializable {
	private static final long serialVersionUID = 1L;
	private Trabajar trabajo;

	int monedas;
	int experiencia;
	int experienciaLevel;
	Inventario inventario;
	int bonificadorRenacimiento;
	int renacimientos;

	// estadisticas EXTRA (principalmente afectan trabajos o eventos)
	public double suerte;
	public double multiplicadorVenta;
	public double multiplicadorGanancia;
	Item[] itemsEquipados;

	public Jugador(String nombre) {
		super(nombre, 100, 1); // Empieza con 100 de vida y nivel 1.
		monedas = 100;
		experiencia = 0;
		inventario = new Inventario();
		bonificadorRenacimiento = 1;
		renacimientos = 0;
		trabajo = null;
		experienciaLevel = 100;

		// estadisticas extra
		suerte = 0;
		multiplicadorGanancia = 1;

		itemsEquipados = new Item[4];
	}

	public String getNombre() {
		return nombre;
	}

	public int getNivel() {
		return nivel;
	}

	public int getRebirth() {
		return renacimientos;
	}

	public int getMonedas() {
		return monedas;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void addMonedas(int cantidad) {
		int cantidadReal = cantidad * bonificadorRenacimiento;
		monedas += cantidadReal;
		System.out.println("Has ganado $" + cantidadReal + " Monedas");
	}

	public void ganarExperiencia(int expGanada) {
		int expGanadaReal = expGanada * bonificadorRenacimiento;
		experiencia += expGanadaReal;
		System.out.println("Ganaste " + expGanadaReal + "EXP");

	}

	public void perderMonedas(int monedas) {
		this.monedas -= monedas;
		System.out.println("Perdiste $" + monedas + " Monedas");
	}

	public void addItem(Item item) {
		inventario.addItem(item);
		System.out.println("Has adquirido 1 " + item.getNombre());

	}

	@Override
	public void morir() {
		perderMonedas((int) (monedas * 0.5)); // Pierde la mitad de sus monedas actuales
		vidaActual = vidaMaxima;
	}

	@Override
	public void actualizar() {
		if (vidaActual <= 0) {
			System.out.println("Moriste...");
			morir();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void setExperiencia(int value) {
		experiencia += value;
	}

	public int getExperienciaLevel() {
		return experienciaLevel;
	}

	public void setTrabajo(Trabajar choise) {
		trabajo = choise;
	}

	public Trabajar getTrabajo() {
		return trabajo;
	}

	//////////////////////////////////////////////////////////////
	// estadisticas
	public double getSuerte() {
		return suerte;
	}

	public void modificarSuerte(double valor) {
		suerte = +valor;
	}

	/////////////////////////////////////////////////////////////

	public void agregarDinero(int amount) {
		monedas = monedas + amount;
	}

	public Item[] getItemsEquipados() {
		return itemsEquipados;
	}

	public void equiparItem(Item item, int index) {
		Item remplazo = getItemsEquipados()[index];
		if (remplazo != null)
			inventario.addItem(remplazo);
		itemsEquipados[index] = item;
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
