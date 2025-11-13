package acciones;

import java.util.ArrayList;
import java.util.Scanner;

import items.Consumible;
import items.Equipable;
import items.Item;
import items.ItemConsumible;
import items.ItemEquipable;
import todo.Jugador;
import utiles.DatosJuego;
import utiles.MyUtil;
import utiles.Titulos;

public class AccionTienda implements Accion {
	
	// Titulos dibujos; // ELIMINADO: No necesitas una instancia si TITULO_TIENDA es estático
	private ArrayList<Item> stock = new ArrayList<>();
	private int paginaActual;
	private final int itemsPorPagina;
	Scanner sc = new Scanner(System.in); // CUIDADO: Esto crea un nuevo Scanner. Es mejor pasarlo.

	public AccionTienda() {
		inicializarStock();
		paginaActual = 0;
		itemsPorPagina = 10;
		// dibujos = new Titulos(); // ELIMINADO
	}

	@Override
	public void realizar(Jugador jugador, Scanner scTienda) {

		boolean enTienda = true;
		String inputTienda;

		MyUtil.limpiarConsola();
		MyUtil.dibujarArrayString(Titulos.TITULO_TIENDA, 10);
		mostrarItems(jugador); // Pasar jugador para mostrar monedas en el pie de página

		while (enTienda) {
			MyUtil.limpiarConsola();
			MyUtil.dibujarArrayString(Titulos.TITULO_TIENDA, 10);
			mostrarItems(jugador); // Pasar jugador
			
			MyUtil.marco("Monedas: " + MyUtil.ANSI_YELLOW + "$" + jugador.getMonedas() + MyUtil.ANSI_RESET);
			
			inputTienda = scTienda.nextLine().toLowerCase().trim();

			if (inputTienda.equals("/salir") || inputTienda.equals("/s") || inputTienda.equals("/exit")) {
				enTienda = false;
				MyUtil.limpiarConsola();

			} else if (inputTienda.equals("/comandos")) {
				mostrarComandosTienda(scTienda);

			} else if (inputTienda.equals("/next") || inputTienda.equals("/siguiente") 
					|| inputTienda.equals("/n")) {
				paginaActual++;
			} else if (inputTienda.equals("/previous") || inputTienda.equals("/anterior")
					|| inputTienda.equals("/p")) {
				paginaActual--;
			} else if (inputTienda.startsWith("/comprar ") || inputTienda.startsWith("/buy ")
					|| inputTienda.startsWith("/b ")) {
				try {
					int indexItem = Integer.parseInt(extraerIndexItem(inputTienda));
					int indexCorrecto = indexItem - 1;
					if (indexCorrecto >= 0 && indexCorrecto < stock.size()) {
						Item itemAComprar = stock.get(indexCorrecto);
						realizarVenta(jugador, itemAComprar);
					} else {
						// --- MODIFICADO: Usando marco y color ---
						MyUtil.marco(MyUtil.ANSI_RED + "El numero de item " + indexItem + " no es valido" + MyUtil.ANSI_RESET);
						pulsarEnter();
					}
				} catch (NumberFormatException e) {
					// --- MODIFICADO: Usando marco y color ---
					MyUtil.marco(MyUtil.ANSI_RED + "Por favor introduzca un numero valido" + MyUtil.ANSI_RESET);
					pulsarEnter();
				} catch (Exception e) {
					// --- MODIFICADO: Usando marco y color ---
					MyUtil.marco(MyUtil.ANSI_RED + "Ocurrio un error inesperado" + MyUtil.ANSI_RESET);
					pulsarEnter();
				}

			} else {
				// --- MODIFICADO: Usando marco y color ---
				MyUtil.marco(MyUtil.ANSI_RED + "No se reconoció el comando. Usa /comprar, /comandos o /salir." + MyUtil.ANSI_RESET);
				pulsarEnter();
			}
		}

	}
	
	public void realizarTutorial(Jugador jugador, Scanner sc) {
		MyUtil.limpiarConsola();
		MyUtil.dibujarArrayString(Titulos.TITULO_TIENDA, 10);
		mostrarItems(jugador); // Pasar jugador
		System.out.println("\nAhora dentro de la tienda utiliza el comando \"" + MyUtil.ANSI_GREEN
				+ "/comprar [numero de ítem]" + MyUtil.ANSI_RESET + "\" para comprar un ítem.");
		System.out.print("\nPor ahora vamos a comprar una poción de vida. Utiliza el comando " + MyUtil.ANSI_GREEN
				+ "\"/comprar 1\" para comprar una pocion de vida" + MyUtil.ANSI_RESET + ". " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);
		while (true) {
			String input = sc.nextLine().toLowerCase().trim();
			if (input.equals("/comprar 1") || input.equals("/buy 1") || input.equals("/b 1")) {
				MyUtil.limpiarConsola();
				// Simular la compra en el tutorial
				realizarVenta(jugador, stock.get(0)); 
				break;
			}
			System.out.print("\nComando incorrecto. Por favor escriba " + MyUtil.ANSI_GREEN + "/comprar 1"
					+ MyUtil.ANSI_RESET + ". " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);
		}
	}

