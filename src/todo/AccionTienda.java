package todo;
import dibujos.DibujosTienda;

import java.util.HashMap;
import java.util.Scanner;

public class AccionTienda implements Accion {
	
	DibujosTienda dibujos;
	// Crear el inventario de la tienda
	private HashMap<String, Item> stock = new HashMap<>();

	private String descripcion = "Accedes a la tienda, para realizar compra y venta de elementos, también puedes usar /t";

	public AccionTienda() {
		//Instancia de clase para los graficos.
		dibujos = new DibujosTienda();
		
		// Inventario inicial de la tienda
		stock.put("daga", new Arma(TipoArma.DAGA));
		stock.put("espada", new Arma(TipoArma.ESPADA));
		stock.put("hacha", new Arma(TipoArma.HACHA));
		stock.put("arco", new Arma(TipoArma.ARCO));

	}

	public void realizar(Jugador jugador, Controlador controlador) {

		boolean enTienda = true;
		Scanner scTienda = new Scanner(System.in);
		String inputTienda = " ";

		// Al acceder se borra la pantalla, se muesta el título y el inventario de la
		// tienda.
		controlador.limpiarConsola();
		dibujos.mostrarTitulo();
		mostrarItems();

		while (enTienda) {
			inputTienda = scTienda.nextLine().toLowerCase();

			switch (inputTienda) {
			case "/comprar daga":
				realizarVenta(jugador, stock.get("daga"));
				break;

			case "/comprar espada":
				realizarVenta(jugador, stock.get("espada"));
				break;

			case "/comprar hacha":
				realizarVenta(jugador, stock.get("hacha"));
				break;

			case "/comprar arco":
				realizarVenta(jugador, stock.get("arco"));
				break;

			case "/comandos":
				mostrarComandosTienda();
				break;
			case "/salir":
				enTienda = false;
				controlador.limpiarConsola();
				break;

			default:
				System.out.println("No se reconoció el comando, intente de nuevo");

			}
		}
	}

	/**
	 * Función para mostrar los ítems disponibles en la tienda.
	 */
	private void mostrarItems() {
		System.out.println("Elementos disponibles: ");

		for (String item : stock.keySet()) {
			System.out.println(">" + stock.get(item).nombre + " : $" + stock.get(item).precio);
		}

	}

	/**
	 * Función para realizar la venta.
	 */
	private void realizarVenta(Jugador jugador, Item item) {
		if (jugador.monedas >= item.precio) {
			jugador.inventario.addItem(item);
			jugador.monedas -= item.precio;
			System.out.println("Compra realizada con éxito");
		}
	}

	/**
	 * Función para Imprimir los comandos de la tienda
	 */
	private void mostrarComandosTienda() {
		System.out.println("/comprar *Item deseado* -> Comprar un Item específico");
		System.out.println("/salir -> salir de la tienda");
		System.out.println("/comandos -> Listar los comandos");
	}

	/**
	 * Función para poder obtener la descripción fuera de la clase
	 */
	@Override
	public String getDescripcion() {
		return descripcion;
	}
	
}
