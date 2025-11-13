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
/**
 * Sistema de comercio del juego.
 * Permite al jugador comprar ítems (Consumibles y Equipables) usando monedas.
 * Incluye un sistema de paginación para navegar por el catálogo.
 */
public class AccionTienda implements Accion {

	Titulos titulos;
	private ArrayList<Item> stock = new ArrayList<>();
	private int paginaActual;
	private final int itemsPorPagina;
	Scanner sc = new Scanner(System.in);
	public AccionTienda() {
		inicializarStock();
		paginaActual = 0;
		itemsPorPagina = 10;
	}
	/**
	 * Ejecuta el bucle de la tienda.
	 * Muestra el catálogo paginado y procesa los comandos de compra y navegación.
	 */
	@Override
	public void realizar(Jugador jugador, Scanner scTienda) {

		boolean enTienda = true;
		String inputTienda;

		MyUtil.limpiarConsola();
		MyUtil.dibujarArrayString(Titulos.TITULO_TIENDA, 10);
		mostrarItems();

		while (enTienda) {
			MyUtil.limpiarConsola();
			MyUtil.dibujarArrayString(Titulos.TITULO_TIENDA, 10);
			mostrarItems();
			System.out.print("\n[TIENDA] Prueba /comprar [item],/comandos o /salir > \n");
			MyUtil.marco("Monedas: " + jugador.getMonedas());
			inputTienda = scTienda.nextLine().toLowerCase().trim();

			if (inputTienda.equals("/salir") || inputTienda.equals("/s") || inputTienda.equals("/exit")) {
				enTienda = false;
				MyUtil.limpiarConsola();

			} else if (inputTienda.equals("/comandos")) {
				mostrarComandosTienda(scTienda);

			} else if (inputTienda.equals("/next") || inputTienda.equals("/siguiente") // PAGINADO//PAGINADO//PAGINADO
					|| inputTienda.equals("/n")) {
				paginaActual++;
			} else if (inputTienda.equals("/previous") || inputTienda.equals("/anterior") // PAGINADO//PAGINADO//PAGINADO
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
						System.out.println("El numero de item " + indexItem + " no es valido");
						pulsarEnter();
					}
				} catch (NumberFormatException e) {
					System.out.println("Por favor introduzca un numero valido");
					pulsarEnter();
				}catch(Exception e) {
					System.out.println("Ocurrio un error inesperado");
					pulsarEnter();
				}

			} else {
				System.out.print(
						"\nNo se reconoció el comando. Usa /comprar [item], /buy [item], /b [item], /comandos o /salir. > ");
				pulsarEnter();
			}
		}

	}
	
	public void realizarTutorial(Jugador jugador, Scanner sc) {
		MyUtil.limpiarConsola();
		MyUtil.dibujarArrayString(Titulos.TITULO_TIENDA, 10);
		mostrarItems();
		System.out.println("\nAhora dentro de la tienda utiliza el comando \"" + MyUtil.ANSI_GREEN
				+ "/comprar [numero de ítem]" + MyUtil.ANSI_RESET + "\" para comprar un ítem.");
		System.out.print("\nPor ahora vamos a comprar una poción de vida. Utiliza el comando " + MyUtil.ANSI_GREEN
				+ "\"/comprar 1\" para comprar una pocion de vida" + MyUtil.ANSI_RESET + ". " + MyUtil.ANSI_GREEN + "> " + MyUtil.ANSI_RESET);
		while (true) {
			String input = sc.nextLine().toLowerCase().trim();
			if (input.equals("/comprar 1") || input.equals("/buy 1") || input.equals("/b 1")) {
				MyUtil.limpiarConsola();
				break;
			}
			System.out.print("\nComando incorrecto. Por favor escriba " + MyUtil.ANSI_GREEN + "/comprar pocion"
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
	/**
	 * Muestra los ítems disponibles en la página actual.
	 * Calcula dinámicamente el inicio y fin del índice basado en la página.
	 */
	private void mostrarItems() {
		int totalItems = stock.size();
		int totalPaginas = (int) Math.ceil((double) totalItems / itemsPorPagina); // la funcion ceil es un redondeo pero
																					// hacia
		// arriba. Al revez del casteo a int que
		// elimina el decimal para abajo este lo
		// elima para arriba.
		if (paginaActual < 0)
			paginaActual = 0;
		if (paginaActual >= totalPaginas && totalPaginas > 0)
			paginaActual = totalPaginas - 1;

		int inicio = paginaActual * itemsPorPagina;
		int fin = Math.min(inicio + itemsPorPagina, totalItems);

		int largoTienda = 150;
		String textoNumeroPagina = "Pagina Numero " + (paginaActual + 1) + " de " + totalPaginas;
		System.out.println("╔" + "═".repeat((largoTienda - textoNumeroPagina.length()) / 2) + textoNumeroPagina
				+ "═".repeat(
						largoTienda - textoNumeroPagina.length() - ((largoTienda - textoNumeroPagina.length()) / 2))
				+ "╗");

		for (int i = inicio; i < fin; i++) {
			String[] a = { ">" + (i + 1) + ". " + stock.get(i).getNombre() + " : $" + stock.get(i).getPrecio(),
					"   Descripcion: " + stock.get(i).getDescripcion() };
			MyUtil.marcoTienda(a, largoTienda);
		}
		System.out.println("╚" + "═".repeat(largoTienda) + "╝\n");

		String controlesNavegacion = "Cambio de pagina: ";
		if (paginaActual > 0)
			controlesNavegacion += "Pagina anterior: /anterior (/p)";
		if (paginaActual < totalPaginas - 1)
			controlesNavegacion += "|| Pagina siguiente: /siguiente (/n)";
		System.out.println(controlesNavegacion);
	}
	/**
	 * Procesa la transacción de compra.
	 * Verifica fondos, resta monedas y entrega una COPIA nueva del ítem al jugador.
	 * * @param jugador Comprador.
	 * @param item    El ítem modelo de la estantería.
	 */
	private void realizarVenta(Jugador jugador, Item item) {
		if (jugador.getMonedas() >= item.getPrecio()) {
			Item itemNuevo = clonarItem(item);
			if (itemNuevo != null) {
				jugador.addItem(itemNuevo);
				jugador.modMonedas(-itemNuevo.getPrecio());
				System.out.println(
						"Compraste [ " + itemNuevo.getNombre() + " ] por " + itemNuevo.getPrecio() + " monedas");
				pulsarEnter();
			}
		}else {
			System.out.println("No tienes suficiente dinero");
			pulsarEnter();
		}
	}
	/**
	 * Crea una instancia nueva e independiente del ítem para entregar al jugador.
	 * Evita problemas de referencia con los objetos del stock.
	 * * @param item El ítem original.
	 * @return Una copia nueva del mismo tipo.
	 */
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
		System.out.println("[ Pulsa Enter para continuar ]");
		sc.nextLine();
	}

}