	private String extraerIndexItem(String input) {
		if (input.startsWith("/comprar ")) {
			return input.substring("/comprar ".length()).trim();
		} else if (input.startsWith("/buy ")) {
			return input.substring("/buy ".length()).trim();
		} else if (input.startsWith("/b ")) {
			return input.substring("/b ".length()).trim();
		}
		return "";
	}

	// --- MODIFICADO: Añadido Jugador jugador como parámetro ---
	private void mostrarItems(Jugador jugador) {
		int totalItems = stock.size();
		int totalPaginas = (int) Math.ceil((double) totalItems / itemsPorPagina);
		
		if (paginaActual < 0)
			paginaActual = 0;
		if (paginaActual >= totalPaginas && totalPaginas > 0)
			paginaActual = totalPaginas - 1;

		int inicio = paginaActual * itemsPorPagina;
		int fin = Math.min(inicio + itemsPorPagina, totalItems);

		int largoTienda = 150;
		
		String textoNumeroPagina = MyUtil.ANSI_CYAN + " Pagina " + (paginaActual + 1) + " de " + totalPaginas + " " + MyUtil.ANSI_RESET;
		
		System.out.println("╔" + "═".repeat((largoTienda - (textoNumeroPagina.length() - 10)) / 2) + textoNumeroPagina // -10 para ajustar por los caracteres invisibles ANSI
				+ "═".repeat(
						largoTienda - (textoNumeroPagina.length() - 10) - ((largoTienda - (textoNumeroPagina.length() - 10)) / 2))
				+ "╗");

		for (int i = inicio; i < fin; i++) {
			String[] a = { ">" + MyUtil.ANSI_CYAN + (i + 1) + MyUtil.ANSI_RESET + ". " + stock.get(i).getNombre() + " : " + MyUtil.ANSI_YELLOW + "$" + stock.get(i).getPrecio() + MyUtil.ANSI_RESET,
					"   Descripcion: " + stock.get(i).getDescripcion() };
			MyUtil.marcoTienda(a, largoTienda);
		}

		String controlesNavegacion = "Cambio de pagina: ";
		if (paginaActual > 0)
			controlesNavegacion += MyUtil.ANSI_CYAN + "/anterior (/p)" + MyUtil.ANSI_RESET;
		if (paginaActual < totalPaginas - 1)
			controlesNavegacion += " || " + MyUtil.ANSI_CYAN + "/siguiente (/n)" + MyUtil.ANSI_RESET;
		System.out.println(controlesNavegacion);
	}

	private void realizarVenta(Jugador jugador, Item item) {
		if (jugador.getMonedas() >= item.getPrecio()) {
			Item itemNuevo = clonarItem(item);
			if (itemNuevo != null) {
				jugador.addItem(itemNuevo);
				jugador.modMonedas(-itemNuevo.getPrecio());
				// --- MODIFICADO: Usando marco y color ---
				MyUtil.marco(MyUtil.ANSI_GREEN + "Compraste [ " + itemNuevo.getNombre() + " ] por " + itemNuevo.getPrecio()
						+ " monedas" + MyUtil.ANSI_RESET);
				pulsarEnter();
			}
		} else {
			// --- MODIFICADO: Usando marco y color ---
			MyUtil.marco(MyUtil.ANSI_RED + "No tienes suficiente dinero" + MyUtil.ANSI_RESET);
			pulsarEnter();
		}
	}

	private Item clonarItem(Item item) {
		if (item instanceof Consumible)
			return new Consumible(((Consumible) item).getTipo(), 1);
		if (item instanceof Equipable)
			return new Equipable(((Equipable) item).getTipo(), 1);
		return null;

	}

	private void mostrarComandosTienda(Scanner scTienda) {
		MyUtil.marco(DatosJuego.comandosTienda);
		pulsarEnter();
	}

	private void inicializarStock() {
		// Stock consumibles
		for (ItemConsumible tipo : ItemConsumible.values()) {
			if (tipo.seVende()) {
				stock.add(new Consumible(tipo, 1));
			}
		}

		// Stock equipables
		for (ItemEquipable tipo : ItemEquipable.values()) {
			if (tipo.seVende()) {
				stock.add(new Equipable(tipo, 1));
			}
		}

	}
	
	private void pulsarEnter() {
		System.out.println("\n" + MyUtil.ANSI_GREEN + "[ Pulsa Enter para continuar ]" + MyUtil.ANSI_RESET);
		sc.nextLine();
	}

}