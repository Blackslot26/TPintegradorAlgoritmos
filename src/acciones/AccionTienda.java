package acciones;

import java.util.HashMap;
import java.util.Scanner;

import items.Item;
import todo.Jugador;
import utiles.DatosJuego;
import utiles.MyUtil;
import utiles.Titulos;

public class AccionTienda implements Accion {

	Titulos titulos;
	private HashMap<String, Item> stock = new HashMap<>();

	public AccionTienda() {

	}

	@Override
	public void realizar(Jugador jugador, Scanner scTienda) {

		boolean enTienda = true;
		String inputTienda;

		MyUtil.limpiarConsola();
		MyUtil.dibujarArrayString(Titulos.TITULO_TIENDA, 10);
		mostrarItems();

		while (enTienda) {
			System.out.print("\n[TIENDA] Prueba /comprar [item],/comandos o /salir > ");
			inputTienda = scTienda.nextLine().toLowerCase().trim();

			if (inputTienda.equals("/salir") || inputTienda.equals("/s") || inputTienda.equals("/exit")) {
				enTienda = false;
				MyUtil.limpiarConsola();

			} else if (inputTienda.equals("/comandos")) {
				mostrarComandosTienda(scTienda);

			} else if (inputTienda.startsWith("/comprar ") || inputTienda.startsWith("/buy ")
					|| inputTienda.startsWith("/b ")) {
				String nombreItem = extraerNombreItem(inputTienda);
				Item itemAComprar = stock.get(nombreItem);

				if (itemAComprar != null) {
					realizarVenta(jugador, itemAComprar);
				} else {
					System.out.println("El item '" + nombreItem + "' no existe en la tienda.");
				}

			} else {
				System.out.print(
						"\nNo se reconoció el comando. Usa /comprar [item], /buy [item], /b [item], /comandos o /salir. > ");
			}
		}
	}

	private String extraerNombreItem(String input) {
		if (input.startsWith("/comprar ")) {
			return input.substring("/comprar ".length()).trim();
		} else if (input.startsWith("/buy ")) {
			return input.substring("/buy ".length()).trim();
		} else if (input.startsWith("/b ")) {
			return input.substring("/b ".length()).trim();
		}
		return "";
	}

	private void mostrarItems() {
		System.out.println("Elementos disponibles: ");
		for (String item : stock.keySet()) {
			System.out.println(">" + stock.get(item).getNombre() + " : $" + stock.get(item).getPrecio());
		}
	}

	private void realizarVenta(Jugador jugador, Item item) {
		if (jugador.getMonedas() >= item.getPrecio()) {
			jugador.addItem(item);
			jugador.modMonedas(item.getPrecio());
		}
	}

	private void mostrarComandosTienda(Scanner scTienda) {
		MyUtil.marco(DatosJuego.comandosTienda);
		System.out.println("\n[Enter para continuar]");
		scTienda.nextLine();
	}

}